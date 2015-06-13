package roque.core.divison.projetointegrador4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SobreFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SobreFrag extends Fragment {

    // TODO: Rename and change types and number of parameters
    public static SobreFrag newInstance() {
        SobreFrag fragment = new SobreFrag();

        return fragment;
    }

    public SobreFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sobre, container, false);
    }


}
