package com.ibuildapp.moveinandmoveout;

import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.location.Location;
import android.widget.TextView;

import com.appbuilder.sdk.android.AppBuilderModuleMainAppCompat;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ibuildapp.moveinandmoveout.api.maps.GoogleMapsApiService;
import com.ibuildapp.moveinandmoveout.api.maps.MapResponse;
import com.ibuildapp.moveinandmoveout.utils.GeoUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.ibuildapp.moveinandmoveout.utils.rx.SimpleSubscriber;

import java.util.HashSet;
import java.util.Set;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NativeMapActivity extends AppBuilderModuleMainAppCompat implements OnMapReadyCallback {


    private String adrs;

    private View backToMyLocation;
    private View userDirection;

    private GoogleMap mapObject;
    private GeoUtils.CenterCalculator calculator;
    private Location myLocation;
    private LatLng pointLocation;

    @Override
    public void create() {
        super.create();
        setContentView(R.layout.moveinandmoveout_map);
        initContent();
        backToMyLocation = findViewById(R.id.moveinandmoveout_map_back_to_my_location);
        userDirection = findViewById(R.id.moveinandmoveout_map_user_direction);

        setTopBarTitle(getString(R.string.moveinandmoveout_map));

        setTopBarLeftButtonTextAndColor(getResources().getString(R.string.common_back_upper),
                getResources().getColor(android.R.color.black), true, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
        setTopBarTitleColor(getResources().getColor(android.R.color.black));
        setTopBarBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());


        backToMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapObject != null) {
                    Location myLocation = mapObject.getMyLocation();
                    if (myLocation != null)
                        goToMyLocation();
                }
            }
        });

        userDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMapActivity();
            }
        });

        SupportMapFragment mapFragment = new SupportMapFragment();
        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction()
                .replace(R.id.map_main_container, mapFragment)
                .commit();

        mapFragment.getMapAsync(this);
    }

    private void initContent() {
        Intent intent = getIntent();
        adrs =  intent.getStringExtra(MoveInandMoveOutContants.ADDRESS);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapObject = googleMap;
        mapObject.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapObject.getUiSettings().setMapToolbarEnabled(false);
        mapObject.setMyLocationEnabled(true);

        mapObject.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.moveinandmoveout_map_info, null);
                TextView title = (TextView) v.findViewById(R.id.moveinandmoveout_map_info_title);
                title.setText(adrs);
                return v;
            }
        });

        String addressString = adrs == null ? "" :adrs;

        Address address;
        try {
            address = GeoUtils.getAddressByName(NativeMapActivity.this, addressString);
            MarkerOptions builder = new MarkerOptions();
            builder.position(new LatLng(address.getLatitude(), address.getLongitude()));
            mapObject.addMarker(builder);
            pointLocation = builder.getPosition();

            myLocation = mapObject.getMyLocation();
            if (myLocation == null)
                mapObject.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location location) {
                        myLocation = location;
                        mapObject.setOnMyLocationChangeListener(null);
                        goToMyLocation();
                    }
                });

        } catch (Exception e) {
            Log.e(NativeMapActivity.class.getSimpleName(), e.getMessage());
            tryFromApi(addressString);
        }
    }

    private void tryFromApi(String addressString) {
        new GoogleMapsApiService().getIBApi().getAddressLocation(addressString, "true")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleSubscriber<MapResponse>(){
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(MapResponse mapResponse) {
                        com.ibuildapp.moveinandmoveout.api.maps.Location coordinates = mapResponse.getResults()[0].getGeometry().getLocation();
                        MarkerOptions builder = new MarkerOptions();
                        builder.position(new LatLng(coordinates.getLat(), coordinates.getLng()));
                        mapObject.addMarker(builder);
                        pointLocation = builder.getPosition();

                        myLocation = mapObject.getMyLocation();
                        if (myLocation == null)
                            mapObject.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                                @Override
                                public void onMyLocationChange(Location location) {
                                    myLocation = location;
                                    mapObject.setOnMyLocationChangeListener(null);
                                    goToMyLocation();
                                }
                            });
                    }
                });
    }

    private void goToMyLocation(){
        Set<LatLng> set = new HashSet<>();
        if (pointLocation != null) {
            set.add(pointLocation);
        } else
        {
            set.add(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()));
        }




        if (calculator == null)
            calculator = new GeoUtils.CenterCalculator();

        calculator.init(set);
        mapObject.animateCamera(CameraUpdateFactory.newLatLngZoom(calculator.getCenter(),
                calculator.getInitZoom()));

    }

    public void launchMapActivity() {
        try {
            String url = "http://maps.google.com/maps?daddr=" + pointLocation.latitude + "," + pointLocation.longitude;
            Uri gmmIntentUri = Uri.parse(url);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
            overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);
    }
}
