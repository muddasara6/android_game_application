package com.example.mudda.test1;

// A dialog showing a progress indicator and an optional text message or view
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
// Volley is an HTTP library that makes networking for Android apps easier and faster
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
// Importing JSON exceptions and objects
import org.json.JSONException;
import org.json.JSONObject;
// Importing its super class to use the class and methods
import java.util.HashMap;
import java.util.Map;

public class SaveData extends AppCompatActivity {
    EditText editFirstName, editLastName;
    TextView viewPlayerScore;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        viewPlayerScore = findViewById(R.id.viewPlayerScore);
        SharedPreferences settings = getSharedPreferences("GAME DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);
        viewPlayerScore.setText(highScore + "");
        progressDialog = new ProgressDialog(this);
    }

    public void submit(View view){
        // Registers the xml editText with the class file editText
        final String firstname = editFirstName.getText().toString().trim();
        final String lastname = editLastName.getText().toString().trim();
        final String score = viewPlayerScore.getText().toString().trim();
        // Set string to progressDialog
        progressDialog.setMessage("Updating data...");
        // Display the message
        progressDialog.show();
        // Post a request method supported by HTTP
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_HIGHSCORE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            // Provides accessors for a number of different data types, including nested JSONObjects and JSONArrays
                            JSONObject jsonObject = new JSONObject(response);
                            // Provides simple feedback about an operation in a small popup
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            // Prints the stack trace of the instance to System.err.
                            e.printStackTrace();
                        }
                    }
                },
                // Callback interface for delivering error responses
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Hide the message
                        progressDialog.hide();
                        // Display error message in a small popup
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Initialise a HashMap
                Map<String, String> params = new HashMap<>();
                // Put objects into JSONObject
                params.put("firstname", firstname);
                params.put("lastname", lastname);
                params.put("score", score);
                return params;
            }
        };
        // Localise and hinder further error
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void getScore(View view) {
        startActivity(new Intent(getApplicationContext(), ScoreTable.class));
    }

    public void exitPage(View view) {
        startActivity(new Intent(getApplicationContext(), MainMenu.class));
    }
}