package roque.core.divison.projetointegrador4;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rey.material.widget.ProgressView;

import java.util.ArrayList;


public class NivelFrag extends Fragment {


    private LinearLayout LinearRepre;

    private ArrayList<Represa> represas = new ArrayList<Represa>();


    public static NivelFrag newInstance() {
        NivelFrag fragment = new NivelFrag();

        return fragment;
    }

    public NivelFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    int i=0;

    String[] rNameX = {"Cantareira","Alto Tietê","Guarapiranga","Alto Cotia"};
    float[] rProgX = {0.197f,0.105f,0.82f,0.684f};

    for(String rx : rNameX){
        Represa rr=new Represa();
        rr.name=rx;
        rr.prog=rProgX[i];
        represas.add(rr);
        i++;
    }






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nivel, container, false);
        LinearRepre= (LinearLayout) v.findViewById(R.id.LinearRepre);

        for(Represa rx : represas){
            View x = inflater.inflate(R.layout.nivel_holder,null);
            TextView xName= (TextView) x.findViewById(R.id.textRepre);
            TextView xTprog = (TextView) x.findViewById(R.id.textProgRepre);
            ProgressView xProg = (ProgressView) x.findViewById(R.id.progRepre);

            xName.setText(rx.name);
            //xTprog.setText(rx.toString() + "%");
            xProg.setProgress(rx.prog);

            LinearRepre.addView(x);

        }

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



private class Represa {


    private String name;
    private float prog;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getProg() {
        return prog;
    }

    public void setProg(float prog) {
        this.prog = prog;
    }






    public Represa(){

    }

    public Represa(String n,float p){
        this.name=n;
        this.prog=p;
    }



    }



}
