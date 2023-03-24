package com.kyc.reports.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class ContractServiceRequest {

    @NotNull
    private Long customerNumber;
    @NotNull
    private String customerName;
    @NotNull
    private String customerAddress;
    @NotNull
    private Long folio;
    @Valid
    @NotNull
    private List<ServiceRequest> contractedServices;

    private String serialNumber;
}
