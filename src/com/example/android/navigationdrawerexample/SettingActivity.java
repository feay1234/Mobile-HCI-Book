package com.example.android.navigationdrawerexample;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class SettingActivity extends FragmentActivity {
	
	private GoogleMap mMap;
	private static TextView dateT;
	private static TextView timeT;
	private static TextView locationT;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
		mMap = mapFrag.getMap();
		mMap.setMyLocationEnabled(true);
		
		
		dateT = (TextView) findViewById(R.id.date);
		timeT = (TextView) findViewById(R.id.time);
		locationT = (TextView) findViewById(R.id.location);
		
		MyMap myMap = new MyMap(this,mMap, locationT);
        myMap.initial2();
        
        Button button = (Button) findViewById(R.id.finish);
        
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				Toast.makeText(getApplicationContext(), "The Book is added to the Smart Library", Toast.LENGTH_SHORT).show();

			}
 
		});
	}

	
	
	public void showTimePickerDialog(View v) {
	    DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getSupportFragmentManager(), "timePicker");
	}
	
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getSupportFragmentManager(), "datePicker");
	}
	public void fisnih(View v){
		
	}
	
	public static class TimePickerFragment extends DialogFragment
		    implements TimePickerDialog.OnTimeSetListener {
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		
		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute,
		DateFormat.is24HourFormat(getActivity()));
		}
		
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// Do something with the time chosen by the user
			timeT.setText(hourOfDay+":"+minute);
		}
	}
	
	public static class DatePickerFragment extends DialogFragment
		    implements DatePickerDialog.OnDateSetListener {
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		
		public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
			dateT.setText(day+" "+month+" "+year);
		}
	}
	

}
