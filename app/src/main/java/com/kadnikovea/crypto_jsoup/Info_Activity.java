package com.kadnikovea.crypto_jsoup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Info_Activity extends AppCompatActivity {
    ImageView icon;
    ImageView graph;
    TextView name;
    TextView price;
    TextView capital;
    TextView volume;
    TextView circ;
    TextView changes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("CALLED INFO ACTIVITY");
        setContentView(R.layout.activity_info_);
        icon=findViewById(R.id.icon_info_id);
        graph=findViewById(R.id.graph_view_info_id);
        name=findViewById(R.id.name_info_id);
        price=findViewById(R.id.price_value_id);
        capital=findViewById(R.id.capital_info_id);
        volume=findViewById(R.id.volume_value_id);
        circ=findViewById(R.id.circulating_pr__value_id);
        changes=findViewById(R.id.change_value_id);

        getIncomingExtra();
    }
    private void getIncomingExtra(){
        if(getIntent().hasExtra("icon")&&getIntent().hasExtra("graphic")){

             Picasso.with(this).load(getIntent().getStringExtra("icon"))
                     .error(R.drawable.ic_launcher_background).into(icon);
            Picasso.with(this).load(getIntent().getStringExtra("graphic"))
                    .error(R.drawable.ic_launcher_background).into(graph);

        }
        if(getIntent().hasExtra("price")&&getIntent().hasExtra("name")){

           name.setText(getIntent().getStringExtra("name"));
           price.setText(getIntent().getStringExtra("price"));

        }
        if(getIntent().hasExtra("capital")){
            capital.setText(getIntent().getStringExtra("capital"));
        }
        if(getIntent().hasExtra("volume")){
            volume.setText(getIntent().getStringExtra("volume"));
        }
        if(getIntent().hasExtra("circ")){
            circ.setText(getIntent().getStringExtra("circ"));
        }
        if(getIntent().hasExtra("changes")){
            changes.setText(getIntent().getStringExtra("changes"));
        }

    }
}
