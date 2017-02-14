package com.gigasoft.proyectosemana2curso3;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class ContactDetails extends AppCompatActivity {

    private TextView tvName;
    private TextView tvBday;
    private TextView tvPhone;
    private TextView tvEmail;
    private TextView tvDescription;

    private String nam, day, pho, mail, desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Bundle parameters = getIntent().getExtras();
        String name = parameters.getString("Name");
        String bday = parameters.getString("Bday");
        String phone = parameters.getString("Phone");
        String email = parameters.getString("Email");
        String description = parameters.getString("Description");

        tvName = (TextView) findViewById(R.id.tvName);
        tvBday = (TextView) findViewById(R.id.tvBirthday);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvDescription = (TextView) findViewById(R.id.tvDescription);

        tvName.setText(name);
        tvBday.setText(bday);
        tvPhone.setText(phone);
        tvEmail.setText(email);
        tvDescription.setText(description);

    }

    public void EditInformation(View v) {

        Intent intentB = new Intent(ContactDetails.this , MainActivity.class);
        intentB.putExtra("Name", tvName.getText().toString());
        intentB.putExtra("Bday", tvBday.getText().toString());
        intentB.putExtra("Phone", tvPhone.getText().toString());
        intentB.putExtra("Email", tvEmail.getText().toString());
        intentB.putExtra("Description", tvDescription.getText().toString());
        startActivity(intentB);
        finish();
    }
}
