package com.example.mtta.googlemapapi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mtta.googlemapapi.fragment.InfoDialogFragment;
import com.example.mtta.googlemapapi.model.MapData;
import com.example.mtta.googlemapapi.model.MapMarker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, MapDataCallBack, View.OnClickListener {
    Button btnQuanAn;
    Button btnDiaDiem;
    Button btnKhachSan;
    private GoogleMap mMap;
    private MapData mapData;
    private ArrayList<MapMarker> listDiaDiem = new ArrayList<>();
    private ArrayList<MapMarker> listQuanAn = new ArrayList<>();
    private ArrayList<MapMarker> listKhachSan = new ArrayList<>();
    private boolean mLocationPerMissionGranted;
    private LocationManager mLocationManager;
    Location loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnQuanAn = (Button) findViewById(R.id.btnQuanAn);
        btnDiaDiem = (Button) findViewById(R.id.btnDiaDiem);
        btnKhachSan = (Button) findViewById(R.id.btnKhachSan);

        btnQuanAn.setOnClickListener(this);
        btnDiaDiem.setOnClickListener(this);
        btnKhachSan.setOnClickListener(this);

        MapDataAsyncTask mapDataAsyncTask = new MapDataAsyncTask(this);
        mapDataAsyncTask.execute(10.0, 10.0);

        //Khoi tao lay vi tri nguoi dung va kiem tra quyen
        mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //1
            //Lấy giá trị vị trí cũ
            loc = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (loc!=null)
                Toast.makeText(getApplicationContext(), String.valueOf(loc.getLatitude()), Toast.LENGTH_SHORT).show();
            //2
            else
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0, mLocationListener);
        }else{
            Toast.makeText(this, "Xin quyen", Toast.LENGTH_SHORT).show();
        }

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
        //Kiểm tra và xin quyền lấy vị trí người dùng
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mLocationPerMissionGranted = true;
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        if(mLocationPerMissionGranted){
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else{
            mMap.setMyLocationEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            //mLastKnownLocation = null;
        }

        // Xử lý sự kiện click vào marker
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                InfoDialogFragment dialog = new InfoDialogFragment();
                final LatLng viTri = marker.getPosition();
                final MapMarker mapMarker = new MapMarker();
                mapMarker.setTen(marker.getTitle());
                mapMarker.setLat(viTri.latitude);
                mapMarker.setLng(viTri.longitude);
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ChiDuong(mapMarker.getLat(), mapMarker.getLng());
                    }
                };
                dialog.setMapData(mapMarker, onClickListener);
                dialog.show(getSupportFragmentManager(), "info");

                return false;
            }
        });
    }

    private void ChiDuong(double lat, double lng) {
    }

    @Override
    public void DiaDiemCallBack(MapData mapData) {
        this.mapData = mapData;
        MapMarker mapMarker;
        for (int i = 0; i < mapData.getMapMarkerTranfers().size(); i++) {
            mapMarker = mapData.getMapMarkerTranfers().get(i);
            //Phân loại
            switch (mapMarker.getLoaiMarKer()){
                case "DiaDiem":
                    listDiaDiem.add(mapMarker);
                    break;
                case "QuanAn":
                    listQuanAn.add(mapMarker);
                    break;
                case "KhachSan":
                    listKhachSan.add(mapMarker);
                    break;
            }
            LatLng test = new LatLng(mapMarker.getLat(), mapMarker.getLng());
            mMap.addMarker(new MarkerOptions().position(test).title(mapMarker.getTen()));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(mapData.getMapMarkerTranfers().get(0).getLat(), mapData.getMapMarkerTranfers().get(0).getLng())));

        //mMap.clear;
    }

    @Override
    public void onClick(View v) {
        MapMarker mapMarker;
        switch (v.getId()){
            case R.id.btnDiaDiem:
                mMap.clear();
                for (int i = 0; i < listDiaDiem.size(); i++) {
                    mapMarker = listDiaDiem.get(i);
                    mMap.addMarker(new MarkerOptions().position(new LatLng(mapMarker.getLat(), mapMarker.getLng())).title(mapMarker.getTen()).snippet(mapMarker.getMoTa()));
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(listDiaDiem.get(0).getLat(), listDiaDiem.get(0).getLng())));
                break;
            case R.id.btnQuanAn:
                mMap.clear();
                for (int i = 0; i < listQuanAn.size(); i++) {
                    mapMarker = listQuanAn.get(i);
                    mMap.addMarker(new MarkerOptions().position(new LatLng(mapMarker.getLat(), mapMarker.getLng())).title(mapMarker.getTen()));
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(listQuanAn.get(0).getLat(), listDiaDiem.get(0).getLng())));
                break;
            case R.id.btnKhachSan:
                mMap.clear();
                if (listKhachSan.size() == 0)
                    break;
                for (int i = 0; i < listKhachSan.size(); i++) {
                    mapMarker = listKhachSan.get(i);
                    mMap.addMarker(new MarkerOptions().position(new LatLng(mapMarker.getLat(), mapMarker.getLng())).title(mapMarker.getTen()));
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(listKhachSan.get(0).getLat(), listDiaDiem.get(0).getLng())));
                break;
        }
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //Lấy giá trị vị trí
            loc = location;
            Toast.makeText(getApplicationContext(), String.valueOf(location.getLatitude()), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}
