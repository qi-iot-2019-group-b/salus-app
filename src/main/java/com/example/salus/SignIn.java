package com.example.salus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends Activity {
    EditText email, pw;
    Button signIn, signUp, fpw;
    int count = Constants.COUNT;
    // REST
    RequestQueue queue = null;
    // ~

    private boolean isEmpty(EditText etText) {      //  check EditText is empty
        if (etText.getText().toString().trim().length() > 0)
            return false;
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        email = (EditText)findViewById(R.id.email_addr);
        pw = (EditText)findViewById(R.id.password);
        signIn = (Button)findViewById(R.id.sign_in);
        signUp = (Button)findViewById(R.id.sign_up);
        fpw = (Button)findViewById(R.id.forgotpw);

        // REST
        queue = Volley.newRequestQueue(this);
        // ~

        signIn.setOnClickListener(new View.OnClickListener() {      //  clicked signIn button
            @Override
            public void onClick(View v) {
                // region Email/Password format confirm
                if (email.getText().toString().matches(Constants.EMAIL_REGEX) && pw.getText().toString().matches(Constants.PW_REGEX)) {
                    Toast.makeText(getApplicationContext(), "Sign in success!", Toast.LENGTH_SHORT).show();

                    Intent suc_signin = new Intent(SignIn.this, Main.class);        //  sign
                    // in -> main screen
                    startActivity(suc_signin);
                } else {        //incorrect email address & pw
                    if(!isEmpty(email) && email.getText().toString().matches(Constants.EMAIL_REGEX)) {
                        if (!isEmpty(pw)) {     //  wrong email address & pw
                            count--;
                            Toast.makeText(getApplicationContext(), "Wrong!\n" + "You didn't match password format!\n"
                                + "You can attempt "+ count + "times", Toast.LENGTH_SHORT).show();

                            if(count == 0) {        // no attempt chance
                                signIn.setEnabled(false);
                                signIn.setBackgroundColor(0xC1C1C1);
                                Toast.makeText(getApplicationContext(), "You don't have chance because you attempted 10times.", Toast.LENGTH_SHORT).show();
                            }
                        } else {        //  there's no password
                            Toast.makeText(getApplicationContext(), "You didn't write password. \n" +
                                        "Write your password!", Toast.LENGTH_SHORT).show();
                        }
                    }  else {    //  there's no email address or pw
                            Toast.makeText(getApplicationContext(), "You didn't write email address. \n" +
                                        "Write your email address!", Toast.LENGTH_SHORT).show();
                    }
                }
                // endregion

                // REST
                JSONObject jsonObj = new JSONObject();
                try {
                    jsonObj.put("rvalue", 1);
                    jsonObj.put("lvalue", 1);
                } catch(Exception e) {
                    e.printStackTrace();
                }

                // Initialize a new JsonObjectRequest instance
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        "http://teamb-iot.calit2.net/rest/app/add",
                        jsonObj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Do something with response
                                //mTextView.setText(response.toString());

                                // Process the JSON
                                try{
                                    // Get the JSON array
                                    int add = response.getInt("result");

                                    Log.d("REST_API", String.valueOf(add));
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

        signUp.setOnClickListener(new View.OnClickListener() {      //  clicked signUp button
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SignIn.this, SignUp.class);
                startActivity(intent1);
            }
        });

        fpw.setOnClickListener(new View.OnClickListener() {     //  clicked ForgottonPassword button
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), ForgotPw.class);
                startActivity(intent2);
            }
        });
    }
}