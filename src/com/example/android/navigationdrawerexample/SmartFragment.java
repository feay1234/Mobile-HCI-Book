package com.example.android.navigationdrawerexample;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SmartFragment extends Fragment{
	public GoogleMap mMap;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_smart, container, false);

		mMap = ( (SupportMapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		return v;
	}
}
