package com.daxij1.manageboot.framework.util;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/25 20:08
 * @description：AesUtil
 */
public class AesUtil {

    // 自定义密钥
    private static String secretKey = "manage-boot@2023";

    private static AES aes = SecureUtil.aes(DigestUtil.sha256(secretKey.getBytes()));

    public static String encode(String source) {
        byte[] encryptData = aes.encrypt(source);
        return HexUtil.encodeHexStr(encryptData);
    }

    public static String decode(String target) {
        byte[] decryptData = aes.decrypt(HexUtil.decodeHex(target));
        return new String(decryptData);
    }

    public static void main(String[] args) {
        System.out.println(encode("123456"));
    }

}
