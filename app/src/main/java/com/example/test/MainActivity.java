package com.example.test;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<FoodItem> foodList = new ArrayList<>();
    public static int totalCalories = 0;
    private FoodItemAdapter foodAdapter;
    private EditText foodNameInput;
    private EditText foodCaloriesInput;
    private TextView caloriesTextView;
    private ListView foodListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodNameInput = findViewById(R.id.foodNameInput);
        foodCaloriesInput = findViewById(R.id.foodCaloriesInput);
        caloriesTextView = findViewById(R.id.caloriesTextView);
        foodListView = findViewById(R.id.foodListView);

        foodAdapter = new FoodItemAdapter(this, getFoodList());
        foodListView.setAdapter(foodAdapter);

        Button addFoodButton = findViewById(R.id.addFoodButton);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood();
            }
        });

        Button viewFoodListButton = findViewById(R.id.viewFoodListButton);
        viewFoodListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFoodList();
            }
        });

    }




    // ฟังก์ชันอื่น ๆ ที่คุณมีที่นี่

    private void addFood() {
        String foodName = foodNameInput.getText().toString().trim();
        String caloriesStr = foodCaloriesInput.getText().toString().trim();

        // ตรวจสอบว่าไม่มีช่องว่าง
        if (foodName.isEmpty() || caloriesStr.isEmpty()) {
            // แจ้งเตือนว่าต้องกรอกข้อมูล
            // ตรงนี้คุณอาจใช้ Toast หรือ AlertDialog แจ้งเตือนผู้ใช้
            return;
        }

        // แปลงข้อมูลแคลอรี่เป็นตัวเลข
        int calories = Integer.parseInt(caloriesStr);

        // สร้างอ็อบเจ็กต์ FoodItem จากข้อมูลที่รับมา
        FoodItem newFoodItem = new FoodItem(foodName, calories);

        // เพิ่มอาหารลงในรายการ
        MainActivity.foodList.add(newFoodItem);

        // อัปเดตรวมแคลอรี่
        MainActivity.totalCalories += calories;
        MainActivity.updateCaloriesText();

        // ล้างข้อมูลใน EditText
        foodNameInput.setText("");
        foodCaloriesInput.setText("");

        // อัปเดต Adapter แสดงรายการ
        foodAdapter.notifyDataSetChanged();
    }

    private void showFoodList() {
        // โค้ดแสดงรายการอาหารที่นี่
    }

    public static void updateCaloriesText() {
        // โค้ดอัปเดตข้อความแคลอรี่ที่นี่
    }
    public static ArrayList<FoodItem> getFoodList() {
        return foodList;
    }
}