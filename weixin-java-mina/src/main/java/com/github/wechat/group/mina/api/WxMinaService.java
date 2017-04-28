package com.github.wechat.group.mina.api;

import com.github.wechat.group.mina.bean.WxMinaJsCode2Session;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * 微信小程序API的Service。
 *
 * @author YuJian (mgcnrx11@hotmail.com) on 2017/4/28.
 * @since 2.7.0
 */
public interface WxMinaService {

  /**
   * code 换取 session_key 的接口。
   * ​ 这是一个 HTTPS 接口，开发者服务器使用登录凭证 code 获取 session_key 和 openid。
   * 其中 session_key 是对用户数据进行加密签名的密钥。为了自身应用安全，session_key 不应该在网络上传输。
   *
   * @param code OAuth2认证后获得的code
   * @return WxMinaJsCode2Session中含有openId和sessionKey
   * @throws WxErrorException 请求接口错误时抛出
   */
  WxMinaJsCode2Session getJsCode2SessionKey(String code) throws WxErrorException;

}
