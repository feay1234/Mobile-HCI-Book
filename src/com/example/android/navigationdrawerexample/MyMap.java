package com.example.android.navigationdrawerexample;


import android.content.Context;
import android.location.Location;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMap {
	public final static String EXTRA_MESSAGE = "com.example.mainactivity.MESSAGE";
	public final static String EXTRA_ID = "com.example.mainactivity.EXTRA_ID";
	private GoogleMap mMap;
	private Context context;
	private TextView locationT;
	private WebView myWebView;
	private String near_location = "";
	private Location current_location;
	String[] location_name = {"My Home","Boyd Orr Building","Glasgow Central Station"};
    Double[] locationBook = {55.871291,-4.267722,55.873555,-4.292677,55.860444,-4.258028};
    private String mode;
	
    public MyMap(Context context, GoogleMap mMap, TextView locationT){
    	this.mMap = mMap;
    	this.context = context;
    	this.locationT = locationT;
    }
	public MyMap(Context context,GoogleMap mMap, WebView myWebView, Location current_location, String mode){
		this.mMap = mMap;
		this.context = context;
		this.myWebView = myWebView;
		this.current_location = current_location;
		this.mode = mode;
	}
	
	public void initial2(){
		mMap.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public void onMapClick(final LatLng point) {
				// TODO Auto-generated method stub
				Double lat = point.latitude;
				Double lng = point.longitude;
				mMap.clear();
	            
				CameraPosition cameraPosition = new CameraPosition.Builder()
			    .target(point)
			    .zoom(15)                   
			    .build();         
			    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			    Marker marker = mMap.addMarker(new MarkerOptions()
				.position(point)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin3)));
			    locationT.setText("Latitude: "+lat+"  Longitude: "+lng);
			}
		});
		
	}
	
	public void initial(){
		
		mMap.setMyLocationEnabled(true);
		mMap.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener() {
			
			@Override
			public boolean onMyLocationButtonClick() {
				mode = "location";
				 for(int i=0;i<locationBook.length;i+=2){
						if(distance(locationBook[i],locationBook[i+1],current_location.getLatitude(), current_location.getLongitude(), 'K') < 0.1){
							near_location = location_name[i/2];
							Toast.makeText(context, near_location,Toast.LENGTH_LONG).show();
						}
					}
				 	clearMap();
				    myWebView.loadUrl("file:///android_asset/SmartLibrary.html");
	        		myWebView.setWebViewClient(new WebViewClient(){
	        		    public void onPageFinished(WebView view, String url){  
	        		    	
	        		    	myWebView.loadUrl("javascript:filterBookByLocation('"+near_location+"')");
	        		    }           
	        		});
	        		
				return false;
			}
		});
		
		mMap.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public void onMapClick(final LatLng point) {
				// TODO Auto-generated method stub
				mode = "location";
				Double lat = point.latitude;
				Double lng = point.longitude;
				mMap.clear();
				//add marker
	            clearMap();
	            
				CameraPosition cameraPosition = new CameraPosition.Builder()
			    .target(point)
			    .zoom(15)                   
			    .build();         
			    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
				
			    
			    
			    for(int i=0;i<locationBook.length;i+=2){
					if(distance(locationBook[i],locationBook[i+1], lat, lng, 'K') < 0.1){
						near_location = location_name[i/2];
						Toast.makeText(context, near_location,Toast.LENGTH_LONG).show();
					}
				}
			    myWebView.loadUrl("file:///android_asset/SmartLibrary.html");
        		myWebView.setWebViewClient(new WebViewClient(){
        		    public void onPageFinished(WebView view, String url){  
        		    	
        		    	myWebView.loadUrl("javascript:filterBookByLocation('"+near_location+"')");
        		    }           
        		});
				
				Marker marker = mMap.addMarker(new MarkerOptions()
												.position(point)
												.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin3)));
//				Marker marker = dropdown.animate(mMap, point, new MarkerOptions()
//												                  .position(point)
//												                  .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin3)));
			}
		});
		
		
		mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener(){
			
			@Override
			public void onInfoWindowClick(Marker marker) {
				// TODO Auto-generated method stub
				
				String id = marker.getSnippet();
				
			}
			
		});
		
		mMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
	}
	private void clearMap(){
		mMap.clear();
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
}
