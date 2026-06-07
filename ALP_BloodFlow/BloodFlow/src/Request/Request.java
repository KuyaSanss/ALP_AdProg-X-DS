package Request;
import java.util.ArrayList;
import java.util.LinkedList;

import App.*;
import Darah.TesDarah;
import Model.MyMinHeap;
import User.*;

public class Request implements Comparable<Request> {
    // static
    private static long requestTerbuat;
    private static LinkedList<Request> liveRequestList = new LinkedList<>();//todo pas unapprove add
    // general
    private Form form;
    private ArrayList<TesDarah> listTesDarah;//kalau gagal auto clear biar ga kecampur sama yang dulu
    private String idPermintaan;
    private boolean isApproved;//todo unapprove
    // for form
    private FasilitasDarah fasilitasDarah;
    

    public Request(FasilitasDarah fasilitasDarah) {
        idPermintaan = "RQ" + requestTerbuat;
        this.fasilitasDarah = fasilitasDarah;
        liveRequestList.add(this);
    }

    public void menuRequest(App app){
        form = new Form(fasilitasDarah);
        form.menuForm(app);
    }

    public void approveRequest(App app,FasilitasDarah fasilitasDarah) {
        System.out.println("=== APPROVE REQUEST ===");
        form.tampilkanForm();
        System.out.println();
        System.out.println("1. Approve");
        System.out.println("0. Back to menu");
        boolean wrong = false;
        do {
            String input = app.getSc().next() + app.getSc().nextLine();
            if (input.equals("1")) {

            } else if (input.equals("0")) {

            } else {
                System.out.println("Wrong input! Only 1 or 0");
                wrong = true;
            }
        } while (wrong);

        fasilitasDarah.getListApproveRequest().add(this);
        liveRequestList.remove(this);
        isApproved=true;

    }

    @Override
    public int compareTo(Request other) {
        int thisWeight = this.getForm().hitungWeight();
        int otherWeight = other.getForm().hitungWeight();

        if (thisWeight < otherWeight) {
            //lebih priority
            return -1;
        } else if (thisWeight > otherWeight) {
            return 1;
        } else {
            //lebih dulu
            return this.idPermintaan.compareTo(other.idPermintaan);
        }
    }


    // #region static

    public static Request displayRequests(App app) {
        MyMinHeap<Request> maxHeap = new MyMinHeap<>(liveRequestList);
        int size = liveRequestList.size();
        Request[] list = new Request[size];

        System.out.println("=== REQUEST LIST ===");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + " =====================");
            list[i] = maxHeap.extractMin();
            list[i].getForm().tampilkanForm();
            System.out.println();
        }

        int choice = -1;
        boolean isValid = false;

        while (!isValid) {
            System.out.print("Input (1-" + size + " to select, 0 to exit): ");
            String input = app.getSc().next() + app.getSc().nextLine();

            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty.\n");
                continue;
            }

            boolean isNumeric = true;
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (!Character.isDigit(c)) {
                    isNumeric = false;
                    break;
                }
            }

            if (!isNumeric) {
                System.out.println("Error: Input must contain numbers only (no letters or symbols).\n");
                continue;
            }

            choice = Integer.parseInt(input);

            if (choice >= 0 && choice <= size) {
                isValid = true;
            } else {
                System.out.println("Error: Number out of bounds. Please enter a number between 0 and " + size + ".\n");
            }
        }

        if (choice == 0) {
            app.getCurrentUser().tampilkanMenuUtama(app);
        } else {
            Request selectedRequest = list[choice - 1];
            System.out.println("\nYou selected request ID: " + selectedRequest.getIdPermintaan());
            return selectedRequest;
        }
        return null;
    }

    // #endregion

    // #region Getter Setter

    public static LinkedList<Request> getLiveRequestList() {
        return liveRequestList;
    }

    public static void setLiveRequestList(LinkedList<Request> liveRequestList) {
        Request.liveRequestList = liveRequestList;
    }

    public String getIdPermintaan() {
        return idPermintaan;
    }

    public void setIdPermintaan(String idPermintaan) {
        this.idPermintaan = idPermintaan;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    // #endregion

}