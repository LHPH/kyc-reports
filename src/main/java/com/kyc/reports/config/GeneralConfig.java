package com.kyc.reports.config;

import com.kyc.core.exception.handlers.KycUnhandledExceptionHandler;
import com.kyc.core.properties.KycMessages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static com.kyc.reports.constants.AppConstants.MSG_APP_001;

@Configuration
@Import(value = {KycMessages.class})
public class GeneralConfig {

    @Bean
    public KycUnhandledExceptionHandler kycUnhandledExceptionHandler(KycMessages kycMessages){

        return new KycUnhandledExceptionHandler(kycMessages.getMessage(MSG_APP_001));
    }
}
