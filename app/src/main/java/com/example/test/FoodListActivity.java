package com.example.test;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class FoodListActivity extends AppCompatActivity {

    private ListView foodListView;
    private FoodItemAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        foodListView = findViewById(R.id.foodListView);

        // ใช้ getter method จาก MainActivity เพื่อเข้าถึง foodList
        foodAdapter = new FoodItemAdapter(this, MainActivity.getFoodList());
        foodListView.setAdapter(foodAdapter);

        foodAdapter.setOnDeleteClickListener(new FoodItemAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                onFoodItemDelete(position);
            }
        });
    }

    private void onFoodItemDelete(int position) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("deletedPosition", position);
        setResult(RESULT_OK, resultIntent);

        FoodItem deletedFoodItem = MainActivity.getFoodList().get(position);
        MainActivity.totalCalories -= deletedFoodItem.getCalories();
        MainActivity.updateCaloriesText();

        MainActivity.getFoodList().remove(position);
        foodAdapter.notifyDataSetChanged();

        // Finish the activity
        finish();
    }
}