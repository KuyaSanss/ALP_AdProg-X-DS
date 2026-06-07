package Enum;

import java.util.HashSet;
import java.util.Set;

import App.App;

public enum Provinsi {
    ACEH,
    SUMATERA_UTARA,
    SUMATERA_BARAT,
    RIAU,
    KEPULAUAN_RIAU,
    JAMBI,
    BENGKULU,
    SUMATERA_SELATAN,
    KEPULAUAN_BANGKA_BELITUNG,
    LAMPUNG,
    DKI_JAKARTA,
    BANTEN,
    JAWA_BARAT,
    JAWA_TENGAH,
    DI_YOGYAKARTA,
    JAWA_TIMUR,
    BALI,
    NUSA_TENGGARA_BARAT,
    NUSA_TENGGARA_TIMUR,
    KALIMANTAN_BARAT,
    KALIMANTAN_TENGAH,
    KALIMANTAN_SELATAN,
    KALIMANTAN_TIMUR,
    KALIMANTAN_UTARA,
    SULAWESI_UTARA,
    GORONTALO,
    SULAWESI_TENGAH,
    SULAWESI_BARAT,
    SULAWESI_SELATAN,
    SULAWESI_TENGGARA,
    MALUKU,
    MALUKU_UTARA,
    PAPUA_BARAT,
    PAPUA,
    PAPUA_SELATAN,
    PAPUA_TENGAH,
    PAPUA_PEGUNUNGAN,
    PAPUA_BARAT_DAYA;

    
    public static Provinsi inputProvinsi(App app) {
        
        Set<String> set = new HashSet<>();

        System.out.println("=== PROVINSI ===");
        for(Provinsi p: Provinsi.values()){
            System.out.println(p);
            set.add(p.toString());
        }
        String input;

        while (true) {
            System.out.println("""
                Input Provinsi sesuai contoh format menggunakan \"_\" (underscore)
                Contoh : 
                LAMPUNG
                NUSA_TENGGARA_BARAT
                KEPULAUAN_BANGKA_BELITUNG
            """);
            System.out.print("Input : ");
            input = app.getSc().nextLine();
            input=input.toUpperCase().trim();

            // Check if input is empty
            if (input.isEmpty()) {
                System.out.println("Error: Input tidak boleh kosong!");
                continue;
            }

            if (!set.contains(input)) {
                System.out.println("Provinsi belum sesuai");
                continue;
            }

            break;
        }

        return Provinsi.valueOf(input);

    }



}