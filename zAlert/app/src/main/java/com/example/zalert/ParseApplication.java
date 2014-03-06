package com.example.zalert;

import com.parse.Parse;
import com.parse.ParseACL;
 
import com.parse.ParseUser;
 
import android.app.Application;
 
public class ParseApplication extends Application {
 
    @Override
    public void onCreate() {
        super.onCreate();
 
        // the parse key and initialization token is removed
        // the app Wont work until the key is initialized to the correct one obtained from the parse server

        Parse.initialize(this, "parse_key", "authentication_id");
 
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
 

        defaultACL.setPublicReadAccess(true);
 
        ParseACL.setDefaultACL(defaultACL, true);
    }
 
}
