package org.jaksa;

public interface Consts {
    String API_KEY = "INSERT YOUR API KEY HERE";
    String API_REST_PATH = "https://api.twelvedata.com/time_series?&start_date=%s&end_date=%s&symbol=%s&interval=1day&apikey=%s";
    String API_429 = "You've reached your API request limits.";
    Double YEARLY_BOND_RETURN = 0.0176;

}
