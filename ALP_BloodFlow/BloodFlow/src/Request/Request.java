package Request;

import java.util.ArrayList;
import java.util.LinkedList;

import App.*;
import Darah.SampelDarah;
import Darah.TesDarah;
import Model.MyMinHeap;
import User.*;

public class Request implements Comparable<Request> {
    // static
    private static long requestTerbuat;
    private static LinkedList<Request> liveRequestList = new LinkedList<>();// todo pas unapprove add
    // general
    private Form form;
    private ArrayList<TesDarah> listTesDarah;// kalau gagal auto clear biar ga kecampur sama yang dulu
    private String idPermintaan;
    private boolean isDone;
    // is approve artinya ada yang mau tes darah is done artinya udah di terima
    private Transaksi transaksi;
    // kalau mau status transaksi pakek setter
    private SampelDarah sampelDarahPeminta;

    // for form
    private FasilitasDarah fasilitasDarahPeminta;
    private FasilitasDarah fasilitasDarahApprove;// todo unapprove jangan lupa di null in

    public Request(FasilitasDarah fasilitasDarahPeminta) {
        idPermintaan = "RQ" + requestTerbuat;
        this.fasilitasDarahPeminta = fasilitasDarahPeminta;
        liveRequestList.add(this);
    }

    public void buatRequest(App app) {
        form = new Form(fasilitasDarahPeminta);
        form.menuForm(app);
        sampelDarahPeminta = new SampelDarah(form.getGolonganDarah(), form.getRhesus(), form.getFasilitasDarah());
    }

    public void approveRequest(App app, FasilitasDarah fasilitasDarahApprove) {
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

        fasilitasDarahApprove.getListApproveRequest().add(this);
        liveRequestList.remove(this);
        this.fasilitasDarahApprove = fasilitasDarahApprove;

    }

    public void tampilkanRequest() {
        System.out.println("======= DETAIL PERMINTAAN (REQUEST) =======");
        System.out.println("ID Permintaan      : " + this.idPermintaan);

        // Menampilkan nama fasilitas peminta
        if (this.fasilitasDarahPeminta != null) {
            System.out.println("Fasilitas Peminta  : " + this.fasilitasDarahPeminta.getNama());
        } else {
            System.out.println("Fasilitas Peminta  : -");
        }

        // Menampilkan nama fasilitas yang meng-approve
        if (this.fasilitasDarahApprove != null) {
            System.out.println("Fasilitas Approve  : " + this.fasilitasDarahApprove.getNama());
        } else {
            System.out.println("Fasilitas Approve  : Belum ditentukan (Pending)");
        }

        // Menampilkan status approval (isApproved)
        System.out.print("Status Approval    : ");
        if (fasilitasDarahApprove != null) {
            System.out.println("Disetujui (Approved)");
        } else {
            System.out.println("Menunggu Persetujuan (Pending)");
        }

        // Menampilkan status transaksi (hanya statusnya saja)
        System.out.print("Status Transaksi   : ");
        if (this.transaksi != null) {
            if (this.transaksi.isStatusTransaksi()) {
                System.out.println("Lunas");
            } else {
                System.out.println("Belum Lunas");
            }
        } else {
            System.out.println("Belum ada transaksi");
        }

        // Menampilkan status pemrosesan sampai selesai (isDone)
        System.out.print("Status Permintaan  : ");
        if (this.isDone) {
            System.out.println("Selesai (Darah Sudah Diterima)");
        } else {
            System.out.println("Dalam Proses (In Progress)");
        }
        System.out.println("===========================================");
    }

    @Override
    public int compareTo(Request other) {
        int output = this.form.compareTo(other.getForm());
        if (output != 0) {
            return output;
        } else {
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
                System.out.println(
                        "Error: Number out of bounds. Please enter a number between 0 and " + itemsOnPage + ".\n");
            }
        }
    }

    // #endregion

    // #region Getter Setter

    public FasilitasDarah getFasilitasDarahPeminta() {
        return fasilitasDarahPeminta;
    }

    public void setFasilitasDarahPeminta(FasilitasDarah fasilitasDarahPeminta) {
        this.fasilitasDarahPeminta = fasilitasDarahPeminta;
    }

    public FasilitasDarah getFasilitasDarahApprove() {
        return fasilitasDarahApprove;
    }

    public void setFasilitasDarahApprove(FasilitasDarah fasilitasDarahApprove) {
        this.fasilitasDarahApprove = fasilitasDarahApprove;
    }

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

    public static long getRequestTerbuat() {
        return requestTerbuat;
    }

    public static void setRequestTerbuat(long requestTerbuat) {
        Request.requestTerbuat = requestTerbuat;
    }

    public ArrayList<TesDarah> getListTesDarah() {
        return listTesDarah;
    }

    public void setListTesDarah(ArrayList<TesDarah> listTesDarah) {
        this.listTesDarah = listTesDarah;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public SampelDarah getSampelDarahPeminta() {
        return sampelDarahPeminta;
    }

    public void setSampelDarahPeminta(SampelDarah sampelDarahPeminta) {
        this.sampelDarahPeminta = sampelDarahPeminta;
    }

    public FasilitasDarah getFasilitasDarah() {
        return fasilitasDarahPeminta;
    }

    public void setFasilitasDarah(FasilitasDarah fasilitasDarahPeminta) {
        this.fasilitasDarahPeminta = fasilitasDarahPeminta;
    }

    // #endregion

}