package com.example.personlist;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MyAlertDialog extends DialogFragment {
    Removable removable;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Removable) {
            removable = (Removable) context;


        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        String user = (String) requireArguments().get("user");
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        return builder.setTitle("Внимание!!!")
                .setMessage("Удалить пользователя " + user + "?")
                .setPositiveButton("Да", ((dialog, which) -> {
                    removable.remove(user);
                })).setNegativeButton("Нет", ((dialog, which) -> {
                    dialog.cancel();
                })).create();
    }

//    public static void createDialog(Context context, int position, ArrayAdapter adapter) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Внимание!!!")
//                .setMessage("Удалить пользователя?")
//                .setCancelable(false)
//                .setNegativeButton("Нет", (dialog, which) -> {
//                    dialog.cancel();
//                })
//                .setPositiveButton("Да", (dialog, which) -> {
//                    User user = (User) adapter.getItem(position);
//                    adapter.remove(user);
//                    dialog.cancel();
//                    Toast.makeText(context, "Пользователь успешно удален", Toast.LENGTH_SHORT).show();
//                });
//        builder.create().show();
//    }

}
