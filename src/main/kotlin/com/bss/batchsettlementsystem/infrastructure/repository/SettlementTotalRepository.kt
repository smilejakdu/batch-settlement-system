package com.bss.batchsettlementsystem.infrastructure.repository

import com.bss.batchsettlementsystem.domain.entity.settlement.SettlementTotal
import org.springframework.data.jpa.repository.JpaRepository

interface SettlementTotalRepository: JpaRepository<SettlementTotal, Long>{
}