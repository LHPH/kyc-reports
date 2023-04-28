package com.kyc.reports.renders;

import com.kyc.core.reports.renders.AbstractWordTemplateRender;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.properties.KycMessages;
import com.kyc.core.util.DateUtil;
import com.kyc.reports.model.web.ContractServiceRequest;
import com.kyc.reports.model.web.ServiceRequest;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Component
public class ContractDocumentRender extends AbstractWordTemplateRender<ContractServiceRequest> {

    @Autowired
    public ContractDocumentRender(@Value("${kyc-config.reports.contract}") String pathTemplate, KycMessages kycMessages) {
        super(pathTemplate, kycMessages);
    }

    @Override
    protected Map<String, String> fillVariables(XWPFDocument xwpfDocument, String serialNumber, RequestData<ContractServiceRequest> requestData) {

        Map<String, String> map = new HashMap<>();

        ContractServiceRequest data = requestData.getBody();

        map.put("${V_DATE}", DateUtil.dateToString(new Date(),"dd' de 'MMMMM' del 'yyyy"));
        map.put("${V_C_NAME}",data.getCustomerName());
        map.put("${V_C_ADDR}",data.getCustomerAddress());
        map.put("${V_SERIAL_NUMBER}",serialNumber);
        map.put("${V_FOLIO}", Objects.toString(data.getFolio()));

        return map;
    }

    @Override
    protected void additionalProcessing(XWPFDocument doc, RequestData<ContractServiceRequest> data) {

        XWPFTable table = doc.getTableArray(0);

        List<ServiceRequest> services = data.getBody().getContractedServices();

        DecimalFormatSymbols dfs = new DecimalFormatSymbols(new Locale("mx"));
        dfs.setCurrencySymbol("$");
        String pattern = "¤###,###.00";
        DecimalFormat df = new DecimalFormat(pattern,dfs);

        double total = 0.0;
        for(ServiceRequest service : services){

            XWPFTableRow newRow = table.createRow();

            newRow.getCell(0).setText(service.getName());
            newRow.getCell(1).setText(df.format(service.getCost()));
            total +=service.getCost();
        }
        XWPFTableRow totalRow = table.createRow();
        totalRow.getCell(0).setText("Total");
        totalRow.getCell(0).getParagraphArray(0).getRuns().get(0).setBold(true);
        totalRow.getCell(1).setText(df.format(total));
    }
}
