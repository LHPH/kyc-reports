package com.kyc.reports.service;

import com.kyc.core.exception.KycException;
import com.kyc.core.exception.KycRestException;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import com.kyc.core.properties.KycMessages;
import com.kyc.reports.entity.KycRecordReportComplex;
import com.kyc.reports.model.web.BillRequest;
import com.kyc.reports.model.web.ContractServiceRequest;
import com.kyc.reports.model.web.ReceiptRequest;
import com.kyc.reports.model.web.ServiceRequestForm;
import com.kyc.reports.renders.ApplicationServiceRender;
import com.kyc.reports.renders.BillServiceRender;
import com.kyc.reports.renders.ContractDocumentRender;
import com.kyc.reports.renders.ReceiptServiceRender;
import com.kyc.reports.repository.KycRecordReportsComplexRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.MediaType;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GenerateReportServiceTest {

    @Mock
    private ApplicationServiceRender serviceReqPdfProcessor;

    @Mock
    private ReceiptServiceRender receiptPdfProcessor;

    @Mock
    private ContractDocumentRender contractDocProcessor;

    @Mock
    private BillServiceRender billServiceRender;

    @Mock
    private KycRecordReportsComplexRepository kycRecordReportsComplexRepository;

    @Mock
    private KycMessages kycMessages;
    
    @InjectMocks
    private GenerateReportService service;

    @Test
    public void generateServiceReqFormReport_generateReportAndSaveDatabase_successfulResponse(){

        RequestData<ServiceRequestForm> requestData = RequestData.<ServiceRequestForm>builder()
                .body(new ServiceRequestForm())
                .build();

        given(serviceReqPdfProcessor.generateReport(anyString(),any()))
                .willReturn(new ByteArrayResource(new byte[]{0}));

        service.generateServiceReqFormReport(requestData);
        verify(kycRecordReportsComplexRepository,times(1))
                .save(any(KycRecordReportComplex.class));
    }

    @Test
    public void generateServiceReqFormReport_errorGeneratingReport_throwException(){

        Assertions.assertThrows(KycRestException.class,()->{

            RequestData<ServiceRequestForm> requestData = RequestData.<ServiceRequestForm>builder()
                    .body(new ServiceRequestForm())
                    .build();

            given(serviceReqPdfProcessor.generateReport(anyString(),any()))
                    .willThrow(KycException.builder().build());

            service.generateServiceReqFormReport(requestData);
        });
    }

    @Test
    public void generateServiceReqFormReport_errorSavingReport_throwException(){

        Assertions.assertThrows(KycRestException.class,()->{

            RequestData<ServiceRequestForm> requestData = RequestData.<ServiceRequestForm>builder()
                    .body(new ServiceRequestForm())
                    .build();

            given(serviceReqPdfProcessor.generateReport(anyString(),any()))
                    .willReturn(new ByteArrayResource(new byte[]{0}));
            given(kycRecordReportsComplexRepository.save(any(KycRecordReportComplex.class)))
                    .willThrow(new InvalidDataAccessResourceUsageException("test db error"));

            service.generateServiceReqFormReport(requestData);
        });
    }

    @Test
    public void generateReceipt_generateReportAndSaveDatabase_successfulResponse(){

        RequestData<ReceiptRequest> requestData = RequestData.<ReceiptRequest>builder()
                .body(new ReceiptRequest())
                .build();

        given(receiptPdfProcessor.generateReport(anyString(),any()))
                .willReturn(new ByteArrayResource(new byte[]{0}));

        service.generateReceipt(requestData);
        verify(kycRecordReportsComplexRepository,times(1))
                .save(any(KycRecordReportComplex.class));
    }

    @Test
    public void generateReceipt_errorGeneratingReport_throwException(){

        Assertions.assertThrows(KycRestException.class,()->{

            RequestData<ReceiptRequest> requestData = RequestData.<ReceiptRequest>builder()
                    .body(new ReceiptRequest())
                    .build();

            given(receiptPdfProcessor.generateReport(anyString(),any()))
                    .willThrow(KycException.builder().build());

            service.generateReceipt(requestData);
        });
    }

    @Test
    public void generateReceipt_errorSavingReport_throwException(){

        Assertions.assertThrows(KycRestException.class,()->{

            RequestData<ReceiptRequest> requestData = RequestData.<ReceiptRequest>builder()
                    .body(new ReceiptRequest())
                    .build();

            given(receiptPdfProcessor.generateReport(anyString(),any()))
                    .willReturn(new ByteArrayResource(new byte[]{0}));
            given(kycRecordReportsComplexRepository.save(any(KycRecordReportComplex.class)))
                    .willThrow(new InvalidDataAccessResourceUsageException("test db error"));

            service.generateReceipt(requestData);
        });
    }

    @Test
    public void generateContract_generateReportAndSaveDatabase_successfulResponse(){

        RequestData<ContractServiceRequest> requestData = RequestData.<ContractServiceRequest>builder()
                .body(new ContractServiceRequest())
                .build();

        given(contractDocProcessor.generateReport(anyString(),any()))
                .willReturn(new ByteArrayResource(new byte[]{0}));

        service.generateContract(requestData);
        verify(kycRecordReportsComplexRepository,times(1))
                .save(any(KycRecordReportComplex.class));
    }

    @Test
    public void generateContract_errorGeneratingReport_throwException(){

        Assertions.assertThrows(KycRestException.class,()->{

            RequestData<ContractServiceRequest> requestData = RequestData.<ContractServiceRequest>builder()
                    .body(new ContractServiceRequest())
                    .build();

            given(contractDocProcessor.generateReport(anyString(),any()))
                    .willThrow(KycException.builder().build());

            service.generateContract(requestData);
        });
    }

    @Test
    public void generateContract_errorSavingReport_throwException(){

        Assertions.assertThrows(KycRestException.class,()->{

            RequestData<ContractServiceRequest> requestData = RequestData.<ContractServiceRequest>builder()
                    .body(new ContractServiceRequest())
                    .build();

            given(contractDocProcessor.generateReport(anyString(),any()))
                    .willReturn(new ByteArrayResource(new byte[]{0}));
            given(kycRecordReportsComplexRepository.save(any(KycRecordReportComplex.class)))
                    .willThrow(new InvalidDataAccessResourceUsageException("test db error"));

            service.generateContract(requestData);
        });
    }

    @Test
    public void generateBill_generateReportAndSaveDatabase_successfulResponse(){

        RequestData<BillRequest> requestData = RequestData.<BillRequest>builder()
                .body(new BillRequest())
                .build();

        given(billServiceRender.generateReport(anyString(),any()))
                .willReturn(new ByteArrayResource(new byte[]{0}));

        service.generateBill(requestData);
        verify(kycRecordReportsComplexRepository,times(1))
                .save(any(KycRecordReportComplex.class));
    }

    @Test
    public void generateBill_errorGeneratingReport_throwException(){

        Assertions.assertThrows(KycRestException.class,()->{

            RequestData<BillRequest> requestData = RequestData.<BillRequest>builder()
                    .body(new BillRequest())
                    .build();

            given(billServiceRender.generateReport(anyString(),any()))
                    .willThrow(KycException.builder().build());

            service.generateBill(requestData);
        });
    }

    @Test
    public void generateBill_errorSavingReport_throwException(){

        Assertions.assertThrows(KycRestException.class,()->{

            RequestData<BillRequest> requestData = RequestData.<BillRequest>builder()
                    .body(new BillRequest())
                    .build();

            given(billServiceRender.generateReport(anyString(),any()))
                    .willReturn(new ByteArrayResource(new byte[]{0}));
            given(kycRecordReportsComplexRepository.save(any(KycRecordReportComplex.class)))
                    .willThrow(new InvalidDataAccessResourceUsageException("test db error"));

            service.generateBill(requestData);
        });
    }

    @Test
    public void retrieveReport_retrievingExistingReport_returnReport(){

        RequestData<String> requestData = RequestData.<String>builder()
                .body(UUID.randomUUID().toString())
                .build();

        KycRecordReportComplex entity = new KycRecordReportComplex();
        entity.setFolio(1L);
        entity.setReport(new byte[]{0});
        entity.setName("TEST");
        entity.setMimeType(MediaType.APPLICATION_PDF_VALUE);

        given(kycRecordReportsComplexRepository.findById(any(UUID.class)))
                .willReturn(Optional.of(entity));

        ResponseData<Resource> result = service.retrieveReport(requestData);
        Assertions.assertNotNull(result);
    }

    @Test
    public void retrieveReport_retrievingNonExistingReport_throwException(){

        Assertions.assertThrows(KycRestException.class,()->{

            RequestData<String> requestData = RequestData.<String>builder()
                    .body(UUID.randomUUID().toString())
                    .build();

            given(kycRecordReportsComplexRepository.findById(any(UUID.class)))
                    .willReturn(Optional.empty());

            service.retrieveReport(requestData);
        });
    }

    @Test
    public void retrieveReport_errorOnDatabase_throwException(){

        Assertions.assertThrows(KycRestException.class,()->{

            RequestData<String> requestData = RequestData.<String>builder()
                    .body(UUID.randomUUID().toString())
                    .build();

            given(kycRecordReportsComplexRepository.findById(any(UUID.class)))
                    .willThrow(new InvalidDataAccessResourceUsageException("test db error"));

            service.retrieveReport(requestData);
        });
    }
}
