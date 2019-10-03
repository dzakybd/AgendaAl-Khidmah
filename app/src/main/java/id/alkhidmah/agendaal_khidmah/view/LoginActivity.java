package id.alkhidmah.agendaal_khidmah.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.alkhidmah.agendaal_khidmah.R;
import id.alkhidmah.agendaal_khidmah.home.MainActivity;
import id.alkhidmah.agendaal_khidmah.model.Akun;
import id.alkhidmah.agendaal_khidmah.util.AppController;
import id.alkhidmah.agendaal_khidmah.util.PrefKeys;
import id.alkhidmah.agendaal_khidmah.util.SharedMethods;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty(message = "Harap diisi")
    private EditText mFieldakun;
    @NotEmpty(message = "Harap diisi")
    @Password(min = 6,message = "karakter minimal 6")
    private EditText mFieldpassword;

    Validator validator;
    SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFieldakun = findViewById(R.id.fieldakun);
        mFieldpassword = findViewById(R.id.fieldpassword);

        validator = new Validator(this);
        validator.setValidationListener(this);

    }

    private void move() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void masukclick(View view) {
        validator.validate();
    }

    public void daftarclick(View view) {
        Intent i = new Intent(this, EditAkunActivity.class);
        i.putExtra(PrefKeys.mode, PrefKeys.mode_create);
        startActivity(i);
    }

    @Override
    public void onValidationSucceeded() {
        SharedMethods sharedMethods = new SharedMethods();
        if(sharedMethods.checkint(this, true)){
            login();
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void login() {
        String url = PrefKeys.GET_AKUN;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject result;
                        result = new JSONObject(response);
                        if(result.getString(PrefKeys.result).contentEquals(PrefKeys.null_value)) {
                            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setContentText(PrefKeys.input_error)
                                    .show();
                        } else{
                            Gson gson = new Gson();
                            Akun akunku = gson.fromJson(result.toString(), Akun.class);
                            Log.d("Cobaa", akunku.alamat);
                            Toast.makeText(this, akunku.alamat, Toast.LENGTH_SHORT).show();
//                            move();
                        }

                    } catch (Exception e) {
                        Log.d(PrefKeys.ErrorTAG, Objects.requireNonNull(e.getMessage()));
                    }
                },
                (VolleyError error) -> {
                    Log.d(PrefKeys.ErrorTAG, Objects.requireNonNull(error.getMessage()));
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setContentText(PrefKeys.connection_error)
                            .show();
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put(PrefKeys.mode,"login");
                params.put(PrefKeys.no_hp,mFieldakun.getText().toString());
                params.put(PrefKeys.password,mFieldpassword.getText().toString());
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void hubungiclick(View view) {
        Intent i = new Intent(this, InfoActivity.class);
        startActivity(i);
    }
}
