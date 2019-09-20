package id.alkhidmah.agendaal_khidmah.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.alkhidmah.agendaal_khidmah.R;
import id.alkhidmah.agendaal_khidmah.util.PrefKeys;
import id.alkhidmah.agendaal_khidmah.util.SharedMethods;
import id.alkhidmah.agendaal_khidmah.home.MainActivity;

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
        if(!sharedMethods.checkint(this, true))return;
        login();
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
//        String url = PrefKeys.LOGIN;
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                response -> {
//                    JSONArray result;
//                    JSONObject header, content;
//                    Log.d("Cobaa", response.toString());
//                    try {
//                        header = new JSONObject(response);
//                        String msg = header.getString(PrefKeys.msg);
//                        if(msg.contentEquals("Sukses")){
//                            result = header.getJSONArray(PrefKeys.result);
//                            content = (JSONObject) result.get(0);
//                            //Deserialkan pada class POJO
//                            Akun akunku = gson.fromJson(content.toString(), Akun.class);
//                            Log.d("Cobaa", akunku.nama);
//                        }else{
//                            Log.d(PrefKeys.ErrorTAG,"efdef");
//                        }
//
//                    } catch (Exception e) {
//                        Log.d(PrefKeys.ErrorTAG,e.getMessage());
//                    }
//                },
//                (VolleyError error) -> {
//
//                }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<>();
//                params.put(PrefKeys.nohp,mTextNohp.getEditText().getText().toString());
//                params.put(PrefKeys.password,mTextPassword.getEditText().getText().toString());
//                return params;
//            }
//        };
//        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void hubungiclick(View view) {
        Intent i = new Intent(this, InfoActivity.class);
        startActivity(i);
    }
}
