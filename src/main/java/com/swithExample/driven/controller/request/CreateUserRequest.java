package com.swithExample.driven.controller.request;


import com.swithExample.driven.util.ParamError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = ParamError.FIELD_NAME)
    private String username;
    @NotBlank(message = ParamError.FIELD_NAME)
    private String email;
    @NotBlank(message = ParamError.FIELD_NAME)
    private String password;
}
