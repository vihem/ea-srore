package com.ea.mapper;

import com.ea.entity.SysRole;
import org.springframework.stereotype.Component;

@Component
public interface SysRoleMapper {
    SysRole findByRole(String role);
}
