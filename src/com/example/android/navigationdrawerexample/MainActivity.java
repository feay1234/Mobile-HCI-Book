package com.example.android.navigationdrawerexample;

/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.android.navigationdrawerexample.MyLocation.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This example illustrates a common usage of the DrawerLayout widget
 * in the Android support library.
 * <p/>
 * <p>When a navigation (left) drawer is present, the host activity should detect presses of
 * the action bar's Up affordance as a signal to open and close the navigation drawer. The
 * ActionBarDrawerToggle facilitates this behavior.
 * Items within the drawer should fall into one of two categories:</p>
 * <p/>
 * <ul>
 * <li><strong>View switches</strong>. A view switch follows the same basic policies as
 * list or tab navigation in that a view switch does not create navigation history.
 * This pattern should only be used at the root activity of a task, leaving some form
 * of Up navigation active for activities further down the navigation hierarchy.</li>
 * <li><strong>Selective Up</strong>. The drawer allows the user to choose an alternate
 * parent for Up navigation. This allows a user to jump across an app's navigation
 * hierarchy at will. The application should treat this as it treats Up navigation from
 * a different task, replacing the current task stack using TaskStackBuilder or similar.
 * This is the only form of navigation drawer that should be used outside of the root
 * activity of a task.</li>
 * </ul>
 * <p/>
 * <p>Right side drawers should be used for actions, not navigation. This follows the pattern
 * established by the Action Bar that navigation should be to the left and actions to the right.
 * An action should be an operation performed on the current contents of the window,
 * for example enabling or disabling a data overlay on top of the current content.</p>
 */
public class MainActivity extends FragmentActivity {
    public static final String EXTRA_MESSAGE = null;
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;

    
    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
        
        
//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mSensorListener = new ShakeEventListener();   
//
//        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
//
//          public void onShake() {
//            Toast.makeText(MainActivity.this, "Shake!", Toast.LENGTH_SHORT).show();
//            
//            
//            
//            ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
//            toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200); 
//          }
//        });
        
     // create file
//        File file = new File(this.getFilesDir(), "book");
//        String string = "Hello world!";
//        FileOutputStream outputStream;
//
//        try {
//          outputStream = openFileOutput("book", Context.MODE_PRIVATE);
//          outputStream.write(string.getBytes());
//          outputStream.close();
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
        
        
        writeToFile("test");
        String text = readFromFile();
        Log.e("String", text);
        
    }
    
    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        } 
    }


    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
    
//    @Override
//    protected void onResume() {
//      super.onResume();
//      mSensorManager.registerListener(mSensorListener,
//          mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//          SensorManager.SENSOR_DELAY_UI);
//    }
//
//    @Override
//    protected void onPause() {
//      mSensorManager.unregisterListener(mSensorListener);
//      super.onPause();
//    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        
//        
//        return super.onCreateOptionsMenu(menu);
    	// Inflate the options menu from XML
    	
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        

        return true;
    }
    
    @Override
    public boolean onSearchRequested() {
//        pauseSomeStuff();
        return super.onSearchRequested();
    }

    


    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
    	Fragment fragment = null;
    	switch (position) {
        case 0:
		    	fragment = new SmartFragment();
        		break;
        case 1:
	        	fragment = new LibraryFragment();
	    		break;
	    		
        case 2:
        		fragment = new BorrowFragment();
        		break;
        case 3:
        		fragment = new StoreFragment();
        		break;
    	}
        

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class SmartFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";
		private static final String EXTRA_MESSAGE = "bookName";
        private LatLng userLatLng;
        private String[] location_name = {"My Home","Boyd Orr Building","Glasgow Central Station"};
        private Double[] locationBook = {55.871291,-4.267722,55.873555,-4.292677,55.860444,-4.258028};
        private String near_location = "";
        private SupportMapFragment fragment;
        private GoogleMap mMap;
        private WebView myWebView;
        private String mode = "location";
        
        private SensorManager mSensorManager;
        private ShakeEventListener mSensorListener;
        
        public SmartFragment() {
            // Empty constructor required for fragment subclasses
        }
        
        @SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
		@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
            setHasOptionsMenu(true);
            myWebView = (WebView) rootView.findViewById(R.id.webview);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.addJavascriptInterface(this, "Android");
            
            // create map
            mMap = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            
            
            //add marker
            mMap.addMarker(new MarkerOptions()
								.position(new LatLng( 55.871291,-4.267722))
								.title("My Home"));
            
            mMap.addMarker(new MarkerOptions()
								.position(new LatLng(55.873555,-4.292677))
								.title("Boyd Orr Bldg"));
            
            mMap.addMarker(new MarkerOptions()
								.position(new LatLng(55.860444,-4.258028))
								.title("Glasgow Central Station"));
            
            
            
            // get location can call webview
            LocationResult locationResult = new LocationResult() {
    			@Override
    			public void gotLocation(Location location) {
    				userLatLng = new LatLng(location.getLatitude(),
    						location.getLongitude());
    				MyMap myMap = new MyMap(getActivity(),mMap, myWebView, location, mode);
    	            myMap.initial();
    				
    				for(int i=0;i<locationBook.length;i+=2){
    					if(distance(locationBook[i],locationBook[i+1], location.getLatitude(), location.getLongitude(), 'K') < 0.07){
    						
    						near_location = location_name[i/2];
    						Toast.makeText(getActivity(),near_location , Toast.LENGTH_LONG).show();
    						
    					}
    				}
    				myWebView.loadUrl("file:///android_asset/SmartLibrary.html");
            		myWebView.setWebViewClient(new WebViewClient(){
            		    public void onPageFinished(WebView view, String url){  
            		    	
            		    	myWebView.loadUrl("javascript:filterBookByLocation('"+near_location+"')");
            		    }           
            		});
            		CameraPosition cameraPosition = new CameraPosition.Builder()
    			    .target(userLatLng)
    			    .zoom(15)                   
    			    .build();         
    			    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    			}
    		};
            
            
            MyLocation myLocation = new MyLocation();
    		myLocation.getLocation(getActivity(), locationResult);
            
    		
          mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
          mSensorListener = new ShakeEventListener();   
  
          mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
  
            public void onShake() {
              Toast.makeText(getActivity(), "Shake!", Toast.LENGTH_SHORT).show();
              
              	if(mode == "location"){
//		    		myWebView.loadUrl("javascript:filterBookByLocation('"+near_location+"')");
		    		myWebView.loadUrl("javascript:getFirstBook()");
		    	}
		    	else if (mode == "time"){
//		    		myWebView.loadUrl("javascript:filterBookByTime()");
		    		myWebView.loadUrl("javascript:getFirstBook()");
		    	}
		    	else{
//		    		myWebView.loadUrl("javascript:filterBookByDate()");
		    		myWebView.loadUrl("javascript:getFirstBook()");
		    	}
              
              ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
              toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200); 
            }
          });
            
    		
            return rootView;
        }
        
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
        }
        
        @Override
		public void onResume() {
	        super.onResume();
	        mSensorManager.registerListener(mSensorListener,
	            mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	            SensorManager.SENSOR_DELAY_UI);
	      }
  
	      @Override
	      public void onPause() {
	        mSensorManager.unregisterListener(mSensorListener);
	        super.onPause();
	      }
        
        @JavascriptInterface
        public void readBook(String toast) {
        	Intent intent = new Intent(getActivity(), ReadActivity.class);
        	String message = toast;
        	intent.putExtra(EXTRA_MESSAGE, message);
        	getActivity().startActivity(intent);
        }
        
        private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
            double theta = lon1 - lon2;
            double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
            dist = Math.acos(dist);
            dist = rad2deg(dist);
            dist = dist * 60 * 1.1515;
            if (unit == 'K') {
              dist = dist * 1.609344;
            } else if (unit == 'N') {
              dist = dist * 0.8684;
              }
            return (dist);
          }

          /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
          /*::  This function converts decimal degrees to radians             :*/
          /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
          private double deg2rad(double deg) {
            return (deg * Math.PI / 180.0);
          }

          /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
          /*::  This function converts radians to decimal degrees             :*/
          /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
          private double rad2deg(double rad) {
            return (rad * 180.0 / Math.PI);
          }
          
         
          public void onDestroyView() 
          {
             super.onDestroyView(); 
             Fragment fragment = (getFragmentManager().findFragmentById(R.id.map));  
             FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
             ft.remove(fragment);
             ft.commit();
         }
         
          @Override
          public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
              // TODO Auto-generated method stub
              super.onCreateOptionsMenu(menu, inflater);
              inflater.inflate(R.menu.smart, menu);
          }

          @Override
          public boolean onOptionsItemSelected(MenuItem item) {
             // handle item selection'
        	  switch(item.getItemId()) {
              case R.id.action_time:
            	  myWebView.loadUrl("file:///android_asset/SmartLibrary.html");
            	  myWebView.setWebViewClient(new WebViewClient(){
          		    public void onPageFinished(WebView view, String url){  
          		    	mode = "time";
          		    	myWebView.loadUrl("javascript:filterBookByTime()");
          		    }           
          		  });
            	  Toast.makeText(getActivity(), "Sort By Time", Toast.LENGTH_SHORT).show();
            	  break;
              case R.id.action_date:
            	  myWebView.loadUrl("file:///android_asset/SmartLibrary.html");
            	  myWebView.setWebViewClient(new WebViewClient(){
          		    public void onPageFinished(WebView view, String url){  
          		    	mode = "date";
          		    	myWebView.loadUrl("javascript:filterBookByDate()");
          		    }           
          		  });
            	  Toast.makeText(getActivity(), "Sort By Date", Toast.LENGTH_SHORT).show();
            	  break;
              }
             return false;
          }
        
        
    }
    
    public static class LibraryFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";
		private static final String EXTRA_MESSAGE = "bookName";
        private LatLng userLatLng;
        private String[] location_name = {"My Home","Boyd Orr Building","Glasgow Central Station"};
        private Double[] locationBook = {55.871291,-4.267722,55.873555,-4.292677,55.860444,-4.258028};
        private String near_location = "";
        private SensorManager mSensorManager;
        private ShakeEventListener mSensorListener;
        private WebView myWebView;
        
        public LibraryFragment() {
            // Empty constructor required for fragment subclasses
        }

        @SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
		@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_library, container, false);
            
            myWebView = (WebView) rootView.findViewById(R.id.webview);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.addJavascriptInterface(this, "Android");
            myWebView.loadUrl("file:///android_asset/MyLibrary.html");
            
            setHasOptionsMenu(true);
            
            mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
            mSensorListener = new ShakeEventListener();   
    
            mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
    
              public void onShake() {
                Toast.makeText(getActivity(), "Shake!", Toast.LENGTH_SHORT).show();
                
                myWebView.loadUrl("javascript:getFirstBook()");
                
                ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
                toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200); 
              }
            });
    		
            return rootView;
        }
        @Override
		public void onResume() {
	        super.onResume();
	        mSensorManager.registerListener(mSensorListener,
	            mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	            SensorManager.SENSOR_DELAY_UI);
	      }
  
	      @Override
	      public void onPause() {
	        mSensorManager.unregisterListener(mSensorListener);
	        
	        super.onPause();
	      }
        @JavascriptInterface
        public void readBook(String toast) {
        	Intent intent = new Intent(getActivity(), ReadActivity.class);
        	String message = toast;
        	intent.putExtra(EXTRA_MESSAGE, message);
        	getActivity().startActivity(intent);
        }
        
        
        
        @JavascriptInterface
        public void addBook(String toast) {
        	Intent intent = new Intent(getActivity(), SettingActivity.class);
        	String message = toast;
        	intent.putExtra(EXTRA_MESSAGE, message);
        	getActivity().startActivity(intent);
        	
        }
        
        
        
        
        private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
            double theta = lon1 - lon2;
            double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
            dist = Math.acos(dist);
            dist = rad2deg(dist);
            dist = dist * 60 * 1.1515;
            if (unit == 'K') {
              dist = dist * 1.609344;
            } else if (unit == 'N') {
              dist = dist * 0.8684;
              }
            return (dist);
          }

          /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
          /*::  This function converts decimal degrees to radians             :*/
          /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
          private double deg2rad(double deg) {
            return (deg * Math.PI / 180.0);
          }

          /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
          /*::  This function converts radians to decimal degrees             :*/
          /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
          private double rad2deg(double rad) {
            return (rad * 180.0 / Math.PI);
          }
          
          @Override
          public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
              // TODO Auto-generated method stub
              super.onCreateOptionsMenu(menu, inflater);
              inflater.inflate(R.menu.library, menu);
              
           // Get the SearchView and set the searchable configuration
              SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
              SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
              // Assumes current activity is the searchable activity
              searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
              searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
              
              SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
                  public boolean onQueryTextChange(String newText) {
                      // this is your adapter that will be filtered
                      return true;
                  }

                  public boolean onQueryTextSubmit(final String query) {
                	  myWebView.loadUrl("file:///android_asset/MyLibrary.html");
                	  myWebView.setWebViewClient(new WebViewClient(){
              		    public void onPageFinished(WebView view, String url){  
              		    	
              		    	myWebView.loadUrl("javascript:search('"+query+"')");
              		    }           
              		  });
                	  return true;
                  }
              };
              searchView.setOnQueryTextListener(queryTextListener);
          }

          @Override
          public boolean onOptionsItemSelected(MenuItem item) {
             // handle item selection'
        	  switch(item.getItemId()) {
              case R.id.recent:
            	  myWebView.loadUrl("file:///android_asset/MyLibrary.html");
            	  myWebView.setWebViewClient(new WebViewClient(){
          		    public void onPageFinished(WebView view, String url){  
          		    	
          		    	myWebView.loadUrl("javascript:recentRead()");
          		    }           
          		  });
            	  break;
              case R.id.category:
            	  myWebView.loadUrl("file:///android_asset/MyLibrary.html");
            	  myWebView.setWebViewClient(new WebViewClient(){
          		    public void onPageFinished(WebView view, String url){  
          		    	
          		    	myWebView.loadUrl("javascript:showModal()");
          		    }           
          		  });
            	  break;
              }
             return false;
          }
        
    }
    
    public static class BorrowFragment extends Fragment {
        private WebView myWebView;
        
        public BorrowFragment() {
            // Empty constructor required for fragment subclasses
        }

        @SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
		@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_library, container, false);
            
            myWebView = (WebView) rootView.findViewById(R.id.webview);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.addJavascriptInterface(this, "Android");
            myWebView.loadUrl("file:///android_asset/borrow.html");
            
            setHasOptionsMenu(true);
            

    		
            return rootView;
        }
        
        @JavascriptInterface
        public void shareBook() {
        	Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");//for Qr code, its "QR_CODE_MODE" instead of "PRODUCT_MODE"
            intent.putExtra("SAVE_HISTORY", false);//this stops saving ur barcode in barcode scanner app's history
            startActivityForResult(intent, 0);
        }
        
        @Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 0) {
                if (resultCode == RESULT_OK) {
                        String contents = data.getStringExtra("SCAN_RESULT"); //this is the result
                        myWebView.loadUrl("file:///android_asset/borrow.html");
                  	  	myWebView.setWebViewClient(new WebViewClient(){
	                		    public void onPageFinished(WebView view, String url){  
	                		    	
	                		    	myWebView.loadUrl("javascript:getBook()");
	                		    }           
	                		  });
                        Toast.makeText(getActivity(),contents, Toast.LENGTH_SHORT).show();
                } else 
                if (resultCode == RESULT_CANCELED) {
                  // Handle cancel
                }
            }
        }
    }
    
    public static class StoreFragment extends Fragment {
        private WebView myWebView;
        
        public StoreFragment() {
            // Empty constructor required for fragment subclasses
        }

        @SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
		@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_library, container, false);
            
            myWebView = (WebView) rootView.findViewById(R.id.webview);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.addJavascriptInterface(this, "Android");
            myWebView.loadUrl("file:///android_asset/Store.html");
            
            setHasOptionsMenu(true);
            

    		
            return rootView;
        }
        
        
        
    }
    
    
    
}