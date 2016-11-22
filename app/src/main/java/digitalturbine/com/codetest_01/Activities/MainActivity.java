package digitalturbine.com.codetest_01.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import digitalturbine.com.codetest_01.Helpers.Downloader;
import digitalturbine.com.codetest_01.R;
import digitalturbine.com.codetest_01.adapters.CustomAdapter;

public class MainActivity extends AppCompatActivity {

    final static String urlAddress = "http://ads.appia.com/getAds?id=236&password=OVUJ1DJN&siteId=4288&deviceId=4230&sessionId=techtestsession&totalCampaignsRequested=10&Iname=Gollamandala";

    private CustomAdapter mAdapter;
    private ListView appsListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appsListView = (ListView) findViewById(R.id.adsListView);

        new Downloader(MainActivity.this, urlAddress, appsListView).execute();
    }

}
