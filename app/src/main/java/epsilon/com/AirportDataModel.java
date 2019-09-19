package epsilon.com;


import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class AirportDataModel {

    long m_Time;
    String m_Status;
    String m_Destination;
    String m_DestResId;
    Airlines m_Airlines;

    public class Airlines
    {
        public Airlines(String logoResId, String flight)
        {
            this.m_Flight = flight;
            this.m_logoResId = logoResId;
        }

        public String m_logoResId;
        public String m_Flight;
    }

    public AirportDataModel() { }

    public AirportDataModel(long curr_time, String status, String dest, String destResId, Airlines airlines)
    {
        this.m_Airlines = airlines;
        this.m_Status = status;
        this.m_Destination = dest;
        this.m_Time = curr_time;
        this.m_DestResId = destResId;
    }

    public AirportDataModel getRandomFlight() {

        Random rand_obj = new Random();
        AirportFlightsDestData flightsData = new AirportFlightsDestData();

        int flight_rnd_index = rand_obj.nextInt(
                flightsData.m_DestCities.size() - 1);

        String destCity = flightsData.m_DestCities.get(flight_rnd_index);
        String destResId = flightsData.getFlagResourceByDestCity(destCity);

        char airline_code_let1 = (char) (rand_obj.nextInt('Z' - 'A') + 'A');
        char airline_code_let2 = (char) (rand_obj.nextInt('Z' - 'A') + 'A');

        String airline_code = "\0";

        airline_code += new StringBuilder().append(airline_code_let1).toString();
        airline_code += new StringBuilder().append(airline_code_let2).toString();

        String flight_code = "\0";
        flight_code += new StringBuilder().append(rand_obj.nextInt(9)).toString();
        flight_code += new StringBuilder().append(rand_obj.nextInt(9)).toString();
        flight_code += new StringBuilder().append(rand_obj.nextInt(9)).toString();
        flight_code += new StringBuilder().append(rand_obj.nextInt(9)).toString();

        flight_code = airline_code + " " + flight_code;

        Airlines airlines = new Airlines(flightsData.m_airlinesResName.get(rand_obj.nextInt(
                flightsData.m_airlinesResName.size() - 1)), flight_code);

        String flight_status = flightsData.m_Status.get(
                rand_obj.nextInt(flightsData.m_Status.size() - 1));

        int time_hours_sign = rand_obj.nextInt(2);
        int time_hours_offset = rand_obj.nextInt(48);

        long currTimeMillis = System.currentTimeMillis();

        if (time_hours_sign > 0)
            currTimeMillis += time_hours_offset * 3.6e+6;
        else currTimeMillis -= time_hours_offset * 3.6e+6;

        return new AirportDataModel(currTimeMillis,
                flight_status, destCity, destResId, airlines);
    }

    public ArrayList<AirportDataModel> InitModel(int numOfItems)
    {
        ArrayList<AirportDataModel> newDataModel = new ArrayList<>();
        for (int index = 0; index < numOfItems; index++) {
            newDataModel.add(this.getRandomFlight());
        }
        return newDataModel;
    }

    public ArrayList<AirportDataModel> Simulate(ArrayList<AirportDataModel> dataSet)
    {
        long currTimeMillis = System.currentTimeMillis();
        currTimeMillis += new Random().nextInt(48) * 3.6e+6;
        for (int index = 0; index < dataSet.size(); index++) {
            AirportDataModel item = dataSet.get(index);
            if (item.m_Time <= currTimeMillis) {
                dataSet.remove(item);
                dataSet.add(new Random().nextInt(dataSet.size()), getRandomFlight());
            }
        }

        return dataSet;
    }

    public ArrayList<AirportDataModel> filterByTime(
        ArrayList<AirportDataModel> dataSet, long time_start, long time_end) {
            ArrayList<AirportDataModel> targetDataSet = new ArrayList<>();
            for (int index = 0; index < dataSet.size(); index++) {
                AirportDataModel item = dataSet.get(index);
                if (item.m_Time > time_start && item.m_Time < time_end)
                    targetDataSet.add(item);
              }

             return targetDataSet;
         }
}

