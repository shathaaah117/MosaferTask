package restAssuredHelpers;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;

import static org.modules.helpers.config.ConfigReader.readConfigFromCommonProp;
import static restAssuredHelpers.ApiErrorsHandler.assertParamsValues;
import static restAssuredHelpers.ApisConstants.BASE_URL;


public class PostApisHelper {

  /**
   * @param apiUrl
   * @param headers
   * @param requestBody
   * @return
   */
  public static Response postByHeaders(
      String apiUrl, HashMap<String, String> headers, JsonObject requestBody) {
    assertParamsValues(apiUrl, headers, requestBody);

    return RestAssured.given()
            .baseUri(readConfigFromCommonProp(BASE_URL))
            .that()
            .headers(headers)
            .body(String.valueOf(requestBody))
            .when()
            .post(apiUrl)
            .thenReturn();

  }


}
