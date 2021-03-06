package com.mapbox.mapboxfragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.mapbox.mapboxfragment.R;

public class MainActivity extends Activity {
	//private GoogleMap gmap;
	//private LatLngBounds bounds;
	private final String TAG = this.getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(new MapboxView(getApplicationContext(), "musiccitycenter.ha727h06"));
		setContentView(R.layout.map_fragment);
		
		//gmap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment)).getMap();
		//gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.157158, -86.777326), 15));
		
		//new TileLoader().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume() { 
		super.onResume();
		
		MapboxFragment fragment = (MapboxFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
		
		fragment.setMapID("musiccitycenter.vqloko6r");
	}
	
	/*private class TileLoader extends 
		AsyncTask<Void, Void, HashMap<String, Object> > {
		
		@Override
		protected void onPreExecute() {
			//setProgressVisibility(true, "Retrieving Tiles", "Shouldn't be long now!");
			
		}
		
	
		@Override
		protected HashMap<String, Object> doInBackground(Void... handlers) {
			
			HashMap<String, Object> tileMap = new HashMap<String, Object>();
			try {
				
				Log.d(TAG, "URL: " + url);
				String tileContent = getContent(url);
				JSONObject tileJSON = new JSONObject(tileContent);
				
				Log.d(TAG, "JSON retreived");
				
				JSONArray centerJSON = tileJSON.getJSONArray("center");
				Log.d(TAG, "center: " + centerJSON.toString());
				ArrayList<Number> centerArr = new ArrayList<Number>();
				for(int i = 0; i < centerJSON.length(); ++i) {
					centerArr.add(centerJSON.optDouble(i));
				}
				
				ArrayList<Number> boundsArr = new ArrayList<Number>();
				JSONArray boundsJSON = tileJSON.getJSONArray("bounds");
				Log.d(TAG, "bounds: " + boundsJSON.toString());
				for(int i = 0; i < boundsJSON.length(); ++i) {
					boundsArr.add(boundsJSON.optDouble(i));
				}
				
				Integer minZoom = tileJSON.getInt("minzoom");
				Integer maxZoom = tileJSON.getInt("maxzoom");
				
				Log.d(TAG, "Center: " + centerArr.toString());
				Log.d(TAG, "Bounds: " + boundsArr.toString());
				Log.d(TAG, "minZoom: " + minZoom + " maxZoom: " + maxZoom);
				
				tileMap.put("center", centerArr);
				tileMap.put("bounds", boundsArr);
				tileMap.put("minzoom", minZoom);
				tileMap.put("maxzoom", maxZoom);
				
				return tileMap;
				
			} catch (ClientProtocolException e) {
				Log.d(TAG, "ClientProtocol Exception thrown");
				/** handle specific exceptions 
			} catch (IOException e) {
				Log.d(TAG, "IO Exception thrown");
				/** handle specific exceptions 
			} catch (JSONException e) {
				Log.d(TAG, "JSON Exception thrown");
				/** handle specific exceptions 
			}
			
			return null;
		}	*/
		
		
		/*@Override
		protected void onPostExecute(HashMap<String, Object> tileMap) {			
			//setProgressVisibility(false, null, null);
			
			ArrayList<Number> centerArrayList = (ArrayList<Number>) tileMap.get("center");
			LatLng centerCoordinate = new LatLng(centerArrayList.get(1).doubleValue(), centerArrayList.get(0).doubleValue());
			
			ArrayList<Number> boundsArrayList = (ArrayList<Number>) tileMap.get("bounds");
			LatLng northeastCoordinate = new LatLng(((Number) boundsArrayList.get(3)).doubleValue(), ((Number) boundsArrayList.get(2)).doubleValue());
			LatLng southwestCoordinate = new LatLng(((Number) boundsArrayList.get(1)).doubleValue(), ((Number) boundsArrayList.get(0)).doubleValue());
			bounds = new LatLngBounds(southwestCoordinate, northeastCoordinate);
			
			int minZoom = ((Number) tileMap.get("minzoom")).intValue();
			int maxZoom = ((Number) tileMap.get("maxzoom")).intValue();
			
			// TODO - Set the correct width, height
			MapboxTileProvider tileProvider = new MapboxTileProvider(256, 256, mapID, minZoom, maxZoom);
			
			gmap.addTileOverlay(new TileOverlayOptions().tileProvider(tileProvider));
		}	
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
			
			String URLString = String.format(Locale.ENGLISH, 
					"https://%s.tiles.mapbox.com/v3/%s/%d/%d/%d%s.%s", 
					letters[randomIndex], this.mapID, zoom, x, y, "@2x", "png");
			URL url = null;
			
			if (checkTileExists(x, y, zoom)) {
				try {
					url = new URL(URLString);
				} catch (MalformedURLException e) {
					throw new AssertionError(e);
				}
			}
			
			Log.d(TAG, "URL: " + url.toString());
			return url;
		}*/

		/*
		 * Check that the tile server supports the requested x, y and zoom.
		 */
		/*private boolean checkTileExists(int x, int y, int zoom) {
			boolean tileExists = true;

			if ((zoom < minZoom || zoom > maxZoom)) {
				tileExists = false;
			}

			return tileExists;
		}
	}*/
}
