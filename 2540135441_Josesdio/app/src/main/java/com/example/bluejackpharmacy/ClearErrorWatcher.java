package com.example.bluejackpharmacy;

import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

public class ClearErrorWatcher implements TextWatcher {

    private final TextInputLayout layout;

    public ClearErrorWatcher(TextInputLayout textInputLayout) {
        this.layout = textInputLayout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        layout.setError(null);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
