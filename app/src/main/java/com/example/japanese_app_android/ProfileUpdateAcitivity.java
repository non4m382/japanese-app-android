package com.example.japanese_app_android;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ProfileUpdateAcitivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;

    private Button btnDob, btnSave, btnBack, btnLogout;

    private EditText editLastName, editFirstName, editMail, editPass, editPhone;

    private TextView tvFullName;

    private ImageView imgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_update);
        initDatePicker();

        //------------------------------
        //findViewById
        btnDob = findViewById(R.id.profileUpdate_btnDob);
        btnSave = findViewById(R.id.profileUpdate_btnSave);
        btnBack = findViewById(R.id.profileUpdate_btnBack);
        btnLogout = findViewById(R.id.profileUpdate_btnLogout);

        editMail = findViewById(R.id.profileUpdate_mailView);
        editLastName = findViewById(R.id.profileUpdate_lastNameInput);
        editFirstName = findViewById(R.id.profileUpdate_firstNameInput);
        editPass = findViewById(R.id.profileUpdate_passwordInput);
        editPhone = findViewById(R.id.profileUpdate_phoneInput);

        tvFullName = findViewById(R.id.profileUpdate_tvFullName);

        imgAvatar = findViewById(R.id.profileUpdate_imgAvatar);

        //------------------------------
        //setText
        btnDob.setText(getTodaysDate());

        editMail.setText("@gmail.com");
        editLastName.setText("Ngọc");
        editFirstName.setText("Mai");
        editPass.setText("123");
        editPhone.setText("0337892644");

        String last = editLastName.getText().toString();
        String first = editFirstName.getText().toString();
        tvFullName.setText(last + " " + first);

        imgAvatar.setImageResource(R.drawable.doremon_anbanh);

        //------------------------------
        //enable
        editMail.setEnabled(false);
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                btnDob.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        //androidx.appcompat.app.AlertDialog
        int style = AlertDialog.BUTTON_POSITIVE;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        return day + " / " + month + " / " + year;
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}