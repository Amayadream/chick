package com.amayadream.chick.web.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author : Amayadream
 * @date :   2017-09-18 15:48
 */
public class PathUtils {

    /**
     * 获取纯净的中间路径, /path/ -> path
     * @param path 中间路径
     * @return
     */
    public static String pure(String path) {
        if (StringUtils.isEmpty(path)) {
            return "";
        }
        if (path.startsWith("/")) {
            path = path.substring(1, path.length());
        }
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    /**
     * 拼接路径
     * @param path
     * @param appendPath
     * @return
     */
    public static String append(String path, String appendPath) {
        return PathUtils.pure(path) + "/" + PathUtils.pure(appendPath);
    }

}
