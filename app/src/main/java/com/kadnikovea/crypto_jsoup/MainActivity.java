package com.kadnikovea.crypto_jsoup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String url = "https://coinmarketcap.com/ru/";

    String src;
    String src_grph;
    String imgName;
    String refer;
    String volume;
    String price;
    String capital;
    String changes;
    String circul;

    ArrayList list = new ArrayList();
    ArrayList imgNameList = new ArrayList();
    ArrayList graphList = new ArrayList();
    ArrayList referList = new ArrayList();
    ArrayList priceList = new ArrayList();
    ArrayList volumeList = new ArrayList();
    ArrayList capitalList = new ArrayList();
    ArrayList changesList = new ArrayList();
    ArrayList circulatings = new ArrayList();

    ProgressDialog progressdialog;
    MyRecyclerViewAdapter adapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //testIm =findViewById(R.id.default_IMG_ID);
        recyclerView = findViewById(R.id.recyclerview_id);

        Content content = new Content();
        content.execute();


    }
    //при нажатии кнопок на вью в списках переход в InfoActivity


    private class Content extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressdialog = new ProgressDialog(MainActivity.this);
            progressdialog.setMessage("          PLEASE WAIT.....");
            progressdialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new MyRecyclerViewAdapter(imgNameList, list, priceList, capitalList
                    , MainActivity.this, graphList, referList, volumeList, circulatings, changesList);//тут в листенере ошибка мб т/к/ нулл
            //recyclerView.setLayoutManager(new LinearLayoutManager(this));
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            progressdialog.dismiss();
            //тестирую чисто
            //Picasso.with(getApplicationContext()).load(list.get(3).toString()).error(R.drawable.ic_launcher_background).into(testIm);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //настраиваю соединение с сайтом и потом получаю с него данные по тегам
                Document doc = Jsoup.connect(url).get();
                //собираю элементы в коллекции
                Elements img = doc.getElementsByTag("img");
                Elements refs = doc.getElementsByAttributeValue("class", "no-wrap text-right");
                Elements volumes = doc.getElementsByAttributeValue("class", "volume");
                Elements circulEls = doc.getElementsByAttributeValue("class"
                        , "no-wrap text-right circulating-supply");
                Elements changeEls = doc.getElementsByAttribute("data-percentusd");

                //картинки эмблемы
                Element e1;
                Element e2;
                for (int i = 0; i < img.size() - 4; i++) {


                    //получаю адреса картинок в строку src
                    if (i % 2 == 0) {
                        e1 = img.get(i);


                        if (i < 20) {
                            src = e1.absUrl("src");
                            //добавляю ссылки на картинки в список адаптера(объект)
                            list.add(src);

                        } else {
                            src = e1.absUrl("data-src");
                            //добавляю ссылки на картинки в список адаптера(объект)
                            list.add(src);
                        }
                        imgName = e1.attr("alt");
                        imgNameList.add(imgName);

                        //цена капитализация изменения так же как и сверху делаю
                        e2 = refs.get(i);
                        price = e2.child(0).select("a").last().text();
                        priceList.add(price);
                    } else {
                        //добавить графики в другую коллекцию

                        e1 = img.get(i);
                        if (i < 20) {
                            src_grph = e1.absUrl("src");

                        } else {
                            src_grph = e1.absUrl("data-src");
                        }
                        graphList.add(src_grph);

                    }
                }
                Elements capEls = doc.getElementsByClass("no-wrap market-cap text-right");
                //капитализация
                Element volEl;
                for (int i = 0; i < capEls.size() - 1; i++) {
                    capital = capEls.get(i).text();
                    capitalList.add(capital);
                    //беру объем
                    volEl = volumes.get(i);
                    volume = volEl.getElementsByTag("a").text();
                    volumeList.add(volume);
                    //беру циркулир предложение
                    circul = circulEls.get(i).text();
                    circulatings.add(circul);
                    //беру изменения
                    changes = changeEls.get(i).text();
                    //логирую System.out.println("CHANGES"+changes);

                    changesList.add(changes);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
