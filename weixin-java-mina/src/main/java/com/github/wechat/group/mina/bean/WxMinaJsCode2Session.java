package com.github.wechat.group.mina.bean;

import com.github.wechat.group.mina.util.json.WxMinaGsonBuilder;

import java.io.Serializable;

/**
 * 微信小程序 code 换取 session_key 的接口返回 bean。
 * session_key 是对用户数据进行加密签名的密钥。为了自身应用安全，session_key 不应该在网络上传输。
 *
 * @author YuJian (mgcnrx11@hotmail.com) on 2017/4/28.
 * @since 2.7.0
 */
public class WxMinaJsCode2Session implements Serializable {

  private static final long serialVersionUID = -1491198100921932155L;

  private String sessionKey;

  private String openId;

  public static WxMinaJsCode2Session fromJson(String json) {
    return WxMinaGsonBuilder.create().fromJson(json, WxMinaJsCode2Session.class);
  }

  public String getSessionKey() {
    return sessionKey;
  }

  public void setSessionKey(String sessionKey) {
    this.sessionKey = sessionKey;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  @Override
  public String toString() {
    return "WxMinaJsCode2Session{" +
      "sessionKey='" + sessionKey + '\'' +
      ", openId='" + openId + '\'' +
      '}';
  }
}
