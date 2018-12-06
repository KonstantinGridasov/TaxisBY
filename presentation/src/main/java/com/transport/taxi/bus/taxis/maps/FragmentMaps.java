package com.transport.taxi.bus.taxis.maps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.domain.entity.base.HaltDomain;
import com.transport.taxi.bus.taxis.domain.entity.base.TaxisDomain;

import java.util.List;

public class FragmentMaps extends Fragment implements OnMapReadyCallback {

    public static final String TEXT_KEY = "TEXT_KEY";

    private GoogleMap mMap;
    private TaxisDomain taxisDomain;

    public static FragmentMaps newInstance(TaxisDomain taxis) {
        FragmentMaps fragment = new FragmentMaps();

        Bundle bundle = new Bundle();
        bundle.putSerializable(TEXT_KEY, taxis);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            taxisDomain = (TaxisDomain) getArguments().getSerializable(TEXT_KEY);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        if (taxisDomain != null) {
            drawArrowWithLine(taxisDomain.getDirectHalt(), true);
            drawArrowWithLine(taxisDomain.getReverseHalt(), false);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder()
                .zoom(10)
                .target(new LatLng(53.902621, 27.561454))
                .build()));
    }

    private void drawArrowWithLine(List<HaltDomain> listHalt, Boolean b) {

        int color, iconHalt, iconArrow;

        if (b) {
            color = Color.BLUE;
            iconHalt = R.drawable.halt_maps_direct;
            iconArrow = R.drawable.ic_arrow_direct;
        } else {
            color = Color.RED;
            iconHalt = R.drawable.halt_maps_reverse;
            iconArrow = R.drawable.ic_arrow_reverse;
        }

        for (int i = 0; i < listHalt.size(); i++) {
            LatLng l = new LatLng(listHalt.get(i).getLat(),
                    listHalt.get(i).getLng());

            mMap.addMarker(new MarkerOptions()
                    .position(l)
                    .title(listHalt.get(i).getHaltName())
                    .icon(BitmapDescriptorFactory.fromResource(iconHalt)));

            if ((i + 1) < listHalt.size()) {

                mMap.addPolyline(new PolylineOptions()
                        .color(color)
                        .width(6)
                        .add(l)
                        .add(new LatLng(
                                (listHalt.get(i).getLat() + listHalt.get(i + 1).getLat()) / 2,
                                (listHalt.get(i).getLng() + listHalt.get(i + 1).getLng()) / 2))
                        .endCap(new CustomCap(BitmapDescriptorFactory.fromResource(iconArrow), 7)));

            }
        }
    }
}

