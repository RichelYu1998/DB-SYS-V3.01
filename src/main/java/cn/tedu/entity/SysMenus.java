package cn.tedu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源管理(SysMenus)实体类
 *
 * @author makejava
 * @since 2020-07-10 16:38:12
 */
public class SysMenus implements Serializable {
    private static final long serialVersionUID = 309787307410686506L;

    private Integer id;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源URL
     */
    private String url;
    /**
     * 类型     1：菜单   2：按钮
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 备注
     */
    private String note;
    /**
     * 父菜单ID，一级菜单为0
     */
    private Integer parentid;
    /**
     * 授权(如：user:create)
     */
    private String permission;
    /**
     * 创建时间
     */
    private Date createdtime;
    /**
     * 修改时间
     */
    private Date modifiedtime;
    /**
     * 创建用户
     */
    private String createduser;
    /**
     * 修改用户
     */
    private String modifieduser;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public Date getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(Date modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public String getCreateduser() {
        return createduser;
    }

    public void setCreateduser(String createduser) {
        this.createduser = createduser;
    }

    public String getModifieduser() {
        return modifieduser;
    }

    public void setModifieduser(String modifieduser) {
        this.modifieduser = modifieduser;
    }

}