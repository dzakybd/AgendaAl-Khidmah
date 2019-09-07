package id.alkhidmah.agendaal_khidmah.util;

import java.util.ArrayList;
import java.util.List;

public class PrefKeys {
    public static final String nohp="nohp";
    public static final String permissions="permissions";

    public static final String BASE_URL = "http://159.65.133.176/alkhidmah/";
    public static final String LOGIN = BASE_URL+"login.php";

    public static final List<String> kategoriRombongan = new ArrayList<String>() {{
        add("VVIP");
        add("VIP");
        add("Santri");
        add("Umum");
    }};
}
