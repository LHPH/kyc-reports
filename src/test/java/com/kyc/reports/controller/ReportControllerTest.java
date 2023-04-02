package com.kyc.reports.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyc.core.model.reports.ReportData;
import com.kyc.core.util.TestsUtil;
import com.kyc.reports.controller.delegate.ReportDelegate;
import com.kyc.reports.model.web.BillRequest;
import com.kyc.reports.model.web.ContractServiceRequest;
import com.kyc.reports.model.web.ReceiptRequest;
import com.kyc.reports.model.web.ServiceRequest;
import com.kyc.reports.model.web.ServiceRequestForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReportController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportDelegate reportDelegate;

    private JacksonTester<Object> jacksonTester;

    @BeforeEach
    public void init(){

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this,objectMapper);
    }

    @Test
    public void printApplicationForm_processingRequest_returnResponse() throws Exception{

        ServiceRequestForm req = new ServiceRequestForm();
        req.setServices(Collections.singletonList(new ServiceRequest()));

        String content = jacksonTester.write(req).getJson();

        given(reportDelegate.generateServiceReqFormReport(ArgumentMatchers.any()))
                .willReturn(TestsUtil.getResponseTest(new ReportData()));

        mockMvc.perform(post("/reports/application-form")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void printServiceReceipt_processingRequest_returnResponse() throws Exception{

        ReceiptRequest req = new ReceiptRequest();
        req.setContractedServices(Collections.singletonList(new ServiceRequest()));

        String content = jacksonTester.write(req).getJson();

        given(reportDelegate.generateReceipt(ArgumentMatchers.any()))
                .willReturn(TestsUtil.getResponseTest(new ReportData()));

        mockMvc.perform(post("/reports/receipt")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void printServiceContract_processingRequest_returnResponse() throws Exception{

        ContractServiceRequest req = new ContractServiceRequest();
        req.setContractedServices(Collections.singletonList(new ServiceRequest()));

        String content = jacksonTester.write(req).getJson();

        given(reportDelegate.generateContract(ArgumentMatchers.any()))
                .willReturn(TestsUtil.getResponseTest(new ReportData()));

        mockMvc.perform(post("/reports/contract")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void printServiceBill_processingRequest_returnResponse() throws Exception{

        BillRequest req = new BillRequest();

        String content = jacksonTester.write(req).getJson();

        given(reportDelegate.generateBill(ArgumentMatchers.any()))
                .willReturn(TestsUtil.getResponseTest(new ReportData()));

        mockMvc.perform(post("/reports/bill")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getReport_processingRequest_returnResponse() throws Exception{


        given(reportDelegate.retrieveReport(ArgumentMatchers.any()))
                .willReturn(ResponseEntity.ok(new ByteArrayResource(new byte[]{0})));

        mockMvc.perform(get("/reports/{uuid}","344"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
