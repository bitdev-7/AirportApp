package epsilon.com;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ArrivalsFragment extends android.support.v4.app.Fragment {

    public  RecyclerView m_ArrivalsRecyclerView;

    public ArrayList<AirportDataModel> m_ArrivalsDataSet;

    public FlightsFragment m_FlightsFragment;


    public RecyclerView.Adapter m_RecyclerAdapter;
    public RecyclerView.LayoutManager m_LayoutManager;



    public ArrivalsFragment() {
        // Required empty public constructor
        m_ArrivalsDataSet = new AirportDataModel().InitModel(20);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View ArrivalsView =  inflater.inflate(R.layout.fragment_arrivals, container, false);

        m_FlightsFragment = (FlightsFragment) this.getParentFragment();

        m_ArrivalsRecyclerView = ArrivalsView.findViewById(R.id.arrivals_recycler_view);

        m_ArrivalsRecyclerView.setHasFixedSize(true);

        m_LayoutManager = new LinearLayoutManager(getContext());

        m_ArrivalsRecyclerView.setLayoutManager(m_LayoutManager);

        m_RecyclerAdapter = new FlightsRecyclerAdapter(m_ArrivalsDataSet, getContext());

        m_ArrivalsRecyclerView.setAdapter(m_RecyclerAdapter);

        return ArrivalsView;
    }

}
