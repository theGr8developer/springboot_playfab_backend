package com.flecklab.authentication.controllers;



import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.flecklab.authentication.entity.LocationEntity;
import com.flecklab.authentication.entity.Position;
import com.flecklab.authentication.entity.WeatherEntity;
import com.playfab.PlayFabClientAPI;
import com.playfab.PlayFabDataAPI;
import com.playfab.PlayFabDataModels.EntityKey;
import com.playfab.PlayFabDataModels.GetObjectsRequest;
import com.playfab.PlayFabDataModels.GetObjectsResponse;
import com.playfab.PlayFabDataModels.SetObject;
import com.playfab.PlayFabDataModels.SetObjectsRequest;
import com.playfab.PlayFabDataModels.SetObjectsResponse;
import com.playfab.PlayFabErrors.PlayFabResult;
import com.playfab.PlayFabSettings;




@RestController
public class HomeController {

    PlayFabClientAPI client = new PlayFabClientAPI();

    
    RestTemplate restTemplate = new RestTemplate();
    LocationEntity locationEntity;
    WeatherEntity weatherEntity;

    
    String ipstackApiKey = "044936b0cc76b77f664e5c97d9b79ea8";
    String weatherstackApiKey = "b0e379b1b5b12141a4d0851a02474fc0";
    String ipstackApiUrl; 
    String weatherstackApiUrl;

    @PostMapping("getweather")
    ResponseEntity<String> GetWeather(@RequestBody Position position){
        System.out.println("request comming");
        weatherstackApiUrl = "http://api.weatherstack.com/current?access_key="+weatherstackApiKey+"&query="+position.getLatitude()+","+position.getLongitude();
         String result = restTemplate.getForObject(weatherstackApiUrl, String.class);
         System.out.println(result);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/update-temperature")
    public ResponseEntity<PlayFabResult> UpdateTemperature(){
        PlayFabSettings.TitleId = "6FAC5";
        PlayFabDataAPI clienDataAPI = new PlayFabDataAPI();
        SetObjectsRequest request = new SetObjectsRequest();
         GetObjectsRequest getRequest = new GetObjectsRequest();

        EntityKey entityKey = new EntityKey();
        entityKey.Id = "7F2654DADF7358B1";
        entityKey.Type = "title_player_account";
        request.Entity = entityKey;
        getRequest.Entity = entityKey;

        PlayFabResult<GetObjectsResponse> getResult = new PlayFabResult<>();
        getResult = clienDataAPI.GetObjects(getRequest);
        
        SetObject obj = new SetObject();
        obj.ObjectName = "Temperature";
        ArrayList temp = new ArrayList<>();
        ArrayList prevTemp = new ArrayList<>();
        
        prevTemp.add(getResult.Result.Objects.get("Temperature").DataObject);
        for(int i = 0; i < prevTemp.size(); i++){
            System.out.println("array->> "+prevTemp.get(i));
            temp.add(prevTemp.indexOf(i));
        }
        
        temp.add("500");
        temp.add("800");
        obj.DataObject =temp;
         ArrayList<SetObject>objects = new ArrayList<>();
        objects.add(obj);
        request.Objects = objects;
         PlayFabResult<SetObjectsResponse>result = new PlayFabResult<>();
        result = clienDataAPI.SetObjects(request);
        System.out.println(result.Result);
        return ResponseEntity.ok(result);
    }

     @GetMapping("/temperature")
     ResponseEntity<PlayFabResult> storeTemeperature(){
        PlayFabDataAPI clienDataAPI = new PlayFabDataAPI();
        GetObjectsRequest request = new GetObjectsRequest();

        PlayFabSettings.TitleId = "6FAC5";
        EntityKey entityKey = new EntityKey();
        entityKey.Id = "7F2654DADF7358B1";
        entityKey.Type = "title_player_account";
        request.Entity = entityKey;
       System.out.println("temperature api is calling");
        PlayFabResult<GetObjectsResponse> result = new PlayFabResult<>();
        result = clienDataAPI.GetObjects(request);

        return ResponseEntity.ok(result);
    }
}
