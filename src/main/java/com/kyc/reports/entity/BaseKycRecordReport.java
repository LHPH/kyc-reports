package com.kyc.reports.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
