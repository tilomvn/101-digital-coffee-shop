package com.interview.project.security;

import java.util.List;

public class UserIdentity {

    String sub;
    List<SystemRole> roleList;
    String roles;
    String jwtToken;
    String refreshToken;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public List<SystemRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SystemRole> roleList) {
        this.roleList = roleList;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
