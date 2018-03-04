package trincoll.norahdo.raethermap;

/**
 * Created by ngocdo67 on 3/3/18.
 */

public enum Level {
    A ("Level A"),
    B ("Level B"),
    C ("Level C"),
    ONE ("Level 1"),
    TWO ("Level 2"),
    THREE ("Level 3"),
    TEST ("Test level");

    private final String name;

    Level (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }
}
