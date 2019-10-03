package id.alkhidmah.agendaal_khidmah.util;

import java.util.ArrayList;
import java.util.List;

public class PrefKeys {

    public static final String null_value="null";
    public static final String idakun="idakun";
    public static final String is_pengurus="is_pengurus";
    public static final String tingkat_pengurus="tingkat_pengurus";
    public static final String no_hp="no_hp";
    public static final String alamat="alamat";
    public static final String password="password";
    public static final String is_active="is_active";
    public static final String wilayah_idwilayah="wilayah_idwilayah";
    public static final String daerah_iddaerah="daerah_iddaerah";
    public static final String cabang_idcabang="cabang_idcabang";
    public static final String nama_wilayah="nama_wilayah";
    public static final String nama_daerah="nama_daerah";
    public static final String nama_cabang="nama_cabang";
    public static final String idmajelis="idmajelis";
    public static final String akun_idakun="akun_idakun";
    public static final String jenis_acara="jenis_acara";
    public static final String judul="judul";
    public static final String latitude="latitude";
    public static final String longitude="longitude";
    public static final String tanggal_waktu="tanggal_waktu";
    public static final String keterangan="keterangan";
    public static final String url_poster="url_poster";
    public static final String imam_khususi="imam_khususi";
    public static final String idwilayah="idwilayah";
    public static final String iddaerah="iddaerah";
    public static final String idcabang="idcabang";
    public static final String idwildacab="idwildacab";
    public static final String init="init";
    public static final String permissions="permissions";
    public static final String result="result";
    public static final String input_error="Masukan salah";
    public static final String no_hp_exist="no_hp_exist";
    public static final String pengurus_exist="pengurus_exist";
    public static final String no_hp_exist_error="No HP sudah terdaftar";
    public static final String pengurus_exist_error="Sudah ada pengurus di tingkat tersebut";
    public static final String process_error="Proses bermasalah";
    public static final String connection_error="Koneksi bermasalah";


    public static final String mode="mode";
    public static final String mode_nothing="nothing";
    public static final String mode_create="create";
    public static final String mode_read="read";
    public static final String mode_update="update";
    public static final String mode_delete="delete";

    public static final String BASE_URL = "http://159.65.133.176/alkhidmah/";
    public static final String GET_AKUN = BASE_URL+"get_akun.php";
    public static final String EDIT_AKUN = BASE_URL+"edit_akun.php";
    public static final String GET_MAJELIS = BASE_URL+"get_majelis.php";
    public static final String EDIT_MAJELIS = BASE_URL+"edit_majelis.php";
    public static final String GET_WILDACAB = BASE_URL+"get_wildacab.php";


    public static final List<String> jenisakun = new ArrayList<String>() {{
        add("Jamaah");
        add("Pengurus");
    }};

    public static final List<String> tingkatpengurus = new ArrayList<String>() {{
        add("Pusat");
        add("Wilayah");
        add("Daerah");
        add("Cabang");
    }};

    public static final String zero_string="0";
    public static final String one_string="1";

    public static final int zero_int=0;
    public static final int one_int=1;

    public static final int peran_jamaah=0;
    public static final int peran_pengurus=1;

    public static final int tingkat_pusat=0;
    public static final int tingkat_wilayah=1;
    public static final int tingkat_daerah=2;
    public static final int tingkat_cabang=3;

    public static final int status_nonaktif=0;
    public static final int status_aktif=1;

    public static final String ErrorTAG = "ErrorTAG";
    public static final String InfoTAG = "InfoTAG";

}
