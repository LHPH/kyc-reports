package com.kyc.reports.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Setter
@Getter
public class BaseKycRecordReport implements Serializable, Persistable<UUID> {

    @Id
    private UUID id;

    //TO PREVENT SELECT BEFORE INSERT IN ASSIGNED UUID
    @Transient
    private boolean persisted;

    @Column(name = "NAME")
    private String name;

    @Column(name = "OUTPUT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date outputDate;

    @Column(name = "REPORT_TYPE_ID")
    private Integer idReportType;

    @Column(name = "MIME_TYPE")
    private String mimeType;

    @Column(name = "RELATED_FOLIO")
    private Long folio;

    @Column(name = "ID_CUSTOMER")
    private Long idCustomer;

    @Column(name = "CREATOR")
    private String creator;
    
    @Override
    public boolean isNew() {
        return !persisted;
    }

    @PostPersist
    @PostLoad
    public void setPersisted(){
        persisted = true;
    }
}
