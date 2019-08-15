package com.kadnikovea.crypto_jsoup;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    //logging
    String log = "message!" ;

    private List<String> mData;
    private List<String> mimgUrl;
    private List<String> mGraphUrl;
    private List<String> referList;
    private List<String> mPrice;
    private List<String> mCapital;
    private List<String> mChanges;
    private List<String> mVolumes;
    private List<String> mCirc;
    //private LayoutInflater mInflater;
    private Activity mActivity;
    //private final OnItemClickListener listener;
    //Context mcontext;

    public MyRecyclerViewAdapter(List<String>mData, List<String> mimgUrl, List<String> mPrice,
    List<String> mCapital,MainActivity mActivity,List<String >mGraphUrl
    ,List<String>referList,List<String> mVolumes,List<String> mCirc,List<String>mChanges) {
        this.mData = mData;
        this.mimgUrl = mimgUrl;
        this.mGraphUrl=mGraphUrl;
        this.mPrice = mPrice;
        this.mCapital = mCapital;
        this.mChanges = mChanges;
        this.mActivity = mActivity;
        this.referList=referList;
        this.mVolumes=mVolumes;
        this.mCirc=mCirc;
    }


    //data в конструкторе


    //бер]т лэйаут из xml


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("binding!");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        System.out.println("binding_2_____!!!!!!!!");
        //
        holder.nextButt.setOnClickListener((view -> {
            Intent intent=new Intent(mActivity,Info_Activity.class);
            intent.putExtra("icon",mimgUrl.get(position));
            intent.putExtra("graphic",mGraphUrl.get(position));
            intent.putExtra("price",mPrice.get(position));
            intent.putExtra("name",mData.get(position));
            intent.putExtra("capital",mCapital.get(position));
            intent.putExtra("volume",mVolumes.get(position));
            intent.putExtra("circ",mCirc.get(position));
            intent.putExtra("changes",mChanges.get(position));



            mActivity.startActivity(intent);
        }));

        //вывод картинок и графиков на экран


            String text = mData.get(position);
            String img = mimgUrl.get(position);
            String graph= mGraphUrl.get(position);
            String price=mPrice.get(position);
            String volume=mVolumes.get(position);

        /*String capital=mCapital.get(position);*/
//        String changes = mChanges.get(position);
            //вставить значения в поля на вью
            holder.buttonInfo.setText(text);
        holder.priceText.setText(price);
        holder.capitalText.setText(mCapital.get(position));/*
        holder.capitalText.setText(capital);*/
        holder.changesText.setText(volume);//объем за 24 часа

            Log.e("data..?", text);
            //picasso грузит картинки в imageview- в примере был не get  а with
            if (img.isEmpty()) {
                holder.imageView.setImageResource(R.drawable.ic_launcher_background);
                holder.graph_View.setImageResource(R.drawable.ic_launcher_background);

            } else {
                Picasso.with(mActivity).load(img).
                        error(R.drawable.ic_launcher_background).into(holder.imageView);
                Picasso.with(mActivity).load(graph).error(R.drawable.ic_launcher_background)
                        .into(holder.graph_View);
            }

            //String text = mData.get(position);
           // String img = mimgUrl.get(position);
            holder.buttonInfo.setText(text);
        }


    //связывает text и data для каждой строчки


    //всего строк
    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //TextView myTextView;
        Button buttonInfo;//для вида просто-не работает
        Button nextButt;
        ImageView imageView;
        ImageView graph_View;
        TextView priceText;
        TextView capitalText;
        TextView changesText;

        public ViewHolder(View itemView) {
            super(itemView);
           // myTextView = itemView.findViewById(R.id.textView_id);
            imageView = itemView.findViewById(R.id.imageView_id);
            graph_View=itemView.findViewById(R.id.graph_View_id);
            buttonInfo = itemView.findViewById(R.id.button_info_id);
            nextButt=itemView.findViewById(R.id.button_more_id);
            priceText = itemView.findViewById(R.id.textView_price_id);
            capitalText = itemView.findViewById(R.id.textView_capital_id);
            changesText = itemView.findViewById(R.id.textView_changes_id);


        }
    }


}
