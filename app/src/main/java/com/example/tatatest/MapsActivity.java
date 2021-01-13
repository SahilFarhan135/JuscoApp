package com.example.tatatest;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentActivity;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    private GoogleMap mMap;
    //Play Service Location
    private static final int MY_PERMISSION_REQUEST_CODE = 7192;
    private static final int PLAY_SERVICE_RESOLUTION_REQUEST = 300193;

    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;


    private  String name="yasir";
    private String present="p",str;
    private Button enter,exit,refresh;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref =FirebaseDatabase.getInstance().getReference("employee");
    DatabaseReference myref =FirebaseDatabase.getInstance().getReference("employee");
    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    String currentTime = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());

    GeoFire geoFire;
    Marker mCurrent;

    private static int UPDATE_INTERVAL = 5000;
    private static int FATEST_INTERVAL = 3000;
    private static int DISPLACEMENT = 10;
    private FusedLocationProviderClient fusedLocationClient;


    // Marker mCurrent;
    VerticalSeekBar mVerticalSeekBar;
    private static final String TAG = MapsActivity.class.getSimpleName();

    //private GeoService geoService;
    private boolean serviceBound;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        fusedLocationClient= LocationServices.getFusedLocationProviderClient(this);
        mLocationRequest=new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);



        enter= findViewById(R.id.btn);
        exit= findViewById(R.id.btn1);
        refresh= findViewById(R.id.refresh);
        str=getIntent().getStringExtra("id");


        // ref = FirebaseDatabase.getInstance().getReference("QuoteList").child("Quote");
        //mDatabase.child("likes").setValue(mItem.totalLikes + 1);






        //bottomNavigationView=findViewById(R.id.top_nav);
        /*bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.make:{
                        //Toast.makeText(MapsActivity.this,"working",Toast.LENGTH_SHORT).show();
                        //Intent intent=new Intent(TopNav_Map.this,MapsActivity.class);
                        //startActivity(intent);
                        //onBackPressed();
                        break;
                    }
                    case R.id.view:{
                        //Toast.makeText(MapsActivity.this,"working1",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MapsActivity.this,TopNav_Map.class);
                        intent.putExtra("id",str);
                        startActivity(intent);
                        //onBackPressed();
                        break;
                    }
                }
            }
        });*/
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });



        enter.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {

               /* if (pre.equals("p")) {
                    enter.setEnabled(false);
                } else {*/

                    empData d1 = new empData(str, currentDate, currentTime, present);
                    ref.child(str).child("attendance").child(currentDate).setValue(d1);

                    Toast.makeText(getApplicationContext(), "Your Attendance has been done!!!", Toast.LENGTH_LONG).show();
                    sendNotification("attendance","your attendance has been done");
                    //recreate();

               // }
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ref = FirebaseDatabase.getInstance().getReference("employee").child(str).child("attendance").child(currentDate);
                ref.child("exit_time").setValue(currentTime);

                Toast.makeText(getApplicationContext(),"You Can Go Now!!!",Toast.LENGTH_LONG).show();
                //sendNotification("attendance","Your can go now");
                //recreate();



            }
        });





        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        geoFire =new GeoFire(ref);
        setUpdateLocation();


        mVerticalSeekBar = findViewById(R.id.verticalSeekBar);
        mVerticalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mMap.animateCamera(CameraUpdateFactory.zoomTo(progress), 1000, null);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch(requestCode){
            case MY_PERMISSION_REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    if(checkPlayServices()){
                        buildGoogleApiClient();
                        createLocationRequest();
                        displayLocation();
                    }
                }
                break;
        }

    }

    private void setUpdateLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, MY_PERMISSION_REQUEST_CODE);
        } else {
            if (checkPlayServices()) {
                buildGoogleApiClient();
                createLocationRequest();
                displayLocation();


            }
        }
        //recreate();
    }

    private void displayLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        mLastLocation= LocationServices.FusedLocationApi.getLastLocation((mGoogleApiClient));
        if(mLastLocation !=null){
            final double latitude=mLastLocation.getLatitude();
            final double longitude=mLastLocation.getLongitude();

            geoFire.setLocation("you", new GeoLocation(latitude, longitude),
                    new GeoFire.CompletionListener() {
                        @Override
                        public void onComplete(String key, DatabaseError error) {

                            //add Marker
                            if(mCurrent !=null)
                                mCurrent.remove();
                            mCurrent=mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude))
                                   .title("You"));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),20.0f));
                        }
                    });





            //Toast.makeText(MapsActivity.this,"Your Location was change : "+ latitude+longitude,Toast.LENGTH_SHORT).show();
        }
        //else
            //Toast.makeText(MapsActivity.this,"Cant get your location ",Toast.LENGTH_SHORT).show();
        //recreate();

    }

    private void createLocationRequest() {
        mLocationRequest=new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    private void buildGoogleApiClient() {

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode))
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICE_RESOLUTION_REQUEST).show();
            else {
                Toast.makeText(this, "This device is not support", Toast.LENGTH_SHORT).show();
                finish();
            }
            return false;

        }
        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        displayLocation();
        startLocationUpdate();
        //recreate();
    }

    private void startLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation=location;
        recreate();
        displayLocation();


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double lat=22.8370210;
        double longt=86.1985954;

        LatLng dangraous_area=new LatLng(lat , longt);     // my 22.826270,86.203057
                                                                            //22.8356850, 86.1952680
                                                                            //22.804291 , 86.183527
        mMap.addCircle(new CircleOptions()
                .center(dangraous_area)
                .radius(45)
                .strokeColor(Color.BLUE)
                .fillColor(0x220000FF)
                .strokeWidth(5.0f)
        );

        GeoQuery geoQuery=geoFire.queryAtLocation(new GeoLocation(dangraous_area.latitude,dangraous_area.longitude),0.04f);
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                //sendNotification("jusco",String.format("%s You Enter in jusco ",key,location.latitude,location.longitude));
                Toast.makeText(getApplicationContext(),"You Entered in Jusco "+key,Toast.LENGTH_LONG).show();
                enter.setEnabled(true);
                exit.setEnabled(true);

            }

            @Override
            public void onKeyExited(String key) {
                //sendNotification("jusco",String.format("%s No longer  in jusco ",key));
                Toast.makeText(getApplicationContext(),"You Leave Jusco",Toast.LENGTH_LONG).show();
                enter.setEnabled(false);
                exit.setEnabled(false);
               // recreate();

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                Log.d("Move",String.format("%s moved within the dangerous area[%f/%f]",key,location.latitude,location.longitude));
               // recreate();

            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(DatabaseError error) {
                Log.e("ERROR",""+error);

            }
        });

//22.826270, 86.203057
        googleMap.setMyLocationEnabled(true);
        //recreate();
    }
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    private void sendNotification(String title, String body) {
        Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
        String CHANNEL_ID="MYCHANNEL";
        NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"name", NotificationManager.IMPORTANCE_LOW);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),1,intent,0);
        Notification notification=new Notification.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentText(body)
                .setContentTitle(title)
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.sym_action_chat,title,pendingIntent)
                .setChannelId(CHANNEL_ID)
                .setSmallIcon(android.R.drawable.sym_action_chat)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(1,notification);

    }
    public void onBackPressed() {
        Intent intent=new Intent(MapsActivity.this,AttendanceHome.class);
        intent.putExtra("id",str);
        startActivity(intent);

        super.onBackPressed();
    }
}