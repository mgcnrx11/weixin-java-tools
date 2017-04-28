package com.github.wechat.group.mina.api;

/**
 * 微信小程序配置信息的存储。
 *
 * @author YuJian (mgcnrx11@hotmail.com) on 2017/4/28.
 * @since 2.7.0
 */
public class WxMinaInMemoryConfigStorage implements WxMinaConfigStorage {

  protected volatile String appId;
  protected volatile String secret;

  @Override
  public String getAppId() {
    return this.appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  @Override
  public String getSecret() {
    return this.secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }
}
