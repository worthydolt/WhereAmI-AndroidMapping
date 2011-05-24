package com.mashsoftware.whereami;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
	private Context mContext;

	public MyItemizedOverlay(Drawable defaultMarker, Context context) {
		super(defaultMarker);
		mContext = context;
		// items = new ArrayList<OverlayItem>();
		populate();
	}

	
	@Override
	protected OverlayItem createItem(int index) {
		return items.get(index);
	}

	public void removeItem(int index) {
		items.remove(index);
		populate();
	}

	public void addNewItem(GeoPoint location, String markerText, String snippet) {
		items.add(new OverlayItem(location, markerText, snippet));
		populate();
	}

	@Override
	public int size() {
		// Number of markers in the collection
		return items.size();
	}

}
