package com.kyc.reports;

import org.apache.logging.log4j.LogManager;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@ExtendWith(MockitoExtension.class)
class PdfTest {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Test
	public void testPdf() {

		String path = "templates/KYC_SERVICE_FORM.pdf";

		ClassPathResource cl = new ClassPathResource(path);
		try(InputStream in = cl.getInputStream()){

			PDDocument pDDocument = PDDocument.load(in);
			PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();

			if(pDAcroForm!=null){

				for(PDField fields : pDAcroForm.getFields()){
					LOGGER.info("{}", fields.getFullyQualifiedName());
				}
			}
			else{
				Assertions.fail("The PDF File does not have acro forms");
			}
		}
		catch(IOException ioex){
			LOGGER.error(" ",ioex);
			Assertions.fail("Should be not thrown an exception");
		}
	}

}
