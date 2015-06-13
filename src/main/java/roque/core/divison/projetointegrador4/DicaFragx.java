package roque.core.divison.projetointegrador4;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DicaFragx#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DicaFragx extends Fragment {


    private WebView webweb;
    private String javascrips;


    public static DicaFragx newInstance() {
        DicaFragx fragment = new DicaFragx();
        return fragment;
    }

    public DicaFragx() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            InputStream input = getResources().openRawResource(R.raw.dicas);
            Reader is = new BufferedReader(
                    new InputStreamReader(input, "windows-1252"));


            //InputStream input = getAssets().open("ws.TXT");
            int size;
            size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            // byte buffer into a string
            javascrips = new String(buffer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dicax, container, false);

        webweb= (WebView) v.findViewById(R.id.webView2);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        //Log.d("JAVAJAVA:",javascrips);
        webweb.loadDataWithBaseURL("file:///android_res/raw/", javascrips, "text/html",
                "UTF-8", null);
        super.onActivityCreated(savedInstanceState);

    }
}
