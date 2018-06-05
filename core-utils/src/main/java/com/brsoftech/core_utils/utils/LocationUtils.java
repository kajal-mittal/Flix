package com.brsoftech.core_utils.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * <p><b>Author: Kartik Sharma @ B.R. Softech<b></p>
 * <p>Date: 20/8/16</p>
 * <p>Time: 10:22 AM</p>
 * <p>Project: Planbeep</p>
 */
public class LocationUtils {
    private static final String ERR_MSG_NO_LOCATION_FOUND = "No location available";

    public static void getLocation(final Context context, final GetLocationListener getLocationListener) {
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                getLocationListener.onLocationSuccess(getAddressFromLatLon(context,
                        location.getLatitude(), location.getLongitude()));
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
                getLocationListener.onLocationFailure(ERR_MSG_NO_LOCATION_FOUND);
            }
        };

        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            getLocationListener.onLocationFailure(ERR_MSG_NO_LOCATION_FOUND);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    public static Address getAddressFromLatLon(Context context, double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            /*String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL*/

            return addresses.get(0);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public interface GetLocationListener {
        void onLocationSuccess(Address address);

        void onLocationFailure(String message);
    }
}
