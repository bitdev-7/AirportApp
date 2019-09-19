package epsilon.com;


import android.content.Context;
import android.support.design.animation.AnimationUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FlightsRecyclerAdapter extends RecyclerView.Adapter<FlightsRecyclerAdapter.ViewHolder> {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    private ArrayList<AirportDataModel> m_DataModel;
    private Context m_context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView m_TimeView;
        public TextView m_FlightCodeView;
        public TextView m_DestView;
        public TextView m_StatusView;
        public ImageView m_AirlinesLogoView;
        public ImageView m_CountryFlagView;
        public ViewHolder(View v) {
            super(v);
            m_TimeView = v.findViewById(R.id.flight_time);
            m_FlightCodeView = v.findViewById(R.id.flight_code);
            m_DestView = v.findViewById(R.id.flight_destination);
            m_StatusView = v.findViewById(R.id.flight_status);

            m_AirlinesLogoView = v.findViewById(R.id.airlines_logo);
            m_CountryFlagView = v.findViewById(R.id.country_flag);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FlightsRecyclerAdapter(ArrayList<AirportDataModel> m_dataModel, Context context) {
        m_DataModel = m_dataModel; m_context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FlightsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flights_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.m_TimeView.setText(new SimpleDateFormat("MM.dd HH:mm")
                .format(m_DataModel.get(position).m_Time));
        holder.m_StatusView.setText(m_DataModel.get(position).m_Status);
        holder.m_DestView.setText(m_DataModel.get(position).m_Destination);
        holder.m_FlightCodeView.setText(m_DataModel.get(position).m_Airlines.m_Flight);

        Context airlines_logo_context = holder.m_AirlinesLogoView.getContext();
        String airlines_logo = m_DataModel.get(position).m_Airlines.m_logoResId;
        holder.m_AirlinesLogoView.setImageResource(this.getResourceIdFromString(airlines_logo_context, airlines_logo));

        Context flag_context = holder.m_CountryFlagView.getContext();
        String flag = "flag" + m_DataModel.get(position).m_DestResId;
        holder.m_CountryFlagView.setImageResource(this.getResourceIdFromString(flag_context, flag));

        setAnimation(holder.m_TimeView, position);
        setAnimation(holder.m_StatusView, position);
        setAnimation(holder.m_DestView, position);
        setAnimation(holder.m_FlightCodeView, position);
        setAnimation(holder.m_AirlinesLogoView, position);
        setAnimation(holder.m_CountryFlagView, position);
    }

    public void setAnimation(View view, int pos)
    {
        Animation flightAnimation = android.view.animation.
                AnimationUtils.loadAnimation(m_context, R.anim.fade_interpolator);

        flightAnimation.setDuration(700);

        view.startAnimation(flightAnimation);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return m_DataModel.size();
    }

    public int getResourceIdFromString(Context context, String resource)
    {
        return context.getResources().getIdentifier(resource,
                "drawable", context.getPackageName());

    }
}
