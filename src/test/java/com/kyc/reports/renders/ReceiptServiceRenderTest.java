package com.kyc.reports.renders;

import com.kyc.core.model.web.RequestData;
import com.kyc.core.properties.KycMessages;
import com.kyc.reports.model.web.ReceiptRequest;
import com.kyc.reports.model.web.ServiceRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.StaticApplicationContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.Collections;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceRenderTest {

    private ReceiptServiceRender render;

    @BeforeEach
    public void setUp(){

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(new StaticApplicationContext());
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.setTemplateResolver(templateResolver);

        render = new ReceiptServiceRender("KYC_PAYMENT_RECEIPT",new KycMessages(),templateEngine);

    }

    @Test
    public void generateReport_processingData_generatedReport(){

        Assertions.assertDoesNotThrow(()->{

            ReceiptRequest req = new ReceiptRequest();
            req.setCustomerAddress("TEST");
            req.setCustomerName("TEST");
            req.setCustomerNumber(1L);
            req.setDateDocument(new Date());
            req.setIdPaymentMethod(1);
            req.setPaidAmount(10D);

            ServiceRequest serviceRequest = new ServiceRequest();
            serviceRequest.setName("TEST");
            serviceRequest.setCost(10D);
            req.setContractedServices(Collections.singletonList(serviceRequest));

            RequestData<ReceiptRequest> requestData = RequestData.<ReceiptRequest>builder()
                    .body(req)
                    .build();

            Assertions.assertNotNull(render.generateReport("TEST",requestData));
        });
    }
}
