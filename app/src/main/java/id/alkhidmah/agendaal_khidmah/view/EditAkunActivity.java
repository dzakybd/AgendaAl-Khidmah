package id.alkhidmah.agendaal_khidmah.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.android.volley.Request;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.alkhidmah.agendaal_khidmah.R;
import id.alkhidmah.agendaal_khidmah.model.Akun;
import id.alkhidmah.agendaal_khidmah.model.Wildacab;
import id.alkhidmah.agendaal_khidmah.util.AppController;
import id.alkhidmah.agendaal_khidmah.util.PrefKeys;
import id.alkhidmah.agendaal_khidmah.util.SharedMethods;

public class EditAkunActivity extends AppCompatActivity implements Validator.ValidationListener {

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
    @NotEmpty(message = "Harap diisi")
    private EditText mFieldnohp;
    @Password(min = 6, message = "karakter minimal 6")
    private EditText mFieldpass1;
    @ConfirmPassword
    private EditText mFieldpass2;
    @NotEmpty(message = "Harap diisi")
    private EditText mFieldalamat;
    private LinearLayout mLayoutTingkat;
    private LinearLayout mLayoutWilayah;
    private LinearLayout mLayoutDaerah;
    private LinearLayout mLayoutCabang;


    Validator validator;
    SweetAlertDialog pDialog;
    SharedMethods sharedMethods;

    Map<String, Integer> wilayah_map, daerah_map, cabang_map;
    String[] jenisakun_data, tingkatpengurus_data, wilayah_data, daerah_data, cabang_data;
    ArrayAdapter<String> jenisakun_adapter, tingkatpengurus_adapter, wilayah_adapter, daerah_adapter, cabang_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);


        mSpinnerjenisakun = findViewById(R.id.spinnerjenisakun);
        mFieldnama = findViewById(R.id.fieldnama);
        mLayoutNama = findViewById(R.id.layout_nama);
        mSpinnertingkat = findViewById(R.id.spinnertingkat);
        mSpinnerwilayah = findViewById(R.id.spinnerwilayah);
        mSpinnerdaerah = findViewById(R.id.spinnerdaerah);
        mSpinnercabang = findViewById(R.id.spinnercabang);
        mFieldnohp = findViewById(R.id.fieldnohp);
        mFieldpass1 = findViewById(R.id.fieldpass1);
        mFieldpass2 = findViewById(R.id.fieldpass2);
        mFieldalamat = findViewById(R.id.fieldalamat);
        mLayoutTingkat = findViewById(R.id.layout_tingkat);
        mLayoutWilayah = findViewById(R.id.layout_wilayah);
        mLayoutDaerah = findViewById(R.id.layout_daerah);
        mLayoutCabang = findViewById(R.id.layout_cabang);

        sharedMethods = new SharedMethods();
        validator = new Validator(this);
        validator.setValidationListener(this);

        mode = Objects.requireNonNull(getIntent().getExtras()).getInt(PrefKeys.mode);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        jenisakun_data = PrefKeys.jenisakun.toArray(new String[0]);
        jenisakun_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, jenisakun_data);
        mSpinnerjenisakun.setAdapter(jenisakun_adapter);

        tingkatpengurus_data = PrefKeys.tingkatpengurus.toArray(new String[0]);
        tingkatpengurus_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tingkatpengurus_data);
        mSpinnertingkat.setAdapter(tingkatpengurus_adapter);

        mSpinnerjenisakun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    mLayoutNama.setVisibility(View.VISIBLE);
                    mLayoutTingkat.setVisibility(View.GONE);
                } else {
                    mLayoutNama.setVisibility(View.GONE);
                    mLayoutTingkat.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        get_wildacab(EditAkunActivity.this, PrefKeys.tingkat_wilayah, true,"", "");

        mSpinnertingkat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= PrefKeys.tingkat_wilayah) {
                    mLayoutWilayah.setVisibility(View.VISIBLE);
                } else {
                    mLayoutWilayah.setVisibility(View.GONE);
                }

                if (i >= PrefKeys.tingkat_daerah) {
                    mLayoutDaerah.setVisibility(View.VISIBLE);
                } else {
                    mLayoutDaerah.setVisibility(View.GONE);
                }

                if (i >= PrefKeys.tingkat_cabang) {
                    mLayoutCabang.setVisibility(View.VISIBLE);
                } else {
                    mLayoutCabang.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerwilayah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (mSpinnertingkat.getSelectedItemPosition() >= PrefKeys.tingkat_daerah) {
                    get_wildacab(EditAkunActivity.this, PrefKeys.tingkat_daerah, true, mSpinnerwilayah.getSelectedItem().toString(), "");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mSpinnerdaerah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (mSpinnertingkat.getSelectedItemPosition() >= PrefKeys.tingkat_cabang) {
                    get_wildacab(EditAkunActivity.this, PrefKeys.tingkat_cabang, false, mSpinnerwilayah.getSelectedItem().toString(), mSpinnerdaerah.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onValidationSucceeded() {
        if (sharedMethods.checkint(this, true)) {
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

    public void get_wildacab(Context ctx, int tingkat_pengurus, boolean init, String nama_wilayah, String nama_daerah) {
        Log.d(PrefKeys.ErrorTAG, tingkat_pengurus + " " +nama_wilayah+" "+nama_daerah);
        String url = PrefKeys.GET_WILDACAB;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONArray result;
                        result = new JSONArray(response);

                        Gson gson = new Gson();
                        List<Wildacab> wildacabs = Arrays.asList(gson.fromJson(result.toString(), Wildacab[].class));
                        List<String> sortedKeys;

                        if (wildacabs.size() > 0) {
                            switch (tingkat_pengurus) {
                                case PrefKeys.tingkat_wilayah:
                                    wilayah_map = convertlistomap(wildacabs);
                                    sortedKeys = new ArrayList<>(wilayah_map.keySet());
                                    Collections.sort(sortedKeys);
                                    wilayah_data = sortedKeys.toArray(new String[0]);
                                    wilayah_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, wilayah_data);
                                    mSpinnerwilayah.setAdapter(wilayah_adapter);
                                    break;
                                case PrefKeys.tingkat_daerah:
                                    daerah_map = convertlistomap(wildacabs);
                                    sortedKeys = new ArrayList<>(daerah_map.keySet());
                                    Collections.sort(sortedKeys);
                                    daerah_data = sortedKeys.toArray(new String[0]);
                                    daerah_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, daerah_data);
                                    mSpinnerdaerah.setAdapter(daerah_adapter);
                                    break;
                                case PrefKeys.tingkat_cabang:
                                    cabang_map = convertlistomap(wildacabs);
                                    sortedKeys = new ArrayList<>(cabang_map.keySet());
                                    Collections.sort(sortedKeys);
                                    cabang_data = sortedKeys.toArray(new String[0]);
                                    cabang_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cabang_data);
                                    mSpinnercabang.setAdapter(cabang_adapter);
                                    break;
                            }

                            if(tingkat_pengurus==PrefKeys.tingkat_wilayah && init){
                                get_wildacab(EditAkunActivity.this, PrefKeys.tingkat_daerah, true, mSpinnerwilayah.getSelectedItem().toString(), "");
                            }
                            if(tingkat_pengurus==PrefKeys.tingkat_daerah && init){
                                get_wildacab(EditAkunActivity.this, PrefKeys.tingkat_cabang, false, mSpinnerwilayah.getSelectedItem().toString(), mSpinnerdaerah.getSelectedItem().toString());
                            }

                        }


                    } catch (Exception e) {
                        Log.d(PrefKeys.ErrorTAG, Objects.requireNonNull(e.getMessage()));
                    }
                },
                (VolleyError error) -> {
                    Log.d(PrefKeys.ErrorTAG, Objects.requireNonNull(error.getMessage()));
                    new SweetAlertDialog(ctx, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Maaf")
                            .setContentText("Koneksi bermasalah!")
                            .show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(PrefKeys.tingkat_pengurus, String.valueOf(tingkat_pengurus));
                if (tingkat_pengurus >= PrefKeys.tingkat_daerah) {
                    params.put(PrefKeys.wilayah_idwilayah, String.valueOf(wilayah_map.get(nama_wilayah)));
                    Log.d(PrefKeys.ErrorTAG, nama_wilayah+" " + wilayah_map.get(nama_wilayah));
                }
                if (tingkat_pengurus >= PrefKeys.tingkat_cabang) {
                    params.put(PrefKeys.daerah_iddaerah, String.valueOf(daerah_map.get(nama_daerah)));
                    Log.d(PrefKeys.ErrorTAG, nama_daerah+" " + daerah_map.get(nama_daerah));
                }
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public Map<String, Integer> convertlistomap(List<Wildacab> wildacabs) {
        @SuppressLint("UseSparseArrays") Map<String, Integer> map = new HashMap<>();
        for (Wildacab wildacab : wildacabs) {
            map.put(wildacab.nama_wildacab, wildacab.idwildacab);
        }
        return map;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getParentActivityIntent() == null) {
                    onBackPressed();
                } else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
