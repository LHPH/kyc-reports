package com.kyc.reports.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ReceiptRequest {

    @NotNull
    private String customerName;
    @NotNull
    private String customerAddress;
    @NotNull
    private Long customerNumber;
    @NotNull
    private Long folio;
    @NotNull
    private Date dateDocument;
    @NotNull
    private Double paidAmount;
    @NotNull
    private Integer idPaymentMethod;
    @Valid
    @NotNull
    private List<ServiceRequest> contractedServices;

    private String serialNumber;
}
