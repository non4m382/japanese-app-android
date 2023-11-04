package com.example.japanese_app_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.japanese_app_android.model.request.RegisterRequest;
import com.example.japanese_app_android.model.response.GeneralResponse;
import com.example.japanese_app_android.retrofit.AuthenApi;
import com.example.japanese_app_android.retrofit.RetrofitService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView txtGoToLogin, registerError;

    EditText editFirstName, editLastName, editEmail, editPassword, editPasswordAgain;

    String firstName, lastName, email, pass, passAgain;

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        editFirstName = findViewById(R.id.register_firstNameInput);
        editLastName = findViewById(R.id.register_lastNameInput);
        editEmail = findViewById(R.id.register_emailInput);
        editPassword = findViewById(R.id.register_passwordInput);
        editPasswordAgain = findViewById(R.id.register_confirmPasswordInput);
        register = findViewById(R.id.registerButton);
        registerError = findViewById(R.id.registerError);
        txtGoToLogin = findViewById(R.id.txtGoToLogin);

    }

    @Override
    protected void onResume() {
        super.onResume();
        register.setOnClickListener(v -> {
            firstName = String.valueOf(editFirstName.getText());
            lastName = String.valueOf(editLastName.getText());
            email = String.valueOf(editEmail.getText());
            pass = String.valueOf(editPassword.getText());
            passAgain = String.valueOf(editPasswordAgain.getText());

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || pass.isEmpty() || passAgain.isEmpty()) {
                registerError.setText("Các trường không được trống");
                return;
            }

            if (!pass.equals(passAgain)) {
                registerError.setText("Password không trùng");
                return;
            }

            RetrofitService retrofitService = new RetrofitService();
            AuthenApi radicalApi = retrofitService.getRetrofit().create(AuthenApi.class);
            RegisterRequest request = new RegisterRequest(email, pass, firstName, lastName);

            radicalApi.register(request).enqueue(new Callback<GeneralResponse<Object>>() {
                @Override
                public void onResponse(Call<GeneralResponse<Object>> call, Response<GeneralResponse<Object>> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            String error = jObjError.getString("data");
                            Log.d("register", "register failed");
                            registerError.setText(error);
                        } catch (JSONException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<GeneralResponse<Object>> call, Throwable t) {
                    Log.d("login", "failed");
                }


            });
        });

        txtGoToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}