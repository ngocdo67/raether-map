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
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

class GetOneElement {
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
    url = rootUrl + question + path + and + apiKey;
    try {
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new URL(url).openStream());
      Element rootElement = doc.getDocumentElement();
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult (new File ("./readapi.xml"));
      transformer.transform (source, result);
      System.out.println ("File saved");
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
