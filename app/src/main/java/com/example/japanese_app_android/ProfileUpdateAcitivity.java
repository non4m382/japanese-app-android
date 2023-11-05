package com.example.japanese_app_android;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.japanese_app_android.util.SharedPref;

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
        SharedPref.init(getApplicationContext());

        //------------------------------
        //1. findViewById
        //1.1. Button
        btnDob = findViewById(R.id.profileUpdate_btnDob);
        btnSave = findViewById(R.id.profileUpdate_btnSave);
        btnBack = findViewById(R.id.profileUpdate_btnBack);
        btnLogout = findViewById(R.id.profileUpdate_btnLogout);

        //1.2. EditText
        editMail = findViewById(R.id.profileUpdate_mailView);
        editLastName = findViewById(R.id.profileUpdate_lastNameInput);
        editFirstName = findViewById(R.id.profileUpdate_firstNameInput);
//        editPass = findViewById(R.id.profileUpdate_passwordInput);
        editPhone = findViewById(R.id.profileUpdate_phoneInput);

        //1.3. TextView
        tvFullName = findViewById(R.id.profileUpdate_tvFullName);

        //1.4. ImageView
        imgAvatar = findViewById(R.id.profileUpdate_imgAvatar);

        //------------------------------
        //2. setText
        //2.1. Button: Date of birth
        //2.1.1. Lấy Dob từ SharePref
        String txtDob = SharedPref.read(SharedPref.DOB, getTodaysDate());
        //2.1.2. Tách chuỗi để lấy ngày, tháng, năm
        String[] strDob = txtDob.split(" ");
        String monthDob = strDob[1];
        String dayDob = strDob[2];
        String yearDob = strDob[strDob.length - 1];
        //2.1.3. Tạo chuỗi mới theo format "dd/MM/yyyy"
        String formatDob = dayDob + " / " + getMonthFormat(monthDob) + " / " + yearDob;
        btnDob.setText(formatDob.toString());

        //2.2. EditText
        editMail.setText(SharedPref.read(SharedPref.MAIL, ""));
        editLastName.setText(SharedPref.read(SharedPref.LAST_NAME, ""));
        editFirstName.setText(SharedPref.read(SharedPref.FIRST_NAME, ""));
//        editPass.setText(SharedPref.read(SharedPref.TOKEN, ""));
        editPhone.setText(SharedPref.read(SharedPref.PHONE, ""));

        //2.3. TextView
        String last = editLastName.getText().toString();
        String first = editFirstName.getText().toString();
        tvFullName.setText(last + " " + first);

        //2.4. Image View
        imgAvatar.setImageResource(R.drawable.doremon_anbanh);
//        imgAvatar.setImageURI(Uri.parse(SharedPref.read(SharedPref.AVATAR, "")));

        //------------------------------
        //3. setEnabled
        editMail.setEnabled(false);
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1; // Tháng bắt đầu từ 0 nên cộng thêm 1
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1; // Tháng bắt đầu từ 0 nên cộng thêm 1
                String date = makeDateString(day, month, year);
                btnDob.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        //androidx.appcompat.app.AlertDialog (calendar view)
        int style = AlertDialog.BUTTON_POSITIVE;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        return day + " / " + month + " / " + year;
    }

    //Select date
    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    //Back to home page
    public void btnBack_click(View view) {
        Intent intent = new Intent(ProfileUpdateAcitivity.this, MainActivity.class);
        Log.d("profile_update_activity", "button back clicked: return home");
        startActivity(intent);
    }

    //Log out
    public void btnLogout_click(View view) {
        Log.d("profile_update_activity", "button log out clicked");

        SharedPref.write(SharedPref.LOGGED, false);
        SharedPref.write(SharedPref.TOKEN, "");

        if (!SharedPref.read(SharedPref.LOGGED, false)
                || SharedPref.read(SharedPref.TOKEN, "").isEmpty()) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            Log.d("profile_update_activity", "return login");
            startActivity(intent);
        }
    }

    //format month
    private String getMonthFormat(String month) {
        if (month.toLowerCase().contains("JAN".toLowerCase()))
            return "01";
        if (month.toLowerCase().contains("FEB".toLowerCase()))
            return "02";
        if (month.toLowerCase().contains("MAR".toLowerCase()))
            return "03";
        if (month.toLowerCase().contains("APR".toLowerCase()))
            return "04";
        if (month.toLowerCase().contains("MAY".toLowerCase()))
            return "05";
        if (month.toLowerCase().contains("JUN".toLowerCase()))
            return "06";
        if (month.toLowerCase().contains("JUL".toLowerCase()))
            return "07";
        if (month.toLowerCase().contains("AUG".toLowerCase()))
            return "08";
        if (month.toLowerCase().contains("SEP".toLowerCase()))
            return "09";
        if (month.toLowerCase().contains("OCT".toLowerCase()))
            return "10";
        if (month.toLowerCase().contains("NOV".toLowerCase()))
            return "11";
        if (month.toLowerCase().contains("DEC".toLowerCase()))
            return "12";

        //default should never happen
        return "01";
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }
}