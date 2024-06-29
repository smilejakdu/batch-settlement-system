package com.bss.batchsettlementsystem.domain.entity.order

import com.bss.batchsettlementsystem.domain.entity.Product
import com.bss.batchsettlementsystem.domain.entity.Seller
import com.bss.batchsettlementsystem.domain.enums.TaxType
import com.bss.batchsettlementsystem.domain.enums.TaxTypeConverter
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
data class OrderItemSnapshot(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "order_item_snapshot_id")
        val id: Long? = null,

        val createdAt: ZonedDateTime? = ZonedDateTime.now(),
        val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
        val deletedAt: ZonedDateTime? = null,

        @Column(name = "product_id")
        val productId: Long,

        @Column(name = "seller_id")
        val sellerId: Long,

        val sellPrice: BigDecimal? = BigDecimal.ZERO,
        val supplyPrice: BigDecimal? = BigDecimal.ZERO,
        val promotionAmount: BigDecimal? = BigDecimal.ZERO,
        val defaultDeliveryAmount: BigDecimal? = BigDecimal.valueOf(3000),
        val mileageUsageAmount: BigDecimal? = BigDecimal.ZERO,

        val itemCategory: Int? = 0, //TODO : Enum으로 변경
        val taxRate: Int? = 3,

        @Convert(converter = TaxTypeConverter::class)
        val taxType: TaxType? = TaxType.TAX,

        @ManyToOne
        @JoinColumn(name = "seller_id", insertable = false, updatable = false)
        val seller: Seller,

        @ManyToOne
        @JoinColumn(name = "product_id", insertable = false, updatable = false)
        val product: Product,
)
