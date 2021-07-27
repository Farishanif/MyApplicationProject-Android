package id.ac.farz.mfarishanifw_uas;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static LatLng posisi = null;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        sharedPreferences = this.getSharedPreferences("Locate", MODE_PRIVATE);
        if(sharedPreferences.getString("Location" , "") == "Home"){
            posisi = new LatLng(-6.368473, 106.824644);
            Toast.makeText(this, "PNJ", Toast.LENGTH_SHORT).show();

        }
        else if(sharedPreferences.getString("Location" , "") == "myLocation") {
            LocationManager locMgr = (LocationManager)
                    this.getSystemService(LOCATION_SERVICE);
            String locProvider = LocationManager.NETWORK_PROVIDER;

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            Location lastKnownLocation = locMgr.getLastKnownLocation(locProvider);

            // Add a marker di posisi saya
            posisi = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
            Toast.makeText(this, "You're Location", Toast.LENGTH_SHORT).show();
        }
        else{}
        if(posisi != null){
        mMap.addMarker(new MarkerOptions().position(posisi).title("Posisi Saya"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posisi));}
        else{
            Toast.makeText(this,"Posisi null, harap coba lagi setelah 1 menit", Toast.LENGTH_SHORT).show();
        }


        }
    }



