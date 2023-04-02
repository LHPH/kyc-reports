package com.kyc.reports.renders;

import com.kyc.core.model.web.RequestData;
import com.kyc.core.properties.KycMessages;
import com.kyc.reports.model.web.ServiceRequest;
import com.kyc.reports.model.web.ServiceRequestForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceRenderTest {

    private ApplicationServiceRender render = new ApplicationServiceRender("templates/KYC_SERVICE_FORM.pdf",new KycMessages());

    @Test
    public void generateReport_processingData_generatedReport() throws IOException {

        Assertions.assertDoesNotThrow(()->{

            ServiceRequestForm req = new ServiceRequestForm();
            req.setAcceptPromotions(true);
            req.setAcceptPromotionsCellPhone(true);
            req.setAcceptPromotionsEmail(true);
            req.setCampaign("TEST");
            req.setCustomerName("TEST");
            req.setDateApplication(new Date());

            ServiceRequest service = new ServiceRequest();
            service.setId(1);
            service.setCost(10D);
            service.setName("TEST");
            req.setServices(Collections.singletonList(service));

            RequestData<ServiceRequestForm> requestData = RequestData.<ServiceRequestForm>builder()
                    .body(req)
                    .build();

            render.loadBytesTemplate();
            Assertions.assertNotNull(render.generateReport("test",requestData));
        });
    }

}
