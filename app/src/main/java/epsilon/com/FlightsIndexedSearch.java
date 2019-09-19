package epsilon.com;

import android.support.v4.app.FragmentManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightsIndexedSearch {

    public ArrayList<AirportDataModel> doSearch(String text,
               ArrayList<AirportDataModel> dataSet) {

        ArrayList<AirportDataModel> targetDataset = new ArrayList<>();

        for (int index = 0; index < dataSet.size(); index++) {
            AirportDataModel currItem = dataSet.get(index);

            String stime = new SimpleDateFormat("MM.dd HH:mm")
                    .format(currItem.m_Time);
            boolean m_time = Pattern.compile(".*" + text + ".*",
                    Pattern.CASE_INSENSITIVE).matcher(stime).matches();
            boolean dest = Pattern.compile(".*" + text + ".*",
                    Pattern.CASE_INSENSITIVE).matcher(currItem.m_Destination).matches();

            boolean flight = Pattern.compile(".*" + text + ".*",
                    Pattern.CASE_INSENSITIVE).matcher(currItem.m_Airlines.m_Flight).matches();

            boolean status = Pattern.compile(".*" + text + ".*",
                    Pattern.CASE_INSENSITIVE).matcher(currItem.m_Status).matches();

            if (dest != false || flight != false || status != false || m_time!=false) {
                targetDataset.add(currItem);
            }
        }

        return targetDataset;
    }
}
