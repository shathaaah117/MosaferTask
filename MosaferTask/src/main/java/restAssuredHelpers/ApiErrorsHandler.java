package restAssuredHelpers;

import org.testng.Assert;

import java.util.ArrayList;

public class ApiErrorsHandler {

  private static int validateMethodParams(ArrayList methodParams) {
    ArrayList validatedParams = new ArrayList();
    for (Object param : methodParams) {
      if (param != null) validatedParams.add(param);
    }
    return validatedParams.size();
  }

  private static ArrayList setParamsInArrayList(Object... args) {
    ArrayList preparedList = new ArrayList();
    for (Object arg : args) {
      preparedList.add(arg);
    }
    return preparedList;
  }

  public static void assertParamsValues(Object... params) {
    Assert.assertEquals(
        validateMethodParams(setParamsInArrayList(params)),
        params.length,
        "There is some passed params have null values, Could you please check them again");
  }
}
