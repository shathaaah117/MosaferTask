package org.modules.hotels;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import restAssuredHelpers.PostApisHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import static org.modules.helpers.ModulesEndpoints.ASYNC_API;
import static org.modules.helpers.config.ConfigReader.readConfigFromCommonProp;
import static restAssuredHelpers.ApisConstants.CONTENT_TYPE;
import static restAssuredHelpers.ApisConstants.TOKEN;


public class AsyncHandler {

    public Response callAsyncApi(JsonObject requestBody, HashMap<String, String> headers)
    {
        return PostApisHelper.postByHeaders(ASYNC_API,headers,requestBody);
    }
    public String getValidRandomCheckInDate(int bound)
    {
        LocalDate localDate=LocalDate.now().plusDays(bound+1);
        return DateTimeFormatter.ofPattern("YYYY-MM-dd").format(localDate);
    }

    public String getValidRandomCheckOutDate(String checkinDate, int bound)
    {
        LocalDate checkInDay= LocalDate.parse(checkinDate);
        LocalDate localDate=LocalDate.of(checkInDay.getYear(), checkInDay.getMonth(), checkInDay.getDayOfMonth()+bound);
        return DateTimeFormatter.ofPattern("YYYY-MM-dd").format(localDate);
    }

    public JsonArray generateRoomsInfo(int adultsCount, int noOfKids, int... ages)
    {
        JsonArray roomInfo=new JsonArray();
        JsonObject roomPeople=new JsonObject();
        JsonArray kidsAges=new JsonArray();
        roomPeople.addProperty("adultsCount", adultsCount);
        if(noOfKids>0)
        {
            for(int i=0;i<ages.length;i++)
            {
                kidsAges.add(ages[i]);
            }
        }
        roomPeople.add("kidsAges", kidsAges);
        roomInfo.add(roomPeople);
        return roomInfo;
    }

//    public static void main(String args[])
//    {
//        int[] kidsAges= {3,5};
//        System.out.println(generateRoomsInfo(2,4, kidsAges));
//    }

public HashMap<String, String> getHeaders()
{
    HashMap headers=new HashMap<>();
   headers.put("content-type",CONTENT_TYPE);
   headers.put("token",readConfigFromCommonProp(TOKEN));
   return headers;
}
}
