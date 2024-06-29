package com.bss.batchsettlementsystem.domain.entity.claim

import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
data class ClaimRefundHistory(
    @Id @Column(name = "claim_refund_id") val id : Long,
    val claimReceiptId: Long,

    val refundedAt: ZonedDateTime? = null, //환불시간

    val sellerId: Long,
    val refundCashAmount: BigDecimal = BigDecimal.ZERO,
    val couponSaleAmount: BigDecimal = BigDecimal.ZERO,
    val refundMileageAmount: BigDecimal = BigDecimal.ZERO,
    val subtractDeliveryAmount: BigDecimal = BigDecimal.ZERO,
    val refundDeliveryAmount: BigDecimal = BigDecimal.ZERO,

    val createdAt: ZonedDateTime? = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,
)
