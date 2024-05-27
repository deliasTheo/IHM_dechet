package edu.polytech.ihmtd2dechet.activities;

import static com.google.android.gms.common.util.DeviceProperties.isTablet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;


import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

import edu.polytech.ihmtd2dechet.R;
import edu.polytech.ihmtd2dechet.objects.ReportsList;

public class MapActivity extends AppCompatActivity  {

    private static final ReportsList wasteList = ReportsList.getInstance();

    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isTablet()) {

            Intent intent = new Intent(this, TabletActivity.class);
            startActivity(intent);

            finish();

        }




        else {

            setContentView(R.layout.activity_map);
            includeMap();
        }



    }





    @Override
    public void onPause(){
        super.onPause();
        map.onPause();

    }
    @Override
    public void onResume(){
        super.onResume();
        map.onResume();
    }



    private void includeMap() {
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        map = findViewById(R.id.mapView);
        map.setTileSource(TileSourceFactory.MAPNIK);    // render
        map.setBuiltInZoomControls(true);               // roomable
        GeoPoint startPoint = new GeoPoint(43.65020, 7.00517);
        IMapController mapController = map.getController();
        mapController.setZoom(18.0);
        mapController.setCenter(startPoint);

        ArrayList<OverlayItem> items= new ArrayList<>();
        items.add(new OverlayItem("Signalement 1", "", new GeoPoint(43.65020, 7.00517)));
        items.add(new OverlayItem("Signalement 2", "", new GeoPoint(43.64950, 7.00517)));

        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(),
                items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });

        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);
    }

    private boolean isTablet() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float screenWidthInDp = displayMetrics.widthPixels / displayMetrics.density;
        float screenHeightInDp = displayMetrics.heightPixels / displayMetrics.density;
        float screenSize = Math.min(screenWidthInDp, screenHeightInDp);

        return screenSize >= 700;
    }







}