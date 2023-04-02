package com.kyc.reports.controller.delegate;

import com.kyc.core.model.reports.ReportData;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import com.kyc.reports.model.web.BillRequest;
import com.kyc.reports.model.web.ContractServiceRequest;
import com.kyc.reports.model.web.ReceiptRequest;
import com.kyc.reports.model.web.ServiceRequestForm;
import com.kyc.reports.service.GenerateReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReportDelegateTest {

    @Mock
    private GenerateReportService generateReportService;

    @InjectMocks
    private ReportDelegate delegate;

    @Test
    public void generateServiceReqFormReport_passRequest_returnResponse(){

        given(generateReportService.generateServiceReqFormReport(any()))
                .willReturn(ResponseData.of(new ReportData()));

        delegate.generateServiceReqFormReport(RequestData.<ServiceRequestForm>builder().build());
        verify(generateReportService,times(1)).generateServiceReqFormReport(any());
    }

    @Test
    public void generateReceipt_passRequest_returnResponse(){

        given(generateReportService.generateReceipt(any()))
                .willReturn(ResponseData.of(new ReportData()));

        delegate.generateReceipt(RequestData.<ReceiptRequest>builder().build());
        verify(generateReportService,times(1)).generateReceipt(any());
    }

    @Test
    public void generateContract_passRequest_returnResponse(){

        given(generateReportService.generateContract(any()))
                .willReturn(ResponseData.of(new ReportData()));

        delegate.generateContract(RequestData.<ContractServiceRequest>builder().build());
        verify(generateReportService,times(1)).generateContract(any());
    }

    @Test
    public void generateBill_passRequest_returnResponse(){

        given(generateReportService.generateBill(any()))
                .willReturn(ResponseData.of(new ReportData()));

        delegate.generateBill(RequestData.<BillRequest>builder().build());
        verify(generateReportService,times(1)).generateBill(any());
    }

    @Test
    public void retrieveReport_passRequest_returnResponse(){

        given(generateReportService.retrieveReport(any()))
                .willReturn(ResponseData.of(new ByteArrayResource(new byte[]{0})));

        delegate.retrieveReport(RequestData.<String>builder().build());
        verify(generateReportService,times(1)).retrieveReport(any());
    }
}
