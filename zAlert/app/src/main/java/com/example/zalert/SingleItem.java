package com.example.zalert;



import com.parse.ParseObject;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleItem extends Activity {

	String productId="loading";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_item);
		
		String productName = " loading";
		String price = "loading";
		String imageUrl="loading";
		 String productId="loading";
		
		Bundle extras = getIntent().getExtras();
        if (extras != null) {
            productName = extras.getString("ProductName");
            price = extras.getString("price");
            imageUrl = extras.getString("imageurl");
            productId = extras.getString("ProductId");
           }
		

        Button saveButton = (Button) findViewById(R.id.button1);
        
       TextView product_name = (TextView)findViewById(R.id.category_name);
		ImageView image_logo = (ImageView)findViewById(R.id.img_category_logo);
		TextView productPrice= (TextView)findViewById(R.id.txt_item_count);
        
	     product_name.setText(productName);
	     productPrice.setText(price);
		
	     Picasso.with(getBaseContext()).load(imageUrl).into(image_logo);
		
     
    	final String p =productId;
         
         saveButton.setOnClickListener(new OnClickListener() {
         	 
             public void onClick(View arg0) {
            	 
             	ParseUser user = ParseUser.getCurrentUser();
            	 
             	// Make a new post
             	//final String p =productId;
             	ParseObject post = new ParseObject("product");
             	post.put("productID", p);
             	post.put("user", user);
             	post.saveInBackground();
             	
             	Toast.makeText(SingleItem.this, "Suceccfully saved , lookout for the discount email",
						Toast.LENGTH_LONG).show();
             	
             	
             	
             }
         });
         
	}

	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
