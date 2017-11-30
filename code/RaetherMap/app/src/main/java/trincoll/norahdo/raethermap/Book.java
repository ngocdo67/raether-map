package trincoll.norahdo.raethermap;

/**
 * Created by ngocdo67 on 11/27/17.
 */

public class Book {
    private String title;
    private String callNumber;
    private String serialNumber;

    public Book(String title, String callNumber, String serialNumber) {
        this.title = title;
        this.callNumber = callNumber;
        this.serialNumber = serialNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
