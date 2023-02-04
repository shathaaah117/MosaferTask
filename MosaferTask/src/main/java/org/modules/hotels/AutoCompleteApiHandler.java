package org.modules.hotels;
import io.restassured.response.Response;
import restAssuredHelpers.GetApisHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.modules.helpers.ModulesEndpoints.AUTOCOMPLETE_API;
import static org.modules.helpers.config.ConfigReader.readConfigFromCommonProp;
import static restAssuredHelpers.ApisConstants.TOKEN;

public class AutoCompleteApiHandler {

    public Response callAutoCompleteApi(String query, HashMap<String, String> headers)
    {
        HashMap<String, String>queryParams=new HashMap<>();
        queryParams.put("query",query);
        return GetApisHelper.getByHeadersAndQueryParams(AUTOCOMPLETE_API, headers,queryParams);
    }
    private boolean checkLocationComponent(LinkedHashMap location)
    {
        return location.containsKey("name")&&
         location.containsKey("city")&&
         location.containsKey("country")&&
         location.containsKey("displayType")&&
         location.containsKey("placeId")&&
         location.containsKey("source")&&
         location.containsKey("googleType");
    }

    private boolean checkHotelComponent(LinkedHashMap hotel)
    {
        return hotel.containsKey("hotelId") &&
                hotel.containsKey("name")&&
                hotel.containsKey("city")&&
                hotel.containsKey("country")&&
                hotel.containsKey("countryCode")&&
                hotel.containsKey("displayType")&&
                hotel.containsKey("thumbnail_url")&&
                hotel.containsKey("latitude")&&
                hotel.containsKey("longitude")&&
                hotel.containsKey("isActive");
    }

    public boolean verifyLocationsObjects(ArrayList<LinkedHashMap> locations)
    {
        int locationsLength=locations.size();
        int countOfValidLocations=0;
        for(LinkedHashMap location:locations)
        {
            if (checkLocationComponent(location))
                countOfValidLocations++;
        }
        return locationsLength==countOfValidLocations;
    }

    public boolean verifyHotelsObjects(ArrayList<LinkedHashMap> hotels)
    {
        int hotelsSize=hotels.size();
        int countOfValidHotels=0;
        for(LinkedHashMap hotel:hotels)
        {
            if (checkHotelComponent(hotel))
                countOfValidHotels++;
        }
        return hotelsSize==countOfValidHotels;
    }


    public HashMap getTokenHeaders()
    {
        HashMap<String, String> headers=new HashMap();
        headers.put("token", readConfigFromCommonProp(TOKEN));
        return headers;
    }

}