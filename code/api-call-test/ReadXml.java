import java.lang.*;
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
    try {
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new URL(url).openStream());
      Element rootElement = doc.getDocumentElement();
      NodeList nodes = rootElement.getElementsByTagName("Column2");
      String isFinished = rootElement.getElementsByTagName("IsFinished").item(0).getTextContent();
      resumptionToken = "token=" + rootElement.getElementsByTagName ("ResumptionToken").item(0).getTextContent();
      ArrayList<String> elements = new ArrayList<String>();
      for (int i=0; i < nodes.getLength(); i++){
        //System.out.println (nodes.item(i).getTextContent());
        elements.add(nodes.item(i).getTextContent());
      }
      while (isFinished.equals("false")){
        db = dbf.newDocumentBuilder();
        url = rootUrl + question + resumptionToken + and + path + and + limit + and + apiKey;
        //System.out.println ("Url: " + url);
        doc = db.parse(new URL(url).openStream());
        rootElement = doc.getDocumentElement();
        //System.out.println (rootElement.getTextContent());
        isFinished = rootElement.getElementsByTagName("IsFinished").item(0).getTextContent();
        nodes = rootElement.getElementsByTagName("Column2");
        for (int i=0; i < nodes.getLength(); i++){
          //System.out.println (nodes.item(i).getTextContent());
          elements.add(nodes.item(i).getTextContent());
        }
        System.out.println ("Node: " + nodes.getLength() + "Size: " + elements.size());
      }
      System.out.println ("Size: " + elements.size());


    } catch (Exception e) {
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
