package ua.com.superdeal.githubsearch;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    private AutoCompleteTextView completeTextView;
    private List<Item> organizationsList;
    private List<String> item;
    private MyTask mt;
    private CardView cardView;
    private TextView orgName,orgAdress, orgUrl;
    private ImageView orgAvatar;
    private String searchChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        completeTextView = (AutoCompleteTextView) findViewById(R.id.editText);
        cardView = (CardView)findViewById(R.id.cardView);
        cardView.setVisibility(View.GONE);
        orgName=(TextView)findViewById(R.id.org_name);
        orgAdress=(TextView)findViewById(R.id.org_adress);
        orgUrl =(TextView)findViewById(R.id.org_url);
        orgAvatar=(ImageView)findViewById(R.id.org_avatar);


        completeTextView.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i >=2){
                    searchChar=completeTextView.getText().toString();
                    mt = new MyTask(searchChar);
                    mt.execute();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        completeTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > adapterView, View view,int i, long l){
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);


                cardView.setVisibility(View.VISIBLE);
                orgName.setText(organizationsList.get(i).getLogin());
                orgAdress.setText(organizationsList.get(i).getType());
                orgUrl.setText(organizationsList.get(i).getReposUrl());
                Picasso.with(MainActivity.this)
                        .load(organizationsList.get(i).getAvatarUrl())
                        .error(R.mipmap.no_image_available)
                        .into(orgAvatar);
            }
        });
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


    public class MyTask extends AsyncTask<Void, Void, List<String>> {
        String searchChar;
        public ProgressDialog dialog;
        Context ctx;
        public MyTask(String searchChar) {
            this.searchChar=searchChar;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Поиск...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(true);
            dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected List<String> doInBackground(Void... voids) {
            Response<Repo> response = null;
            organizationsList = new ArrayList<>();
            item = new ArrayList<>();
            try {
                response = App.getApi().getData(searchChar).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            response.body().getItems();
            organizationsList.addAll(response.body().getItems());

            for (int i = 0; i < organizationsList.size(); i++) {
                if (organizationsList.get(i).getType().contains("Organization")){
                item.add(organizationsList.get(i).getLogin());}
            }
            return item;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            completeTextView.setAdapter(new ArrayAdapter<>(MainActivity.this,
                    android.R.layout.simple_dropdown_item_1line, item));
            dialog.dismiss();

        }
    }

}
