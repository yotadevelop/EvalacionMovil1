package cl.felipe.evaluacionmovil1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cl.felipe.evaluacionmovil1.adapter.AdapterDBZ;
import cl.felipe.evaluacionmovil1.model.DBZ;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static final String URL="https://dragon-ball-api.herokuapp.com/api/character/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processHttp();
    }

    public void processHttp(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String data = new String(responseBody);
                Log.d("INFO", data);
                processDBZ(data);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void processDBZ(String data) {
        try {
            JSONArray root = new JSONArray(data);

            List<DBZ> list = new ArrayList<>();

            for (int i = 0; i<data.length();i++){
                JSONObject user1 = root.getJSONObject(i);

                String species = user1.getString("species");
                String status = user1.getString("status");
                String originPlanet = user1.getString("originPlanet");
                String name = user1.getString("name");
                String image = user1.getString("image");

                DBZ dbz = new DBZ(species, status, originPlanet, name, image);
                list.add(dbz);

            }

            RecyclerView rc = findViewById(R.id.rc_dbz);
            AdapterDBZ ad = new AdapterDBZ(this, list, R.layout.activity_detail);
            LinearLayoutManager lm = new LinearLayoutManager(this);
            lm.setOrientation(RecyclerView.VERTICAL);

            rc.setLayoutManager(lm);
            rc.setAdapter(ad);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}