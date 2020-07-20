package cn.tedu.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源管理(SysMenus)实体类
 *
 * @author makejava
 * @since 2020-07-10 16:56:43
 */
@Data
public class SysMenus implements Serializable {
    private static final long serialVersionUID = 808370860595569121L;

    private Integer id;
    /**菜单名称*/
    private String name;
    /**菜单 url: log/doFindPageObjects*/
    private String url;
    /**菜单类型(两种:按钮,普通菜单)*/
    private Integer type=1;
    /**排序(序号)*/
    private Integer sort;
    /**备注*/
    private String note;
    /**上级菜单 id*/
    private Integer parentId;
    /**菜单对应的权限标识(sys:log:delete)*/
    private String permission;
}