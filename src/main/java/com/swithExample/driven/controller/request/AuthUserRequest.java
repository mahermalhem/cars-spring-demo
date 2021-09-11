package com.swithExample.driven.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.swithExample.driven.util.ParamError;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthUserRequest {
    @NotBlank(message = ParamError.FIELD_NAME)
    private String username;
    @NotBlank(message = ParamError.FIELD_NAME)
    private String password;

}
