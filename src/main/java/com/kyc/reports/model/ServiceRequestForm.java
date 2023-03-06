package com.kyc.reports.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ServiceRequestForm {

    private Long folio;
    private Date dateApplication;
    private Double total;
    private String campaign;

    private Long customerNumber;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;
    private String customerRfc;

    private String executiveName;
    private String idExecutive;
    private Integer idBranch;
    private String branchName;

    private Boolean acceptPromotions;
    private Boolean acceptPromotionsEmail;
    private Boolean acceptPromotionsCellPhone;

    private List<ServiceRequest> services;

    private String serialNumber;

}
