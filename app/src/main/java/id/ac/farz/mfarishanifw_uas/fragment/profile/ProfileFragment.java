package id.ac.farz.mfarishanifw_uas.fragment.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.farz.mfarishanifw_uas.MainActivity;
import id.ac.farz.mfarishanifw_uas.R;

import static android.content.Context.MODE_PRIVATE;


public class ProfileFragment extends Fragment {

    TextView edtEmail,edtNama;
    Button actionOut;
    SharedPreferences sharedPreferences;
    //SharedPreferences sharedPreferences;

    //public String[] doc;
    //public static String FILENAME = "register.txt";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //sharedPreferences = getActivity().getSharedPreferences("DataPribadi", Context.MODE_PRIVATE);
        //edtNim = view.findViewById(R.id.edtNim);
        //edtKelas = view.findViewById(R.id.edtKelas);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtNama = view.findViewById(R.id.edtNama);
        actionOut = view.findViewById(R.id.logOut);

        /*bacaData();
        edtEmail.setText(doc[0]);
        edtNama.setText(doc[2]);
        edtNim.setText(doc[3]);
        edtKelas.setText(doc[4]);*/

        actionOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getContext().getSharedPreferences("LoginStatus", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLogin", false);
                //edit.clear();
                editor.commit();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /*void bacaData(){
        File file = new File(getContext().getFilesDir(), FILENAME);

        if(file.exists()) {
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();

                while (line !=null){
                    text.append(line);
                    line = bufferedReader.readLine();
                }

                bufferedReader.close();


            }catch (Exception e){
                Log.e("ERROR", ""+e.getMessage());
            }

            doc = text.toString().split(" ");

        }

    }*/
}

