package com.bjsxt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {
    private Long id;
    private String name;
    private String url;
    /*父菜单主键*/
    private Long parentId;
    /*权限*/
    private String permission;
}
