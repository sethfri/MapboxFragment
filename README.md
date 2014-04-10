# MapboxFragment

MapboxFragment is an Android Fragment allowing one-line integration of Mapbox custom maps into Google Maps on Android. Best of all, the `MapboxFragment` class is simply a drop-in replacement for the `MapFragment` class you already use.

## Usage

1. [Set up the Google Maps Android API](https://developers.google.com/maps/documentation/android/start#getting_the_google_maps_android_api_v2) for your application.
2. Add *MapboxFragment.java* to your project.
3. Use `MapboxFragment` wherever you would normally use `MapFragment`.
4. Call `setMapID` on your fragment in your activity's `onResume` method.

## Credits

MapboxFragment was created by [Seth Friedman](https://github.com/sethfri) and [Nolan Smith](https://github.com/nolan330), based off of [MBXMapKit](https://github.com/mapbox/mbxmapkit) for iOS.

## License

MapboxFragment is available under the [MIT license](LICENSE).
