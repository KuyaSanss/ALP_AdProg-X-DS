package Darah;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import App.App;
import User.FasilitasDarah;

public abstract class TesDarah {

    protected FasilitasDarah fasilitasDarah;
    protected LocalDateTime waktuTes;

    private void rencanaWaktuTransfusi(App app) {
        while (true) {

            try {

                System.out.print(
                        "Rencana Waktu Transfusi "
                                + "(YYYY-MM-DD HH:MM): ");

                String inputWaktu = app.getSc().nextLine();

                DateTimeFormatter format = DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd HH:mm");

                LocalDateTime waktuTes = LocalDateTime.parse(
                        inputWaktu,
                        format);

                if (waktuTes.isBefore(
                        LocalDateTime.now())) {

                    System.out.println(
                            "Waktu transfusi tidak boleh "
                                    + "sebelum waktu sekarang.");

                    continue;
                }

                this.waktuTes=waktuTes;

                break;

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Format salah.");

                System.out.println(
                        "Gunakan format:");

                System.out.println(
                        "YYYY-MM-DD HH:MM");
            }
        }
    }
}
