package com.example.foodorderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.foodorderapp.Adapters.MainAdapter;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String name , price , description;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.burger, name= "Burger" , price= "70" , description= "Beef burger with extra cheese" )) ;
        list.add(new MainModel(R.drawable.pizzah, name= "Pizza" , price= "100" , description= " dish made of bread dough spread including tomatoes and cheese and often other toppings" )) ;
        list.add(new MainModel(R.drawable._acm8a8uw7_1612872734, name= "Crepe" , price= "30" , description= " French pancake made of a thin batter containing flour, eggs, melted butter, salt, milk, water." )) ;
        list.add(new MainModel(R.drawable.hotdogs_te_square_200702, name= "Hot Dog" , price= "40" , description= "cooked sausage that is skinless or stuffed in a casing." )) ;
        list.add(new MainModel(R.drawable.chicken_shawarma_angle, name= "Shawarma" , price= "20" , description= "sandwich especially of sliced lamb or chicken, vegetables, and often tahini wrapped in pita bread." )) ;

        MainAdapter adapter= new MainAdapter(list , this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}