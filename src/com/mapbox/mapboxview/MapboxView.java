package com.mapbox.mapboxview;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class MapboxView extends MapView {
	private String mapID;
	
	public MapboxView(Context context) {
		super(context);
	}
	
	public MapboxView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public MapboxView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public MapboxView(Context context, GoogleMapOptions options) {
		super(context, options);
	}
	
	public MapboxView(Context context, String mapID) {
		this(context);
		
		this.mapID = mapID;
		
		// TODO - Get JSON tile map from format(Locale.ENGLISH, "https://a.tiles.mapbox.com/v3/%s.json", this.mapID)
		HashMap<String, Object> tileMap = null;
		
		ArrayList<Number> centerArrayList = (ArrayList<Number>) tileMap.get("center");
		LatLng centerCoordinate = new LatLng(centerArrayList.get(1).doubleValue(), centerArrayList.get(0).doubleValue());
		
		ArrayList<Number> boundsArrayList = (ArrayList<Number>) tileMap.get("bounds");
		LatLng northeastCoordinate = new LatLng(((Number) boundsArrayList.get(3)).doubleValue(), ((Number) boundsArrayList.get(2)).doubleValue());
		LatLng southwestCoordinate = new LatLng(((Number) boundsArrayList.get(1)).doubleValue(), ((Number) boundsArrayList.get(0)).doubleValue());
		LatLngBounds bounds = new LatLngBounds(southwestCoordinate, northeastCoordinate);
		
		int minZoom = ((Number) tileMap.get("minzoom")).intValue();
		int maxZoom = ((Number) tileMap.get("maxzoom")).intValue();
		
		GoogleMap map = getMap();
		// TODO - No idea if 5 is a good padding number - I just chose one
		map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 5));
		
		// TODO - Set the correct width, height
		MapboxTileProvider tileProvider = new MapboxTileProvider(200, 200, this.mapID, minZoom, maxZoom);
		
		map.addTileOverlay(new TileOverlayOptions().tileProvider(tileProvider));
	}
	
	String getMapID() {
		return mapID;
	}
	
	public class MapboxTileProvider extends UrlTileProvider {
		private String mapID;
		private int minZoom;
		private int maxZoom;
		
		public MapboxTileProvider(int width, int height, String mapID, int minZoom, int maxZoom) {
			super(width, height);
			
			this.mapID = mapID;
			this.minZoom = minZoom;
			this.maxZoom = maxZoom;
		}

		@Override
		public URL getTileUrl(int x, int y, int zoom) {
			String[] letters = { "a", "b", "c", "d" };
			int randomIndex = new Random().nextInt(4);
			
			String URLString = String.format(Locale.ENGLISH, "https://%s.tiles.mapbox.com/v3/%s/%d/%d/%d%s.%s", letters[randomIndex], this.mapID, zoom, x, y, "2x", "png");
			URL url = null;
			
			if (checkTileExists(x, y, zoom)) {
				try {
					url = new URL(URLString);
				} catch (MalformedURLException e) {
					throw new AssertionError(e);
				}
			}
			
			return url;
		}

		/*
		 * Check that the tile server supports the requested x, y and zoom.
		 */
		private boolean checkTileExists(int x, int y, int zoom) {
			boolean tileExists = true;

			if ((zoom < minZoom || zoom > maxZoom)) {
				tileExists = false;
			}

			return tileExists;
		}
	}
}
