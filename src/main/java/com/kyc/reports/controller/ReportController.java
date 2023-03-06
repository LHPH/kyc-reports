package com.kyc.reports.controller;

import com.kyc.core.model.reports.ReportData;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import com.kyc.reports.controller.delegate.ReportDelegate;
import com.kyc.reports.model.ServiceRequestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<ResponseData<ReportData>> printServiceHiringApplication(@RequestBody ServiceRequestForm request){

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

        ResponseData<ReportData> result = reportDelegate.retrieveReport(req);
        ReportData reportData = result.getData();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+reportData.getName()+"\"");
        httpHeaders.add(HttpHeaders.CONTENT_LENGTH,""+reportData.getSize());
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, reportData.getMimeType());

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(reportData.getResource());
    }
}
