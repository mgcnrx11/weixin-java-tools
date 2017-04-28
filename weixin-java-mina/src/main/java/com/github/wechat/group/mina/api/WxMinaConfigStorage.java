package com.github.wechat.group.mina.api;

/**
 * 微信小程序配置信息的存储。
 *
 * @author YuJian (mgcnrx11@hotmail.com) on 2017/4/28.
 * @since 2.7.0
 */
public interface WxMinaConfigStorage {

  String getAppId();

  String getSecret();

}
