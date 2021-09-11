package com.swithExample.driven.models.auditable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.swithExample.driven.util.Constant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//lombok annotations
@Data
@ToString

//spring auditing annotations
//annotation designates a class whose mapping information is applied to the
//entities that inherit from it. A mapped super class has no separate table defined
//for it
@MappedSuperclass
//specifies the callback com.swithExample.driven.listener classes to be used for an entity or mapped
//superclass
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class AuditableDomain<U> implements Serializable {

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private U created_by;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.API_FORMAT_DATE_TIME)
    private Date created_date;

    @LastModifiedBy
    @Column(name = "updated_by")
    private U updated_by;

    @LastModifiedDate
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.API_FORMAT_DATE_TIME)
    private Date updated_date;

    @PrePersist
    protected void onCreate() {
        this.created_date = new Date();
        this.updated_date = null;
        this.updated_by = null;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_date = new Date();
    }
}
