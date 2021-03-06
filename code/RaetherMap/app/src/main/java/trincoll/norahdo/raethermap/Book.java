package trincoll.norahdo.raethermap;

/**
 * Created by ngocdo67 on 2/11/18.
 */

public class Book {
    private String title;
    private String callNumber;
    private String barcode;

    public Book () {

    }
    public Book(String title, String callNumber, String barcode) {
        this.title = title;
        this.callNumber = callNumber;
        this.barcode = barcode;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String toString () {
        return "Title: " + title + " Call Number: " + callNumber + " Barcode: " + barcode;
    }
}
