package com.example.zooapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zooapp.R;
import com.example.zooapp.databinding.FragmentDetailBinding;
import com.example.zooapp.databinding.FragmentListBinding;
import com.example.zooapp.model.AnimalModel;
import com.example.zooapp.util.Util;


public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;

    private AnimalModel animal;

    public DetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            animal = DetailFragmentArgs.fromBundle(getArguments()).getAnimalModel();
        }

        if (animal != null) {

            binding.animalName.setText(animal.name);
            binding.animalLocation.setText(animal.location);
            binding.animalDiet.setText(animal.diet);
            binding.animalLifespan.setText(animal.lifeSpan);

            Util.loadImage(binding.animalImage, animal.imageUrl, Util.getProgressDrawable(binding.animalImage.getContext()));

        }

    }
}