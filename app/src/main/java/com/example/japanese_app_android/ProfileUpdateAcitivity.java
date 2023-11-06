package com.example.japanese_app_android;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.japanese_app_android.model.request.AccountUpdateRequest;
import com.example.japanese_app_android.model.response.GeneralResponse;
import com.example.japanese_app_android.retrofit.AccountApi;
import com.example.japanese_app_android.retrofit.RetrofitService;
import com.example.japanese_app_android.util.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileUpdateAcitivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;

    private Button btnDob, btnSave, btnBack, btnLogout;

    private EditText editLastName, editFirstName, editMail, editPhone;

    private TextView tvFullName;

    private ImageView imgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_update);
        SharedPref.init(getApplicationContext());
        //0. init Date of birth calendar
        initDatePicker();
        //1. findViewById
        findViewById_Custom();
        //2. setText
        setText_Custom();
        //3. setEnabled
        editMail.setEnabled(false);
    }

    //------------------------------
    private void findViewById_Custom() {
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
        editPhone = findViewById(R.id.profileUpdate_phoneInput);

        //1.3. TextView
        tvFullName = findViewById(R.id.profileUpdate_tvFullName);

        //1.4. ImageView
        imgAvatar = findViewById(R.id.profileUpdate_imgAvatar);
    }

    //------------------------------
    private void setText_Custom() {
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
        editPhone.setText(SharedPref.read(SharedPref.PHONE, ""));

        //2.3. TextView
        String last = editLastName.getText().toString();
        String first = editFirstName.getText().toString();
        tvFullName.setText(last + " " + first);

        //2.4. Image View
        imgAvatar.setImageResource(R.drawable.doremon_anbanh);
//        imgAvatar.setImageURI(Uri.parse(SharedPref.read(SharedPref.AVATAR, "")));
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

    //Update profile
    public void btnSave_click(View view) {
        Long id = Long.valueOf(SharedPref.read(SharedPref.ID, -1));
        String lastName = String.valueOf(editLastName.getText());
        String firstName = String.valueOf(editFirstName.getText());
        String phone = String.valueOf(editPhone.getText());
        String txtDob = String.valueOf(btnDob.getText());
        String avatar = SharedPref.read(SharedPref.AVATAR, "https://bom.so/IHAaqt");

        String[] strDob = txtDob.split("/");
        String dayDob = strDob[0].trim();
        String monthDob = strDob[1].trim();
        String yearDob = strDob[2].trim();
        String dob = yearDob + "-" + monthDob + "-" + dayDob;

        if (id == -1) {
            Toast.makeText(getApplicationContext(), "Không thể cập nhật!",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (firstName.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tên không được để trống!",
                    Toast.LENGTH_LONG).show();
            Log.d("profile_update_activity", "firstName is not null");
            return;
        }

        RetrofitService retrofitService = new RetrofitService(getApplicationContext());
        AccountApi accountApi = retrofitService.getRetrofit().create(AccountApi.class);
        AccountUpdateRequest request = new AccountUpdateRequest(id, lastName, firstName, phone, dob, avatar);

        accountApi.update(request).enqueue(new Callback<GeneralResponse<Object>>() {
            @Override
            public void onResponse(Call<GeneralResponse<Object>> call, Response<GeneralResponse<Object>> response) {
                if (response.isSuccessful()) {
                    tvFullName.setText(lastName + " " + firstName);
                    Toast.makeText(getApplicationContext(), "Cập nhật thành công!",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    Log.d("profile_update_activity", "update success");

//                    SharedPref.write(SharedPref.LAST_NAME, lastName);
//                    SharedPref.write(SharedPref.FIRST_NAME, firstName);
//                    SharedPref.write(SharedPref.PHONE, phone);
//                    SharedPref.write(SharedPref.DOB, dob);
//                    SharedPref.write(SharedPref.AVATAR, avatar);

                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String error = jObjError.getString("data");
                        Log.d("profile_update_activity", "update failed");
                        Toast.makeText(getApplicationContext(), error,
                                Toast.LENGTH_LONG).show();
                    } catch (JSONException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse<Object>> call, Throwable t) {
                Log.d("profile_update_activity", "update failed");
            }
        });
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

    //format month: MMM -> MM
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

    //format month: MM -> MMM
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