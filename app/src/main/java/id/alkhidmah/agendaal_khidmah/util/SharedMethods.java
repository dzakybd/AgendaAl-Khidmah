package id.alkhidmah.agendaal_khidmah.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.alkhidmah.agendaal_khidmah.model.Akun;
import id.alkhidmah.agendaal_khidmah.model.Wildacab;
import id.alkhidmah.agendaal_khidmah.view.LoginActivity;

public class SharedMethods {
    public void nointalert(final Context ctx) {
        new SweetAlertDialog(ctx, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Internet mati")
                .setContentText("Mohon menghidupkan internet")
                .setConfirmText("Ya")
                .setCancelText("Tidak")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                    ctx.startActivity(intent);
                })
                .show();
    }


    private void nogpsalert(final Context ctx) {
        new SweetAlertDialog(ctx, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("GPS mati")
                .setContentText("Mohon aktifkan GPS Anda")
                .setConfirmText("Ya")
                .setCancelText("Tidak")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    ctx.startActivity(intent);
                })
                .show();
    }

    public boolean checkgps(Context ctx, boolean showdialog){
        final LocationManager manager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return true;
        }else{
            if(showdialog){
                nogpsalert(ctx);
            }
            return false;
        }
    }

    public boolean checkint(Context ctx, boolean showdialog){
        ConnectivityManager connectivity = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity.getActiveNetworkInfo() != null) {
            if (connectivity.getActiveNetworkInfo().isConnected()) return true;
            else {
                if(showdialog){
                    nointalert(ctx);
                }
                return false;
            }
        }else {
            if(showdialog){
                nointalert(ctx);
            }
            return false;
        }
    }

//    private void get_wildacab(Context ctx, int pengurus_level) {
//        String url = PrefKeys.GET_WILDACAB;
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                response -> {
//                    try {
//                        JSONArray result;
//                        result = new JSONArray(response);
//                        if(result.length()==0) {
//                            new SweetAlertDialog(ctx)
//                                    .setTitleText("Login salah!")
//                                    .show();
//                        } else{
//                            Gson gson = new Gson();
//                            Wildacab[] wildacabs = gson.fromJson(result.getJSONArray().toString(), Akun.class);
//                            Log.d("Cobaa", akunku.alamat);
//                            Toast.makeText(ctx, akunku.alamat, Toast.LENGTH_SHORT).show();
////                            move();
//                        }
//
//                    } catch (Exception e) {
//                        Log.d(PrefKeys.ErrorTAG, Objects.requireNonNull(e.getMessage()));
//                    }
//                },
//                (VolleyError error) -> {
//                    Log.d(PrefKeys.ErrorTAG, Objects.requireNonNull(error.getMessage()));
//                    new SweetAlertDialog(ctx, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText("Maaf")
//                            .setContentText("Koneksi bermasalah!")
//                            .show();
//                }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<>();
//                params.put(PrefKeys.no_hp,mFieldakun.getText().toString());
//                params.put(PrefKeys.password,mFieldpassword.getText().toString());
//                return params;
//            }
//        };
//        AppController.getInstance().addToRequestQueue(stringRequest);
//    }
}
