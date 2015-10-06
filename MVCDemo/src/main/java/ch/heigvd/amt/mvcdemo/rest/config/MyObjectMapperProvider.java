package ch.heigvd.amt.mvcdemo.rest.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Olivier Liechti
 */
@Provider
public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {

  final ObjectMapper defaultObjectMapper;

  public MyObjectMapperProvider() {
    defaultObjectMapper = createDefaultMapper();
  }

  private static ObjectMapper createDefaultMapper() {
    final ObjectMapper result = new ObjectMapper();
    result.enable(SerializationFeature.INDENT_OUTPUT)
      .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
      .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
    return result;
  }

  @Override
  public ObjectMapper getContext(Class<?> type) {
    return defaultObjectMapper;
  }

}
