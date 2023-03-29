package com.kyc.reports.enums;


import com.kyc.core.util.DateUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.MediaType;

import java.util.Date;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum  ReportTypeEnum {

    APPLICATION_FORM(1,"APPLICATION-FORM", MediaType.APPLICATION_PDF_VALUE,".pdf"),
    RECEIPT(2,"RECEIPT",MediaType.APPLICATION_PDF_VALUE,".pdf"),
    CONTRACT(3,"CONTRACT","application/vnd.openxmlformats-officedocument.wordprocessingml.document",".docx"),
    BILL(4,"BILL",MediaType.APPLICATION_PDF_VALUE,".pdf");

    private final Integer idType;
    private final String prefix;
    private final String mediaType;
    private final String extension;

    public String buildReportName(Object... args){

        StringBuilder reportName = new StringBuilder();
        reportName.append(getPrefix());

        if(args!=null){
            for(Object arg : args){

                reportName.append("-");
                reportName.append(arg);
            }
        }
        else{
            reportName.append("-");
            reportName.append(DateUtil.dateToString(new Date(),"yyyyMMdd"));
        }
        reportName.append(getExtension());
        return reportName.toString();
    }
}
