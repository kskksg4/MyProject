package com.kekstudio.dachshundtablayoutsample.location.search;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kekstudio.dachshundtablayoutsample.location.search.adapter.SearchAddrAdapter;
import com.kekstudio.dachshundtablayoutsample.location.search.presenter.SearchLocationContract;
import com.kekstudio.dachshundtablayoutsample.location.search.presenter.SearchLocationPresenter;
import com.kekstudio.dachshundtablayoutsample.location.search.task.SearchAddressHttpTask;
import com.kekstudio.dachshundtablayoutsample.Listener.ItemClickListener;
import com.kekstudio.dachshundtablayoutsample.R;

public class SearchLocation extends Fragment implements SearchLocationContract.View{

    private LinearLayoutManager searchLocationRecyclerLayoutManager;
    private RecyclerView searchLocationRecyclerView;

    private EditText searchLocationEdittext;

    private SearchLocationPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_location, null);

        presenter = new SearchLocationPresenter();
        presenter.attachView(this);

        final InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        searchLocationRecyclerLayoutManager = new LinearLayoutManager(this.getContext());
        searchLocationRecyclerView = (RecyclerView) view.findViewById(R.id.search_location_recyclerView);
        searchLocationRecyclerView.setLayoutManager(searchLocationRecyclerLayoutManager);

        searchLocationEdittext = (EditText)view.findViewById(R.id.search_location_edittext);
        searchLocationEdittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    imm.hideSoftInputFromWindow(searchLocationEdittext.getWindowToken(), 0); // 키보드 돋보기 클릭시 키보드 "hide"

                    presenter.restapiTask(v.getText().toString(), getContext());
                }

                return false;
            }
        });

        return view;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void settingAdapter(SearchAddrAdapter adapter) {
        searchLocationRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.detachView();
//        searchAddressHttpTask.cancel(true);
    }


}
