package com.bss.batchsettlementsystem.core.job.purchaseconfirmed.daily

import com.bss.batchsettlementsystem.domain.entity.settlement.SettlementDaily
import com.bss.batchsettlementsystem.infrastructure.repository.SettlementDailyRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter

class DailySettlementItemWriter(
    private val settlementDailyRepository: SettlementDailyRepository
): ItemWriter<SettlementDaily> {

    override fun write(chunk: Chunk<out SettlementDaily>) {
        for (item in chunk.items) {
            settlementDailyRepository.save(item)
        }
    }
}