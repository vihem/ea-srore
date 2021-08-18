package com.ea.entity;

import lombok.Data;

@Data
public class SysUserRole {
    private User user;
    private SysRole role;
}
