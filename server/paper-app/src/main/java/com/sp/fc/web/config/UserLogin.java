package com.sp.fc.web.config;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLogin {
    private String username;
    private String password;
    private String site;
    private boolean rememberme;
}
