package cn.tedu.common.util;

public class AssertUtil {
    public static void isArgsValid(Boolean statement, String msg) {
        if (statement)
            throw new IllegalArgumentException(msg);
    }
}
