package Request;

import Enum.golDarahEnum;
import User.BDRS;

public class Request {
    //untuk id
    private static long requestTerbuat;
    //general
    private String idPermintaan, namaRumahSakit;
    private golDarahEnum golonganDarah;
    private int jumlahKantong;
    private BDRS bdrs;
    //form
    String hospitalName;
    String address;
    String phone;
    String bdrsUnit;
    String requestDate;
    String requestTime;

    String patientName;
    String birthDateOrAge;
    String medicalRecordNumber;
    Gender gender;
    String ward;
    String clinicalDiagnosis;

    BloodComponent bloodComponent;
    int bloodUnits;
    String transfusionPlanTime;
    String clinicalReason;

    String doctorName;
    String doctorPosition;
    String sipNumber;
    

    public Request(){
        idPermintaan="RQ"+requestTerbuat;
    }

    // TODO the most important function
    public void getUrgency(){

    }

    

    public void printForm() {

        System.out.println("=== BLOOD REQUEST FORM ===");
        System.out.println("Patient Name    : " + patientName);
        System.out.println("Validity        : " + validity);
        System.out.println("Urgency Level   : " + urgencyLevel);
        System.out.println("Needed Time     : " + neededTime);
        System.out.println("Component Type  : " + componentType);
        System.out.println("Stock Level     : " + stockLevel);
        System.out.println("Patient Category: " + patientCategory);
    }

    
}
