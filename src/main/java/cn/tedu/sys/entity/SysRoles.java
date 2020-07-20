package cn.tedu.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色(SysRoles)实体类
 *
 * @author makejava
 * @since 2020-07-10 16:56:44
 */
@Data
public class SysRoles implements Serializable {
    private static final long serialVersionUID = 715140585017166930L;
    private Integer id;
    private String name;
    private String note;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
}