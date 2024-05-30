package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.flashcards.databinding.ActivityAddViewBinding;

import java.util.ArrayList;
import java.util.List;

public class Add_View extends AppCompatActivity {
    private ArrayList<CardName> cardNameArrayList;
    private RecyclerView recyclerView;
    private CardNameAdapter cardNameAdapter;
    private ActivityAddViewBinding binding;
    private MyViewModel myViewModel;

    // Step 12- Dialog
    private Dialog addNewCardDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);

        // Step 6
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_view);
        // Step 7 - Define ViewModel
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // Step 8 - RecyclerView with Data Binding
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Step 9 - Setup an observer to listen to changes in Live data Object (in our case getCardName() in MyViewModel
        myViewModel.getCardName().observe(this, new Observer<List<CardName>>() {
            @Override
            public void onChanged(List<CardName> cardNames) {
                // Updated data is rcvd in the list named cardNames above
                cardNameArrayList = new ArrayList<>();
                cardNameArrayList.addAll(cardNames);

                cardNameAdapter = new CardNameAdapter(cardNameArrayList); // So this adapter gets updated data to present to recyclerview
                recyclerView.setAdapter(cardNameAdapter); // Now recyclerView will show updated data accordingly
                cardNameAdapter.notifyDataSetChanged();
            }
        });

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }
    public void showDialog(){ // Step 13
        addNewCardDialog = new Dialog(this);
        addNewCardDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog_box,null);

        addNewCardDialog.setContentView(view);
        addNewCardDialog.show();
        Button submit = view.findViewById(R.id.button2);
        TextView textView = view.findViewById(R.id.textView2);
        EditText editText = view.findViewById(R.id.editText);
        EditText editText2 = view.findViewById(R.id.editText2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = editText.getText().toString();
                String getDetail = editText2.getText().toString();
                myViewModel.createNewCard(getName,getDetail);
                addNewCardDialog.dismiss();
            }
        });
    }
}