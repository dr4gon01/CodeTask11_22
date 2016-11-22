package digitalturbine.com.codetest_01.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import digitalturbine.com.codetest_01.R;

public class SingleItem extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);

        TextView productName = (TextView)findViewById(R.id.name);
        TextView productRating = (TextView)findViewById(R.id.rating);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String rating = i.getStringExtra("rating");
        String productThumbnailUrl = i.getStringExtra("thumbnail");
        productName.setText(name);
        productRating.setText("Rating: " +rating);


        //Picasso
        Picasso.with(this).load(productThumbnailUrl).into(imageView);
    }
    public void onBack(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        onPause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
