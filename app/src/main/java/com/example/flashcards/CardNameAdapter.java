package com.example.flashcards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashcards.databinding.SampleCardBinding;

import java.util.ArrayList;

public class CardNameAdapter extends RecyclerView.Adapter<CardNameAdapter.CardNameViewHolder> { // Step 5
    private ArrayList<CardName> cardNameArrayList;

    public CardNameAdapter(ArrayList<CardName> cardNameArrayList) {
        this.cardNameArrayList = cardNameArrayList;
    }

    @NonNull
    @Override
    public CardNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Step 5.2
        SampleCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.sample_card, parent,false);
        return new CardNameViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardNameViewHolder holder, int position) { // Step 5.3
        CardName currentCard = cardNameArrayList.get(position);
        holder.sampleCardBinding.setCardName(currentCard);
        holder.sampleCardBinding.setCardDetail(currentCard);
        boolean isVisible = currentCard.visibility;
        holder.sampleCardBinding.textView4.setVisibility(isVisible ? View.VISIBLE: View.GONE);
    }

    @Override
    public int getItemCount() { // Step 5.4
        return cardNameArrayList.size();
    }


    public class CardNameViewHolder extends RecyclerView.ViewHolder{ // Step 5.1
        private SampleCardBinding sampleCardBinding;

        public CardNameViewHolder( SampleCardBinding sampleCardBinding) {
            super(sampleCardBinding.getRoot());
            this.sampleCardBinding = sampleCardBinding;

            sampleCardBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    CardName cardName = cardNameArrayList.get(getAdapterPosition());
                    cardName.setVisibility(!cardName.isVisibility());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
