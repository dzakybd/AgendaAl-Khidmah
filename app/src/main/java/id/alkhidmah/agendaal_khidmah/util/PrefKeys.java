package id.alkhidmah.agendaal_khidmah.util;

import java.util.ArrayList;
import java.util.List;

public class PrefKeys {
    public static final String nohp="nohp";
    public static final String permissions="permissions";
    public static final String result="result";
    public static final String msg="msg";
    public static final String mode="mode";

    public static final int nothing=-1;
    public static final int mode_create=0;
    public static final int mode_read=1;
    public static final int mode_update=2;
    public static final int mode_delete=3;

    public static final String BASE_URL = "http://159.65.133.176/alkhidmah/";
    public static final String LOGIN = BASE_URL+"login.php";

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

    public static final int peran_jamaah=1;
    public static final int peran_pengurus=2;

    public static final int tingkat_pusat=1;
    public static final int tingkat_wilayah=2;
    public static final int tingkat_daerah=3;
    public static final int tingkat_cabang=4;

    public static final int status_nonaktif=0;
    public static final int status_aktif=1;

    public static final String ErrorTAG = "ErrorTAG";

}
