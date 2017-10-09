package ua.com.superdeal.githubsearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;


public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView completeTextView;
    private List<Item> organizationsList;
    private Observable<Repo> item;
    private CardView cardView;
    private TextView orgName, orgAdress, orgUrl;
    private ImageView orgAvatar;
    private String searchChar;
    private ArrayAdapter adapter;
    private Response<Repo> response = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        completeTextView = (AutoCompleteTextView) findViewById(R.id.editText);
        cardView = (CardView) findViewById(R.id.cardView);
        cardView.setVisibility(View.GONE);
        orgName = (TextView) findViewById(R.id.org_name);
        orgAdress = (TextView) findViewById(R.id.org_adress);
        orgUrl = (TextView) findViewById(R.id.org_url);
        orgAvatar = (ImageView) findViewById(R.id.org_avatar);
//        adapter = new ArrayAdapter<>(MainActivity.this,
//                android.R.layout.simple_dropdown_item_1line, item);

        organizationsList = new ArrayList<>();

//        Observable<Repo> resp = App.getApi().getData("f");
//        resp.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(repo -> {
//
//                });

        RxSearchView.fromSearchView(completeTextView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(item -> item.length() > 2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> {
                    item =  App.getApi().getData(query);
                             //organizationsList= repo.getItems());
                    //// TODO: 04.10.17  add to adapter
                });

//        completeTextView.addTextChangedListener(new TextWatcher(){
//
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (i >=2){
//                    searchChar=completeTextView.getText().toString();
//                    mt = new MyTask(searchChar);
//                    mt.execute();
//                }
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        completeTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick (AdapterView < ? > adapterView, View view,int i, long l){
//                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
//
//
//                cardView.setVisibility(View.VISIBLE);
//                orgName.setText(organizationsList.get(i).getLogin());
//                orgAdress.setText(organizationsList.get(i).getType());
//                orgUrl.setText(organizationsList.get(i).getReposUrl());
//                Picasso.with(MainActivity.this)
//                        .load(organizationsList.get(i).getAvatarUrl())
//                        .error(R.mipmap.no_image_available)
//                        .into(orgAvatar);
//            }
//        });
//
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
    }


//    public class MyTask extends AsyncTask<Object, Object, Void> {
//        String searchChar;
//        public ProgressDialog dialog;
//        Context ctx;

//        public MyTask(String searchChar) {
//            this.searchChar = searchChar;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            dialog = new ProgressDialog(MainActivity.this);
//            dialog.setMessage("Поиск...");
//            dialog.setIndeterminate(true);
//            dialog.setCancelable(true);
//            dialog.show();
//        }
//
//        @Override
//        protected void onProgressUpdate(Object... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected Void doInBackground(Object... voids) {
//            Response<Repo> response = null;
////            organizationsList = new ArrayList<>();
////            item = new ArrayList<>();
//////            try {
//////             //   response = App.getApi().getData(searchChar).execute();
//////            } catch (IOException e) {
//////                e.printStackTrace();
//////            }
////            response.body().getItems();
////            organizationsList.addAll(response.body().getItems());
////
////            for (int i = 0; i < organizationsList.size(); i++) {
////                if (organizationsList.get(i).getType().contains("Organization")) {
////                   // item.add(organizationsList.get(i).getLogin());
////                }
////            }
////            return ;
//        }
//
//        @Override
//        protected void onPostExecute(Void strings) {
//            super.onPostExecute(strings);
////            completeTextView.setAdapter(new ArrayAdapter<>(MainActivity.this,
////                    android.R.layout.simple_dropdown_item_1line, item));
//            dialog.dismiss();
//
//        }
//    }

}
