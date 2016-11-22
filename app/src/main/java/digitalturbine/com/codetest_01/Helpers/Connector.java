package digitalturbine.com.codetest_01.Helpers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nirmesh on 11/21/2016.
 */

public class Connector {
    public static Object connect(String urlAddress) {

        try
        {
            URL url = new URL(urlAddress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //properties
                con.setRequestMethod("GET");
                con.setConnectTimeout(15000);
                con.setReadTimeout(15000);
                con.setDoInput(true);

                return con;
            } catch (IOException e) {
                e.printStackTrace();
                return ErrorTracer.CONNECTION_ERROR;
            }

        }

}
