package com.mapbox.mapboxview;

import java.net.MalformedURLException;
import java.net.URL;
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
		
		// TODO - Get JSON tile dictionary from format(Locale.ENGLISH, "https://a.tiles.mapbox.com/v3/%s.json", this.mapID)
		
		
		
		GoogleMap map = getMap();
		// TODO - Set the correct width, height
		MapboxTileProvider tileProvider = new MapboxTileProvider(200, 200, this.mapID);
		
		map.addTileOverlay(new TileOverlayOptions().tileProvider(tileProvider));
	}
	
	String getMapID() {
		return mapID;
	}
	
	public class MapboxTileProvider extends UrlTileProvider {
		private String mapID;
		
		public MapboxTileProvider(int width, int height, String mapID) {
			super(width, height);
			
			this.mapID = mapID;
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
			int minZoom = 12;
			int maxZoom = 16;
			
			boolean tileExists = true;

			if ((zoom < minZoom || zoom > maxZoom)) {
				tileExists = false;
			}

			return tileExists;
		}
	}
}
