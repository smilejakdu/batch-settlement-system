package com.bss.batchsettlementsystem.domain.collection

import com.bss.batchsettlementsystem.domain.command.PgSalesAmountMaterial
import java.math.BigDecimal

class PgSalesAmountCalculator(
    //private val orderItemSnapshot: OrderItemSnapshot,
    private val pgSalesAmountMaterial: PgSalesAmountMaterial,
) {

    /**
     * PG - 결제 수단 , 현금 금액
     */
    fun getPgSaleAmount(): BigDecimal {
        val price = pgSalesAmountMaterial.sellPrice ?: BigDecimal.ZERO

        return price.subtract(pgSalesAmountMaterial.promotionAmount)
            .subtract(pgSalesAmountMaterial.mileageUsageAmount)
    }
}




