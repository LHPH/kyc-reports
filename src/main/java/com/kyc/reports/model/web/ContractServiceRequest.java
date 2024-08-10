package com.kyc.reports.model.web;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
}
