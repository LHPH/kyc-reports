package com.kyc.reports.renders;

import com.kyc.core.model.reports.renders.AbstractPdfBoxTemplateRender;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.properties.KycMessages;
import com.kyc.core.util.DateUtil;
import com.kyc.reports.constants.PdfConstants;
import com.kyc.reports.model.web.ServiceRequest;
import com.kyc.reports.model.web.ServiceRequestForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

import static com.kyc.reports.constants.PdfConstants.CHECK_BOX_PROMOTIONS_EMAIL;
import static com.kyc.reports.constants.PdfConstants.CHECK_BOX_PROMOTIONS_PHONE;
import static com.kyc.reports.constants.PdfConstants.CHECK_BOX_SERVICE_1;
import static com.kyc.reports.constants.PdfConstants.CHECK_BOX_SERVICE_2;
import static com.kyc.reports.constants.PdfConstants.CHECK_BOX_SERVICE_3;
import static com.kyc.reports.constants.PdfConstants.CHECK_BOX_SERVICE_4;
import static com.kyc.reports.constants.PdfConstants.CHECK_BOX_SERVICE_5;
import static com.kyc.reports.constants.PdfConstants.CHECK_BOX_SERVICE_6;
import static com.kyc.reports.constants.PdfConstants.TEXT_FIELD_BRANCH;
import static com.kyc.reports.constants.PdfConstants.TEXT_FIELD_COST;
import static com.kyc.reports.constants.PdfConstants.TEXT_FIELD_CUSTOMER_ADDRESS;
import static com.kyc.reports.constants.PdfConstants.TEXT_FIELD_CUSTOMER_EMAIL;
import static com.kyc.reports.constants.PdfConstants.TEXT_FIELD_CUSTOMER_NAME;
import static com.kyc.reports.constants.PdfConstants.TEXT_FIELD_CUSTOMER_NUMBER;
import static com.kyc.reports.constants.PdfConstants.TEXT_FIELD_CUSTOMER_PHONE;
import static com.kyc.reports.constants.PdfConstants.TEXT_FIELD_CUSTOMER_RFC;
import static com.kyc.reports.constants.PdfConstants.TEXT_FIELD_DATE;
import static com.kyc.reports.constants.PdfConstants.TEXT_FIELD_EXECUTIVE;
import static com.kyc.reports.constants.PdfConstants.TEXT_FIELD_FOLIO;
import static com.kyc.reports.constants.PdfConstants.TEXT_FIELD_OFFER_CODE;

@Component
public class ApplicationServiceRender extends AbstractPdfBoxTemplateRender<ServiceRequestForm> {

    @Autowired
    public ApplicationServiceRender(@Value("${kyc-config.reports.service-application}") String pathTemplate,
                                    KycMessages kycMessages){
        super(pathTemplate,kycMessages);
    }

    @Override
    protected void fillFields(PDAcroForm pdAcroForm, String serialNumber, RequestData<ServiceRequestForm> data) throws IOException {

        ServiceRequestForm app = data.getBody();

        setTextField(pdAcroForm, TEXT_FIELD_EXECUTIVE, app.getIdExecutive()+": "+app.getExecutiveName());
        setTextField(pdAcroForm, TEXT_FIELD_FOLIO, Objects.toString(app.getFolio(),null));
        setTextField(pdAcroForm, TEXT_FIELD_BRANCH, app.getIdBranch()+": "+app.getBranchName());
        setTextField(pdAcroForm, TEXT_FIELD_DATE, DateUtil.dateToString(app.getDateApplication(),"dd/MM/yyyy"));
        setTextField(pdAcroForm, TEXT_FIELD_COST, app.getTotal());
        setTextField(pdAcroForm, TEXT_FIELD_OFFER_CODE, app.getCampaign());

        setTextField(pdAcroForm, TEXT_FIELD_CUSTOMER_NUMBER, app.getCustomerNumber());
        setTextField(pdAcroForm, TEXT_FIELD_CUSTOMER_NAME, app.getCustomerName());
        setTextField(pdAcroForm, TEXT_FIELD_CUSTOMER_RFC, app.getCustomerRfc());
        setTextField(pdAcroForm, TEXT_FIELD_CUSTOMER_EMAIL, app.getCustomerEmail());
        setTextField(pdAcroForm, TEXT_FIELD_CUSTOMER_PHONE, app.getCustomerPhone());
        setTextField(pdAcroForm, TEXT_FIELD_CUSTOMER_ADDRESS, app.getCustomerAddress());


        String promotions = Boolean.TRUE.equals(app.getAcceptPromotions()) ? "Yes" : "No";
        setRadioButton(pdAcroForm, PdfConstants.RADIO_BUTTON_RECEIVE_PROMOTIONS, promotions);

        if(Boolean.TRUE.equals(app.getAcceptPromotionsCellPhone())){

            setCheckBox(pdAcroForm,CHECK_BOX_PROMOTIONS_PHONE,true);
        }

        if(Boolean.TRUE.equals(app.getAcceptPromotionsEmail())){

            setCheckBox(pdAcroForm,CHECK_BOX_PROMOTIONS_EMAIL,true);
        }

        for(ServiceRequest service : app.getServices()){

            switch (service.getId()){
                case 1:
                    setCheckBox(pdAcroForm, CHECK_BOX_SERVICE_1, true);
                    break;
                case 2:
                    setCheckBox(pdAcroForm, CHECK_BOX_SERVICE_2, true);
                    break;
                case 3:
                    setCheckBox(pdAcroForm, CHECK_BOX_SERVICE_3, true);
                    break;
                case 4:
                    setCheckBox(pdAcroForm, CHECK_BOX_SERVICE_4, true);
                    break;
                case 5:
                    setCheckBox(pdAcroForm, CHECK_BOX_SERVICE_5, true);
                    break;
                case 6:
                    setCheckBox(pdAcroForm, CHECK_BOX_SERVICE_6, true);
                    break;
            }
        }

        setTextField(pdAcroForm, PdfConstants.TEXT_FIELD_SERIAL_NUMBER, serialNumber);
    }
}
