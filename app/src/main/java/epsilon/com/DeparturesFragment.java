package epsilon.com;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class DeparturesFragment extends android.support.v4.app.Fragment {

    public  RecyclerView m_DeparturesRecyclerView;

    public ArrayList<AirportDataModel> m_DeparturesDataSet;

    public FlightsFragment m_FlightsFragment;

    public RecyclerView.Adapter m_RecyclerAdapter;
    public RecyclerView.LayoutManager m_LayoutManager;


    public DeparturesFragment() {
        // Required empty public constructor
        m_DeparturesDataSet = new AirportDataModel().InitModel(20);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View DeparturesView =  inflater.inflate(R.layout.fragment_departures, container, false);

        m_FlightsFragment =
                (FlightsFragment) this.getParentFragment();
        m_DeparturesRecyclerView = DeparturesView.findViewById(R.id.departures_recycler_view);


        m_DeparturesRecyclerView.setHasFixedSize(true);

        m_LayoutManager = new LinearLayoutManager(getContext());

        m_DeparturesRecyclerView.setLayoutManager(m_LayoutManager);

        m_RecyclerAdapter = new FlightsRecyclerAdapter(m_DeparturesDataSet, getContext());

        m_DeparturesRecyclerView.setAdapter(m_RecyclerAdapter);

        return DeparturesView;
    }

}
