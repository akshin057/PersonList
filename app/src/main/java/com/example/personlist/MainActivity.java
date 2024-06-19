package com.example.personlist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbarTB;
    EditText nameET;
    EditText ageET;
    Button addBTN;
    ListView users;
    ConstraintLayout main;
    List<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        users.setAdapter(adapter);

        addBTN.setOnClickListener(v -> {
            User user = new User(nameET.getText().toString(), Integer.parseInt(ageET.getText().toString()));
            list.add(user);
            adapter.notifyDataSetChanged();
            nameET.getText().clear();
            ageET.getText().clear();
        });

        users.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Внимание!!!")
                    .setMessage("Удалить пользователя?")
                    .setCancelable(false)
                    .setNegativeButton("Нет", (dialog, which) -> {
                        dialog.cancel();
                    })
                    .setPositiveButton("Да", (dialog, which) -> {
                        User user = (User) adapter.getItem(position);
                        adapter.remove(user);
                        dialog.cancel();
                        Snackbar.make(Objects.requireNonNull(getCurrentFocus()), "Пользователь удален", Snackbar.LENGTH_SHORT).show();
                    });
            builder.create().show();
        });


    }

    private void initUI() {
        toolbarTB = findViewById(R.id.toolbarTB);
        nameET = findViewById(R.id.nameET);
        ageET = findViewById(R.id.ageET);
        addBTN = findViewById(R.id.addBTN);
        users = findViewById(R.id.listViewLV);
        main = findViewById(R.id.main);

        setSupportActionBar(toolbarTB);
        toolbarTB.setTitleTextColor(getColor(R.color.textColor));
        toolbarTB.setSubtitleTextColor(getColor(R.color.textColor));

        getSupportActionBar().setTitle("Каталог пользователей");
        getSupportActionBar().setSubtitle("Версия 1.0");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.exitBTN) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}