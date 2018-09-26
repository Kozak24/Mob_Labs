package kozak.labs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StorageActivity extends AppCompatActivity {

    ListView storageListView;
    SharedPreferences preferences;
    Set<String> recordsSet = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        preferences = getSharedPreferences(Constants.preference, Context.MODE_PRIVATE);

        recordsSet = preferences.getStringSet(Constants.preferenceRecord, null);
        List<String> recordsList = new ArrayList<>(recordsSet);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, recordsList);

        storageListView = findViewById(R.id.storageListView);

        storageListView.setAdapter(arrayAdapter);

    }


}
