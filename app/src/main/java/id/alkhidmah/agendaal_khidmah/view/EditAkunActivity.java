package id.alkhidmah.agendaal_khidmah.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.alkhidmah.agendaal_khidmah.R;
import id.alkhidmah.agendaal_khidmah.model.Akun;
import id.alkhidmah.agendaal_khidmah.util.PrefKeys;
import id.alkhidmah.agendaal_khidmah.util.SharedMethods;

public class EditAkunActivity extends AppCompatActivity implements Validator.ValidationListener{

    private int mode;
    private Akun akunku;
    private Spinner mSpinnerjenisakun;
    @NotEmpty(message = "Harap diisi")
    private EditText mFieldnama;
    private LinearLayout mLayoutNama;
    private Spinner mSpinnertingkat;
    private Spinner mSpinnerwilayah;
    private Spinner mSpinnerdaerah;
    private Spinner mSpinnercabang;
    private LinearLayout mLayoutWildacab;
    @NotEmpty(message = "Harap diisi")
    private EditText mFieldnohp;
    @Password(min = 6,message = "karakter minimal 6")
    private EditText mFieldpass1;
    @ConfirmPassword
    private EditText mFieldpass2;
    @NotEmpty(message = "Harap diisi")
    private EditText mFieldalamat;

    Validator validator;
    SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);

        mode = Objects.requireNonNull(getIntent().getExtras()).getInt(PrefKeys.mode);

        mSpinnerjenisakun = findViewById(R.id.spinnerjenisakun);
        mFieldnama = findViewById(R.id.fieldnama);
        mLayoutNama = findViewById(R.id.layout_nama);
        mSpinnertingkat = findViewById(R.id.spinnertingkat);
        mSpinnerwilayah = findViewById(R.id.spinnerwilayah);
        mSpinnerdaerah = findViewById(R.id.spinnerdaerah);
        mSpinnercabang = findViewById(R.id.spinnercabang);
        mLayoutWildacab = findViewById(R.id.layout_wildacab);
        mFieldnohp = findViewById(R.id.fieldnohp);
        mFieldpass1 = findViewById(R.id.fieldpass1);
        mFieldpass2 = findViewById(R.id.fieldpass2);
        mFieldalamat = findViewById(R.id.fieldalamat);

        validator = new Validator(this);
        validator.setValidationListener(this);

        String[] jenisakun = PrefKeys.jenisakun.toArray(new String[0]);
        ArrayAdapter<String> akun_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,jenisakun);
        akun_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerjenisakun.setAdapter(akun_adapter);

        mSpinnerjenisakun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    mLayoutNama.setVisibility(View.VISIBLE);
                    mLayoutWildacab.setVisibility(View.GONE);
                }else{
                    mLayoutNama.setVisibility(View.GONE);
                    mLayoutWildacab.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        String[] tingkatpengurus = PrefKeys.tingkatpengurus.toArray(new String[0]);
        ArrayAdapter<String> tingkat_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,tingkatpengurus);
        tingkat_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnertingkat.setAdapter(tingkat_adapter);

        mSpinnertingkat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    mSpinnerwilayah.setVisibility(View.GONE);
                    mSpinnerdaerah.setVisibility(View.GONE);
                    mSpinnercabang.setVisibility(View.GONE);
                }else if(i==1){
                    mSpinnerwilayah.setVisibility(View.VISIBLE);
                    mSpinnerdaerah.setVisibility(View.GONE);
                    mSpinnercabang.setVisibility(View.GONE);
                }else if(i==2){
                    mSpinnerwilayah.setVisibility(View.VISIBLE);
                    mSpinnerdaerah.setVisibility(View.VISIBLE);
                    mSpinnercabang.setVisibility(View.GONE);
                }else if(i==3){
                    mSpinnerwilayah.setVisibility(View.VISIBLE);
                    mSpinnerdaerah.setVisibility(View.VISIBLE);
                    mSpinnercabang.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        SharedMethods sharedMethods = new SharedMethods();
        if(sharedMethods.checkint(this, true)){
            daftar();
        }
    }

    private void daftar() {
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

}
