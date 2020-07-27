package cn.tedu.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysUserMenuVo implements Serializable {
    private static final long serialVersionUID = 4308484978917379031L;
    private Integer id;
    private String name;
    private String url;
    private List<SysUserMenuVo> childs;
}
