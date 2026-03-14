 import java.io.BufferedInputStream;
 import java.io.DataInputStream;
 import java.io.IOException;
 import java.io.InputStream;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

 public class Main {
  private final String _FANART_TV_API_KEY_ = "6ebec3370b7ecd7835ba426b8fb556bd"; 
  private final String _FANART_TV_CLIENT_KEY_ = "9d08abffee1ce3618262e64b243814c3";
  private final String _GET_MOVIE_URL_ = "http://webservice.fanart.tv/v3/movies/";
  
  public static void main(String[] args) {
   // HTTP
   System.setProperty("http.proxyHost", "proxy.indra.es");
   System.setProperty("http.proxyPort", "8080");
   System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");

   // HTTPS
   System.setProperty("https.proxyHost", "proxy.indra.es");
   System.setProperty("https.proxyPort", "443");

   // Settings proxy credentials
   System.setProperty("http.proxyUser", "jppaino");
   System.setProperty("http.proxyPassword", "Asturias_2001");

   // Java ignores http.proxyUser. Here come's the workaround.
   Authenticator.setDefault(
    new Authenticator() {
     @Override
     protected PasswordAuthentication getPasswordAuthentication() {
      if (getRequestorType() == RequestorType.PROXY) {
       String prot = getRequestingProtocol().toLowerCase();
       String host = System.getProperty(prot + ".proxyHost", "");
       String port = System.getProperty(prot + ".proxyPort", "80");
       String user = System.getProperty(prot + ".proxyUser", "");
       String password = System.getProperty(prot + ".proxyPassword", "");
       if (getRequestingHost().equalsIgnoreCase(host)) {
        if (Integer.parseInt(port) == getRequestingPort()) {
         return new PasswordAuthentication(user, password.toCharArray());
        }
       }
      }
      return null;
     }
    }
   );
   
   Main main = new Main();
   System.out.println(main.CallServiceGetMovies(10195));
  }

  private String CallServiceGetMovies(final int imdbIdParam) {
   return this.JGet(
    _GET_MOVIE_URL_ + imdbIdParam + "?api_key=" + _FANART_TV_API_KEY_ + "&client_key=" + _FANART_TV_CLIENT_KEY_
   );
  }
 
  private final String JGet(final String urlparam) {
   String url = urlparam;
   URL u;
   InputStream is = null;
   DataInputStream dis;
   String s;
   final StringBuilder scriptResult = new StringBuilder();
   try {
    u = new URL(url);
    is = u.openStream();
    dis = new DataInputStream(new BufferedInputStream(is));
    while ((s = dis.readLine()) != null) {
     scriptResult.append(s);
    }
   } catch (MalformedURLException mue) {
    System.err.println("Ouch - a MalformedURLException happened.");
    mue.printStackTrace();
    System.exit(2);
   } catch (IOException ioe) {
    System.err.println("Oops- an IOException happened.");
    ioe.printStackTrace();
    System.exit(3);
   } finally {
    try {
     is.close();
    } catch (IOException ioe) {}
   }
   return scriptResult.toString();
  }

 }
