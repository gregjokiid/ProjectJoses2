package com.example.bluejackpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bluejackpharmacy.database.AppDatabase;
import com.example.bluejackpharmacy.database.User;
import com.example.bluejackpharmacy.database.UserDao;
import com.example.bluejackpharmacy.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UserDao userDao = AppDatabase.getInstance(this).userDao();

        binding.edtEmail.addTextChangedListener(new ClearErrorWatcher(binding.layoutEmail));
        binding.edtPassword.addTextChangedListener(new ClearErrorWatcher(binding.layoutPassword));

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean passed = true;

                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();

                if(email.isEmpty()) {
                    passed = false;
                    binding.layoutEmail.setError("Email tidak boleh kosong");
                }

                if(password.isEmpty()) {
                    passed = false;
                    binding.layoutPassword.setError("Password tidak boleh kosong");
                }

                if(passed) {
                    User user = userDao.login(email, password);
                    if(user != null) {
                        SharedPreferences sharedPreferences = getSharedPreferences("sharedpref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("uid", user.uid);
                        editor.apply();

                        Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "User tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}