package com.github.wechat.group.mina.util.json;

import com.github.wechat.group.mina.bean.WxMinaJsCode2Session;
import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;

import java.lang.reflect.Type;

/**
 * {@link WxMinaJsCode2Session}的反序列化Adapter。
 *
 * @author YuJian (mgcnrx11@hotmail.com) on 2017/4/28.
 * @since 2.7.0
 */
public class WxMinaJsCode2SessionAdapter implements JsonDeserializer<WxMinaJsCode2Session> {

  @Override
  public WxMinaJsCode2Session deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
    JsonParseException {
    WxMinaJsCode2Session jsCode2Session = new WxMinaJsCode2Session();
    JsonObject accessTokenJsonObject = json.getAsJsonObject();

    if (accessTokenJsonObject.get("openid") != null && !accessTokenJsonObject.get("openid").isJsonNull()) {
      jsCode2Session.setOpenId(GsonHelper.getAsString(accessTokenJsonObject.get("openid")));
    }
    if (accessTokenJsonObject.get("session_key") != null && !accessTokenJsonObject.get("session_key").isJsonNull()) {
      jsCode2Session.setSessionKey(GsonHelper.getAsString(accessTokenJsonObject.get("session_key")));
    }
    return jsCode2Session;
  }

}
