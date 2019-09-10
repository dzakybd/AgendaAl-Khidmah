package id.alkhidmah.agendaal_khidmah.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import id.alkhidmah.agendaal_khidmah.R;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {

    private EditText mFieldakun;
    private EditText mFieldpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFieldakun = findViewById(R.id.fieldakun);
        mFieldpassword = findViewById(R.id.fieldpassword);
    }

    public void masukclick(View view) {
    }

    public void daftarclick(View view) {
    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

    }
}
