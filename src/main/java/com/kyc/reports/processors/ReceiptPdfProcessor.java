package com.kyc.reports.processors;

import com.kyc.core.model.reports.processors.AbstractGeneratePdfThymeleafTemplate;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.properties.KycMessages;
import com.kyc.core.util.DateUtil;
import com.kyc.reports.model.ReceiptRequest;
import com.kyc.reports.model.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReceiptPdfProcessor extends AbstractGeneratePdfThymeleafTemplate<ReceiptRequest> {

    @Autowired
    public ReceiptPdfProcessor(@Value("${kyc-config.reports.receipt}")String nameTemplate,
                               KycMessages kycMessages, SpringTemplateEngine templateEngine) {
        super(nameTemplate, kycMessages, templateEngine);
    }

    @Override
    protected Context fillContext(RequestData<ReceiptRequest> requestData) {

        Context context = new Context();
        Map<String,Object> vars = new HashMap<>();
        ReceiptRequest data = requestData.getBody();

        vars.put("customer_name",data.getCustomerName());
        vars.put("customer_address",data.getCustomerAddress());
        vars.put("customer_number",data.getCustomerNumber());
        vars.put("folio",data.getFolio());
        vars.put("folio_date", DateUtil.dateToString(data.getDateDocument()));
        vars.put("paid_amount",data.getPaidAmount());
        vars.put("services",data.getContractedServices());

        Double totalAmount = data.getContractedServices()
                .stream()
                .mapToDouble(ServiceRequest::getCost)
                .sum();

        vars.put("total_amount",totalAmount);
        vars.put("payment_option",data.getIdPaymentMethod());
        vars.put("id_report",data.getSerialNumber());

        context.setVariables(vars);
        return context;
    }
}
