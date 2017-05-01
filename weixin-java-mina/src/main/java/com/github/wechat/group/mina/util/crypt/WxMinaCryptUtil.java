package com.github.wechat.group.mina.util.crypt;

import me.chanjar.weixin.common.util.crypto.PKCS7Encoder;
import me.chanjar.weixin.common.util.crypto.SHA1;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * 微信小程序的签名及解密工具类。
 *
 * @author YuJian (mgcnrx11@hotmail.com) on 2017/5/1.
 * @since 2.7.0
 */
public class WxMinaCryptUtil {

  /**
   * 接口如果涉及敏感数据（如wx.getUserInfo当中的 openId 和unionId ），接口的明文内容将不包含这些敏感数据。
   * 开发者如需要获取敏感数据，需要对接口返回的加密数据( encryptedData )进行对称解密。 解密算法如下：
   *
   * 1.对称解密使用的算法为 AES-128-CBC，数据采用PKCS#7填充。
   * 2.对称解密的目标密文为 Base64_Decode(encryptedData)。
   * 3.对称解密秘钥 aeskey = Base64_Decode(session_key), aeskey 是16字节。
   * 4.对称解密算法初始向量 为Base64_Decode(iv)，其中iv由数据接口返回。
   *
   * @param sessionKey 算法中的session_key，wx.login()后获得
   * @param encryptedData 密文
   * @param ivString 解密算法初始向量
   * @return 原文字符串
   */
  public static String decryptData(String sessionKey, String encryptedData, String ivString) {
    byte[] original;
    try {
      // 设置解密模式为AES的CBC模式
      Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
      SecretKeySpec key_spec = new SecretKeySpec(Base64.decodeBase64(sessionKey), "AES");
      IvParameterSpec iv = new IvParameterSpec(Base64.decodeBase64(ivString));
      cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);

      // 使用BASE64对密文进行解码
      byte[] encrypted = Base64.decodeBase64(encryptedData);

      // 解密
      original = cipher.doFinal(encrypted);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    // 去除补位字符
    byte[] bytes = PKCS7Encoder.decode(original);

    return new String(bytes, StandardCharsets.UTF_8);
  }

  /**
   * 为了确保 开放接口 返回用户数据的安全性，微信会对明文数据进行签名。开发者可以根据业务需要对数据包进行签名校验，确保数据的完整性。
   *
   * @param signature 接口数据返回的签名
   * @param rawData 原始数据
   * @param sessionKey wx.login()后获得的sessionKey
   * @return 是否正确
   */
  public static boolean checkSignature(String signature, String rawData, String sessionKey) {
    return signature.equals(SHA1.gen(rawData + sessionKey));
  }
}
