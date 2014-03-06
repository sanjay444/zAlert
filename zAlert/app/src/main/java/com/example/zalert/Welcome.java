package com.example.zalert;

import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
 
public class Welcome extends Activity {
 
    // Declare Variable
    Button logout;
    Button searchButton;
    EditText searchText;
    
    String product;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.activity_main);
 
       
        // Locate TextView in welcome.xml
        searchText = (EditText) findViewById(R.id.editText1);
 
        searchButton = (Button) findViewById(R.id.button1);
        logout = (Button) findViewById(R.id.logout);
 
        Editable p = searchText.getText();
        product=p.toString();
     
        
        
        searchButton.setOnClickListener(new OnClickListener() {
        	 
            public void onClick(View arg0) {
                // Logout current user
            	/*Intent i = new Intent(getApplicationContext(), listView.class);
            	i.putExtra("productValue",product);
            	startActivity(i);*/
            	
            	Editable p = searchText.getText();
                product=p.toString();
                
              
                
            	if(product.isEmpty())
            	{
            		Toast.makeText(Welcome.this, "Please enter search item ",
    						Toast.LENGTH_LONG).show();
            	}
            	else
            	{
            		if(product.contains(" ")){
            			int j=product.indexOf(" ");
            			product=product.substring(0, j);
            		}
            		
            	Intent i = new Intent(getApplicationContext(),CategoryActivity.class);
            	i.putExtra("searchProduct",product);
            	startActivity(i);
            	}
            	
            }
        });
        
        // Logout Button Click Listener
        logout.setOnClickListener(new OnClickListener() {
 
            public void onClick(View arg0) {
                // Logout current user
                ParseUser.logOut();
                finish();
            }
        });
    }
}