package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodorderapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    EditText phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        phone = findViewById(R.id.phoneBox);
        address = findViewById(R.id.addressBox);


        final DBHelper helper = new DBHelper(this);

        if (getIntent().getIntExtra("type", 0) == 1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String foodName = getIntent().getStringExtra("foodName");
            final String description = getIntent().getStringExtra("description");

            binding.detailImage.setImageResource(image);
            binding.priceLabel.setText(String.format("%d", price));
            binding.foodName.setText(foodName);
            binding.detailDescription.setText(description);

            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String ph = phone.getText().toString();
                    String add = address.getText().toString();
                    if (TextUtils.isEmpty(ph) || TextUtils.isEmpty(add)) {
                        Toast.makeText(DetailActivity.this, "Please Fill out All the Data", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean isInserted = helper.insertOrders(binding.phoneBox.getText().toString(),
                                binding.addressBox.getText().toString(),
                                price,
                                image,
                                foodName,
                                description,
                                Integer.parseInt(binding.quantity.getText().toString()));
                        if (isInserted) {
                            Toast.makeText(DetailActivity.this, "Data Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
        else {
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
            int image = cursor.getInt(4);
            binding.detailImage.setImageResource(image);
            binding.priceLabel.setText(String.format("%d", cursor.getInt(2)));
            binding.foodName.setText(cursor.getString(6));
            binding.detailDescription.setText(cursor.getString(5));
            binding.phoneBox.setText(cursor.getString(1));
            binding.addressBox.setText(cursor.getString(3));
            binding.insertButton.setText("Update Now");

            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isUpdated = helper.updateOrder(
                            binding.phoneBox.getText().toString(),
                            binding.addressBox.getText().toString(),
                            Integer.parseInt(binding.priceLabel.getText().toString()),
                            image,
                            binding.detailDescription.getText().toString(),
                            binding.foodName.getText().toString(),
                            1,
                            id);
                    if (isUpdated) {
                        Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(DetailActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}