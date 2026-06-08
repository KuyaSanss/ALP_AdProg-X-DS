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
    private boolean isApproved,isDone;//todo unapprove
    private Transaksi transaksi;
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
        int output = this.form.compareTo(other.getForm());
        if(output!=0){
            return output;
        }else{
            return this.idPermintaan.compareTo(other.idPermintaan);
        }
    }


    // #region static

public static Request displayRequests(App app) {
    MyMinHeap<Request> minHeap = new MyMinHeap<>(liveRequestList);
    int totalRequests = liveRequestList.size();

    if (totalRequests == 0) {
        System.out.println("=== REQUEST LIST ===");
        System.out.println("No requests available.");
        app.getCurrentUser().tampilkanMenuUtama(app);
        return null;
    }

    final int ITEMS_PER_PAGE = 10;
    int totalPages = (totalRequests + ITEMS_PER_PAGE - 1) / ITEMS_PER_PAGE;
    int currentPage = 0;

    // cuma nambah kalau next
    ArrayList<Request> extracted = new ArrayList<>();

    while (true) {
        int start = currentPage * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, totalRequests);
        int itemsOnPage = end - start;

        // Extract kalau masih di dalem end
        while (extracted.size() < end && !minHeap.isEmpty()) {
            extracted.add(minHeap.extractMin());
        }

        System.out.println("=== REQUEST LIST ===");
        System.out.println("Page " + (currentPage + 1) + " of " + totalPages);
        for (int i = start; i < end; i++) {
            int displayNumber = i - start + 1;
            System.out.println(displayNumber + " =====================");
            extracted.get(i).getForm().tampilkanForm();
            System.out.println();
        }

        // Prompt
        System.out.print("Input (1-" + itemsOnPage + " to select, < prev, > next, 0 to exit): ");
        String input = app.getSc().nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Error: Input cannot be empty.\n");
            continue;
        }

        // Navigation
        if (input.equals("<")) {
            if (currentPage > 0) {
                currentPage--;
            } else {
                System.out.println("Already at the first page.\n");
            }
            continue;
        } else if (input.equals(">")) {
            if (currentPage < totalPages - 1) {
                currentPage++;
            } else {
                System.out.println("Already at the last page.\n");
            }
            continue;
        }

        // Numeric validation
        boolean isNumeric = true;
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                isNumeric = false;
                break;
            }
        }
        if (!isNumeric) {
            System.out.println("Error: Input must be a number, '<', or '>'.\n");
            continue;
        }

        int choice = Integer.parseInt(input);

        // Exit
        if (choice == 0) {
            app.getCurrentUser().tampilkanMenuUtama(app);
            return null;
        }

        // Selection from current page
        if (choice >= 1 && choice <= itemsOnPage) {
            Request selected = extracted.get(start + choice - 1);
            System.out.println("\nYou selected request ID: " + selected.getIdPermintaan());
            return selected;
        } else {
            System.out.println("Error: Number out of bounds. Please enter a number between 0 and " + itemsOnPage + ".\n");
        }
    }
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