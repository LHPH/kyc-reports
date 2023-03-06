package com.kyc.reports.service;

import com.kyc.core.model.reports.ReportData;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import com.kyc.reports.entity.KycRecordReportComplex;
import com.kyc.reports.model.ServiceRequestForm;
import com.kyc.reports.processors.ServiceReqPdfProcessor;
import com.kyc.reports.repository.KycRecordReportsComplexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class GenerateReportService {

    @Autowired
    private ServiceReqPdfProcessor serviceReqPdfProcessor;

    @Autowired
    private KycRecordReportsComplexRepository kycRecordReportsComplexRepository;


    public ResponseData<ReportData> generateServiceReqFormReport(RequestData<ServiceRequestForm> req){

        UUID idReport = UUID.randomUUID();
        req.getBody().setSerialNumber(idReport.toString());
        Long folio = req.getBody().getFolio();
        Long customerNumber = req.getBody().getCustomerNumber();

        ByteArrayResource byteArrayResource = serviceReqPdfProcessor.generateReport(req);

        KycRecordReportComplex entity = new KycRecordReportComplex();
        entity.setId(idReport);
        entity.setOutputDate(new Date());
        entity.setName(customerNumber+"-"+folio+".pdf");
        entity.setMimeType(MediaType.APPLICATION_PDF_VALUE);
        entity.setReport(byteArrayResource.getByteArray());
        entity.setIdReportType(1);
        entity.setFolio(folio);

        kycRecordReportsComplexRepository.save(entity);

        ReportData reportData = new ReportData();
        reportData.setDate(entity.getOutputDate());
        reportData.setMimeType(entity.getMimeType());
        reportData.setSize(entity.getReport().length);
        reportData.setName(entity.getName());
        reportData.setUrl("http://localhost:9005/reports/"+idReport);

        return ResponseData.of(reportData);
    }

    public ResponseData<ReportData> retrieveReport(RequestData<String> req){

        UUID idReport = UUID.fromString(req.getBody());

        Optional<KycRecordReportComplex> opRecord = kycRecordReportsComplexRepository.findById(idReport);
        if(opRecord.isPresent()){

            KycRecordReportComplex entity = opRecord.get();

            ReportData reportData = new ReportData();
            reportData.setDate(entity.getOutputDate());
            reportData.setMimeType(entity.getMimeType());
            reportData.setSize(entity.getReport().length);
            reportData.setName(entity.getName());
            reportData.setResource(new ByteArrayResource(entity.getReport()));

            return ResponseData.of(reportData);
        }
        return null;
    }

}
