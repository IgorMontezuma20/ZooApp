package com.example.zooapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.example.zooapp.R;
import com.example.zooapp.databinding.ItemAnimalBinding;
import com.example.zooapp.model.AnimalModel;
import com.example.zooapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class AnimalListAdapter extends RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder> {

    private ArrayList<AnimalModel> animalList = new ArrayList<>();

    public void updateAnimalList(List<AnimalModel> newAnimalList){
        animalList.clear();
        animalList.addAll(newAnimalList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAnimalBinding view = DataBindingUtil.inflate(inflater, R.layout.item_animal, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {

//        ImageView animalImage = holder.itemView.findViewById(R.id.animalImage);
//        ConstraintLayout animalLayout = holder.itemView.findViewById(R.id.animalLayout);
//
//        Util.loadImage(animalImage, animalList.get(position).imageUrl, Util.getProgressDrawable(animalImage.getContext()));
//        animalLayout.setOnClickListener(view -> {
//            NavDirections action = ListFragmentDirections.actionGoToDetails(animalList.get(position));
//            Navigation.findNavController(view).navigate(action);
//        });

        holder.itemView.setAnimal(animalList.get(position));
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    class AnimalViewHolder extends RecyclerView.ViewHolder{

        ItemAnimalBinding itemView;

        public AnimalViewHolder(ItemAnimalBinding view){

            super(view.getRoot());
            itemView = view;
        }
    }
}
