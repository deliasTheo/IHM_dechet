package edu.polytech.ihmtd2dechet.activities;



import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

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
import edu.polytech.ihmtd2dechet.adapter.ReportAdapter;
import edu.polytech.ihmtd2dechet.objects.Report;
import edu.polytech.ihmtd2dechet.objects.ReportsList;

public class LandscapeActivity  extends AppCompatActivity {


    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isLandcape()) {
            setContentView(R.layout.activity_landscape_list);
            ReportAdapter adapter = new ReportAdapter(getApplicationContext(), ReportsList.getInstance().get(), getLayoutInflater());
            ListView listView = findViewById(R.id.liste_signalement);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                Report report = (Report) adapter.getItem(position);
                showStatusDialog(report, adapter);
            });
            includeMap();
        }else {
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);

            finish();

        }

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


    private boolean isLandcape(){
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        if(width < height){
            return false  ;
        } else {
            return true ;
        }
    }

    private void showStatusDialog(Report report, ReportAdapter adapter) {
        String[] statuses = {"A faire", "En cours", "Finis"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choisissez le statut")
                .setItems(statuses, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            report.setAdvancement("A faire");
                            break;
                        case 1:
                            report.setAdvancement("En cours");
                            break;
                        case 2:
                            report.setAdvancement("Finis");
                            break;
                    }
                    adapter.notifyDataSetChanged();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }



}







