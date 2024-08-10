package com.kyc.reports.model.web;

import com.kyc.core.model.BaseModel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
