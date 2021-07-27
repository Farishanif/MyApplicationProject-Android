package id.ac.farz.mfarishanifw_uas;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailFriendPage extends AppCompatActivity {
    public static final String mname = "new name";
    public static final String msex = "new sex";
    public static final String mbod = "new bod";
    public static final String mdetail = "new detail";
    public static final int mphoto = R.drawable.apyr;
    private String title = "Friend Detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);
        setActionBarTitle(title);

        String nname = getIntent().getStringExtra(mname);
        String nsex = getIntent().getStringExtra(msex);
        String nbod = getIntent().getStringExtra(mbod);
        String ndetail = getIntent().getStringExtra(mdetail);
        //int nphoto =  getIntent().getIntExtra(String.valueOf(mphoto), 0);

        TextView sNam = findViewById(R.id.Name);
        TextView sSex = findViewById(R.id.Sex);
        TextView sBod = findViewById(R.id.BOD);
        TextView sdet = findViewById(R.id.Detail);
        //ImageView sPhoto = findViewById(R.id.Photo);

        sNam.setText(nname);
        sSex.setText(nsex);
        sBod.setText(nbod);
        sdet.setText(ndetail);
        //sPhoto.setImageResource(nphoto);

    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

}