package com.kyc.reports.model;


import com.kyc.reports.enums.ReportTypeEnum;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class DataToEntity {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    private Long folio;
    private Long customerNumber;
    private String creator;
    private byte[] document;
    private ReportTypeEnum reportType;
}
