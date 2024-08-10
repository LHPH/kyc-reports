package com.kyc.reports.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;

@Setter
@Getter
@Table(name = "KYC_RECORD_REPORTS")
@Entity
public class KycRecordReportComplex extends BaseKycRecordReport{

    //@Lob
    //@Type(type = "org.hibernate.type.BinaryType")
    @JdbcTypeCode(Types.VARBINARY)
    @Column(name = "REPORT")
    private byte [] report;
}
