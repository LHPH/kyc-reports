package com.kyc.reports.renders;

import com.kyc.core.model.web.RequestData;
import com.kyc.core.properties.KycMessages;
import com.kyc.reports.model.web.ContractServiceRequest;
import com.kyc.reports.model.web.ServiceRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class ContractDocumentRenderTest {

    private ContractDocumentRender render = new ContractDocumentRender("templates/KYC_CONTRACT.docx",new KycMessages());

    @Test
    public void generateReport_processingData_generatedReport(){

        Assertions.assertDoesNotThrow(()->{

            render.loadBytesTemplate();

            ContractServiceRequest req = new ContractServiceRequest();

            req.setCustomerName("TEST");
            req.setCustomerNumber(1L);
            req.setFolio(1L);
            req.setCustomerAddress("TEST");
            ServiceRequest serviceRequest = new ServiceRequest();
            serviceRequest.setName("TEST");
            serviceRequest.setCost(10D);
            req.setContractedServices(Collections.singletonList(serviceRequest));

            RequestData<ContractServiceRequest> requestData = RequestData.<ContractServiceRequest>builder()
                    .body(req)
                    .build();

            Assertions.assertNotNull(render.generateReport("test",requestData));
        });
    }
}
