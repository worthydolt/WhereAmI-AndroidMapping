package com.mashsoftware.whereami;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class HelloItemizedOverlay extends ItemizedOverlay<OverlayItem> {

private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
private Context context;

	public HelloItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		this.context = context; 
		
	}
	
	public void addOverlay(OverlayItem overlayItem){
		mOverlays.add(overlayItem);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return null;
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

}
