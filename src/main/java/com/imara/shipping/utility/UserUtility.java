package com.imara.shipping.utility;

import com.imara.shipping.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtility {

    public static User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null)
            return null;
        Object userObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(userObj instanceof User))
            return null;
        return (User) userObj;
    }

    public static long getCurrentUserId() {
        User currentUser = getCurrentUser();
        if (currentUser == null)
            return 0;
        else
            return currentUser.getId();
    }
}
