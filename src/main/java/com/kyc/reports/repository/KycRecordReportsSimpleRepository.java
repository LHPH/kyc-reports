package com.kyc.reports.repository;

import com.kyc.reports.entity.KycRecordReportSimple;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KycRecordReportsSimpleRepository extends JpaRepository<KycRecordReportSimple, UUID> {
}
