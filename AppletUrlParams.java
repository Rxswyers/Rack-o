/** File: AppletUrlParams.java
*       Demo of accessing html form elements from an applet
*       Adapted from example at Real's Java How-to
*/
import java.applet.Applet;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class AppletUrlParams extends Applet {

  HashMap<String,String> parmsMap ;
  String name;
  public void init() {
    /*
       dump to the console the URL, the URL and its form values
           the URL  http://faculty.kutztown.edu/spiegel/
                CSc421/java/AppletInteraction/AppletUrlParams.html?
                        value1=x&value2=y&value3=z

          then the values passed in are stored in a map for easy reference.
          ex. String name = parmsMap.get("name")
    */
    try {
      doit();
      dumpMap(parmsMap);
      //System.out.println("name is " + parmsMap.get("name"));
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

   }

  public void doit() throws UnsupportedEncodingException {
    String completeURL = getDocumentBase().toString();
    System.out.println("Complete URL: " + completeURL);
    int i = completeURL.indexOf("?");
    if (i > -1) {
       String searchURL = completeURL.substring(completeURL.indexOf("?") + 1);
       System.out.println("Search URL: " + searchURL);
       initMap(searchURL);
    }
  }

  public void initMap(String search) throws UnsupportedEncodingException {
    parmsMap = new HashMap<String,String>();
    String params[] = search.split("&");

    for (String param : params) {
       String temp[] = param.split("=");
       parmsMap.put(temp[0], java.net.URLDecoder.decode(temp[1], "UTF-8"));
    }
  }

  public void dumpMap(Map<?,?> map) {
    int count = 0;
    System.out.println("--------");
    for (Map.Entry<?,?> entry : map.entrySet()) {
      System.out.println(entry.getKey() + ": " + entry.getValue());
      if(count == 1)
      {
        name = (String)entry.getValue();
      }
      count ++;
    }
    System.out.println("--------");
  }

  public void paint(Graphics g) {
    g.drawString("--------",10,10);
    int y=10;
    for (Map.Entry<?,?> entry : parmsMap.entrySet()) {
      g.drawString(entry.getKey() + ": " + entry.getValue(),10,y+=15);
    }
    g.drawString(name,10,y+=15);
    g.drawString("--------",10,y+=15);
  }

}
