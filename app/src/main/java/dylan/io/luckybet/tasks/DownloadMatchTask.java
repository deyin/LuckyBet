package dylan.io.luckybet.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executors;

import dylan.io.luckybet.models.Match;
import dylan.io.luckybet.utils.NetworkUtils;
import dylan.io.luckybet.network.VolleySingleton;
import dylan.io.luckybet.persist.AppRoomDatabase;

public class DownloadMatchTask extends AsyncTask<Void, Void, Void> {

    final String URL_MATCH_LIST = "http://i.sporttery.cn/api/fb_match_info/get_matches";

    private final Context context;

    public DownloadMatchTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void[] objects) {

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("DownloadMatchTask.onErrorResponse: %s", error.getLocalizedMessage());
            }
        };

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                VolleyLog.d("DownloadMatchTask.onResponse: %s", response);
                try {
                    JSONObject root = new JSONObject(response);
                    JSONObject status = root.getJSONObject("status");
                    if (status != null && status.getInt("code") == 0) { // success
                        JSONArray jsonArray = root.getJSONArray("result");
                        for (int i = 0, len = jsonArray.length(); i < len; i++) {
                            JSONObject match = jsonArray.getJSONObject(i);
                            String matchId = match.getString("id");

                            new DownloadMatchInfoTask(matchId).execute();
                        }
                    }
                } catch (JSONException e) {
                    Log.e("DownloadMatchTask", "onResponse.new JSONObject: ", e);
                }
            }
        };

        StringRequest stringRequest = new StringRequest(URL_MATCH_LIST, responseListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return NetworkUtils.fakeHttpHeaders();
            }
        };

        stringRequest.setRetryPolicy(getRetryPolicy());

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

        return null;
    }

    @NonNull
    private static RetryPolicy getRetryPolicy() {
        return new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 60 * 1000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 3;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                VolleyLog.e("Retry error: %s", error.getMessage());
            }
        };
    }

    private class DownloadMatchInfoTask extends AsyncTask<Void, Void, Void> {

        final String URL_MATCH_INFO = "http://i.sporttery.cn/api/fb_match_info/get_match_info?mid=" + "matchId"; // matchId should be replaced real id

        final String matchId;

        private ObjectMapper objectMapper = new ObjectMapper();

        DownloadMatchInfoTask(String matchId) {
            this.matchId = matchId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("DownloadMatchInfoTask.onErrorResponse: %s", error.getLocalizedMessage());
                }
            };

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    VolleyLog.d("DownloadMatchInfoTask.onResponse: %s", response);
                    try {
                        JSONObject root = new JSONObject(response);
                        JSONObject status = root.getJSONObject("status");
                        if (status != null && status.getInt("code") == 0) { // success
                            String result = root.getJSONObject("result").toString();
                            try {
                                final Match match = objectMapper.readValue(result, Match.class);
                                Executors.newSingleThreadExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.d("Insert", "Prepare to insert match into db: " + match);
                                        AppRoomDatabase.getInstance(context.getApplicationContext()).matchDao().insert(match);
                                    }
                                });
                            } catch (IOException e) {
                                Log.e("DownloadMatchInfoTask", "objectMapper.readValue: ", e);
                            }
                        }
                    } catch (JSONException e) {
                        Log.e("DownloadMatchInfoTask", "onResponse.new JSONObject: ", e);
                    }
                }
            };

            String url = URL_MATCH_INFO.replace("matchId", this.matchId);
            StringRequest stringRequest = new StringRequest(url, responseListener, errorListener) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return NetworkUtils.fakeHttpHeaders();
                }
            };
            stringRequest.setRetryPolicy(getRetryPolicy());
            VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
            return null;
        }
    }
}
