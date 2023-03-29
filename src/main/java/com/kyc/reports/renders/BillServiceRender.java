package com.kyc.reports.renders;

import com.kyc.core.model.reports.renders.AbstractPdfJasperRender;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.properties.KycMessages;
import com.kyc.core.util.DateUtil;
import com.kyc.reports.model.web.BillRequest;
import com.kyc.reports.model.web.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BillServiceRender extends AbstractPdfJasperRender<BillRequest> {

    @Autowired
    public BillServiceRender(@Value("${kyc-config.reports.bill}") String pathTemplate,
                             KycMessages kycMessages){
        super(pathTemplate,kycMessages);
    }

    @Override
    protected Map<String, Object> fillParameters(String serialNumber, RequestData<BillRequest> requestData) {

        Map<String, Object> params = new HashMap<>();

        params.put("CUSTOMER_NAME","TEST TEST TEST TEST");
        params.put("RFC","TEST901010AB2");
        params.put("BILL_NUMBER",String.valueOf(requestData.getBody().getId()));
        params.put("DATE_REPORT", DateUtil.dateToString(new Date()));
        params.put("SERIAL_NUMBER",serialNumber);
        params.put("DATE_LIMIT",DateUtil.dateToString(new Date()));
        params.put("CUSTOMER_ADDRESS","AV TEST, COL TEST, ESTADO TEST");
        params.put("CUSTOMER_NUMBER","545436353");
        params.put("IS_DESIGN_VIEW",false);

        List<ServiceRequest> list = new ArrayList<>();
        list.add(new ServiceRequest(1,"SERVICE 1",1000.0D));
        list.add(new ServiceRequest(2,"SERVICE 2",500.00));
        list.add(new ServiceRequest(3,"SERVICE 3",200.00));

        params.put("SERVICES",list);

        return params;
    }
}
