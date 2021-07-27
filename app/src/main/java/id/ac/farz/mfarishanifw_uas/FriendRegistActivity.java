package id.ac.farz.mfarishanifw_uas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.ac.farz.mfarishanifw_uas.database.DataBaseHelper;
import id.ac.farz.mfarishanifw_uas.model.FriendModel;

public class FriendRegistActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioGroup opsi;
    EditText name,bod,detail;
    String nama, lahir, jkelamin, tentang;
    Button actionSimpan;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_regist);
        name = findViewById(R.id.name);
        bod = findViewById(R.id.bod);
        //sex = findViewById(R.id.sex);
        detail = findViewById(R.id.detail);
        actionSimpan = findViewById(R.id.actionSimpan);

        bod.setOnClickListener(this);
        actionSimpan.setOnClickListener(this);

        opsi = findViewById(R.id.opsi);
        opsi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.radio_male:
                        jkelamin = "Laki-laki";
                        break;
                    case R.id.radio_female:
                        jkelamin = "Perempuan";
                        break;
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bod:
                //event click BOD
                new DatePickerDialog(this,dateSetListener,calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            /*case R.id.edtJamLahir:
                //event click Jam Lahir
                new TimePickerDialog(this, timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),true).show();
                break;*/
            case R.id.actionSimpan:
                if (name.getText().toString().length() > 0 && bod.getText().toString().length() > 0 && opsi.getCheckedRadioButtonId() != -1
                        && detail.getText().toString().length()>0){
                    simpan();
                    Intent intent = new Intent(id.ac.farz.mfarishanifw_uas.FriendRegistActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Mohon Lengkapi Data", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    void simpan(){
        nama = name.getText().toString();
        lahir = bod.getText().toString();;
        jkelamin = jkelamin;
        tentang = detail.getText().toString();

        FriendModel friendModel = null;

        // Gets the data repository in write mode
        //SQLiteDatabase db = DataBaseHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        //ContentValues values = new ContentValues();
        //values.put(FeedEntry.COLUMN_NAME_TITLE, title);
        //values.put(FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);
        //mengetes (run),
        try {
            friendModel = new FriendModel(-1, name.getText().toString(), jkelamin, bod.getText().toString(), detail.getText().toString()); //id -1 itu sementara untuk default
        } catch (Exception e) {

            Toast.makeText(this, "Error memasukkan data baru", Toast.LENGTH_SHORT).show(); //customerModel.toString mengambil data customer
            //friendModel = new FriendModel(-1, "Terjadi kesalahan", , );
        }

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this); //constructor DBH butuh param, kasih konteks sesuai di class DataBaseHelper

        boolean sukses = dataBaseHelper.addOne(friendModel);
        Toast.makeText(this, "Berhasil = " +sukses, Toast.LENGTH_SHORT).show();
    }
        //password = edtPassword.getText().toString();

        /*String data = email + " " + password + " " + nama + " " + nim + " " + kelas;

        //buat file baru
        File file = new File(getFilesDir(),FILENAME);

        //pemasukan isi file
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,false);
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            Log.e("ERROR", ""+e.getMessage());
        }*/


    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            bod.setText(dateFormat.format(calendar.getTime()));

        }
    };

}
