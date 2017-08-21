package com.johnpetitto.example.validator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.johnpetitto.validator.ValidatingTextInputLayout;
import com.johnpetitto.validator.Validator;
import com.johnpetitto.validator.Validators;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ValidatingTextInputLayout name = (ValidatingTextInputLayout) findViewById(R.id.name);
        name.setValidator(Validators.minimum(6, true));

        final ValidatingTextInputLayout email = (ValidatingTextInputLayout) findViewById(R.id.email);
        final ValidatingTextInputLayout phone = (ValidatingTextInputLayout) findViewById(R.id.phone);

        final ValidatingTextInputLayout password = (ValidatingTextInputLayout) findViewById(R.id.password);
        password.setValidator(Validators.minimum(8));

        final ValidatingTextInputLayout confirm = (ValidatingTextInputLayout) findViewById(R.id.confirm);
        confirm.setValidator(new Validator() {
            @Override
            @SuppressWarnings("ConstantConditions")
            public boolean isValid(String input) {
                return input.equals(password.getEditText().getText().toString());
            }
        });

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validators.validate(name, email, phone, password, confirm)) {
                    Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
