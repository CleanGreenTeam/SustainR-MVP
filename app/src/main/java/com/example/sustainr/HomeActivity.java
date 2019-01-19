package com.example.sustainr;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class HomeActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = "MapActivity",
            FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION,
            COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION,
            NO_VALUE_BUNDLE_STRING = "STRING NOT FOUND IN BUNDLE";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234,
            NO_VALUE_BUNDLE_INT = -1;
    private static final float DEFAULT_ZOOM = 17f;
    private static final double NO_VALUE_BUNDLE_NUMBER = -1f;
    private static final boolean NO_VALUE_BUNDLE_BOOLEAN = false;

    private Boolean locationPermissionsGranted = false;
    private float currZoomValue;
    private static LatLng currPosLatLng;
    private CameraPosition cameraPosition;
    private TextView zoomText;
    private GoogleMap mMap;
    private LatLng currLoc;
    private FusedLocationProviderClient fusedLocationProviderClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLocationPermission();
        currZoomValue = DEFAULT_ZOOM;
        getDeviceLocation();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);




        if(currPosLatLng != null) {
            cameraPosition = CameraPosition.builder().target(currPosLatLng).zoom(DEFAULT_ZOOM).tilt(45).bearing(0f).build();
        }


        if (savedInstanceState != null) {
            Toast.makeText(this, "saved instance state", Toast.LENGTH_SHORT).show();
            zoomText.setText(savedInstanceState.getString("Zoom"));
            float zoom = savedInstanceState.getFloat("zoom");
            //Toast.makeText(this, new Float(zoom).toString(), Toast.LENGTH_SHORT).show();
        }

    }


    public LatLng getCurrPosLatLng() {
        return currPosLatLng;
    }

    public void AddMarker(Event event){
//        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.happy);
//        String type = event.getType().toString();
//        if(type.equals("cool")) icon = BitmapDescriptorFactory.fromResource(R.drawable.cool);
//        if(type.equals("happy")) icon = BitmapDescriptorFactory.fromResource(R.drawable.happy);
//        if(type.equals("laughing")) icon = BitmapDescriptorFactory.fromResource(R.drawable.laughing);
//        if(type.equals("love")) icon = BitmapDescriptorFactory.fromResource(R.drawable.love);
//        if(type.equals("sad")) icon = BitmapDescriptorFactory.fromResource(R.drawable.sad);
//        if(type.equals("surprise")) icon = BitmapDescriptorFactory.fromResource(R.drawable.surprise);
//        if(type.equals("weird")) icon = BitmapDescriptorFactory.fromResource(R.drawable.weird);
//
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(event.getLatLng().getLatitude(),event.getLatLng().getLongitude()))
//                .title(event.getMesssage()).icon(icon));

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (locationPermissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            // sets map to listen for marker clicks
            // Google Map options
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mMap.getUiSettings().setRotateGesturesEnabled(false);
        }
    }
    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationPermissionsGranted = true;
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }
    private void getDeviceLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if(locationPermissionsGranted) {
                final Task location = fusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful() && task.getResult() != null) {

                            // current location marker
                            Location currentLocation = (Location)task.getResult();
                            currPosLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                            cameraPosition = CameraPosition.builder().target(currPosLatLng).zoom(21).tilt(45).bearing(0f).build();
                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 21);


                            // add job marker
                            /*
                            if (getBundleStringInfo("add marker").equals("add marker")) {
                                LatLng latLng = new LatLng(addJobHandler.getLocation().getLatitude(), addJobHandler.getLocation().getLongitude());
                                addMarker(latLng, addJobTitle, addJobHandler.getTag());
                                moveCamera(latLng, DEFAULT_ZOOM - 0f);
                            }
                            */

                        } else {
                            Toast.makeText(HomeActivity.this, "Unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.d(TAG, "getDeviceLocation: security exception " + e.getMessage());
        }
    }
    private void moveCamera(LatLng latLng, float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private boolean isNearby(LatLng marker, LatLng currLoc){
        double x1 = marker.latitude;
        double y1 = marker.longitude;
        double x2 = currLoc.latitude;
        double y2 = currLoc.longitude;
        double theta = y1 - y2;
        double dist = Math.sin(Math.toRadians(x1)) * Math.sin(Math.toRadians(x2)) + Math.cos(Math.toRadians(x1)) * Math.cos(Math.toRadians(x2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1609.3444;
        return (dist<10);
    }
//    public BitmapDescriptor resizeBitmap(int drawable, int width, int height){
//        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(drawableName);
//        Bitmap b=bitmapdraw.getBitmap();
//
//        BitmapDescriptor imageBitmap = BitmapDescriptorFactory.fromBitmap(b);
//        return BitmapDescriptorFactory.fromResource(imageBitmap).
//        (imageBitmap, width, height, false);
//    }

}