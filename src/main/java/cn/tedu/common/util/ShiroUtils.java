package cn.tedu.common.util;
import org.apache.shiro.SecurityUtils;
import cn.tedu.sys.entity.SysUsers;

public class ShiroUtils {public static String getUsername() {
    return getUser().getUsername();
}
    /**s
     * 获取登陆用户信息
     * @retrn
     */
    public static SysUsers getUser() {
        return (SysUsers)SecurityUtils.getSubject().getPrincipal();
    }
}