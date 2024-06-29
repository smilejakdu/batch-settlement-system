package com.bss.batchsettlementsystem.core.job.purchaseconfirmed.delivery

import com.bss.batchsettlementsystem.domain.entity.order.OrderItem
import com.bss.batchsettlementsystem.infrastructure.repository.OrderItemRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.lang.NonNull
import java.time.ZonedDateTime

class PurchaseConfirmedWriter(
        private val orderItemRepository: OrderItemRepository
): ItemWriter<OrderItem> {
    override fun write(@NonNull chunk: Chunk<out OrderItem>) {
        if (true) {
            throw Exception()
        }

        for (item in chunk.items) {
            println(item)
            //TODO Hidden Task : item PurchaseConfirmedAt 업데이트하는 작업을 넣어줘야 한다.
            val newItem = item.copy(id = item.id, purchaseConfirmedAt = ZonedDateTime.now())
            //OrderItem을 저장
            orderItemRepository.save(newItem)
        }
    }
}
