package User;

<<<<<<< Updated upstream
=======
import App.App;
import Request.Request;

>>>>>>> Stashed changes
public class BDRS extends User{
    private String alamat;

    public BDRS(String username, String password, String noTelp, String alamat) {
        super(username, password, noTelp);
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
<<<<<<< Updated upstream
    public void tampilkanMenuUtama() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'tampilkanMenuUtama'");
=======
    public void tampilkanMenuUtama(App app) {
        System.out.println("=== MENU UTAMA ===");
        System.out.println("1. Buat permintaan darah");
        System.out.println("0. Log Out");
        String input = app.getSc().next() + app.getSc().nextLine();
        switch (input) {
            case "0":
                app.menuAwal();
                break;
            case "1":
                makeRequest();
                break;
            case "2":

                break;
            case "3":

                break;
            default:
                System.out.println("Invalid Input!!");
                tampilkanMenuUtama(app);
        }
>>>>>>> Stashed changes
    }

    public void makeRequest(App app){

        Request form = new Request();
        
        System.out.println("=== INPUT FORM PERMINTAAN DARAH ===");

        System.out.print("Nama Rumah Sakit: ");
        form.hospitalName = input.nextLine();

        System.out.print("Alamat: ");
        form.address = input.nextLine();

        System.out.print("Telepon: ");
        form.phone = input.nextLine();

        System.out.print("Unit BDRS: ");
        form.bdrsUnit = input.nextLine();

        System.out.print("Tanggal permintaan: ");
        form.requestDate = input.nextLine();

        System.out.print("Jam permintaan: ");
        form.requestTime = input.nextLine();

        System.out.println("\nA. DATA PASIEN");

        System.out.print("Nama pasien: ");
        form.patientName = input.nextLine();

        System.out.print("Tanggal lahir atau usia: ");
        form.birthDateOrAge = input.nextLine();

        System.out.print("Nomor rekam medis: ");
        form.medicalRecordNumber = input.nextLine();

        System.out.println("Jenis kelamin");
        System.out.println("1. LAKI LAKI");
        System.out.println("2. PEREMPUAN");
        System.out.print("Pilih: ");
        int genderChoice = Integer.parseInt(input.nextLine());

        if (genderChoice == 1) {
            form.gender = Gender.LAKI_LAKI;
        } else {
            form.gender = Gender.PEREMPUAN;
        }

        System.out.print("Ruang perawatan: ");
        form.ward = input.nextLine();

        System.out.print("Diagnosa klinis: ");
        form.clinicalDiagnosis = input.nextLine();

        System.out.println("\nB. DATA PERMINTAAN DARAH");

        System.out.println("Jenis komponen darah yang diminta");
        System.out.println("1. WB");
        System.out.println("2. PRC");
        System.out.println("3. FFP");
        System.out.println("4. TC");
        System.out.println("5. OTHER");
        System.out.print("Pilih: ");
        int componentChoice = Integer.parseInt(input.nextLine());

        switch (componentChoice) {
            case 1 -> form.bloodComponent = BloodComponent.WB;
            case 2 -> form.bloodComponent = BloodComponent.PRC;
            case 3 -> form.bloodComponent = BloodComponent.FFP;
            case 4 -> form.bloodComponent = BloodComponent.TC;
            default -> form.bloodComponent = BloodComponent.OTHER;
        }

        System.out.print("Jumlah kantong: ");
        form.bloodUnits = Integer.parseInt(input.nextLine());

        System.out.print("Rencana waktu transfusi: ");
        form.transfusionPlanTime = input.nextLine();

        System.out.print("Alasan klinis permintaan: ");
        form.clinicalReason = input.nextLine();

        System.out.println("\nC. DATA DOKTER PEMINTA");

        System.out.print("Nama dokter: ");
        form.doctorName = input.nextLine();

        System.out.print("Jabatan: ");
        form.doctorPosition = input.nextLine();

        System.out.print("Nomor SIP: ");
        form.sipNumber = input.nextLine();
    }
}
