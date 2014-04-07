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
		
		GoogleMap map = getMap();
		// TODO - Set the correct width, height
		MapboxTileProvider tileProvider = new MapboxTileProvider(200, 200);
		
		map.addTileOverlay(new TileOverlayOptions().tileProvider(tileProvider));
	}
	
	String getMapID() {
		return mapID;
	}
	
	public class MapboxTileProvider extends UrlTileProvider {
		public MapboxTileProvider(int width, int height) {
			super(width, height);
		}

		@Override
		public URL getTileUrl(int x, int y, int zoom) {
			String[] letters = { "a", "b", "c", "d" };
			int randomIndex = new Random().nextInt(4);
			
			String URLString = String.format(Locale.ENGLISH, "https://%s.tiles.mapbox.com/v3/%s/%d/%d/%d%s.%s", letters[randomIndex], zoom, x, y, "2x", "png");
			URL url = null;
			
			try {
				url = new URL(URLString);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return url;
		}
	}
}
