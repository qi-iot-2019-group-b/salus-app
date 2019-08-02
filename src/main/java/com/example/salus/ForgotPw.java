package com.example.salus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPw extends Activity {
    EditText email_addr;
    Button ok, cancel;

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pw);

        email_addr = (EditText) findViewById(R.id.email_addr);
        ok = (Button) findViewById(R.id.ok_btn);
        cancel = (Button) findViewById(R.id.cancel_btn);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(email_addr)) {
                    Toast.makeText(getApplicationContext(), "Write your email address!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
