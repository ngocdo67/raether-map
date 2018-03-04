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

    public Position getPosition (String callNumber) {
        Level level = buildLevel(callNumber);
        LatLng coordinate = buildCoordinate(callNumber);
        return new Position(level, coordinate);
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

    private Level buildLevel (String callNumber) {
        if (callNumber != null && callNumber.length() > 7 && callNumber.contains("QUARTO")) {
            callNumber = callNumber.substring(7);
        }
        if ("ABCDEFG".contains(String.valueOf(callNumber.charAt(0)))
                || (callNumber.length() > 1
                && callNumber.charAt(0) == 'T' && callNumber.charAt(1) <= 'R')) {
            return Level.THREE;
        } else if ("HJKLMN".contains(String.valueOf(callNumber.charAt(0)))
                || (callNumber.length() > 1
                && callNumber.charAt(0) == 'T' && callNumber.charAt(1) > 'R')) {
            return Level.TWO;
        } else if ("P".contains(String.valueOf(callNumber.charAt(0)))) {
            return Level.ONE;
        } else {
            return Level.A;
        }
    }

    private LatLng buildCoordinate (String callNumber) {
//        return new LatLng(41.7476,-72.691);
        return new LatLng(41.7441, -72.6919);
    }
}
