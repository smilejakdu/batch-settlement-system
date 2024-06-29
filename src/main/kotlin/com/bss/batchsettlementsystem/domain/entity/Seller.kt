package com.bss.batchsettlementsystem.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.ZonedDateTime

@Entity
data class Seller(
        @Id @Column(name = "seller_id") val id: Long,
        val sellerName: String,
        val businessId: Int? = 0,
        val sellType: String,
        val createdAt: ZonedDateTime? = ZonedDateTime.now(),
        val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
        val deletedAt: ZonedDateTime? = null,
        val bankType: String? = null,
        val accountId: Int? = null,
        val accountOwnerName: String? = null,
        val isActive: Boolean? = true,
        val defaultDeliveryAmount: Int? = 3000,
        val commission: Int? = 0,
)
