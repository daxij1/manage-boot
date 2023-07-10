package com.daxij1.manageboot.utils;

import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ：daxij1
 * @date ：Created in 2023/7/10 10:44
 * @description：IPUtils
 */
public class IPUtils {

    public IPUtils() {
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.length() != 0 && ip.indexOf(",") >= 0) {
            String[] array = ip.split(",");

            for(int i = 0; i < array.length; ++i) {
                if (array[i] != null && !"unknown".equalsIgnoreCase(array[i])) {
                    ip = array[i];
                    break;
                }
            }
        }

        if (ip == null) {
            ip = "";
        }

        return ip.trim();
    }

    public static String getRemotePort(HttpServletRequest request) {
        try {
            String port = request.getHeader("Remote_PORT");
            if (StringUtils.isNotEmpty(port)) {
                try {
                    return port;
                } catch (NumberFormatException var3) {
                    port = request.getRemotePort() + "";
                    return StringUtils.isNotEmpty(port) ? port : "0";
                }
            } else {
                port = request.getRemotePort() + "";
                return StringUtils.isNotEmpty(port) ? port : "0";
            }
        } catch (Exception var4) {
            return "0";
        }
    }

    public static Long ipToLong(String ip) {
        try {
            String[] arr = ip.split("\\.");
            return (Long.parseLong(arr[0]) << 24) + (Long.parseLong(arr[1]) << 16) + (Long.parseLong(arr[2]) << 8) + Long.parseLong(arr[3]);
        } catch (Exception var2) {
            return 0L;
        }
    }

    public static String longToIp(long ipLong) {
        String strip = "";
        Long ip1 = ipLong >> 24 & 255L;
        Long ip2 = ipLong >> 16 & 255L;
        Long ip3 = ipLong >> 8 & 255L;
        Long ip4 = ipLong & 255L;
        strip = ip1.toString() + "." + ip2.toString() + "." + ip3.toString() + "." + ip4.toString();
        return strip;
    }
    
}
