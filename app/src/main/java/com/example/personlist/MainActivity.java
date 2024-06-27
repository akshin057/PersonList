package com.example.personlist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements Removable {

    Toolbar toolbarTB;
    EditText nameET;
    EditText ageET;
    Button addBTN;
    ListView usersLV;
    ConstraintLayout main;
    ArrayAdapter adapter;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        addBTN.setOnClickListener(v -> {
            if (nameET.getText().toString().isEmpty() || ageET.getText().toString().isEmpty()){
                Snackbar.make(main, "Данные пользователя не введены!!!", Snackbar.LENGTH_SHORT).show();
                return;
            }

            User user = new User(nameET.getText().toString(), Integer.parseInt(ageET.getText().toString()));

            userViewModel.add(user);
            adapter.notifyDataSetChanged();

            nameET.getText().clear();
            ageET.getText().clear();
        });

        userViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                usersLV.setAdapter(adapter);
            }
        });


        usersLV.setOnItemClickListener((parent, view, position, id) -> {
            String user = adapter.getItem(position).toString();
            MyAlertDialog dialog = new MyAlertDialog();
            Bundle args = new Bundle();
            args.putString("user", user);
            dialog.setArguments(args);
            dialog.show(getSupportFragmentManager(), "custom");

        });



    }

    private void initUI() {
        toolbarTB = findViewById(R.id.toolbarTB);
        nameET = findViewById(R.id.nameET);
        ageET = findViewById(R.id.ageET);
        addBTN = findViewById(R.id.addBTN);
        usersLV = findViewById(R.id.listViewLV);
        main = findViewById(R.id.main);


        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userViewModel.getUserList());
        usersLV.setAdapter(adapter);

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

    @Override
    public void remove(String user) {
        if (!userViewModel.getUserList().isEmpty()) {
            for (User user1 : userViewModel.getUserList()) {
                if (user1.toString().equals(user)) {
                    adapter.remove(user1);
                    return;
                }
            }
        }
    }

}