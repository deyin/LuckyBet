package dylan.io.luckybet.utils;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class NetworkUtils {

    public static Map<String, String> fakeHttpHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept-Language", "en-US,en;q=0.9");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        headers.put("Accept", "*/*");
        headers.put("Referer", "http://info.sporttery.cn/football/hhad_list.php");
        headers.put("Connection", "keep-alive");
        headers.put("Cookie", "BIGipServerPool_apache_web=1241579786.20480.0000; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221653109b9b94cf-03e4c0ed07ead3-9393265-1764000-1653109b9babcb%22%2C%22%24device_id%22%3A%221653109b9b94cf-03e4c0ed07ead3-9393265-1764000-1653109b9babcb%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E9%90%A9%E5%AD%98%E5%B8%B4%E5%A8%B4%E4%BE%80%E5%99%BA%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E9%8F%88%EE%81%84%E5%BD%87%E9%8D%92%E6%9D%BF%E2%82%AC%E7%B3%AD%E9%90%A9%E5%AD%98%E5%B8%B4%E9%8E%B5%E6%92%B3%E7%B4%91%22%2C%22platForm%22%3A%22information%22%2C%22%24ip%22%3A%22152.62.44.202%22%2C%22source%22%3A%22pc%E7%AB%AF%22%2C%22browser_name%22%3A%22chrome%22%2C%22browser_version%22%3A%2268.0.3440.106%22%2C%22user_gent%22%3A%22Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20Win64%3B%20x64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F68.0.3440.106%20Safari%2F537.36%22%2C%22cname%22%3A%22UNITED%20STATES%22%7D%2C%22data_from%22%3A%22information%22%7D; Hm_lvt_860f3361e3ed9c994816101d37900758=1535446849,1535953137,1535954722,1535965595; Hm_lpvt_860f3361e3ed9c994816101d37900758=1536063057");
        return headers;
    }
}
