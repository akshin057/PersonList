package com.example.personlist;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MyAlertDialog {
    public static void createDialog(Context context, int position, ArrayAdapter adapter){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
                    Toast.makeText(context, "Пользователь успешно удален", Toast.LENGTH_SHORT).show();
                });
        builder.create().show();
    }

}
