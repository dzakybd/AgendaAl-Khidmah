package id.alkhidmah.agendaal_khidmah.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.arisan.ArisanForm;

import id.alkhidmah.agendaal_khidmah.R;
import id.alkhidmah.agendaal_khidmah.model.Akun;

public class EditAkunActivity extends AppCompatActivity {

    private ArisanForm vForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);

        vForm = findViewById(R.id.arisan_form);

//Assign your model
        vForm.setModels(new Akun());

//Build Adapter
        vForm.setOnSubmitListener(result -> {/*Do something with json result*/});
        vForm.buildForm();
    }
}
