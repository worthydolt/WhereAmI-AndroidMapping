package com.mashsoftware.whereami;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MyOverlay extends Overlay {
	private Double lat = -31.960906 * 1E6;
	private Double lng = 115.844822 * 1E6;

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		Projection projection = mapView.getProjection();

		GeoPoint geoPoint = new GeoPoint(lat.intValue(), lng.intValue());

		if (shadow == false) {
			Point myPoint = new Point();
			projection.toPixels(geoPoint, myPoint);

			// Set up paintbrush
			Paint paint = new Paint();
			paint.setARGB(250, 250, 0, 0);
			paint.setAntiAlias(true);
			paint.setFakeBoldText(true);
			
			//Draw the circle
			int rad = 5;
			RectF oval = new RectF(myPoint.x-rad, myPoint.y-rad, myPoint.x+rad, myPoint.y+rad);
			
			//Draw on the canvas
			canvas.drawOval(oval, paint);
			canvas.drawText("Red Circle", myPoint.x+rad, myPoint.y, paint);			

		} else {

		}
	}

	public boolean onTap(GeoPoint point, MapView myMapView) {
		return false;

	}
}
