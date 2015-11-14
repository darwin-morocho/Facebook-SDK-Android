package com.futuroinginiero.dsmr;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {


    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        final TextView resultado=(TextView)findViewById(R.id.key);



        //Check si alquien ya se loego anteriormente
        if (AccessToken.getCurrentAccessToken() != null && com.facebook.Profile.getCurrentProfile() != null){

            Profile profile= com.facebook.Profile.getCurrentProfile();
            String name=profile.getName();

            Uri uri=profile.getProfilePictureUri(200, 200);

            // App code
            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            intent.putExtra("NOMBRE",name);
            intent.putExtra("FOTO",uri.toString());
            startActivity(intent);
            finish();
        }

        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

               Profile perfil= com.facebook.Profile.getCurrentProfile();
                String nombre=perfil.getName();
                Uri uriFoto=perfil.getProfilePictureUri(200,200);


                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("NOMBRE",nombre);
                intent.putExtra("FOTO",uriFoto.toString());
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancel() {
                resultado.setText("se cancelo");
            }

            @Override
            public void onError(FacebookException error) {
                resultado.setText("Error");
            }
        });




    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }






}
