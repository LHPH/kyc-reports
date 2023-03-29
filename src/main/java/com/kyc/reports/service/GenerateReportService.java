package com.kyc.reports.service;

import com.kyc.core.model.reports.ReportData;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import com.kyc.reports.entity.KycRecordReportComplex;
import com.kyc.reports.enums.ReportTypeEnum;
import com.kyc.reports.model.web.BillRequest;
import com.kyc.reports.model.web.ContractServiceRequest;
import com.kyc.reports.model.DataToEntity;
import com.kyc.reports.model.web.ReceiptRequest;
import com.kyc.reports.model.web.ServiceRequestForm;
import com.kyc.reports.renders.BillServiceRender;
import com.kyc.reports.renders.ContractDocumentRender;
import com.kyc.reports.renders.ReceiptServiceRender;
import com.kyc.reports.renders.ApplicationServiceRender;
import com.kyc.reports.repository.KycRecordReportsComplexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class GenerateReportService {

    @Autowired
    private ApplicationServiceRender serviceReqPdfProcessor;

    @Autowired
    private ReceiptServiceRender receiptPdfProcessor;

    @Autowired
    private ContractDocumentRender contractDocProcessor;

    @Autowired
    private BillServiceRender billServiceRender;

    @Autowired
    private KycRecordReportsComplexRepository kycRecordReportsComplexRepository;


    public ResponseData<ReportData> generateServiceReqFormReport(RequestData<ServiceRequestForm> req){

        UUID idReport = UUID.randomUUID();
        Long folio = req.getBody().getFolio();
        Long customerNumber = req.getBody().getCustomerNumber();

        ByteArrayResource byteArrayResource = serviceReqPdfProcessor.generateReport(idReport.toString(),req);

        DataToEntity dataToEntity = DataToEntity.builder()
                .id(idReport)
                .folio(folio)
                .customerNumber(customerNumber)
                .creator("creator")
                .reportType(ReportTypeEnum.APPLICATION_FORM)
                .build();

        KycRecordReportComplex entity = getBaseEntity(dataToEntity);
        entity.setReport(byteArrayResource.getByteArray());

        kycRecordReportsComplexRepository.save(entity);

        return ResponseData.of(getReportData(entity));
    }

    public ResponseData<ReportData> generateReceipt(RequestData<ReceiptRequest> req){

        UUID idReport = UUID.randomUUID();
        Long folio = req.getBody().getFolio();
        Long customerNumber = req.getBody().getCustomerNumber();

        ByteArrayResource byteArrayResource = receiptPdfProcessor.generateReport(idReport.toString(),req);

        DataToEntity dataToEntity = DataToEntity.builder()
                .id(idReport)
                .folio(folio)
                .customerNumber(customerNumber)
                .creator("creator")
                .reportType(ReportTypeEnum.RECEIPT)
                .build();

        KycRecordReportComplex entity = getBaseEntity(dataToEntity);
        entity.setReport(byteArrayResource.getByteArray());

        kycRecordReportsComplexRepository.save(entity);

        return ResponseData.of(getReportData(entity));

    }

    public ResponseData<ReportData> generateContract(RequestData<ContractServiceRequest> req){

        UUID idReport = UUID.randomUUID();
        Long folio = req.getBody().getFolio();
        Long customerNumber = req.getBody().getCustomerNumber();

        ByteArrayResource byteArrayResource = contractDocProcessor.generateReport(idReport.toString(),req);

        DataToEntity dataToEntity = DataToEntity.builder()
                .id(idReport)
                .folio(folio)
                .customerNumber(customerNumber)
                .creator("creator")
                .reportType(ReportTypeEnum.CONTRACT)
                .build();

        KycRecordReportComplex entity = getBaseEntity(dataToEntity);
        entity.setReport(byteArrayResource.getByteArray());

        kycRecordReportsComplexRepository.save(entity);

        return ResponseData.of(getReportData(entity));
    }

    public ResponseData<ReportData> generateBill(RequestData<BillRequest> req){

        UUID idReport = UUID.randomUUID();

        ByteArrayResource byteArrayResource = billServiceRender.generateReport(idReport.toString(),req);

        DataToEntity dataToEntity = DataToEntity.builder()
                .id(idReport)
                .folio(req.getBody().getId())
                .customerNumber(4L) //FIX
                .creator("creator")
                .reportType(ReportTypeEnum.BILL)
                .build();

        KycRecordReportComplex entity = getBaseEntity(dataToEntity);
        entity.setReport(byteArrayResource.getByteArray());

        kycRecordReportsComplexRepository.save(entity);

        return ResponseData.of(getReportData(entity));
    }

    public ResponseData<Resource> retrieveReport(RequestData<String> req){

        UUID idReport = UUID.fromString(req.getBody());

        Optional<KycRecordReportComplex> opRecord = kycRecordReportsComplexRepository.findById(idReport);
        if(opRecord.isPresent()){

            KycRecordReportComplex entity = opRecord.get();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+entity.getName()+"\"");
            httpHeaders.add(HttpHeaders.CONTENT_LENGTH,""+entity.getReport().length);
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, entity.getMimeType());

            return ResponseData.of(new ByteArrayResource(entity.getReport()),httpHeaders, HttpStatus.OK);
        }
        return null;
    }


    private ReportData getReportData(KycRecordReportComplex entity){

        ReportData reportData = new ReportData();
        reportData.setDate(entity.getOutputDate());
        reportData.setMimeType(entity.getMimeType());
        reportData.setSize(entity.getReport().length);
        reportData.setName(entity.getName());
        reportData.setId(String.valueOf(entity.getId()));
        return reportData;
    }

    private KycRecordReportComplex getBaseEntity(DataToEntity data){

        KycRecordReportComplex entity = new KycRecordReportComplex();
        entity.setId(data.getId());
        entity.setOutputDate(new Date());
        entity.setIdCustomer(data.getCustomerNumber());
        entity.setFolio(data.getFolio());

        ReportTypeEnum reportTypeEnum = data.getReportType();

        entity.setName(reportTypeEnum.buildReportName(data.getCustomerNumber(),data.getFolio()));
        entity.setMimeType(reportTypeEnum.getMediaType());
        entity.setIdReportType(reportTypeEnum.getIdType());

        return entity;
    }
}
