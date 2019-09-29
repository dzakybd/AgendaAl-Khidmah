package id.alkhidmah.agendaal_khidmah.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Akun {
    @SerializedName("idakun")
    @Expose
    public int idakun;
    @SerializedName("is_pengurus")
    @Expose
    public boolean is_pengurus;
    @SerializedName("tingkat_pengurus")
    @Expose
    public int tingkat_pengurus;
    @SerializedName("no_hp")
    @Expose
    public String no_hp;
    @SerializedName("alamat")
    @Expose
    public String alamat;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("is_active")
    @Expose
    public boolean is_active;
    @SerializedName("wilayah_idwilayah")
    @Expose
    public int wilayah_idwilayah;
    @SerializedName("daerah_iddaerah")
    @Expose
    public int daerah_iddaerah;
    @SerializedName("cabang_idcabang")
    @Expose
    public int cabang_idcabang;
    @SerializedName("nama_wilayah")
    @Expose
    public String nama_wilayah;
    @SerializedName("nama_daerah")
    @Expose
    public String nama_daerah;
    @SerializedName("nama_cabang")
    @Expose
    public String nama_cabang;
}