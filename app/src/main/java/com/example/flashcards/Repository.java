package com.example.flashcards;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    // Acts as a bridge between ViewModel and Data Source
    MutableLiveData<List<CardName>> cardNamemutableLiveData;// Step 1
    FirebaseDatabase database; // Step 1
    DatabaseReference reference; // Step 1

    public Repository() { // Step 2
        this.cardNamemutableLiveData = new MutableLiveData<>();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    // Getting Card names available from FireBase DataBase

    public MutableLiveData<List<CardName>> getCardNamemutableLiveData() { // Step 3
        List<CardName> cardNameList = new ArrayList<>();// This helps to fetch data from database and put into cardNameList ArrayList ,Step 3.1

        reference.addValueEventListener(new ValueEventListener() { // Step 3.2,The Value Event Listener is used to listen for changes in data at a specific database in FireBase
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cardNameList.clear(); // Prevents Duplication of rcvd data from FireBase

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){ // This iterates through each child in the database
                    String cardName = dataSnapshot.getKey();
                    String cardDetail = dataSnapshot.child("cardDetail").getValue(String.class);

                    CardName card = new CardName(cardName, cardDetail);
                    cardNameList.add(card);
                }
                cardNamemutableLiveData.postValue(cardNameList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return cardNamemutableLiveData;
    }

    //Step 10- Creating a new Card
    public void createNewCard(String cardName, String cardDetail){
        Map<String, Object> cardData = new HashMap<>();
        cardData.put("cardName", cardName);
        cardData.put("cardDetail", cardDetail);
        reference.child(cardName).setValue(cardData);
    }
}
