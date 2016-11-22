package digitalturbine.com.codetest_01.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import digitalturbine.com.codetest_01.Activities.SingleItem;
import digitalturbine.com.codetest_01.R;
import digitalturbine.com.codetest_01.model.Ad;

/**
 * Created by Nirmesh on 11/21/2016.
 */

public class CustomAdapter extends BaseAdapter {


    ArrayList<Ad> ads;
    private Context context;

    public CustomAdapter(Context context, ArrayList<Ad> ads) {
        this.context = context;
        this.ads = ads;
    }

    @Override
    public int getCount() {
        return ads.size();
    }

    @Override
    public Object getItem(int position) {
        return ads.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.one_row, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        final TextView rating = (TextView) convertView.findViewById(R.id.rating);
        ImageView imageThumbnail = (ImageView) convertView.findViewById(R.id.appthumbnail);

        Ad ad = (Ad) this.getItem(position);

        final String productName = ad.getProductName();
        final String productRating = ad.getRating();
        final String productThumbnail = ad.getProductThumbnail();

        name.setText(productName);
        rating.setText("Product Rating: " +productRating);
        Picasso.with(context).load(productThumbnail).into(imageThumbnail);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,SingleItem.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("name", productName);
                i.putExtra("rating", productRating);
                i.putExtra("thumbnail", productThumbnail);
                context.startActivity(i);
            }
        });

        return convertView;
    }
}
