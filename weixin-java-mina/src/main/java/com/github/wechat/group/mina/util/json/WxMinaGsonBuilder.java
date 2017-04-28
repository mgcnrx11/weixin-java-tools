package com.github.wechat.group.mina.util.json;

import com.github.wechat.group.mina.bean.WxMinaJsCode2Session;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * GSON注册器。
 *
 * @author YuJian (mgcnrx11@hotmail.com) on 2017/4/28.
 * @since 2.7.0
 */
public class WxMinaGsonBuilder {

  public static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxMinaJsCode2Session.class, new WxMinaJsCode2SessionAdapter());
  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
