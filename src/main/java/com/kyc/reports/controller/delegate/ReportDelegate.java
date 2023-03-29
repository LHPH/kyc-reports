package com.kyc.reports.controller.delegate;

import com.kyc.core.model.reports.ReportData;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import com.kyc.reports.model.web.BillRequest;
import com.kyc.reports.model.web.ContractServiceRequest;
import com.kyc.reports.model.web.ReceiptRequest;
import com.kyc.reports.model.web.ServiceRequestForm;
import com.kyc.reports.service.GenerateReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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

    public ResponseEntity<Resource> retrieveReport(RequestData<String> req){

        return generateReportService.retrieveReport(req)
                .toResponseEntityStream();
    }

    public ResponseEntity<ResponseData<ReportData>> generateReceipt(RequestData<ReceiptRequest> req){

        return generateReportService.generateReceipt(req)
                .toResponseEntity();
    }

    public ResponseEntity<ResponseData<ReportData>> generateContract(RequestData<ContractServiceRequest> req){

        return generateReportService.generateContract(req)
                .toResponseEntity();
    }

    public ResponseEntity<ResponseData<ReportData>> generateBill(RequestData<BillRequest> req){

        return generateReportService.generateBill(req)
                .toResponseEntity();
    }
}
