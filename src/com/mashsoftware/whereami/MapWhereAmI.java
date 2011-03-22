package com.mashsoftware.whereami;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MapWhereAmI extends MapActivity {
	/** Called when the activity is first created. */
	private MapController mapController;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		MapView myMapView = (MapView) findViewById(R.id.myMapView);
		mapController = myMapView.getController();

		myMapView.setSatellite(true);
		myMapView.setStreetView(true);
		myMapView.displayZoomControls(false);

		mapController.setZoom(17);
		
		LocationManager locationManager;
		String context = Context.LOCATION_SERVICE;
		locationManager = (LocationManager) getSystemService(context);

		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(false);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		
		String provider = locationManager.getBestProvider(criteria, true);
		Location location = locationManager.getLastKnownLocation(provider);
		updateWithNewLocation(location);
		locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
	}

	@Override
	protected boolean isRouteDisplayed() {

		return false;
	}
	
	private void updateWithNewLocation(Location location) {
		StringBuilder latlongString;
		TextView myLocationText;
		myLocationText = (TextView) findViewById(R.id.myLocationText);
		latlongString = new StringBuilder();
		if (location != null) {
			Double geoLat = location.getLatitude()*1E6;
			Double geoLong = location.getLongitude()*1E6;
			GeoPoint point = new GeoPoint(geoLat.intValue(), geoLong.intValue());
			mapController.animateTo(point);
			double lat = location.getLatitude();
			double lng = location.getLongitude();
			latlongString.append("Lat:").append(lat).append("\nLong:")
					.append(lng);
		} else {
			latlongString.append("No location found");
		}
		myLocationText.setText(latlongString.toString());
	}
	
	private final LocationListener locationListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			updateWithNewLocation(location);
			
		}
	};
}