package trincoll.norahdo.raethermap;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;
import com.indooratlas.android.sdk.IARegion;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Raether Map";
    private final int CODE_PERMISSIONS = 0;
    private IALocationManager mIALocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] neededPermissions = {
                android.Manifest.permission.CHANGE_WIFI_STATE,
                android.Manifest.permission.ACCESS_WIFI_STATE,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
        };
        ActivityCompat.requestPermissions( this, neededPermissions, CODE_PERMISSIONS );

        mIALocationManager = IALocationManager.create(this);
        mIALocationManager.registerRegionListener(mRegionListener);
    }

    private IALocationListener mIALocationListener = new IALocationListener() {

        // Called when the location has changed.
        @Override
        public void onLocationChanged(IALocation location) {

            Log.d(TAG, "Latitude: " + location.getLatitude());
            Log.d(TAG, "Longitude: " + location.getLongitude());
            Log.d(TAG, "Floor number: " + location.getFloorLevel());
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            //TODO: do something here
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mIALocationManager.requestLocationUpdates(IALocationRequest.create(), mIALocationListener);
    }

    private IARegion.Listener mRegionListener = new IARegion.Listener() {
        // when null, we are not on any mapped area
        // this information can be used for indoor-outdoor detection
        IARegion mCurrentFloorPlan = null;

        @Override
        public void onEnterRegion(IARegion region) {
            if (region.getType() == IARegion.TYPE_FLOOR_PLAN) {
                // triggered when entering the mapped area of the given floor plan
                Log.d(TAG, "Entered " + region.getName());
                Log.d(TAG, "floor plan ID: " + region.getId());
                mCurrentFloorPlan = region;
            }
            else if (region.getType() == IARegion.TYPE_VENUE) {
                // triggerend when near a new location
                Log.d(TAG, "Location changed to " + region.getId());
            }
        }

        @Override
        public void onExitRegion(IARegion region) {
            // leaving a previously entered region
            if (region.getType() == IARegion.TYPE_FLOOR_PLAN) {
                mCurrentFloorPlan = null;
                // notice that a change of floor plan (e.g., floor change)
                // is signaled by an exit-enter pair so ending up here
                // does not yet mean that the device is outside any mapped area
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //Handle if any of the permissions are denied, in grantResults
    }
}
