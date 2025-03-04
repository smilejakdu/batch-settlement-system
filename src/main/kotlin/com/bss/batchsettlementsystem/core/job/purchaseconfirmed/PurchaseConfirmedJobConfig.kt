package com.bss.batchsettlementsystem.core.job.purchaseconfirmed

import com.bss.batchsettlementsystem.core.job.purchaseconfirmed.daily.DailySettlementItemProcessor
import com.bss.batchsettlementsystem.core.job.purchaseconfirmed.daily.DailySettlementItemWriter
import com.bss.batchsettlementsystem.core.job.purchaseconfirmed.delivery.PurchaseConfirmedWriter
import com.bss.batchsettlementsystem.core.listener.PurchaseConfirmedChunkListener
import com.bss.batchsettlementsystem.domain.entity.claim.ClaimItem
import com.bss.batchsettlementsystem.domain.entity.order.OrderItem
import com.bss.batchsettlementsystem.domain.entity.settlement.SettlementDaily
import com.bss.batchsettlementsystem.infrastructure.repository.OrderItemRepository
import com.bss.batchsettlementsystem.infrastructure.repository.SettlementDailyRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement


/**
 * 구매확정을 위한 Batch Job
 * 5.0 이하 버전에서는 JobBuilderFactory, StepBuilderFactory 를 사용하지만,
 * 5 버전 부터는 둘은 Deprecated 되었다.
 * 대신 JobRepository, transactionManager 명시해서 사용한다.
 * https://github.com/spring-projects/spring-batch/issues/4188
 */
@Configuration
@EnableBatchProcessing
@EnableTransactionManagement // 추가 annotation 발생 :) !
class PurchaseConfirmedJobConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
    @Qualifier("deliveryCompletedJpaItemReader")
    private val deliveryCompletedJpaItemReader: JpaPagingItemReader<OrderItem>,
    @Qualifier("dailySettlementJpaItemReader")
    private val dailySettlementJpaItemReader: JpaPagingItemReader<OrderItem>,
    @Qualifier("claimSettlementJpaItemReader")
    private val claimSettlementJpaItemReader: JpaPagingItemReader<ClaimItem>,
    private val orderItemRepository: OrderItemRepository,
    private val settlementDailyRepository: SettlementDailyRepository,
) {

    val JOB_NAME = "purchaseConfirmedJob"
    val chunkSize = 500

    @Bean
    @JobScope
    fun purchaseConfirmedJobStep(): Step {
        return StepBuilder(JOB_NAME+"_step", jobRepository)
                .chunk<OrderItem, OrderItem>(chunkSize, transactionManager)
                .reader(deliveryCompletedJpaItemReader)
                .writer(purchaseConfirmedItemWriter())
                .listener(PurchaseConfirmedChunkListener())
                .allowStartIfComplete(true)
                .build()
    }

    @Bean
    fun purchaseConfirmedItemWriter(): PurchaseConfirmedWriter {
        return PurchaseConfirmedWriter(orderItemRepository)
    }

    @Bean
    @JobScope
    fun dailySettlementJobStep(): Step {
        return StepBuilder(JOB_NAME+"_dailySettlement_step", jobRepository)
            .chunk<OrderItem, SettlementDaily>(chunkSize, transactionManager)
            .reader(dailySettlementJpaItemReader)
            .processor(dailySettlementItemProcessor())
            .writer(dailySettlementItemWriter())
            .allowStartIfComplete(true)
            .build()
    }

    @Bean
    fun dailySettlementItemProcessor(): DailySettlementItemProcessor {
        return DailySettlementItemProcessor()
    }

    @Bean
    fun dailySettlementItemWriter(): DailySettlementItemWriter {
        return DailySettlementItemWriter(settlementDailyRepository)
    }
}
