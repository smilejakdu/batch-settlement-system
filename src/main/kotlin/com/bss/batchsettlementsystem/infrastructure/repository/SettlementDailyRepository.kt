package com.bss.batchsettlementsystem.infrastructure.repository

import com.bss.batchsettlementsystem.domain.entity.settlement.SettlementDaily
import org.springframework.data.jpa.repository.JpaRepository

interface SettlementDailyRepository: JpaRepository<SettlementDaily, Long> {
}