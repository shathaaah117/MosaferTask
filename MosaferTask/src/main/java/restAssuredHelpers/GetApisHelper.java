package restAssuredHelpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;

import static org.modules.helpers.config.ConfigReader.readConfigFromCommonProp;
import static restAssuredHelpers.ApiErrorsHandler.assertParamsValues;
import static restAssuredHelpers.ApisConstants.BASE_URL;

public class GetApisHelper {


  /**
   * @param apiUrl
   * @param headers
   * @param queryParams
   * @return
   */
  public static Response getByHeadersAndQueryParams(
      String apiUrl, HashMap<String, String> headers, HashMap<String, String> queryParams) {
    assertParamsValues(apiUrl, headers, queryParams);

    return RestAssured.given()
        .baseUri(readConfigFromCommonProp(BASE_URL))
        .that()
        .headers(headers)
        .params(queryParams)
        .when()
        .get(apiUrl)
        .thenReturn();
  }

}
