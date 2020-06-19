
package com.general;

/*import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;*/

import java.io.IOException;

public class JsonUtils {

  public static void main(String[] args) throws IOException {

/*

    ObjectMapper objectMapper = new ObjectMapper();
    BaseClientDetails clientDetails = objectMapper.readValue(new File("/Users/Pushparaj/Desktop"
            + "/code/etc"
            + "/testJson.json"),
        BaseClientDetails.class);
    Set<String> redirectUris = new HashSet<>();
    redirectUris.add("http://localhost:2380");
    clientDetails.setRegisteredRedirectUri(redirectUris);
    String jsonString = objectMapper.writeValueAsString(clientDetails);
    BaseClientDetails reConstructed = objectMapper.readValue(jsonString,BaseClientDetails.class);

    objectMapper.writeValue(new File("/Users/Pushparaj/Desktop/code/etc/testJson.json"),
        reConstructed);

    BaseClientDetails clientDetailsAfterUpdate = objectMapper.readValue(new File("/Users/Pushparaj"
            + "/Desktop/code/etc/testJson.json"), BaseClientDetails.class);
*/

    System.out.println("hello");
  }

}
