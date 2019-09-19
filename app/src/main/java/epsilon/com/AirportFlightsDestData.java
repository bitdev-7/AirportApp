package epsilon.com;

import java.util.Arrays;
import java.util.List;

public class AirportFlightsDestData
{
    public class CountryCityRel
    {
        public CountryCityRel(int countryId, int[] cityIds)
        {
            this.m_cityIds = cityIds;
            this.m_countryId = countryId;
        }

        private int m_countryId;
        private int[] m_cityIds;
    }

    public String getFlagResourceByDestCity(String destCity)
    {
        int countryId = -1;
        for (int index = 0; index < m_DestCities.size(); index++) {
            if (m_DestCities.get(index) == destCity) {
                for (int country = 0; country < m_CountryCityRelTable.size(); country++) {
                    int[] cityIds = m_CountryCityRelTable.get(country).m_cityIds;
                    for (int city = 0; city < cityIds.length && cityIds != null; city++)
                        countryId = (cityIds[city] == index) ?
                                m_CountryCityRelTable.get(country).m_countryId : countryId;
                }
            }
        }

        return m_countryResName.get(countryId);
    }

    public List<String> m_DestCities = Arrays.asList(
            "Atlanta", "Beijing", "Dubai", "Tokyo", "Los Angeles", "Chicago", "London", "Hong Kong",
            "Shanghai", "Paris", "Amsterdam", "Dallas", "Guangdong", "Frankfurt", "Istanbul", "Delhi", "Tangerang",
            "Changi", "Incheon", "Denver", "New York", "San Francisco", "Madrid", "Las Vegas", "Barcelona", "Mumbai", "Torronto");

    public List<String> m_countryResName = Arrays.asList(
            "peoplesrepublicofchina", "unitedstates", "unitedarabemirates", "japan", "unitedkingdom",
            "hongkong", "france", "netherlands", "germany", "turkey", "india", "indonesia",
            "singapore", "southkorea", "spain", "canada");

    public List<String> m_airlinesResName = Arrays.asList(
            "aa2", "aeromexico", "airberlin", "aircanada", "airfrance2", "airindia2", "airmadagascar",
            "airphillipines", "airtran", "alaskaairlines3", "alitalia", "austrian2", "avianca1",
            "ba2", "brusselsairlines2", "cathaypacific21", "china_airlines", "continental",
            "croatia2", "dagonair", "delta3", "elal2", "emirates_logo2", "ethiopianairlines4",
            "garudaindonesia", "hawaiian2", "iberia2", "icelandair", "jal2", "klm2", "korean",
            "lan2", "lot2", "lufthansa4", "malaysia", "midweat", "newzealand", "nwa1", "oceanic",
            "qantas2", "sabena2", "singaporeairlines", "southafricanairways2", "southwest2",
            "spirit", "srilankan", "swiss", "swissair3", "tap", "tarom", "thai4", "turkish",
            "united", "varig", "vietnamairlines", "virgin4", "wideroe1");

    public List<CountryCityRel> m_CountryCityRelTable =
            Arrays.asList(new CountryCityRel(0, new int[] { 1, 8, 12, }),
                    new CountryCityRel(1, new int[] { 0, 4, 5, 11, 19, 20, 21,23 }),
                    new CountryCityRel(2, new int[] { 2 }),
                    new CountryCityRel(3, new int[] { 3 }),
                    new CountryCityRel(4, new int[] { 6 }),
                    new CountryCityRel(5, new int[] { 7 }),
                    new CountryCityRel(6, new int[] { 9 }),
                    new CountryCityRel(7, new int[] { 10 }),
                    new CountryCityRel(8, new int[] { 13 }),
                    new CountryCityRel(9, new int[] { 14 }),
                    new CountryCityRel(10, new int[] { 15, 22, 25 }),
                    new CountryCityRel(11, new int[] { 16 }),
                    new CountryCityRel(12, new int[] { 17 }),
                    new CountryCityRel(13, new int[] { 18 }),
                    new CountryCityRel(14, new int[] { 21, 24 }),
                    new CountryCityRel(15, new int[] { 26 }));

    public List<String> m_Status = Arrays.asList("Check-In", "Canceled", "Expected", "Delayed");
}