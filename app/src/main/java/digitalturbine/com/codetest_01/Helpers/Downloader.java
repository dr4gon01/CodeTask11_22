package digitalturbine.com.codetest_01.Helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by Nirmesh on 11/21/2016.
 */

public class Downloader extends AsyncTask<Void, Void, Object> {

    private static final String TAG = "Downloader.class";
    Context c;
    String urlAddress;
    ListView lv;

    ProgressDialog pd;

    public Downloader(Context c, String urlAddress, ListView lv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Fetching something awesome");
        pd.setMessage("please wait..");
        pd.show();

    }

    @Override
    protected Object doInBackground(Void... params) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(Object data) {
        super.onPostExecute(data);
        pd.dismiss();
        if(data.toString().startsWith("Error"))
        {
            Toast.makeText(c, data.toString(), Toast.LENGTH_SHORT).show();
        }else{
            new XMLPullParser(c, (InputStream) data, lv).execute();
        }
    }

    private Object downloadData()
    {
        Object connection = Connector.connect(urlAddress);
        if(connection.toString().startsWith("Error"))
        {
            return connection.toString();

        }
        try {
            HttpURLConnection con = (HttpURLConnection) connection;
            int responseCode = con.getResponseCode();

            if(responseCode == con.HTTP_OK)
            {
                InputStream is = new BufferedInputStream(con.getInputStream());
                return is;
            }
            return ErrorTracer.RESPONSE_ERROR +con.getResponseMessage();

        }catch (IOException e) {
            e.printStackTrace();
            return ErrorTracer.IO_ERROR;
        }
    }
}
