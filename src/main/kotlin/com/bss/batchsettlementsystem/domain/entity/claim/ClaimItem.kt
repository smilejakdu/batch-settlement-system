package com.bss.batchsettlementsystem.domain.entity.claim

import com.bss.batchsettlementsystem.domain.entity.order.OrderItem
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(schema = "commerce", name = "claim_item")
data class ClaimItem(
    @Id @Column(name = "claim_item_id") val id : Long,
    @Column(name = "claim_receipt_id")
    val claimReceiptId: Long,
    val createdAt: ZonedDateTime? = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,

    @Column(name="order_item_id")
    val orderItemId: Long,
    val claimCount: Int? = 1,

    @OneToOne
    @JoinColumn(name = "order_item_id", insertable = false, updatable = false)
    val orderItem: OrderItem,

    @ManyToOne
    @JoinColumn(name = "claim_receipt_id", insertable = false, updatable = false)
    val claimReceipt: ClaimReceipt,
)

