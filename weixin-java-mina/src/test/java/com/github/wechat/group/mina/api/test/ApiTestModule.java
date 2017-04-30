package com.github.wechat.group.mina.api.test;

import com.github.wechat.group.mina.api.WxMinaConfigStorage;
import com.github.wechat.group.mina.api.WxMinaInMemoryConfigStorage;
import com.github.wechat.group.mina.api.WxMinaService;
import com.github.wechat.group.mina.api.impl.WxMinaServiceImpl;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.thoughtworks.xstream.XStream;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;

import java.io.IOException;
import java.io.InputStream;

public class ApiTestModule implements Module {

  @Override
  public void configure(Binder binder) {
    try (InputStream stream = ClassLoader.getSystemResourceAsStream("test-config.xml")) {
      WxMinaInMemoryConfigStorage config = this.fromXml(WxMinaInMemoryConfigStorage.class, stream);
      WxMinaService wxMinaService = new WxMinaServiceImpl();
      wxMinaService.setWxMinaConfigStorage(config);

      binder.bind(WxMinaService.class).toInstance(wxMinaService);
      binder.bind(WxMinaConfigStorage.class).toInstance(config);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private <T> T fromXml(Class<T> clazz, InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.alias("xml", clazz);
    xstream.processAnnotations(clazz);
    return (T) xstream.fromXML(is);
  }

}
