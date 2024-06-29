package com.bss.batchsettlementsystem.domain.entity.order

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
data class OrderItem(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "order_item_id") val id: Long? = null,
        val orderId: Long, //주문번호
        @Column(name = "order_item_snapshot_id")
        val orderItemSnapshotId: Long, //주문 스냅샷 번호

        val orderCount: Int? = 1, //주문수량
        val itemDeliveryStatus: Int? = 0, //주문 배송 상태

        val createdAt: ZonedDateTime? = ZonedDateTime.now(), //생성시간
        val updatedAt: ZonedDateTime? = ZonedDateTime.now(), //업데이트시간
        val deletedAt: ZonedDateTime? = null, //삭제시간

        val purchaseConfirmedAt: ZonedDateTime? = null, //구매확정일
        val shippedCompleteAt: ZonedDateTime? = null, //배송완료일
)
