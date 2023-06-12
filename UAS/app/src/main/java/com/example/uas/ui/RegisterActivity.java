package com.example.uas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uas.DBHelper;
import com.example.uas.R;

public class RegisterActivity extends AppCompatActivity {

    EditText TxName, TxEmail, TxPassword, TxConPassword, TxPhone;
    Button BtnRegister;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this);

        TxName = (EditText)findViewById(R.id.txNameReg);
        TxEmail = (EditText)findViewById(R.id.txEmailReg);
        TxPassword = (EditText)findViewById(R.id.txPasswordReg);
        TxConPassword = (EditText)findViewById(R.id.txConPassword);
        TxPhone = (EditText)findViewById(R.id.txPhoneReg);
        BtnRegister = (Button)findViewById(R.id.btnRegister);

        TextView tvRegister = (TextView)findViewById(R.id.tvRegister);

        tvRegister.setText(fromHtml("Back to " +
                "</font><font color='#3b5998'>Login</font>"));

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = TxName.getText().toString().trim();
                String email = TxEmail.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();
                String conPassword = TxConPassword.getText().toString().trim();
                String phone = TxPhone.getText().toString().trim();

                ContentValues values = new ContentValues();

                if (!password.equals(conPassword)){
                    Toast.makeText(RegisterActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
                }else if (password.equals("") || email.equals("") || name.equals("") || phone.equals("")){
                    Toast.makeText(RegisterActivity.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
                }else if (name.length() < 5){
                    Toast.makeText(RegisterActivity.this, "Name at least five characters", Toast.LENGTH_SHORT).show();
                }else if (!email.endsWith(".com")){
                    Toast.makeText(RegisterActivity.this, "Email must end with .com", Toast.LENGTH_SHORT).show();
                }else if (!password.matches("[A-Za-z0-9]+")){
                    Toast.makeText(RegisterActivity.this, "Password must be alphanumeric", Toast.LENGTH_SHORT).show();
                }else {
                    values.put(DBHelper.row_user_name, name);
                    values.put(DBHelper.row_user_email, email);
                    values.put(DBHelper.row_user_password, password);
                    values.put(DBHelper.row_user_phone, phone);
                    values.put(DBHelper.row_user_isVerified, "0");
                    dbHelper.insertData(values);

                    Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public static Spanned fromHtml(String html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}
