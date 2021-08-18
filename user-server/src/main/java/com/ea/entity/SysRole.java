package com.ea.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 */
@Data
public class SysRole implements Serializable {
    private Integer rid; // 编号
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户
    private List<SysPermission> permissions;// 角色和权限关系
    private List<User> userInfos;//用户 - 角色关系定义; 一个角色对应多个用户

}
