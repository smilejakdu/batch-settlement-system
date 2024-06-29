package com.bss.batchsettlementsystem.core.job.purchaseconfirmed.daily

import com.bss.batchsettlementsystem.domain.collection.PositiveDailySettlementCollection
import com.bss.batchsettlementsystem.domain.entity.order.OrderItem
import com.bss.batchsettlementsystem.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.ItemProcessor

class DailySettlementItemProcessor: ItemProcessor<OrderItem, SettlementDaily> {

    /**
     * + 정산금액을 만들기
     */
    override fun process(item: OrderItem): SettlementDaily {
        val positiveDailySettlementCollection = PositiveDailySettlementCollection(item)

        return positiveDailySettlementCollection.getSettlementDaily()
    }
}
