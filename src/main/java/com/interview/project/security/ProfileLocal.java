package com.interview.project.security;

import java.util.List;

public class ProfileLocal {

    private ProfileLocal() {}

    private static final ThreadLocal<UserIdentity> local = new ThreadLocal<>();

    public static void set(UserIdentity userIdentity) {
        local.set(userIdentity);
    }

    public static void clean() { local.remove(); }

    public static String getUserId() {
        var user = local.get();
        if (user == null) {
            return null;
        }
        return user.getSub();
    }
}
