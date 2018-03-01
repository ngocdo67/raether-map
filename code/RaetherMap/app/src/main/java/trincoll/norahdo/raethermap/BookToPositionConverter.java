package trincoll.norahdo.raethermap;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by ngocdo67 on 2/28/18.
 */

public class BookToPositionConverter {
    private HashMap <String, LatLng> coordinateConverter;

    public BookToPositionConverter () {
        coordinateConverter = new HashMap<>();
        buildInitialMap();
    }

    public LatLng getPosition (String title) {
        return coordinateConverter.get(title) /*== null ? new LatLng(41.7476,-72.691) : coordinateConverter.get(title)*/;
    }
    private void buildInitialMap () {
        addBook("QA76.9.D35 A38 1983", new LatLng(41.7476,-72.691));
        addBook("QA76.76.J38 G66 2006", new LatLng(41.7476,-72.691));
        addBook("QA76.73.J38 G66 2014", new LatLng(41.7476,-72.691));
    }

    private boolean addBook (String callNumber, LatLng position) {
        coordinateConverter.put(callNumber, position);
        return true;
    }
}
