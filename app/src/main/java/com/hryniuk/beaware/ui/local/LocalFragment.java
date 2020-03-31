package com.hryniuk.beaware.ui.local;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.ActionBar;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.hryniuk.beaware.MainActivity;
import com.hryniuk.beaware.R;

import java.util.ArrayList;
import java.util.Objects;

public class LocalFragment extends Fragment {

    private LinearLayout infoPanel;
    private RelativeLayout hideAnim;
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ArrayList<World> worldArrayList;
    private final Handler handler = new Handler();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText("Local Info");
        tv.setTextSize(20);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        Typeface tf = ResourcesCompat.getFont(Objects.requireNonNull(getActivity()), R.font.comfortaa_bold);
        tv.setTypeface(tf);
        Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setCustomView(tv);
        setHasOptionsMenu(true);


        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        hideAnim = root.findViewById(R.id.hideAnim);
        infoPanel = root.findViewById(R.id.infoPanel);
        infoPanel.setVisibility(View.GONE);
        initView(root);



        return root;
    }



    @Override
    public void onStart() {
        super.onStart();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideAnim.setVisibility(View.GONE);
                infoPanel.setVisibility(View.VISIBLE);
            }
        }, 1500);

    }


    private void initView(View view) {
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        createListData();
        adapter = new CardAdapter(getActivity(), worldArrayList);
        recyclerView.setAdapter(adapter);

        // recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    }


    @Override
    public void onCreateOptionsMenu(Menu menu,  MenuInflater inflater) {

        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
         super.onCreateOptionsMenu(menu, inflater);
    }

    private void createListData() {
        worldArrayList = new ArrayList<>();
        World world = new World("China", "81,470", "+31", "3,304", "+4", "75,700", "2,466");
        worldArrayList.add(world);
        world = new World("Spain", "81,470", "+31", "3,304", "+4", "75,700", "2,466");
        worldArrayList.add(world);
        world = new World("Netherlands", "81,470", "+31", "3,304", "+4", "75,700", "2,466");
        worldArrayList.add(world);
        world = new World("Italy", "81,470", "+31", "3,304", "+4", "75,700", "2,466");
        worldArrayList.add(world);
        world = new World("Iran", "81,470", "+31", "3,304", "+4", "75,700", "2,466");
        worldArrayList.add(world);
        world = new World("Germany", "81,470", "+31", "3,304", "+4", "75,700", "2,466");
        worldArrayList.add(world);
        world = new World("France", "81,470", "+31", "3,304", "+4", "75,700", "2,466");
        worldArrayList.add(world);
        world = new World("S. Korea", "81,470", "+31", "3,304", "+4", "75,700", "2,466");
        worldArrayList.add(world);
        world = new World("USA", "81,470", "+31", "3,304", "+4", "75,700", "2,466");
        worldArrayList.add(world);
        world = new World("Switzerland", "81,470", "+31", "3,304", "+4", "75,700", "2,466");
        worldArrayList.add(world);
        //adapter.notifyDataSetChanged();
    }



}
