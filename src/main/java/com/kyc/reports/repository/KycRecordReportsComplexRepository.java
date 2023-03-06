package com.kyc.reports.repository;

import com.kyc.reports.entity.KycRecordReportComplex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KycRecordReportsComplexRepository extends JpaRepository<KycRecordReportComplex, UUID> {
}
