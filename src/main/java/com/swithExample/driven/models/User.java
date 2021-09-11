package com.swithExample.driven.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.swithExample.driven.common.enums.AppStatus;
import com.swithExample.driven.common.enums.UserRole;
import com.swithExample.driven.util.Constant;
import lombok.*;
import com.swithExample.driven.models.auditable.AuditableDomain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "user")
@ToString
public class User extends AuditableDomain<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(nullable = false)
    private UserRole role;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AppStatus status;
    @JsonFormat(pattern = Constant.API_FORMAT_DATE_TIME, shape = JsonFormat.Shape.STRING)
    private Date lastLoginDate;
    @JsonFormat(pattern = Constant.API_FORMAT_DATE_TIME, shape = JsonFormat.Shape.STRING)
    private Date logInDateDisplay;
    private boolean isActive;
    private boolean isNotLocked;

}
