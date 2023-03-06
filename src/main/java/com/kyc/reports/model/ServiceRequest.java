package com.kyc.reports.model;

import com.kyc.core.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceRequest extends BaseModel {

    private Integer id;
    private String name;
    private Double cost;
}
