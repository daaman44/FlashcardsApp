package com.example.flashcards;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    Repository repository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    // Step 4- Getting Card Names
    public MutableLiveData<List<CardName>> getCardName(){
        return repository.getCardNamemutableLiveData();
    }

    // Step 11 - Calling creating new Card Function
    public void createNewCard(String cardName,String cardDetail){
        repository.createNewCard(cardName,cardDetail);
    }
}
