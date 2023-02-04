package autoComplete;

import io.restassured.response.Response;
import org.junit.Assert;
import org.modules.hotels.AutoCompleteApiHandler;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import restAssuredHelpers.ResponseHelper;
import java.util.ArrayList;

public class AutoCompleteTest {
    AutoCompleteApiHandler autoCompleteApiHandlerObj;
    @BeforeTest
    public void initTest()
    {
        autoCompleteApiHandlerObj=new AutoCompleteApiHandler();
    }
    @Test(description = "Verify Sending Empty hotel name")
    public void testSendingEmptyQuery()
    {
        Response response= autoCompleteApiHandlerObj.callAutoCompleteApi("",autoCompleteApiHandlerObj.getTokenHeaders());
        Assert.assertEquals(ResponseHelper.getStatusCode(response),400);
        Assert.assertNull(ResponseHelper.getResponseBodyByKey(response,"locations"));
        Assert.assertNull(ResponseHelper.getResponseBodyByKey(response,"hotels"));
    }

    @Test(description = "Verify Sending valid hotel name that has no locations")
    public void testSendingValidQuery()
    {
        Response response= autoCompleteApiHandlerObj.callAutoCompleteApi("intercontinental",autoCompleteApiHandlerObj.getTokenHeaders());
        Assert.assertEquals(ResponseHelper.getStatusCode(response),200);
        Assert.assertNotNull(ResponseHelper.getResponseBodyByKey(response,"locations"));
        ArrayList locations= (ArrayList) ResponseHelper.getResponseBodyByKey(response,"locations");
        Assert.assertTrue(autoCompleteApiHandlerObj.verifyLocationsObjects(locations));
        Assert.assertNotNull(ResponseHelper.getResponseBodyByKey(response,"hotels"));
        ArrayList hotels= (ArrayList) ResponseHelper.getResponseBodyByKey(response,"hotels");
        Assert.assertTrue(hotels.size()>0);
        Assert.assertTrue(autoCompleteApiHandlerObj.verifyHotelsObjects(hotels));
    }

    @Test(description = "Verify Sending valid query that fits in hotels and locations")
    public void testSendingValidQueryForHotelsAndLocations()
    {
        Response response= autoCompleteApiHandlerObj.callAutoCompleteApi("Sher",autoCompleteApiHandlerObj.getTokenHeaders());
        org.junit.Assert.assertEquals(ResponseHelper.getStatusCode(response),200);
        org.junit.Assert.assertNotNull(ResponseHelper.getResponseBodyByKey(response,"locations"));
        ArrayList locations= (ArrayList) ResponseHelper.getResponseBodyByKey(response,"locations");
        org.junit.Assert.assertTrue(locations.size()>0);
        org.junit.Assert.assertTrue(autoCompleteApiHandlerObj.verifyLocationsObjects(locations));
        org.junit.Assert.assertNotNull(ResponseHelper.getResponseBodyByKey(response,"hotels"));
        ArrayList hotels= (ArrayList) ResponseHelper.getResponseBodyByKey(response,"hotels");
        org.junit.Assert.assertTrue(hotels.size()>0);
        org.junit.Assert.assertTrue(autoCompleteApiHandlerObj.verifyHotelsObjects(hotels));
    }

}
