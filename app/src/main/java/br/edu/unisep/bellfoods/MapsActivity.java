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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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

//        map.animateCamera(CameraUpdateFactory.zoomTo(3), 2000, null);
//        map.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)),4000,null);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 18);
        map.animateCamera(cameraUpdate);

    }

}