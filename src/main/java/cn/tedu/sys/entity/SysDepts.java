package cn.tedu.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门管理(SysDepts)实体类
 *
 * @author makejava
 * @since 2020-07-10 16:56:42
 */
@Data
public class SysDepts implements Serializable {
    private static final long serialVersionUID = -59358674543264217L;
    private Integer id;
    private String name;
    private Integer parentId;
    private Integer sort;
    private String note;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
}