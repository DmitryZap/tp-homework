package ru.techpark.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import ru.techpark.myapplication.Number.Number;
import ru.techpark.myapplication.Number.OnNumberClickListener;

public class MainActivity extends AppCompatActivity implements OnNumberClickListener {

    private ListFragment listFragment;
    private OneNumberFragment oneNumberFragment;
    private ConstraintLayout fragmentContainer;
    private Integer size = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            size = savedInstanceState.getInt("size", 100);
        }
        oneNumberFragment = (OneNumberFragment) getSupportFragmentManager().findFragmentByTag(OneNumberFragment.TAG);
        listFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(ListFragment.TAG);

        if (oneNumberFragment == null) {
            oneNumberFragment = new OneNumberFragment();
        }

        if (listFragment == null) {
            listFragment = new ListFragment();
//            listFragment = ListFragment.newInstance(size);
        }

        showListFragment();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager fm = getSupportFragmentManager();
        //getFragmentManager().putFragment(outState,"list_fragment", fm.findFragmentByTag(ListFragment.TAG));
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        outState.putInt("size", size);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            size = savedInstanceState.getInt("size", 100);
        }
    }


    public void fromFragmentData(Integer size) {
        this.size = size;
    }

    @Override
    public void onNumberClick(Number number) {
        oneNumberFragment.setNumber(number);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, oneNumberFragment, OneNumberFragment.TAG)
                .addToBackStack(OneNumberFragment.TAG)
                .commit();
    }
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            finish();
        }
        getSupportFragmentManager().popBackStack();
    }

    private void showListFragment() {
//        Bundle bundle = new Bundle();
//        bundle.putInt("size", size);
//        listFragment.setArguments(bundle);

        if(getSupportFragmentManager().findFragmentByTag(ListFragment.TAG) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, listFragment, ListFragment.TAG)
                    .commit();
        }
    }
}