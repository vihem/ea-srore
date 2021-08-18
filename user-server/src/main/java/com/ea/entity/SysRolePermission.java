package com.ea.entity;

import lombok.Data;

@Data
public class SysRolePermission {
    private SysPermission permission;
    private SysRole role;
}
