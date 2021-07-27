package id.ac.farz.mfarishanifw_uas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PengembangPage extends AppCompatActivity {

    private String title = "Profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setActionBarTitle(title);

    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
