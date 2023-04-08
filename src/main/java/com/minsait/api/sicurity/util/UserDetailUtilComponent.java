package com.minsait.api.sicurity.util;

import com.minsait.api.sicurity.details.CustomUserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailUtilComponent {
    /**
     * Classe cirada apenas para facilitar o mock de um método estático no teste unitário
     * */
    public CustomUserDetails getAuthenticatedUser(){
        return UserDetailUtil.getAuthenticatedUser();
    }
}
