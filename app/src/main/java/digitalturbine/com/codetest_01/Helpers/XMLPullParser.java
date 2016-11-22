package digitalturbine.com.codetest_01.Helpers;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import digitalturbine.com.codetest_01.adapters.CustomAdapter;
import digitalturbine.com.codetest_01.model.Ad;
import digitalturbine.com.codetest_01.model.AppsData;

/**
 * Created by Nirmesh on 11/21/2016.
 */

public class XMLPullParser extends AsyncTask<Void, Void, Boolean>{
    Context c;
    InputStream is;
    ListView lv;


    ArrayList<Ad> adList = new ArrayList<>();


    ProgressDialog pd;

    Ad ad;
    AppsData appsDataObj;

    static final String TAG = "XMLPullParser.class";


    static final String ADS = "ads";

    static final String AD = "ad";

    static final String RATING = "rating";

    static final String PRODUCT_NAME = "productName";

    static final String PRODUCT_THUMBNAIL = "productThumbnail";

    public XMLPullParser(Context c, InputStream is, ListView lv) {
        this.c = c;
        this.is = is;
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
    protected Boolean doInBackground(Void... params) {
        return this.parseAppData();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);
        pd.dismiss();
        if(isParsed)
        {
            lv.setAdapter(new CustomAdapter(c, adList));
            //Bind
        }
        else {
            Toast.makeText(c,"unable to parse", Toast.LENGTH_LONG).show();
        }
    }
    private Boolean parseAppData()
    {
        try
        {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(is,null);
            int event = parser.getEventType();
            String tagValue = null;


            adList.clear();


            do{
                String tagName = parser.getName();


                    switch (event) {
                        case XmlPullParser.START_TAG:
/*                            if (tagName.equalsIgnoreCase(ADS)) {
                                 appsDataObj = new AppsData();
                            }*/
                             if (tagName.equalsIgnoreCase(AD)) {
                                ad = new Ad();
                            }
                        break;
                        case XmlPullParser.TEXT:
                            tagValue = parser.getText();
                            break;

                        case XmlPullParser.END_TAG:
                            if(tagName.equalsIgnoreCase(PRODUCT_NAME))
                            {
                                ad.setProductName(tagValue);
                            }
                            else if(tagName.equalsIgnoreCase(RATING))
                            {
                                ad.setRating(tagValue);
                            }
                            else if (tagName.equalsIgnoreCase(PRODUCT_THUMBNAIL))
                            {
                                ad.setProductThumbnail(tagValue);
                            }
                            else if(tagName.equalsIgnoreCase(AD))
                            {
                                adList.add(ad);

                            }
                            break;
                    }
                event = parser.next();

            }
            while(event != XmlPullParser.END_DOCUMENT);
                return true;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}


