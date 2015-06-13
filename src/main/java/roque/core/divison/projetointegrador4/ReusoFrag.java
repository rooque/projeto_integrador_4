package roque.core.divison.projetointegrador4;

import android.app.Activity;
import android.net.Uri;
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
 * Activities that contain this fragment must implement the
 * {@link ReusoFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReusoFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReusoFrag extends Fragment {


    private WebView webweb;
    private String javascrips;



    private OnFragmentInteractionListener mListener;


    public static ReusoFrag newInstance() {
        ReusoFrag fragment = new ReusoFrag();
        return fragment;
    }

    public ReusoFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        try {
            InputStream input = getResources().openRawResource(R.raw.text2);
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
        // String html = readFile(is);



        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Log.d("JAVAJAVA:",javascrips);
        webweb.loadDataWithBaseURL("file:///android_res/raw/", javascrips, "text/html",
                "UTF-8", null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dicas, container, false);

        webweb= (WebView) v.findViewById(R.id.webView);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
