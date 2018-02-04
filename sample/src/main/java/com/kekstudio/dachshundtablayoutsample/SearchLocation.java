package com.kekstudio.dachshundtablayoutsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class SearchLocation extends Fragment {

    View view;

    private WebView webView;
    private Handler handler;

    Intent intent;


    public SearchLocation(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_location, null);

        init_webView();

        handler = new Handler();

        return view;
    }

    public void init_webView(){
        webView = (WebView)view.findViewById(R.id.search_location_addressWebView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.addJavascriptInterface(new AndroidBridge(), "TestApp");
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://kskksg4.ivyro.net");
    }

    private class AndroidBridge{
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    init_webView();

                    intent = getActivity().getIntent();
                    intent.putExtra("arg2", arg2);
                    getActivity().setResult(Activity.RESULT_OK, intent);
                    getActivity().finish();
                }
            });
        }
    }

}
