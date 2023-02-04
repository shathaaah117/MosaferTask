package restAssuredHelpers;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ResponseHelper {

  public static int getStatusCode(Response response) {
    return response.getStatusCode();
  }

  public static Object getResponseBodyByKey(Response response, String key) {
    return response.getBody().jsonPath().get(key);
  }
}
