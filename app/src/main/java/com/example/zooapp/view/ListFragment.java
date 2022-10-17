package com.example.zooapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zooapp.R;
import com.example.zooapp.databinding.FragmentListBinding;
import com.example.zooapp.model.AnimalModel;
import com.example.zooapp.viewmodel.ListViewModel;

import java.util.List;


public class ListFragment extends Fragment {

    private FragmentListBinding binding;

    private ListViewModel viewModel;
    private AnimalListAdapter listAdapter = new AnimalListAdapter();


    public ListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);

        viewModel.animals.observe(getViewLifecycleOwner(), list -> {
            if(list != null){
                binding.animalList.setVisibility(View.VISIBLE);
                listAdapter.updateAnimalList(list);
            }
        });
        viewModel.loading.observe(getViewLifecycleOwner(), loading -> {
            binding.loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
            if(loading){
                binding.listError.setVisibility(View.GONE);
                binding.animalList.setVisibility(View.GONE);
            }
        });

        viewModel.loadError.observe(getViewLifecycleOwner(), error -> {
            binding.listError.setVisibility(error ? View.VISIBLE : View.GONE);
        });

        viewModel.refresh();

        if(binding.animalList != null){
            binding.animalList.setLayoutManager(new GridLayoutManager(getContext(), 2));
            binding.animalList.setAdapter(listAdapter);
        }

        binding.refreshLayout.setOnRefreshListener(() -> {
            binding.animalList.setVisibility(View.GONE);
            binding.listError.setVisibility(View.GONE);
            binding.loadingView.setVisibility(View.VISIBLE);
            viewModel.refresh();
            binding.refreshLayout.setRefreshing(false);
        });
    }
}