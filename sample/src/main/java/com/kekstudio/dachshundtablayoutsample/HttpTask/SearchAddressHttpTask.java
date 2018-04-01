package com.kekstudio.dachshundtablayoutsample.HttpTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kekstudio.dachshundtablayoutsample.Adapter.SearchAddrAdapter;
import com.kekstudio.dachshundtablayoutsample.Constructor.SearchAddress;
import com.kekstudio.dachshundtablayoutsample.Constructor.MetaAddr;
import com.kekstudio.dachshundtablayoutsample.SearchLocation;

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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by gimseong-geun on 2018. 3. 17..
 */

public class SearchAddressHttpTask extends AsyncTask<Void, Void, ArrayList<SearchAddress[]>> {

    private Context context;

    private String addr;
    private Boolean nullCheckFlag;

    private ArrayList<SearchAddress[]> arrayList = new ArrayList<>();

    private Gson gson = new Gson();

    public SearchAddressHttpTask(){}

    public SearchAddressHttpTask(String addr, Context context){
        this.addr = addr;
        this.context = context;
    }

    @Override
    protected ArrayList<SearchAddress[]> doInBackground(Void... voids) {

        String URLPATH = "http://116.255.77.198/firstapp/curl.php";
        HttpPost request = new HttpPost(URLPATH);

        Vector<NameValuePair> nameValue = new Vector<>();

        try{
            nameValue.add(new BasicNameValuePair("addr", addr));

            HttpEntity enty = new UrlEncodedFormEntity(nameValue, HTTP.UTF_8);

            request.setEntity(enty);

            HttpClient client = new DefaultHttpClient();
            HttpResponse res = client.execute(request);

            HttpEntity entityResponse = res.getEntity();

            InputStream im = entityResponse.getContent();
            Reader reader = new InputStreamReader(im, "UTF-8");

            JsonParser jsonParser = new JsonParser();

            JsonObject data = jsonParser.parse(reader).getAsJsonObject();
            gson.fromJson(data.get("meta"), MetaAddr.class);
//            Log.d("se", data.get("meta")+"");

            JsonArray addrData = (JsonArray) data.get("documents");
//            Log.d("sea0", addrData+"");

            if(addrData != null){ // 검색시 주소 데이터가 null이 아닐 때,

                Log.d("sea0", addrData.size()+"");
                parseJsonAddr(addrData);

            }else{

            }

//            Log.d("sea1", arrayList.size()+"");

            im.close();

            return arrayList;
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<SearchAddress[]> searchAddresses) {
        super.onPostExecute(searchAddresses);

        Log.d("sea06", searchAddresses.size()+"");
        SearchAddrAdapter searchAddrAdapter = new SearchAddrAdapter();
        searchAddrAdapter.setData(searchAddresses, context, nullCheckFlag);

        SearchLocation.searchLocationRecyclerView.setAdapter(searchAddrAdapter);
    }

    private void parseJsonAddr(JsonArray addrData) {
        if(addrData.size() == 0){
            arrayList.add(null);
            nullCheckFlag = false;
            Log.d("sea2", "null?");

        }else {
            for(int i=0; i<addrData.size(); i++){
                SearchAddress[] s = gson.fromJson(addrData, SearchAddress[].class);

                arrayList.add(s);
            }

            nullCheckFlag = true;
        }

    }
}
