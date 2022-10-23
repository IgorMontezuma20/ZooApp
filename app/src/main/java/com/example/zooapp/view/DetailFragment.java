package com.example.zooapp.view;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.palette.graphics.Palette;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.zooapp.R;
import com.example.zooapp.databinding.FragmentDetailBinding;
import com.example.zooapp.databinding.FragmentListBinding;
import com.example.zooapp.model.AnimalModel;
import com.example.zooapp.model.AnimalPalette;
import com.example.zooapp.util.Util;


public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;

    private AnimalModel animal;

    public DetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            animal = DetailFragmentArgs.fromBundle(getArguments()).getAnimalModel();
        }

        if (animal != null) {

            binding.setAnimal(animal);

            setupBackgroundColor(animal.imageUrl);
        }

    }

    private void setupBackgroundColor(String url) {
        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Palette.from(resource)
                                .generate(palette -> {
                                    Palette.Swatch color = palette.getLightMutedSwatch();
                                    if (color != null) {
                                        int intColor = palette.getLightMutedSwatch().getRgb();
                                        AnimalPalette animalPalette = new AnimalPalette(intColor);
                                        binding.setPalette(animalPalette);
                                    }
                                });
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }
}