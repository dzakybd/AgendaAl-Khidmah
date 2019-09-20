package id.alkhidmah.agendaal_khidmah.model;
import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class Majelis {
    public int idmajelis;
    public int akun_idakun;
    public int jenis_acara;
    public String judul;
    public String alamat;
    public double latitude;
    public double longitude;
    public Date tanggal_waktu;
    public boolean is_active;
    public String keterangan;
    public String url_poster;
    public String imam_khususi;

    public int wilayah_idwilayah;
    public int daerah_iddaerah;
    public int cabang_idcabang;
}
