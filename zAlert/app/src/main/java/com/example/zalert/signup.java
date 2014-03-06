package com.example.zalert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
 
public class signup extends Activity {
    // Declare Variables
    
    Button signup;
    String usernametxt;
    String passwordtxt;
    String passwordtxt2;
    String emailtxt;
    EditText password;
    EditText password2;
    EditText username;
    EditText email;
 
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from aignup.xml
        setContentView(R.layout.signup);
        // Locate EditTexts in aignup.xml
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.confirmPassword);
        email = (EditText) findViewById(R.id.email);
 
        // Locate Buttons in signup.xml
        signup = (Button) findViewById(R.id.signup);
 
        
        
        // Sign up Button Click Listener
        signup.setOnClickListener(new OnClickListener() {
 
            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                passwordtxt2 = password2.getText().toString();
                emailtxt = email.getText().toString();
 
                // Force user to fill up the form before sumitting to server
                if (usernametxt.equals("") ||passwordtxt.equals("") ||passwordtxt.equals("")||emailtxt.equals(""))
                {
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();
 
                } else if(!passwordtxt.equals(passwordtxt2))
                {
                	Toast.makeText(getApplicationContext(),
                            "Passwords dont match ",
                            Toast.LENGTH_LONG).show();
                	
                }
                else
                {
                	
                	
                    // Save new user data into Parse.com Data Storage
                    ParseUser user = new ParseUser();
                    user.setUsername(usernametxt);
                    user.setPassword(passwordtxt);
                    user.setEmail(emailtxt);
                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                // Show a simple Toast message upon successful registration
                                Toast.makeText(getApplicationContext(),
                                        "Successfully Signed up",
                                        Toast.LENGTH_LONG).show();
                             
                                Intent intent = new Intent(signup.this,Welcome.class);
                          	  startActivity(intent);
                                
                                
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Sign up Error", Toast.LENGTH_LONG).show();
                                
                                System.out.println(e);
                            }
                        }
                    });
                }
 
            }
        });
 
    }
}