package com.example.sunnyweather.ui.place;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sunnyweather.R;
import com.example.sunnyweather.logic.model.Place;

import java.util.List;

public class PlaceFragment extends Fragment {
    private PlaceViewModel viewModel;
    private PlaceAdapter adapter;
    private RecyclerView recyclerView;
    private EditText searchPlaceEdit;
    private ImageView bgImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_place, container, false);
        viewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        recyclerView = view.findViewById(R.id.recyclerView);
        searchPlaceEdit = view.findViewById(R.id.searchPlaceEdit);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PlaceAdapter(viewModel.placeList);
        recyclerView.setAdapter(adapter);
        searchPlaceEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                String content = s.toString();
                if (!content.isEmpty()) {
                    viewModel.searchPlaces(content);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    viewModel.placeList.clear();
                    bgImageView.setVisibility(View.VISIBLE);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        viewModel.placeLiveData.observe(getViewLifecycleOwner(), new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> placeList) {
                if (placeList != null) {
                    recyclerView.setVisibility(View.VISIBLE);
                    bgImageView.setVisibility(View.VISIBLE);
                    viewModel.placeList.clear();
                    viewModel.placeList.addAll(placeList);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "未能查询到任何地点",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
