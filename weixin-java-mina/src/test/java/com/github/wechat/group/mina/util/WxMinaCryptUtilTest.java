package com.github.wechat.group.mina.util;

import com.github.wechat.group.mina.util.crypt.WxMinaCryptUtil;
import me.chanjar.weixin.common.util.crypto.SHA1;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by YuJian on 2017/5/1.
 *
 * @author YuJian
 * @version 2017/5/1
 */
@Test
public class WxMinaCryptUtilTest {

  /**
   * 数据解密
   */
  public void testMinaDecrypt() {
    String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";
    String encryptedData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZM"+
      "QmRzooG2xrDcvSnxIMXFufNstNGTyaGS"+
      "9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+"+
      "3hVbJSRgv+4lGOETKUQz6OYStslQ142d"+
      "NCuabNPGBzlooOmB231qMM85d2/fV6Ch"+
      "evvXvQP8Hkue1poOFtnEtpyxVLW1zAo6"+
      "/1Xx1COxFvrc2d7UL/lmHInNlxuacJXw"+
      "u0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn"+
      "/Hz7saL8xz+W//FRAUid1OksQaQx4CMs"+
      "8LOddcQhULW4ucetDf96JcR3g0gfRK4P"+
      "C7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB"+
      "6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns"+
      "/8wR2SiRS7MNACwTyrGvt9ts8p12PKFd"+
      "lqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYV"+
      "oKlaRv85IfVunYzO0IKXsyl7JCUjCpoG"+
      "20f0a04COwfneQAGGwd5oa+T8yO5hzuy"+
      "Db/XcxxmK01EpqOyuxINew==";
    String iv = "r7BXXKkLb8qrSNn05n0qiA==";
    String original = WxMinaCryptUtil.decryptData(sessionKey, encryptedData, iv);
    assertEquals(original, "{\"openId\":\"oGZUI0egBJY1zhBYw2KhdUfwVJJE\",\"nickName\":\"Band\",\"gender\":1," +
      "\"language\":\"zh_CN\",\"city\":\"Guangzhou\",\"province\":\"Guangdong\",\"country\":\"CN\"," +
      "\"avatarUrl\":\"http://wx.qlogo" +
      ".cn/mmopen/vi_32/aSKcBBPpibyKNicHNTMM0qJVh8Kjgiak2AHWr8MHM4WgMEm7GFhsf8OYrySdbvAMvTsw3mo8ibKicsnfN5pRjl1p8HQ/0" +
      "\",\"unionId\":\"ocMvos6NjeKLIBqg5Mr9QjxrP1FA\",\"watermark\":{\"timestamp\":1477314187," +
      "\"appid\":\"wx4f4bc4dec97d474b\"}}");
    System.out.println(original);
  }

  /**
   * 数据签名
   */
  public void testMinaSignature() {
    String rawData = "{\"nickName\":\"Band\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"Guangzhou\"," +
      "\"province\":\"Guangdong\",\"country\":\"CN\",\"avatarUrl\":\"http://wx.qlogo" +
      ".cn/mmopen/vi_32/1vZvI39NWFQ9XM4LtQpFrQJ1xlgZxx3w7bQxKARol6503Iuswjjn6nIGBiaycAjAtpujxyzYsrztuuICqIM5ibXQ/0\"}";

    String signature = "75e81ceda165f4ffa64f4068af58c64b8f54b88c";

    String sessionKey = "HyVFkGl5F5OQWJZZaNzBBg==";

    // SHA1.gen默认按字母顺序排序了，因此调用时需要在手动拼接字符串再传进去
    assertEquals(WxMinaCryptUtil.checkSignature(signature, rawData, sessionKey), true);
  }
}
