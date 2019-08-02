package com.example.salus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends Activity {
    EditText firstName, lastName, email, pw, confirm;
    TextView msg;
    Button signUp;

    // REST
    RequestQueue queue = null;
//    public static final int SUCCESS = 0;
//    public static final int FORMAT_ERROR = 1;
//    public static final int EXIST_EMAIL = 2;
    // ~

    private boolean isEmpty(EditText etText) {      //  check EditText is empty
        if (etText.getText().toString().trim().length() > 0)
            return false;
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email_addr);
        pw = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm_password);
        signUp = findViewById(R.id.sign_up);
        msg = findViewById(R.id.sign_up_caution);

        // REST
        queue = Volley.newRequestQueue(this);
        // ~

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // region signUp's data format check
                if (isEmpty(firstName)) {
                    Toast.makeText(getApplicationContext(), "You didn't write firstName. \n" +
                            "Write your first name!", Toast.LENGTH_SHORT).show();
                } else if (isEmpty(lastName)) {
                    Toast.makeText(getApplicationContext(), "You didn't write lastName. \n " +
                            "Write your last name!", Toast.LENGTH_SHORT).show();
                } else if (isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "You didn't write email address. \n " +
                            "Write your email address!", Toast.LENGTH_SHORT).show();
                } else if (isEmpty(pw)) {
                    Toast.makeText(getApplicationContext(), "You didn't write password. \n " +
                            "Write your password!", Toast.LENGTH_SHORT).show();
                } else if (isEmpty(confirm)) {
                    Toast.makeText(getApplicationContext(), "You didn't write confirm. \n " +
                            "Write your confirm!", Toast.LENGTH_SHORT).show();
                } else if (pw.getText().toString() != confirm.getText().toString()) {
                    Toast.makeText(getApplicationContext(), "Your password mismatched confirm password. \n " +
                            "Check your confirm!", Toast.LENGTH_SHORT).show();
                }

                if (email.getText().toString().matches(Constants.EMAIL_REGEX) && pw.getText().toString().matches(Constants.PW_REGEX)) {
                    msg.setVisibility(View.VISIBLE);

                    Toast.makeText(getApplicationContext(), "Go to your email box and check email authentication!",
                            Toast.LENGTH_SHORT).show();

                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e) { e.printStackTrace(); }

                    Intent signUp_btn = new Intent(SignUp.this, SignIn.class);
                    startActivity(signUp_btn);
                }
                //endregion

                //REST
                final JSONObject jsonObj = new JSONObject();
                final String getFirstName = firstName.getText().toString();
                final String getLastName = lastName.getText().toString();
                final String getEmail = email.getText().toString();
                final String getPw = pw.getText().toString();

                try {
                    jsonObj.put("email", getEmail);
                    jsonObj.put("password", getPw);
                    jsonObj.put("first-name", getFirstName);
                    jsonObj.put("last-name", getLastName);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                Log.d("REST_Log", jsonObj.toString());

                // Initialize a new JsonObjectRequest instance
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        "http://teamb-iot.calit2.net/rest/app/signup",
                        jsonObj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Process the JSON
                                try{
                                    // Get the JSON array
                                    Log.d("REST_signup", "working");

                                    Integer result = response.getInt("result");

//                                    switch (result) {
//                                        case SUCCESS:
//                                        case FORMAT_ERROR:
//                                        case EXIST_EMAIL:
//                                            break;
//                                    }

                                    Log.d("REST_API", String.valueOf(result));
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){

                                Log.d("REST_API", "Error");

                                // Do something when error occurred
//                                Snackbar.make(
//                                        mCLayout,
//                                        "Error.",
//                                        Snackbar.LENGTH_LONG
//                                ).show();
                            }
                        }
                );

                // Add JsonObjectRequest to the RequestQueue
                queue.add(jsonObjectRequest);
                // ~

            }
        });
    }
}
