package com.gigasoft.proyectosemana2curso3;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText name;
    private TextInputEditText number;
    private TextInputEditText email;
    private TextInputEditText description;
    private TextInputEditText bday;
    private Button send;

    private int year_x, month_x, day_x;
    private String date;
    static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name      = (TextInputEditText) findViewById(R.id.tietUserName);
        bday        = (TextInputEditText) findViewById(R.id.tietBday);
        number      = (TextInputEditText) findViewById(R.id.tietPhone);
        email       = (TextInputEditText) findViewById(R.id.tietEmail);
        description = (TextInputEditText) findViewById(R.id.tietDescription);
        send = (Button) findViewById(R.id.btnNext);

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        showDialogOnTextInputClick();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactDetails.class);
                intent.putExtra("Name", name.getText().toString());
                intent.putExtra("Bday", date);
                intent.putExtra("Phone", number.getText().toString());
                intent.putExtra("Email", email.getText().toString());
                intent.putExtra("Description", description.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        checkingExtras();
    }

    public void showDialogOnTextInputClick() {

        bday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View vv, boolean hasFocus) {
                if (hasFocus) {
                    showDialog(DIALOG_ID);
                }
            }
        });
    }

    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            date = day_x + " / " + month_x + " / " + year_x;
            bday.setText(date);
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        return null;
    }

    public void checkingExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle parameters = getIntent().getExtras();
            String pname = parameters.getString("Name");
            String pbday = parameters.getString("Bday");
            String pphone = parameters.getString("Phone");
            String pemail = parameters.getString("Email");
            String pdescription = parameters.getString("Description");

            name.setText(pname);
            bday.setText(pbday);
            number.setText(pphone);
            email.setText(pemail);
            description.setText(pdescription);
        }

    }

}
