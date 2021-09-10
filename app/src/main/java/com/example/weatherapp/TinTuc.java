package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TinTuc extends AppCompatActivity {
    ListView lvTinTuc;
    TinTucAdapter adapter;
    ArrayList<DocBao> docBaoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);

        lvTinTuc = findViewById(R.id.listViewTinTuc);
        docBaoArrayList = new ArrayList<DocBao>();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadData().execute("https://vnexpress.net/rss/the-gioi.rss");
            }
        });

        lvTinTuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TinTuc.this, DetailTinTuc.class);
                intent.putExtra("linktintuc", docBaoArrayList.get(i).getLink());
                startActivity(intent);
            }
        });
    }


    class ReadData extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListDescrip = document.getElementsByTagName("description");
            String image = "";
            String title = "";
            String link = "";

            for (int i=0; i<nodeList.getLength(); i++){
                String cdata = nodeListDescrip.item(i + 1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");

                Matcher matcher = p.matcher(cdata);
                if (matcher.find()){
                    image = matcher.group(1);
                }

                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                link = parser.getValue(element, "link");
                docBaoArrayList.add(new DocBao(title, link, image));
            }
            adapter = new TinTucAdapter(TinTuc.this, android.R.layout.simple_list_item_1, docBaoArrayList);
            lvTinTuc.setAdapter(adapter);

            super.onPostExecute(s);
        }
    }


    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
}