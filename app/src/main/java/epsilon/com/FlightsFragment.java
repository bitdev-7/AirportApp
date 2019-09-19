package epsilon.com;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FlightsFragment extends  android.support.v4.app.Fragment  {
    TabLayout tabLayout;
    ViewPager viewPager;
    FlightsDestPagerAdapter destPagerAdapter;
    ArrivalsFragment m_ArrivalsFragment;
    DeparturesFragment m_DeparturesFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flights,container,false);

        tabLayout = (TabLayout)view.findViewById(R.id.flights_destination_tabs);
        viewPager = (ViewPager)view.findViewById(R.id.flights_destination_pager);

        destPagerAdapter = new FlightsDestPagerAdapter(getChildFragmentManager());

        m_ArrivalsFragment = new ArrivalsFragment();
        m_DeparturesFragment = new DeparturesFragment();

        destPagerAdapter.AddFragment(m_ArrivalsFragment,"ARRIVALS");
        destPagerAdapter.AddFragment(m_DeparturesFragment,"DEPARTURES");
        viewPager.setAdapter(destPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_flight_land_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_flight_takeoff_black_24dp);

        return view;
    }
}
