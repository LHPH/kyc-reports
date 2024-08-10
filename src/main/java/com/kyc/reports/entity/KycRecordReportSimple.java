package com.kyc.reports.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "KYC_RECORD_REPORT")
@Entity
public class KycRecordReportSimple extends BaseKycRecordReport{
}
