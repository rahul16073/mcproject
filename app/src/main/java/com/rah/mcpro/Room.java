package com.rah.mcpro;

import android.location.Location;

public class Room {
    String groundName;
    float dist_in_km;
    Location location;

    public Room(String gn, float d){
        groundName=gn;
        dist_in_km=d;
    }

    public String getDist_in_km() {
        return Float.toString(dist_in_km);
    }

    public String getGroundName() {
        return groundName;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
