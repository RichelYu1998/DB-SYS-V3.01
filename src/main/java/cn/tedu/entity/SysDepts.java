package cn.tedu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门管理(SysDepts)实体类
 *
 * @author makejava
 * @since 2020-07-10 16:38:04
 */
public class SysDepts implements Serializable {
    private static final long serialVersionUID = -92234941970643262L;

    private Integer id;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 上级部门
     */
    private Integer parentid;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 备注
     */
    private String note;
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

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
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