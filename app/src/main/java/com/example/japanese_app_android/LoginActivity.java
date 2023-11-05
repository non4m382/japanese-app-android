package com.example.japanese_app_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.japanese_app_android.model.request.LoginRequest;
import com.example.japanese_app_android.model.response.GeneralResponse;
import com.example.japanese_app_android.model.response.LoginResponseDto;
import com.example.japanese_app_android.retrofit.AuthenApi;
import com.example.japanese_app_android.retrofit.RetrofitService;
import com.example.japanese_app_android.util.SharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView txtRegisterNow, loginError;

    EditText editTextEmail, editTextPass;

    String email, pass;

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editTextEmail = findViewById(R.id.register_emailInput);
        editTextPass = findViewById(R.id.register_passwordInput);
        login = findViewById(R.id.registerButton);
        loginError = findViewById(R.id.loginError);
        SharedPref.init(getApplicationContext());

        if (SharedPref.read(SharedPref.LOGGED, false)) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        login.setOnClickListener(v -> {
            email = String.valueOf(editTextEmail.getText());
            pass = String.valueOf(editTextPass.getText());
            LoginRequest request = new LoginRequest(email, pass);
            /**
             *  Temporary Login
             */
            fastLogin(request);

            RetrofitService retrofitService = new RetrofitService(getApplicationContext());
            AuthenApi radicalApi = retrofitService.getRetrofit().create(AuthenApi.class);
            radicalApi.login(request).enqueue(new Callback<GeneralResponse<LoginResponseDto>>() {
                @Override
                public void onResponse(Call<GeneralResponse<LoginResponseDto>> call, Response<GeneralResponse<LoginResponseDto>> response) {
                    if (response.isSuccessful()) {
                        GeneralResponse<LoginResponseDto> generalResponse = response.body();
                        LoginResponseDto loginResponse = generalResponse.getData();
                        SharedPref.init(getApplicationContext());
                        SharedPref.write(SharedPref.LOGGED, true);
                        SharedPref.write(SharedPref.TOKEN, loginResponse.getToken());
                        SharedPref.write(SharedPref.ID, loginResponse.getId());
                        SharedPref.write(SharedPref.FIRST_NAME, loginResponse.getFirstName());
                        SharedPref.write(SharedPref.LAST_NAME, loginResponse.getLastName());
                        SharedPref.write(SharedPref.MAIL, loginResponse.getMail());
                        SharedPref.write(SharedPref.DOB, loginResponse.getDob().toString());
                        SharedPref.write(SharedPref.PHONE, loginResponse.getPhone());
                        SharedPref.write(SharedPref.AVATAR, loginResponse.getAvatar());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Log.d("login", "login failed");
                        loginError.setText("Sai tài khoản hoặc mật khẩu");
                    }
                }

                @Override
                public void onFailure(Call<GeneralResponse<LoginResponseDto>> call, Throwable t) {
                    Log.d("login", "failed");
                }
            });
        });

        txtRegisterNow = findViewById(R.id.txtGoToRegister);
        txtRegisterNow.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
            finish();
        });


    }

    private void fastLogin(LoginRequest loginRequest) {
        if (loginRequest.getMail().equals("user@gmail.com") && loginRequest.getPassword().equals("123")) {
            SharedPref.write(SharedPref.LOGGED, true);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        }
    }

}