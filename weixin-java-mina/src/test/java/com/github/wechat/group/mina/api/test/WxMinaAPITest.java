package com.github.wechat.group.mina.api.test;

import com.github.wechat.group.mina.api.WxMinaService;
import com.github.wechat.group.mina.bean.WxMinaJsCode2Session;
import com.google.inject.Inject;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 基础API测试
 *
 * @author YuJian (mgcnrx11@hotmail.com) on 2017/4/29.
 * @since 2.7.0
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMinaAPITest {

  private final Logger log = LoggerFactory.getLogger(WxMinaAPITest.class);

  @Inject
  private WxMinaService wxMinaService;

  /**
   * 测试code值为空的情况
   */
  public void testMissingCode() {
    try {
      WxMinaJsCode2Session session = testJsCode2Session("");
      Assert.assertNull(session);
    } catch (WxErrorException e) {
      Assert.assertEquals(e.getError().getErrorCode(), 41008);
    }
  }

  /**
   * 测试code值已经使用的情况
   */
  public void testUsedCode() {
    try {
      WxMinaJsCode2Session session = testJsCode2Session("031SyXCg0rIwuB10M7Fg0cH3Dg0SyXCV");
      Assert.assertNull(session);
    } catch (WxErrorException e) {
      Assert.assertEquals(e.getError().getErrorCode(), 40163);
    }
  }

  /**
   * 调用接口获取登录凭证（code）进而换取用户登录态信息。
   * code值需要通过微信web开发者工具调试获得后，手动注入。因此不能进行正确code值的单元测试。
   * @param code code通过wx.login()获得，手动填写
   * @throws WxErrorException 请求失败抛出异常
   */
  private WxMinaJsCode2Session testJsCode2Session(String code) throws WxErrorException {
    WxMinaJsCode2Session session = wxMinaService.getJsCode2SessionKey(code);
    log.info(session.toString());
    return session;
  }

}
