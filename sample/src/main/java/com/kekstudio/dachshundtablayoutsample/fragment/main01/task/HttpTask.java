package com.kekstudio.dachshundtablayoutsample.fragment.main01.task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.kekstudio.dachshundtablayoutsample.Constructor.Store;
import com.kekstudio.dachshundtablayoutsample.fragment.main01.Fragment01;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by gimseong-geun on 2018. 6. 6..
 */

public class HttpTask extends AsyncTask<Void, Void, ArrayList<Store>> {

    private final String URLPATH = "http://116.255.78.46/firstapp/index.php";

    private Double latitude;
    private Double longitude;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public HttpTask(){}

    public HttpTask(Double lat, Double lng, Context context) {
        this.latitude = lat;
        this.longitude = lng;
        this.context = context;
    }

    @Override
    protected ArrayList<Store> doInBackground(Void... voids) {

        HttpPost request = new HttpPost(URLPATH);

        ArrayList<Store> arrayList = new ArrayList<>();

        Vector<NameValuePair> nameValue = new Vector<>();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();

            nameValue.add(new BasicNameValuePair("lat", Double.toString(latitude)));
            nameValue.add(new BasicNameValuePair("lng", Double.toString(longitude)));

            HttpEntity enty = new UrlEncodedFormEntity(nameValue, HTTP.UTF_8);

            request.setEntity(enty);

            HttpClient client = new DefaultHttpClient();
            HttpResponse res = client.execute(request);

            HttpEntity entityResponse = res.getEntity();

            InputStream im = entityResponse.getContent();

            xmlPullParser.setInput(im, "utf-8");

            Store store = null;

            int eventType = xmlPullParser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT){
                switch(eventType){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:

                        try{
                            String startTag = xmlPullParser.getName();

                            if(startTag.equals("mark")){
                                store = new Store();
                            }

                            if(startTag.equals("name")){
                                store.setStoreName(xmlPullParser.nextText());
                            }

                            if(startTag.equals("address")){
                                store.setAddress(xmlPullParser.nextText());
                            }

                            if(startTag.equals("lat")){
                                store.setLat(xmlPullParser.nextText());
                            }

                            if(startTag.equals("lng")){
                                store.setLng(xmlPullParser.nextText());
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case XmlPullParser.END_TAG:

                        String endTag = xmlPullParser.getName();

                        if(endTag.equals("mark")){
                            arrayList.add(store);
                        }
                        break;
                }

                try{
                    eventType = xmlPullParser.next();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

            im.close();

            return  arrayList;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Store> stores) {
        super.onPostExecute(stores);
        Log.d("sea4", stores.size()+"");
        if(stores.size() <= 0){
            Toast.makeText(context, "주변에 업소가 없어요", Toast.LENGTH_SHORT).show(); // 여기에 Toast말고 Custom Dialog로 구현하기
        }else{
            if(Fragment01.content.size() > 0){
                Fragment01.content.clear();
//                recyclerView.getRecycledViewPool().clear();

                Fragment01.content = stores;
                for(int i=0; i<Fragment01.content.size(); i++){
                    Log.d("sea5", Fragment01.content.size()+"");
                    Fragment01.adapter.setData(Fragment01.content);
                }
            }
        }


    }
}
