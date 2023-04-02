package com.kyc.reports.controller;

import com.kyc.core.model.reports.ReportData;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import com.kyc.reports.controller.delegate.ReportDelegate;
import com.kyc.reports.model.web.BillRequest;
import com.kyc.reports.model.web.ContractServiceRequest;
import com.kyc.reports.model.web.ReceiptRequest;
import com.kyc.reports.model.web.ServiceRequestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportDelegate reportDelegate;

    @PostMapping("/application-form")
    public ResponseEntity<ResponseData<ReportData>> printApplicationForm(@RequestBody ServiceRequestForm request){

        RequestData<ServiceRequestForm> req = RequestData.<ServiceRequestForm>builder()
                .body(request)
                .build();

       return reportDelegate.generateServiceReqFormReport(req);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Resource> getReport(@PathVariable("uuid") String uuid){

        RequestData<String> req = RequestData.<String>builder()
                .body(uuid)
                .build();

        return reportDelegate.retrieveReport(req);
    }

    @PostMapping("/receipt")
    public ResponseEntity<ResponseData<ReportData>> printServiceReceipt(@RequestBody ReceiptRequest request){

        RequestData<ReceiptRequest> req = RequestData.<ReceiptRequest>builder()
                .body(request)
                .build();

        return reportDelegate.generateReceipt(req);
    }

    @PostMapping("/contract")
    public ResponseEntity<ResponseData<ReportData>> printServiceContract(@RequestBody ContractServiceRequest request){

        RequestData<ContractServiceRequest> req = RequestData.<ContractServiceRequest>builder()
                .body(request)
                .build();

        return reportDelegate.generateContract(req);
    }

    @PostMapping("/bill")
    public ResponseEntity<ResponseData<ReportData>> printServiceBill(@RequestBody BillRequest request){

        RequestData<BillRequest> req = RequestData.<BillRequest>builder()
                .body(request)
                .build();

        return reportDelegate.generateBill(req);
    }
}
