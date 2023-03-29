package com.kyc.reports.model.web;

import com.kyc.core.model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest extends BaseModel {

    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Double cost;
}
