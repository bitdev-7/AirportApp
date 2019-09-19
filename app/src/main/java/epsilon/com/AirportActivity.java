package epsilon.com;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class AirportActivity extends AppCompatActivity {


    FlightsFragment flightsFragment;

    private SearchableWithButtonView m_SearchableWithButtonView;
    private FragmentManager m_FragmentMgr;
    private FragmentTransaction m_FragmentTran;
    private NavigationView m_navigationView;
    private BottomNavigationView m_flightsNavigationView;

    private DrawerLayout m_DrawerLayout;


    final private AirportDataModel
            m_AirportDataModel = new AirportDataModel();



    public class SearchableWithButtonListener implements View.OnClickListener, TextWatcher
    {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            FlightsFragment flightsFragment = (FlightsFragment)
                    m_FragmentMgr.findFragmentById(R.id.airport_fragment_container);

            RecyclerView flightsRecyclerView = null;
            ArrayList<AirportDataModel> DataSet, oldDataSet = null;
            TabLayout tabLayout = findViewById(R.id.flights_destination_tabs);

            if (tabLayout.getTabAt(0).isSelected()) {
                //flightsRecyclerView = findViewById(R.id.arrivals_recycler_view);
                flightsRecyclerView = flightsFragment.m_ArrivalsFragment.m_ArrivalsRecyclerView;
                oldDataSet = flightsFragment.m_ArrivalsFragment.m_ArrivalsDataSet;

            }

            else if (tabLayout.getTabAt(1).isSelected()) {
                //flightsRecyclerView = findViewById(R.id.departures_recycler_view);
                flightsRecyclerView = flightsFragment.m_DeparturesFragment.m_DeparturesRecyclerView;
                oldDataSet = flightsFragment.m_DeparturesFragment.m_DeparturesDataSet;
            }

            if (!charSequence.toString().isEmpty()) {
                DataSet = new FlightsIndexedSearch().
                        doSearch(charSequence.toString(), oldDataSet);

                if (DataSet.size() == 0) {
                    DataSet = oldDataSet;
                }
            }

            else DataSet = oldDataSet;

            FlightsRecyclerAdapter recyclerAdapter
                    = new FlightsRecyclerAdapter(DataSet, getBaseContext());

            flightsRecyclerView.setAdapter(recyclerAdapter);

            recyclerAdapter.notifyDataSetChanged();
            recyclerAdapter.notifyItemRangeChanged(0, DataSet.size());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

        @Override
        public void onClick(View view) {
            if (!m_DrawerLayout.isDrawerOpen(GravityCompat.START))
                m_DrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport);

        m_DrawerLayout = findViewById(R.id.airport_drawer_layout);

        m_flightsNavigationView = (BottomNavigationView)findViewById(R.id.flights_navigation) ;

        m_navigationView = (NavigationView)findViewById(R.id.airport_navigation_view) ;

        flightsFragment = new FlightsFragment();

        m_FragmentMgr = getSupportFragmentManager();

        m_FragmentTran = m_FragmentMgr.beginTransaction();

        m_FragmentTran.replace(R.id.airport_fragment_container , flightsFragment);

        m_FragmentTran.commit();

   //     searchView = (SearchView)findViewById(R.id.searchable);


        m_SearchableWithButtonView =new SearchableWithButtonView(AirportActivity.this, R.id.searchable);

        m_SearchableWithButtonView.setupSearchableWithButton();
        m_SearchableWithButtonView.setTextWatchListener(new SearchableWithButtonListener());
        m_SearchableWithButtonView.setSearchButtonClickListener(new SearchableWithButtonListener());

      /*  searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {

                FlightsFragment flightsFragment = (FlightsFragment)
                        m_FragmentMgr.findFragmentById(R.id.airport_fragment_container);

                RecyclerView flightsRecyclerView = null;
                ArrayList<AirportDataModel> DataSet, oldDataSet = null;
                TabLayout tabLayout = findViewById(R.id.flights_destination_tabs);

                if (tabLayout.getTabAt(0).isSelected()) {
                    //flightsRecyclerView = findViewById(R.id.arrivals_recycler_view);
                    flightsRecyclerView = flightsFragment.m_ArrivalsFragment.m_ArrivalsRecyclerView;
                    oldDataSet = flightsFragment.m_ArrivalsFragment.m_ArrivalsDataSet;

                }
                else if (tabLayout.getTabAt(1).isSelected()) {
                    //flightsRecyclerView = findViewById(R.id.departures_recycler_view);
                    flightsRecyclerView = flightsFragment.m_DeparturesFragment.m_DeparturesRecyclerView;
                    oldDataSet = flightsFragment.m_DeparturesFragment.m_DeparturesDataSet;
                }

                if (!s.isEmpty()) {
                    DataSet = new FlightsIndexedSearch().
                            doSearch(s, oldDataSet);

                    if (DataSet.size() == 0) {
                        DataSet = oldDataSet;
                    }
                }

                else DataSet = oldDataSet;

                FlightsRecyclerAdapter recyclerAdapter
                        = new FlightsRecyclerAdapter(DataSet, getBaseContext());

                flightsRecyclerView.setAdapter(recyclerAdapter);

                recyclerAdapter.notifyDataSetChanged();
                recyclerAdapter.notifyItemRangeChanged(0, DataSet.size());

                return false;
            }
        } ); */

        m_navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                menuItem.setChecked(true);

                m_FragmentTran = m_FragmentMgr.beginTransaction();

                if (menuItem.getItemId() == R.id.flights) {
                    Toast.makeText(getApplicationContext(),"flights",Toast.LENGTH_LONG).show();
                    m_FragmentTran.replace(R.id.airport_fragment_container,
                            new FlightsFragment());

                }

                else if (menuItem.getItemId() == R.id.about) {}

                m_FragmentTran.addToBackStack(null); m_FragmentTran.commit();

                if (m_DrawerLayout.isDrawerOpen(GravityCompat.START))
                    m_DrawerLayout.closeDrawers();

                return false;
            }
        });

        m_flightsNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        FlightsFragment flightsFragment = (FlightsFragment)
                                m_FragmentMgr.findFragmentById(R.id.airport_fragment_container);

                        RecyclerView recyclerView = null;
                        ArrayList<AirportDataModel> dataSet = null;
                        FlightsRecyclerAdapter recyclerAdapter = null;
                        TabLayout tabLayout = findViewById(R.id.flights_destination_tabs);

                        if (tabLayout.getTabAt(0).isSelected()) {
                            recyclerView = flightsFragment.m_ArrivalsFragment.m_ArrivalsRecyclerView;
                            dataSet = flightsFragment.m_ArrivalsFragment.m_ArrivalsDataSet;
                        }

                        else if (tabLayout.getTabAt(1).isSelected()) {
                            recyclerView = flightsFragment.m_DeparturesFragment.m_DeparturesRecyclerView;
                            dataSet = flightsFragment.m_DeparturesFragment.m_DeparturesDataSet;
                        }

                        long curr_time = System.currentTimeMillis();

                        if (menuItem.getItemId() == R.id.flights_prev)
                        {
                            recyclerAdapter = new FlightsRecyclerAdapter(m_AirportDataModel.filterByTime(dataSet,
                                    curr_time - (long)3.6e+6 * 48, curr_time), getBaseContext());
                        }

                        else if (menuItem.getItemId() == R.id.flights_now)
                        {
                            recyclerAdapter = new FlightsRecyclerAdapter(m_AirportDataModel.filterByTime(dataSet,
                                    curr_time - (long)3.6e+6 * 24, curr_time + (long)3.6e+6 * 24), getBaseContext());
                        }

                        else if (menuItem.getItemId() == R.id.flights_next)
                        {
                            recyclerAdapter = new FlightsRecyclerAdapter(m_AirportDataModel.filterByTime(dataSet,
                                    curr_time, curr_time + (long)3.6e+6 * 48), getBaseContext());
                        }

                        recyclerView.setAdapter(recyclerAdapter);

                        recyclerAdapter.notifyDataSetChanged();
                        recyclerAdapter.notifyItemRangeChanged(0, dataSet.size());

                        return false;
                    }
                }
        );
    }
}
