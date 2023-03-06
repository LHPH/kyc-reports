package com.kyc.reports.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Table(name = "KYC_RECORD_REPORT")
@Entity
public class KycRecordReportSimple extends BaseKycRecordReport{
}
