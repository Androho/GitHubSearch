package ua.com.superdeal.githubsearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    AutoCompleteTextView textView;
    private ArrayAdapter<String> adapter;
    String item[]={
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    };
    Repo repo =new Repo();
    List<Organizations> organizationsList;

    //    String item[] =repo.getLogin();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        textView = (AutoCompleteTextView) findViewById(R.id.editText);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        organizationsList = new ArrayList<>();
        String s =organizationsList.get(0).getLogin();

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, item);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.editText);
        textView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
