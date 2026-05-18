package User;

import java.util.Scanner;

public class Admin extends User{
    public Admin(String username, String password, String noTelp) {
        super(username, password, noTelp);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void tampilkanMenuUtama() {
        
    }
    
    public void buatAkun() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== BUAT AKUN INSTANSI BARU ===");

        int pilihanInstansi = 0;
        boolean pilihanValid = false;

        while (!pilihanValid) {
            try {
                System.out.println("Pilih tipe instansi:");
                System.out.println("1. Rumah Sakit (BDRS)");
                System.out.println("2. Unit Donor Darah (UDD)");
                System.out.print("Pilihan (1/2): ");
                
                pilihanInstansi = Integer.parseInt(sc.nextLine()); 

                if (pilihanInstansi == 1 || pilihanInstansi == 2) {
                    pilihanValid = true;
                } else {
                    System.out.println("Pilihan tidak valid. Harap masukkan angka 1 atau 2.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka! Silakan coba lagi.\n");
            }
        }

        
    }
}

