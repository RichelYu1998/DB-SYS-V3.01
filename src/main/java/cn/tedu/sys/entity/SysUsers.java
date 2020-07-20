package cn.tedu.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户(SysUsers)实体类
 *
 * @author makejava
 * @since 2020-07-10 16:56:45
 */
@Data
public class SysUsers implements Serializable {
    private static final long serialVersionUID = 325594955302027637L;

    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐  密码加密时前缀，使加密后的值不同
     */
    private String salt;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 状态  0：禁用   1：正常  默认值 ：1
     */
    private Object valid;

    private Integer deptId;
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
    private String modifiedUser;


}