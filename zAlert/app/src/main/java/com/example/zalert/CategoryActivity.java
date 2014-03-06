package com.example.zalert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryActivity extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// ALL JSON node names
	public static final String TAG_ID = "styleId";
	public static final String TAG_NAME = "productName";
	public static final String TAG_CATEGORIES_COUNT = "price";
	public static final String TAG_CATEGORIES_LOGO = "thumbnailImageUrl";

	public static String URL_CATEGORY ;
	
	// products JSONArray
	JSONArray categories = null;
	String product;
	// private String key="a73121520492f88dc3d33daf2103d7574f1a3166";

	// Creating JSON Parser object
	JSONParser jsonParser = new JSONParser();

	// private ListView lv;
	private BaseAdapter mAdapter;

	ArrayList<HashMap<String, String>> categoryList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		
		Bundle extras = getIntent().getExtras();
        if (extras != null) {
            product = extras.getString("searchProduct");
            Toast.makeText(CategoryActivity.this, "Item selected: " + product,
					Toast.LENGTH_LONG).show();
            URL_CATEGORY="http://api.zappos.com/Search?term=".concat(product).concat("&key=a73121520492f88dc3d33daf2103d7574f1a3166");
        }
		
		// Hashmap for ListView
		categoryList = new ArrayList<HashMap<String, String>>();
		
		
		
		// get listview
		final ListView lv = getListView();
		lv.setDivider(null);

		lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				
				
				
                HashMap a=(HashMap)categoryList.get(position);
				
			    String pid=(String) a.get("productId");
			    
				Intent i = new Intent(getApplicationContext(),SingleItem.class);
            	i.putExtra("ProductId",pid);
            	i.putExtra("price",(String)a.get("price"));
            	i.putExtra("imageurl",(String)a.get("thumbnailImageUrl"));
            	i.putExtra("ProductName",(String)a.get("productName"));
            	startActivity(i);
			
				
			}
		});
		
		new LoadCategories().execute();
	}

	class LoadCategories extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(CategoryActivity.this);
			pDialog.setMessage("Loading Products...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();

		
			// getting JSON string from URL
			String json = jsonParser.makeHttpRequest(URL_CATEGORY, "GET",
					params);

			
			

			return json;
		}

		@Override
		protected void onPostExecute(String json) {
			try {
				JSONObject getArray= new JSONObject(json);
				
				categories= getArray.getJSONArray("results");
				
				if(categories.length()==0)
				{
					
					Toast.makeText(CategoryActivity.this, "No Items found for the search item",
							Toast.LENGTH_LONG).show();
					Toast.makeText(CategoryActivity.this, "Please search again ",
							Toast.LENGTH_LONG).show();
					
					Intent i = new Intent(getApplicationContext(),Welcome.class);
	            	
	            	startActivity(i);
					
				}
				
				if (categories != null) {
					// looping through All albums
					for (int i = 0; i < categories.length(); i++) {
						JSONObject c = categories.getJSONObject(i);

						// Storing each json item values in variable
						String id = c.getString(TAG_ID);
						String name = c.getString(TAG_NAME);
						String songs_count = c.getString(TAG_CATEGORIES_COUNT);
						String category_logo = c.getString(TAG_CATEGORIES_LOGO);
						String productNumber = c.getString("productId");

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_ID, id);
						map.put(TAG_NAME, name);
						map.put(TAG_CATEGORIES_COUNT, songs_count);
						map.put(TAG_CATEGORIES_LOGO, category_logo);
						map.put("productId", productNumber);

						// adding HashList to ArrayList
						categoryList.add(map);
					}

					mAdapter = new CategoryListAdapter(CategoryActivity.this,
							categoryList);
					getListView().setAdapter(mAdapter);

					// dismiss the dialog after getting all albums
					pDialog.dismiss();
				} else {
					Log.d("Categories: ", "null");
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
