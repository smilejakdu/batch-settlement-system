package com.bss.batchsettlementsystem.infrastructure.repository

import com.bss.batchsettlementsystem.domain.entity.order.OrderItem
import org.springframework.data.jpa.repository.JpaRepository
import java.time.ZonedDateTime

interface OrderItemRepository : JpaRepository<OrderItem, Long>, OrderItemPurchaseConfirmedCustomerRepository {

    fun findByShippedCompleteAtBetween(startDateTime: ZonedDateTime, endDateTime: ZonedDateTime): List<OrderItem>

    fun findByOrderNo(orderNo: Long): List<OrderItem>
}
