package com.example.admin.myproject.activities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.admin.myproject.R;
import com.example.admin.myproject.utils.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final int LOCATION_PERMISSION = 1000;
    private final String TAG = MapsActivity.class.getSimpleName();
    private final int ZOOM_LEVEL = 15;
    private GoogleMap map;
    private Marker originMarker;
    private Marker destMarker;
    private Polyline polyline;
    private View distanceView;
    private TextView tvDistance;
    private float[] distance;
    private Marker distanceMarker;
    private Bitmap distanceIcon;
    private BitmapDescriptor distanceDescriptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
    }

    @AfterPermissionGranted(LOCATION_PERMISSION)
    private void checkPermissions() {
        String[] permission = new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, permission)) {
            checkGps();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.location_permission), LOCATION_PERMISSION, permission);
        }
    }

    private void checkGps() {
        if (!Utils.isGpsEnabled(this)) {
            Utils.showGPSDialog(this);
        } else {
            setMyLocation();
        }
    }

    private void setMyLocation() {
        SmartLocation.with(this).location().oneFix().start(new OnLocationUpdatedListener() {
            @Override
            public void onLocationUpdated(Location location) {
                addOriginMarker(new LatLng(location.getLatitude(), location.getLongitude()));
                setMapClickListener();
            }
        });
    }

    private void addOriginMarker(LatLng latLng) {
        originMarker = map.addMarker(new MarkerOptions().position(latLng));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_LEVEL));
    }

    private void addDestMaker(LatLng latLng) {
        destMarker = map.addMarker(new MarkerOptions().position(latLng));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_LEVEL));
    }

    private void setMapClickListener() {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (originMarker == null) {
                    addOriginMarker(latLng);
                } else {
                    if (destMarker != null) {
                        originMarker.remove();
                        originMarker = destMarker;
                        polyline.remove();
                    }
                    addDestMaker(latLng);
                    drawPolyline();
                    addDistanceMarker();
                }
            }
        });
    }

    private void addDistanceMarker() {
        if (distanceView == null) {
            distanceView = LayoutInflater.from(this).inflate(R.layout.layout_distance, null);
            tvDistance = (TextView) distanceView.findViewById(R.id.tvDistance);
        }
        setDistance();
        distanceIcon = Utils.createBitmapFromView(distanceView);
        distanceDescriptor = BitmapDescriptorFactory.fromBitmap(distanceIcon);
        if (distanceMarker != null) {
            distanceMarker.remove();
        }
        distanceMarker = map.addMarker(new MarkerOptions().position(Utils.getMiddle(originMarker, destMarker)).icon(distanceDescriptor));
    }

    private void setDistance() {
        distance = new float[1];
        Location.distanceBetween(originMarker.getPosition().latitude,
                originMarker.getPosition().longitude, destMarker.getPosition().latitude, destMarker.getPosition().longitude, distance);
        tvDistance.setText(String.format(getString(R.string.distance), distance[0]));
    }


    private void drawPolyline() {
        polyline = map.addPolyline(new PolylineOptions().
                add(originMarker.getPosition(), destMarker.getPosition()).
                width(5).
                color(Color.RED));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }

    @OnClick(R.id.btnReset)
    public void reset() {
        map.clear();
        originMarker = null;
        destMarker = null;
        distanceMarker = null;
    }
}
