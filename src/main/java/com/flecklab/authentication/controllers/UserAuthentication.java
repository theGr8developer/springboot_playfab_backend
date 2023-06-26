package com.flecklab.authentication.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.playfab.PlayFabClientAPI;
import com.playfab.PlayFabDataAPI;
import com.playfab.PlayFabSettings;
import com.playfab.PlayFabClientModels.LoginResult;
import com.playfab.PlayFabClientModels.LoginWithEmailAddressRequest;
import com.playfab.PlayFabClientModels.RegisterPlayFabUserRequest;
import com.playfab.PlayFabClientModels.RegisterPlayFabUserResult;
import com.playfab.PlayFabDataModels.EntityKey;
import com.playfab.PlayFabDataModels.GetObjectsRequest;
import com.playfab.PlayFabDataModels.GetObjectsResponse;
import com.playfab.PlayFabErrors.PlayFabResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// 
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
class UserCredentials{
    private String Email;
    private String Password;
    private String TitleId = "6FAC5";
}

@RestController
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserAuthentication {

    
    private PlayFabClientAPI client = new PlayFabClientAPI();
    
/*
 * This function calls for user login 
 */
    @PostMapping("/login")
    public ResponseEntity<PlayFabResult<LoginResult>> Login(@RequestBody UserCredentials userCredentials){
        LoginWithEmailAddressRequest request = new LoginWithEmailAddressRequest();
        request.Email = userCredentials.getEmail();
        request.Password = userCredentials.getPassword();
        request.TitleId = userCredentials.getTitleId();
        System.out.println(request.Email);
        System.out.println(request.Password);
        System.out.println(request.TitleId);

        PlayFabResult<LoginResult> loginResult = client.LoginWithEmailAddress(request);
        try {

            System.out.println(loginResult.Result.PlayFabId);
            System.out.println(loginResult.Result);

            
            if(loginResult.Result!=null){
                
                return ResponseEntity.ok(loginResult);

            }
            else{

                return ResponseEntity.ok(loginResult);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
         return ResponseEntity.ok(loginResult);
    }
    
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCredentials credentials) {

            RegisterPlayFabUserRequest request = new RegisterPlayFabUserRequest();
           
            request.Email = credentials.getEmail();
            request.Password = credentials.getPassword();
            request.TitleId=credentials.getTitleId();
            request.RequireBothUsernameAndEmail=false;

        try {
            PlayFabResult<RegisterPlayFabUserResult> registerResult = client.RegisterPlayFabUser(request);
            
            if(registerResult.Result!=null){
                
                return ResponseEntity.ok().body("Registered user successfully");
            }
            else{

                return ResponseEntity.ok().body(registerResult.Error.errorMessage);
            }
        }
        catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");

        }
    }

   

}
