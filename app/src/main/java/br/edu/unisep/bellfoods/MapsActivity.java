package br.edu.unisep.bellfoods;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import br.edu.unisep.bellfoods.vo.PratoVO;

public class MapsActivity extends Activity {
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        PratoVO prato = (PratoVO) intent.getSerializableExtra("prato");

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        Double latitude = Double.valueOf(prato.getEstabelecimento().getLatitude());
        Double longitude = Double.valueOf(prato.getEstabelecimento().getLongitude());
        Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(prato.getNome()));

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null) {
            Marker eu = map.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Eu").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
        }

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15);
        map.animateCamera(cameraUpdate);

    }

}