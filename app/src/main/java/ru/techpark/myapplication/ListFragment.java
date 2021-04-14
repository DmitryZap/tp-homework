package ru.techpark.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import ru.techpark.myapplication.Number.Number;
import ru.techpark.myapplication.Number.NumberAdapter;
import ru.techpark.myapplication.Number.OnNumberClickListener;

public class ListFragment extends Fragment implements OnNumberClickListener {
    private int size = 100;
    private FloatingActionButton floatingActionButton;
    private NumberAdapter numberAdapter;
    private Context mActivity;

    public final static String TAG = "ru.techpark.myapplication.ListFragment";


    public ListFragment() {
        // Required empty public constructor
    }

//    public static ListFragment newInstance(int size) {
//        ListFragment fragment = new ListFragment();
//        Bundle args = new Bundle();
//        size = args.getInt("size", 100);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            mActivity =(Activity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.items_recycler);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.add_item);
        if (this.getArguments() != null){
            size = this.getArguments().getInt("size", 100);
        }


        if (savedInstanceState != null) {
            size = savedInstanceState.getInt("size", 100);
            recyclerView.scrollToPosition(size - 1);
        }

        int orientation = view.getContext().getResources().getConfiguration().orientation;
        int columnsNumber = (orientation == Configuration.ORIENTATION_PORTRAIT) ? 3 : 4;


        numberAdapter = new NumberAdapter(size, this);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), columnsNumber));
        recyclerView.setAdapter(numberAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberAdapter = new NumberAdapter(++size, ListFragment.this);
                recyclerView.setAdapter(numberAdapter);
                recyclerView.scrollToPosition(size - 1);
                toActivity();
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("size", size);
        toActivity();
    }


    @Override
    public void onNumberClick(Number number) {
        if (mActivity != null){
            toActivity();
            ((OnNumberClickListener) mActivity).onNumberClick(number);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    public void toActivity() {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing() && activity instanceof MainActivity) {
            ((MainActivity) activity).fromFragmentData(size);
        }
    }
}