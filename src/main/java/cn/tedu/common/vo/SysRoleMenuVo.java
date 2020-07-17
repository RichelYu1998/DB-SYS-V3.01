package cn.tedu.common.vo;

import java.io.Serializable;
import java.util.List;

public class SysRoleMenuVo implements Serializable {
    private static final long serialVersionUID = -9165076962299838012L;
    private Integer id;
    private String name;
    private String note;
    /**角色对应的菜单 id*/
    private List<Integer> menuIds;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id= id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNote() {
        return note;
    }
    public List<Integer> getMenuIds() {
        return menuIds;
    }
    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }
}
