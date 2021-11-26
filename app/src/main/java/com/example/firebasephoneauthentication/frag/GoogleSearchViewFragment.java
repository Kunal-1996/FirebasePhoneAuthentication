package com.example.firebasephoneauthentication.frag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.SearchView;

import com.example.firebasephoneauthentication.R;

public class GoogleSearchViewFragment extends Fragment {

    private SearchView searchView;
   private WebView webView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_googlesearchview, container, false);


       webView=view.findViewById(R.id.WEBVIEW);
        webView.setWebViewClient(new WebViewClient());

        searchView=view.findViewById(R.id.urlET);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                webView.loadUrl("https://www.google.com/search?q="+searchView.getQuery());
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;


    }

}