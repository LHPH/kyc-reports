package com.kyc.reports.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Setter
@Getter
@Table(name = "KYC_RECORD_REPORTS")
@Entity
public class KycRecordReportComplex extends BaseKycRecordReport{

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "REPORT")
    private byte [] report;
}
