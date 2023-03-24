package com.kyc.reports.controller.delegate;

import com.kyc.core.model.reports.ReportData;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import com.kyc.reports.model.ContractServiceRequest;
import com.kyc.reports.model.ReceiptRequest;
import com.kyc.reports.model.ServiceRequestForm;
import com.kyc.reports.service.GenerateReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ReportDelegate {

    @Autowired
    private GenerateReportService generateReportService;


    public ResponseEntity<ResponseData<ReportData>> generateServiceReqFormReport(RequestData<ServiceRequestForm> req){

        return generateReportService.generateServiceReqFormReport(req)
                .toResponseEntity();
    }

    public ResponseData<ReportData> retrieveReport(RequestData<String> req){

        return generateReportService.retrieveReport(req);
    }

    public ResponseEntity<ResponseData<ReportData>> generateReceipt(RequestData<ReceiptRequest> req){

        return generateReportService.generateReceipt(req)
                .toResponseEntity();
    }

    public ResponseEntity<ResponseData<ReportData>> generateContract(RequestData<ContractServiceRequest> req){

        return generateReportService.generateContract(req)
                .toResponseEntity();
    }
}
