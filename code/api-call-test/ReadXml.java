import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.*;
import java.util.*;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.XMLReader;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
class ReadXml {
    public static void main (String[] args){
        JSONObject jsonObject = new JSONObject();
        JSONArray books = new JSONArray();
        String url;
        String question = "?";
        String and = "&";
        String rootUrl = "https://api-na.hosted.exlibrisgroup.com/almaws/v1/analytics/reports";
        String path = "path=%2fshared%2fCTW%20Consortium%3a%20Trinity%20College%2fReports%2fItems%20for%20Norah&Options=rmf";
        String limit = "limit=1000";
        String apiKey = "apikey=l7xx0e100ce8116041fe8251ba93f314388b";
        String resumptionToken = "";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //String url = "https://api.github.com/users/petrgazarov/events%7B/privacy%7D";
        url = rootUrl + question + path + and + limit + and + apiKey;
        int index = 0;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL(url).openStream());
            Element rootElement = doc.getDocumentElement();
            NodeList rows = rootElement.getElementsByTagName("Row");
            String isFinished = rootElement.getElementsByTagName("IsFinished").item(0).getTextContent();
            resumptionToken = "token=" + rootElement.getElementsByTagName ("ResumptionToken").item(0).getTextContent();
            ArrayList<String> elements = new ArrayList<String>();
            for (int i=0; i < rows.getLength(); i++){
                //System.out.println (nodes.item(i).getTextContent());
                //elements.add(rows.item(i).getTextContent());
                Element row = (Element) rows.item(i);
                NodeList serialNumbers = row.getElementsByTagName("Column5");
                NodeList titles = row.getElementsByTagName("Column2");
                NodeList callNumbers = row.getElementsByTagName("Column3");
                JSONObject bookJson = new JSONObject();
                if (serialNumbers != null && serialNumbers.item(0) != null) {
                    bookJson.put ("barcode", serialNumbers.item(0).getTextContent());
                }
                if (titles != null && titles.item(0) != null) {
                    bookJson.put ("title", titles.item(0).getTextContent());
                }
                if (callNumbers != null && callNumbers.item(0) != null) {
                    bookJson.put ("callNumber", callNumbers.item(0).getTextContent());
                }
                books.put(bookJson);
                System.out.println (index++);
            }
            System.out.println (jsonObject.toString());
            while (isFinished.equals("false")){
                db = dbf.newDocumentBuilder();
                url = rootUrl + question + resumptionToken + and + path + and + limit + and + apiKey;
                //System.out.println ("Url: " + url);
                doc = db.parse(new URL(url).openStream());
                rootElement = doc.getDocumentElement();
                //System.out.println (rootElement.getTextContent());
                isFinished = rootElement.getElementsByTagName("IsFinished").item(0).getTextContent();
                rows = rootElement.getElementsByTagName("Row");
                for (int i=0; i < rows.getLength(); i++){
//                    System.out.println (rows.item(i).getTextContent());
                    Element row = (Element) rows.item(i);
                    NodeList serialNumbers = row.getElementsByTagName("Column5");
                    NodeList titles = row.getElementsByTagName("Column2");
                    NodeList callNumbers = row.getElementsByTagName("Column3");
                    JSONObject bookJson = new JSONObject();
                    if (serialNumbers != null && serialNumbers.item(0) != null) {
                        bookJson.put ("barcode", serialNumbers.item(0).getTextContent());
                    }
                    if (titles != null && titles.item(0) != null) {
                        bookJson.put ("title", titles.item(0).getTextContent());
                    }
                    if (callNumbers != null && callNumbers.item(0) != null) {
                        bookJson.put ("callNumber", callNumbers.item(0).getTextContent());
                    }
                    books.put(bookJson);
                    System.out.println (index++);
                }
               // System.out.println (jsonObject.toString());
//                System.out.println ("Node: " + rows.getLength() + "Size: " + elements.size());
            }

            jsonObject.put("Books", books);
//            System.out.println ("Size: " + elements.size());


        } catch (Exception e) {
            e.printStackTrace();
        }


        try{
//            FileWriter file = new FileWriter("src/library_database_1111_2.json");
            FileWriter file = new FileWriter("src/read_xml_updated.json");
            file.write(jsonObject.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // try{
        //   XMLReader myReader = XMLReaderFactory.createXMLReader();
        //   //myReader.setContentHandler(handler);
        //   myReader.parse(new InputSource(new URL(url).openStream()));
        // }
        // catch(Exception e){
        //   e.printStackTrace();
        // }
    }
}
