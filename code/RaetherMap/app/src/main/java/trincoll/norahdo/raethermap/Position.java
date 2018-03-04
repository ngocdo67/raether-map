package trincoll.norahdo.raethermap;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ngocdo67 on 3/3/18.
 */

public class Position {
    private Level level;
    private LatLng coordinate;

    public Position (Level level, LatLng coordinate) {
        this.level = level;
        this.coordinate = coordinate;
    }

    public Level getLevel () {
        return level;
    }

    public LatLng getCoordinate () {
        return coordinate;
    }
}
