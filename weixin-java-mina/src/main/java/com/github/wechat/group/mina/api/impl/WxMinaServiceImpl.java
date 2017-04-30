package com.github.wechat.group.mina.api.impl;

import com.github.wechat.group.mina.api.WxMinaConfigStorage;
import com.github.wechat.group.mina.api.WxMinaService;
import com.github.wechat.group.mina.bean.WxMinaJsCode2Session;
import jodd.http.HttpConnectionProvider;
import jodd.http.JoddHttp;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.SimpleGetRequestExecutor;

import java.io.IOException;

/**
 * 微信小程序API的Service。
 *
 * @author YuJian (mgcnrx11@hotmail.com) on 2017/4/28.
 * @since 2.7.0
 */
public class WxMinaServiceImpl implements WxMinaService, RequestHttp {

  private WxMinaConfigStorage wxMinaConfigStorage;

  private HttpConnectionProvider httpClient;
  private ProxyInfo httpProxy;

  /**
   * code 换取 session_key 的接口。
   * ​这是一个 HTTPS 接口，开发者服务器使用登录凭证 code 获取 session_key 和 openid。
   * 其中 session_key 是对用户数据进行加密签名的密钥。为了自身应用安全，session_key 不应该在网络上传输。
   *
   * @param code OAuth2认证后获得的code
   * @return WxMinaJsCode2Session中含有openId和sessionKey
   * @throws WxErrorException 请求接口错误时抛出
   */
  @Override
  public WxMinaJsCode2Session getJsCode2SessionKey(String code) throws WxErrorException {
    StringBuilder url = new StringBuilder();
    url.append("https://api.weixin.qq.com/sns/jscode2session?");
    url.append("appid=").append(this.getWxMinaConfigStorage().getAppId());
    url.append("&secret=").append(this.getWxMinaConfigStorage().getSecret());
    url.append("&js_code=").append(code);
    url.append("&grant_type=authorization_code");

    try {
      RequestExecutor<String, String> executor = new SimpleGetRequestExecutor();
      String responseText = executor.execute(this, url.toString(), null);
      return WxMinaJsCode2Session.fromJson(responseText);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  /**
   * 返回httpClient
   *
   * @return Jodd的httpClient
   */
  @Override
  public Object getRequestHttpClient() {
    return this.httpClient;
  }

  /**
   * 返回httpProxy
   *
   * @return Jodd的httpProxy
   */
  @Override
  public Object getRequestHttpProxy() {
    return this.httpProxy;
  }

  @Override
  public void initHttp() {

    WxMinaConfigStorage configStorage = this.getWxMinaConfigStorage();

    // 实际上，jodd的代理设置不能对HTTPS的链接生效，而微信API全部为HTTPS，故下面设置都不能产生效果，只是兼容其他httpClient库而保留。
    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      httpProxy = new ProxyInfo(ProxyInfo.ProxyType.HTTP, configStorage.getHttpProxyHost(), configStorage
        .getHttpProxyPort(), configStorage.getHttpProxyUsername(), configStorage.getHttpProxyPassword());
    }

    httpClient = JoddHttp.httpConnectionProvider;
    httpClient.useProxy(httpProxy);
  }

  private WxMinaConfigStorage getWxMinaConfigStorage() {
    return wxMinaConfigStorage;
  }

  @Override
  public void setWxMinaConfigStorage(WxMinaConfigStorage wxMinaConfigStorage) {
    this.wxMinaConfigStorage = wxMinaConfigStorage;
    this.initHttp();
  }

}
