package id.ac.farz.mfarishanifw_uas.sidebar.map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import id.ac.farz.mfarishanifw_uas.MapActivity;
import id.ac.farz.mfarishanifw_uas.R;

import static android.content.Context.MODE_PRIVATE;

public class MapFragment extends Fragment {

    public static final int FASTEST_INTERVAL = 5;
    public static final int DEFAULT_INTERVAL = 30;
    private static final int PERMISSIONS_FINE_LOCATION = 25;
    TextView tv_lat, tv_lon, tv_altitude, tv_accuracy, tv_speed, tv_sensor, tv_updates, tv_address;
    Button btn_showMap,btn_myHome;
    Switch sw_locationupdates, sw_gps;

    public static final String PREFERENCE_NAME = "location";
    SharedPreferences sharedPreferences;
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;

    boolean updateOn = false;

    Location currentLocation;

    List<Location> markedLocations;

    LocationRequest locationRequest;

    LocationCallback locationCallBack;

    FusedLocationProviderClient fusedLocationProviderClient;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_map, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_lat = getView().findViewById(R.id.tv_lat);
        tv_lon = getView().findViewById(R.id.tv_lon);
        tv_altitude = getView().findViewById(R.id.tv_altitude);
        tv_accuracy = getView().findViewById(R.id.tv_accuracy);
        tv_speed = getView().findViewById(R.id.tv_speed);
        tv_sensor = getView().findViewById(R.id.tv_sensor);
        tv_updates = getView().findViewById(R.id.tv_updates);
        tv_address = getView().findViewById(R.id.tv_address);

        btn_showMap = getView().findViewById(R.id.btn_showMap);
        btn_myHome = getView().findViewById(R.id.btn_myHouse);

        sw_gps = getView().findViewById(R.id.sw_gps);
        sw_locationupdates = getView().findViewById(R.id.sw_locationsupdates);

        locationRequest = new LocationRequest();

        locationRequest.setInterval(300 * DEFAULT_INTERVAL);

        locationRequest.setFastestInterval(1000 * FASTEST_INTERVAL);

        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        locationCallBack = new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                updateUIValues(locationResult.getLastLocation());
            }
        };


        btn_showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPreferences != null) {
                    sharedPreferences = getContext().getSharedPreferences("Locate", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Location", "myLocation");
                    //edit.clear();
                    editor.commit();
                }
                Intent i = new Intent(getActivity(), MapActivity.class);
                startActivity(i);

            }
        });

        btn_myHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getContext().getSharedPreferences("Locate", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Location","Home");
                editor.commit();
                Intent i = new Intent(getActivity(), MapActivity.class);
                startActivity(i);
            }
        });

        sw_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sw_gps.isChecked()) {
                    //menggunakan GPS untuk hasil akurat
                    locationRequest.setPriority(locationRequest.PRIORITY_HIGH_ACCURACY);
                    tv_sensor.setText("Menggunakan sensor GPS");
                } else {
                    locationRequest.setPriority(locationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                    tv_sensor.setText("Menggunakan tower atau WIFI");
                }
            }
        });

        sw_locationupdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sw_locationupdates.isChecked()) {
                    //menyalakan fungsi update lokasi di hp
                    startLocationUpdates();
                } else {
                    //mematikan fungsi update lokasi di hp
                    stopLocationUpdates();
                }
            }
        });

        updateGPS();
    }


    private void startLocationUpdates() {
        tv_updates.setText("Lokasi Anda sedang direkam");
        //ini adalah blok kode yang dihasilkan oleh permission check yang diminta rLU
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null); //DOK: pada line ini terjadi error rLU meminta blok kode di atas untuk mengecek permission
        updateGPS();
    }

    private void stopLocationUpdates() {
        tv_updates.setText("N/A");
        tv_lat.setText("N/A");
        tv_lon.setText("N/A");
        tv_address.setText("N/A");
        tv_speed.setText("N/A");
        tv_accuracy.setText("N/A");
        tv_altitude.setText("N/A");
        tv_sensor.setText("N/A");

        fusedLocationProviderClient.removeLocationUpdates(locationCallBack);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSIONS_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateGPS();
                } else {
                    Toast.makeText(getActivity(), "App ini perlu izin dari Anda agar bekerja dengan baik!", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
                break;
        }
    }

    private void updateGPS() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Location location = locationManager.getLastKnownLocation(bestProvider);
            if (location != null) {
                updateUIValues(location);
                currentLocation = location;
            } else {
                //locationManager.requestLocationUpdates(bestProvider, 1000, 0, getActivity());
            }
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (fusedLocationProviderClient.getLastLocation() != null) {


                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        //direstui oleh user
                        updateUIValues(location);
                        currentLocation = location;
                    }
                });
            } else {
                //kalau masih belum direstui
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
                }
            }
        }
        else{
            //locationManager.requestLocationUpdates(, 1000, 0, this);
        }
    }




    private void updateUIValues(Location location) {

            tv_lat.setText(String.valueOf(location.getLatitude()));
            tv_lon.setText(String.valueOf(location.getLongitude()));
            tv_accuracy.setText(String.valueOf(location.getAccuracy()));


            if (location.hasAltitude()) {
                tv_altitude.setText(String.valueOf(location.getAltitude()));
            } else {
                tv_altitude.setText("Tidak Tersedia");
            }

            if (location.hasSpeed()) {
                tv_speed.setText(String.valueOf(location.getSpeed()));
            } else {
                tv_speed.setText("Tidak Tersedia");
            }


        Geocoder geocoder = new Geocoder(getActivity());

        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
            tv_address.setText(addresses.get(0).getAddressLine(0)); //Address line berarti jalan, sebenarnya bisa yang lain seperti kode pos atau apa saja yang disediakan Geocoder
        }
        catch (Exception e) {
            tv_address.setText("Gagal menemukan alamat");
        }

    }

}