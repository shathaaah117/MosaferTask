package async;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.Assert;
import org.modules.hotels.AsyncHandler;
import org.modules.hotels.AutoCompleteApiHandler;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import restAssuredHelpers.ResponseHelper;

public class AsyncTest {
    AsyncHandler asyncApiHandlerObj;
    @BeforeTest
    public void initTest()
    {

        asyncApiHandlerObj=new AsyncHandler();


    }
    @Test(description = "Verify looking for hotel that does not have a location")
    public void testLookingForHotelWithoutSendingPlaceId()
    {
        JsonObject requestBody=new JsonObject();
        String checkInDate=asyncApiHandlerObj.getValidRandomCheckInDate(0);
        requestBody.addProperty("checkIn",checkInDate);
        requestBody.addProperty("checkOut",asyncApiHandlerObj.getValidRandomCheckOutDate(checkInDate,1));
        requestBody.add("roomsInfo",asyncApiHandlerObj.generateRoomsInfo(2,3, 2, 1, 3));
        requestBody.add("searchInfo",null);
        requestBody.add("crossSellDetail",null);
        requestBody.addProperty("query","intercontinental");

        Response response=asyncApiHandlerObj.callAsyncApi(requestBody,asyncApiHandlerObj.getHeaders());
        Assert.assertEquals(ResponseHelper.getStatusCode(response),200);
        Assert.assertNotNull(ResponseHelper.getResponseBodyByKey(response,"sId"));
    }

    @Test(description = "Verify looking for hotel that does have a location")
    public void testLookingForHotelBySendingPlaceId()
    {
        AutoCompleteApiHandler autoCompleteApiHandler=new AutoCompleteApiHandler();
        Response autoCompleteResponse= autoCompleteApiHandler.callAutoCompleteApi("Sher",autoCompleteApiHandler.getTokenHeaders());
        String placeId= String.valueOf(ResponseHelper.getResponseBodyByKey(autoCompleteResponse,"locations[0].placeId"));

        JsonObject requestBody=new JsonObject();
        String checkInDate=asyncApiHandlerObj.getValidRandomCheckInDate(0);
        requestBody.addProperty("checkIn",checkInDate);
        requestBody.addProperty("checkOut",asyncApiHandlerObj.getValidRandomCheckOutDate(checkInDate,1));
        requestBody.add("roomsInfo",asyncApiHandlerObj.generateRoomsInfo(2,3, 2, 1, 3));
        requestBody.add("searchInfo",null);
        requestBody.add("crossSellDetail",null);
        requestBody.addProperty("query","Sher");
        requestBody.addProperty("placeId",placeId);

        Response response=asyncApiHandlerObj.callAsyncApi(requestBody,asyncApiHandlerObj.getHeaders());
        Assert.assertEquals(ResponseHelper.getStatusCode(response),200);
        Assert.assertNotNull(ResponseHelper.getResponseBodyByKey(response,"sId"));
    }

    @Test(description = "Verify looking for hotel without sending people details")
    public void testLookingForHotelWithoutSendingPeopleDetails()
    {
        JsonObject requestBody=new JsonObject();
        String checkInDate=asyncApiHandlerObj.getValidRandomCheckInDate(0);
        requestBody.addProperty("checkIn",checkInDate);
        requestBody.addProperty("checkOut",asyncApiHandlerObj.getValidRandomCheckOutDate(checkInDate,1));
        requestBody.add("searchInfo",null);
        requestBody.add("crossSellDetail",null);
        requestBody.addProperty("query","intercontinental");

        Response response=asyncApiHandlerObj.callAsyncApi(requestBody,asyncApiHandlerObj.getHeaders());
        Assert.assertEquals(ResponseHelper.getStatusCode(response),200);
        Assert.assertNotNull(ResponseHelper.getResponseBodyByKey(response,"sId"));
    }

    @Test(description = "Verify looking for hotel by sending the same check-in and check-out")
    public void testLookingForHotelBySendingCheckInAndOutSameDate()
    {
        JsonObject requestBody=new JsonObject();
        String checkInDate=asyncApiHandlerObj.getValidRandomCheckInDate(0);
        requestBody.addProperty("checkIn",checkInDate);
        requestBody.addProperty("checkOut",asyncApiHandlerObj.getValidRandomCheckOutDate(checkInDate,0));
        requestBody.add("searchInfo",null);
        requestBody.add("crossSellDetail",null);
        requestBody.addProperty("query","intercontinental");

        Response response=asyncApiHandlerObj.callAsyncApi(requestBody,asyncApiHandlerObj.getHeaders());
        Assert.assertEquals(ResponseHelper.getStatusCode(response),400);
        Assert.assertNull(ResponseHelper.getResponseBodyByKey(response,"sId"));
    }

    @Test(description = "Verify looking for hotel without sending the query")
    public void testLookingForHotelWithoutPassingQuery()
    {
        JsonObject requestBody=new JsonObject();
        String checkInDate=asyncApiHandlerObj.getValidRandomCheckInDate(0);
        requestBody.addProperty("checkIn",checkInDate);
        requestBody.addProperty("checkOut",asyncApiHandlerObj.getValidRandomCheckOutDate(checkInDate,1));
        requestBody.add("searchInfo",null);
        requestBody.add("crossSellDetail",null);

        Response response=asyncApiHandlerObj.callAsyncApi(requestBody,asyncApiHandlerObj.getHeaders());
        Assert.assertEquals(ResponseHelper.getStatusCode(response),400);
        Assert.assertNull(ResponseHelper.getResponseBodyByKey(response,"sId"));
    }


}
