package com.example.bluejackpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.bluejackpharmacy.database.AppDatabase;
import com.example.bluejackpharmacy.database.User;
import com.example.bluejackpharmacy.database.UserDao;
import com.example.bluejackpharmacy.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        UserDao userDao = AppDatabase.getInstance(this).userDao();

        binding.edtName.addTextChangedListener(new ClearErrorWatcher(binding.layoutName));
        binding.edtEmail.addTextChangedListener(new ClearErrorWatcher(binding.layoutEmail));
        binding.edtPassword.addTextChangedListener(new ClearErrorWatcher(binding.layoutPassword));
        binding.edtConfirm.addTextChangedListener(new ClearErrorWatcher(binding.layoutConfirm));
        binding.edtPhone.addTextChangedListener(new ClearErrorWatcher(binding.layoutPhone));

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean passed = true;
                String name = binding.edtName.getText().toString();
                String email = binding.edtEmail.getText().toString();
                String password = binding.edtPassword.getText().toString();
                String confirm = binding.edtConfirm.getText().toString();
                String phone = binding.edtPhone.getText().toString();

                if(name.isEmpty()) {
                    passed = false;
                    binding.layoutName.setError("Nama tidak boleh kosong");
                } else if(name.length() < 5) {
                    passed = false;
                    binding.layoutName.setError("Nama tidak boleh kurang dari 5 karakter");
                }

                if(email.isEmpty()) {
                    passed = false;
                    binding.layoutEmail.setError("Email tidak boleh kosong");
                } else if(!email.endsWith(".com")) {
                    passed = false;
                    binding.layoutEmail.setError("Email harus berakhiran .com");
                }

                if(password.isEmpty()) {
                    passed = false;
                    binding.layoutPassword.setError("Password tidak boleh kosong");
                } else if(!password.matches("[A-Za-z0-9]+")) {
                    passed = false;
                    binding.layoutPassword.setError("Password harus alphanumeric");
                }

                if(confirm.isEmpty()) {
                    passed = false;
                    binding.layoutConfirm.setError("Confirm Password tidak boleh kosong");
                } else if(!confirm.equals(password)) {
                    passed = false;
                    binding.layoutConfirm.setError("Confirm Password tidak sama");
                }

                if(phone.isEmpty()) {
                    passed = false;
                    binding.layoutPhone.setError("Phone tidak boleh kosong");
                }

                if(passed) {
                    User user = new User();
                    user.name = name;
                    user.email = email;
                    user.password = password;
                    user.phone = phone;

                    User registered = userDao.checkEmail(email);

                    if(registered != null) {
                        Toast.makeText(RegisterActivity.this, "Email sudah terdaftar", Toast.LENGTH_SHORT).show();
                    } else {
                        userDao.register(user);
                        Toast.makeText(getApplicationContext(), "Berhasil. Silahkan Login", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Periksa kembali inputan anda", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}