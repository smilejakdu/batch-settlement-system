package com.bss.batchsettlementsystem.infrastructure.repository

import com.bss.batchsettlementsystem.domain.criteria.SearchForPurchaseConfirmedCriteria
import com.bss.batchsettlementsystem.domain.entity.order.OrderItem

interface OrderItemPurchaseConfirmedCustomerRepository {
    fun getOrderItemForPurchaseConfirmedList(criteria: SearchForPurchaseConfirmedCriteria): List<OrderItem>
    fun getOrderItemForPurchaseConfirmedCount(criteria: SearchForPurchaseConfirmedCriteria): Int
}
