package com.rah.mcpro;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements LocationListener{
    RecyclerView recyclerView;
    adapter adapter;
    List<Room> list;
    LocationManager locationManager;
    //    Location thislocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
        list=new ArrayList<>();
        getLocation();
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new adapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list.add(new Room("ground1",2));
        list.add(new Room("ground2",1));
        list.add(new Room("ground3",5));
        list.add(new Room("ground4",4));
        list.add(new Room("ground5",3));
        list.add(new Room("ground6",1));
//        float x=0;
//        Location targetLocation = new Location("Lotus");//provider name is unnecessary
//        targetLocation.setLatitude(28.553325);//your coords of course
//        targetLocation.setLongitude(28.553325);
//        x=targetLocation.distanceTo(thislocation);
//        Room r=new Room("ground7", x);
//        r.setLocation(targetLocation);
//        list.add(r);
        adapter.addAll(list);
        recyclerView.setAdapter(adapter);
    }
    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(Main2Activity.this, "Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
//        thislocation=location;
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(Main2Activity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

}

