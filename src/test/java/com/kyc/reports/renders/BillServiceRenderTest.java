package com.kyc.reports.renders;

import com.kyc.core.model.web.RequestData;
import com.kyc.core.properties.KycMessages;
import com.kyc.reports.model.web.BillRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BillServiceRenderTest {

    private BillServiceRender render = new BillServiceRender("templates/KYC_SERVICE_BILL.jasper",new KycMessages());

    @Test
    public void generateReport_processingData_generatedReport(){

        Assertions.assertDoesNotThrow(()->{

            BillRequest req = new BillRequest();
            req.setId(1L);

            RequestData<BillRequest> requestData = RequestData.<BillRequest>builder()
                    .body(req)
                    .build();

            render.loadBytesTemplate();
            Assertions.assertNotNull(render.generateReport("test", requestData));
        });
    }
}
