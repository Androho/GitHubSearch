package ua.com.superdeal.githubsearch;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public class RxSearchView {
    public static Observable<String> fromSearchView(@NonNull final AutoCompleteTextView searchView) {
        final BehaviorSubject<String> subject = BehaviorSubject.create("");

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String searchChar = searchView.getText().toString();
                if (!searchChar.isEmpty()){
                    subject.onNext(searchChar);
                }
                return;
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                subject.onCompleted();
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (!newText.isEmpty()) {
//                    subject.onNext(newText);
//                }
//                return true;
//            }
//        });

        return subject;
    }
}