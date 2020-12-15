package mak;

import java.util.Map;
import java.util.Scanner;

public class TermekMain {
    private Termekek termekek = termekInit();
    private Raktar raktar = raktarInit();
    private Vevok vevok = new Vevok();
    private Megrendeles megrendeles;
    private Megrendelesek megrendelesek = new Megrendelesek();
    private int rendAzon = 0;

    public static void main(String[] args) {
        System.out.println("Indul a termék szállítást nyomkövető app");
        new TermekMain().indul();
    }

    private void indul() {
        vevok.ujVevo(new Vevo(1, "Piripócs, Petőfi út 11",
                "56/123456", "Kovács Béla", "bejla@freemail.hu"));
        //adatLista();
        boolean vege = false;
        do {
            int v = rendelBeolvas();
            if (v < 1) {
                vege = true;
            } else if (v == 1){
                System.out.println();
                System.out.println("Felvitt/módosított rendelés:");
                System.out.println(megrendelesek.rendelesMegjelen(rendAzon));
            }
        } while (!vege);
    }

    private int rendelBeolvas() {
        Scanner sc = new Scanner(System.in);
        int vevoKod, termKod, mennyiseg;
        String lakcim, telefon, nev, email, komment;
        FizMod fizMod = null;
        KezbesitesMod kezbesitesMod = null;
        Status status = null;
        System.out.println();
        System.out.println("0:kilép, -1:rendelés státusz módostás, -2:rendelés lista");
        System.out.print("Vevőkód: ");
        vevoKod = Integer.parseInt(sc.nextLine());
        if (vevoKod == 0) {
            return 0;
        } else if (vevoKod == -1) {
            System.out.print("Módosítandó rendelés azonosító: ");
            rendAzon = Integer.parseInt(sc.nextLine());
            System.out.print("Státusz (1-DELIVERED, 2-BOOKED, 3-IN_PROGRESS, 4-FAILED_DELIVERY): ");
            byte st = sc.nextByte();
            if (st == 1) status = Status.DELIVERED;
            if (st == 2) status = Status.BOOKED;
            if (st == 3) status = Status.IN_PROGRESS;
            if (st == 4) status = Status.FAILED_DELIVERY;
            megrendelesek.rendelesStatusMod(rendAzon, status);
            if (st == 4) {
                System.out.print("Komment: ");
                komment = sc.nextLine(); // ezt lenyeli
                komment = sc.nextLine(); // Így jó!! Miért??
                megrendelesek.rendelesKommentMod(rendAzon, komment);
            }
            return 1;
        } else if (vevoKod == -2) {
            // megrendelések lista
            for (Map.Entry<Integer, Megrendeles> e : megrendelesek.getRendelesek().entrySet()) {
                Megrendeles m = e.getValue();
                System.out.println(m.getRendAzon() + " " + m.getVevoKod() + " " + m.getTermKod() + " " + m.getMennyiseg() +
                        " " + m.getFizMod() + " " + m.getKezbesitesMod() + " " + m.getStatus() + " " + m.getKomment());
            }
            return 2;
        }
        // Új rendelés felvitele
        if (vevok.vevoKeres(vevoKod) == null) {
            System.out.print("Név: "); nev = sc.nextLine();
            System.out.print("Lakcím: "); lakcim = sc.nextLine();
            System.out.print("Telefon: "); telefon = sc.nextLine();
            System.out.print("Email: "); email = sc.nextLine();
            vevok.ujVevo(new Vevo(vevoKod, lakcim, telefon, nev, email));
        } else {
            System.out.println(vevok.vevoKeres(vevoKod).getNev());
        }
        System.out.print("Termék kód: "); termKod = Integer.parseInt(sc.nextLine());
        for (;;) {
            System.out.print("Mennyiség: "); mennyiseg = Integer.parseInt(sc.nextLine());
            if (mennyiseg == 0) {
                return 2;
            } else if (raktar.mennyiseg(termKod) >= mennyiseg) {
                break;
            }
            System.out.println("Nincs ennyi mennyiség! Elérhető: " + raktar.mennyiseg(termKod));
        }

        System.out.print("Fizetés mód (1-Kp, 2-átutalás, 3-kártya: "); byte fm = sc.nextByte();
        if (fm == 1) fizMod = FizMod.KÉSZPÉNZ;
        if (fm == 2) fizMod = FizMod.ÁTUTALÁS;
        if (fm == 3) fizMod = FizMod.KÁRTYA;

        System.out.print("Kézbesítés mód (1-Helyben, 2-futár, 3-posta: "); byte km = sc.nextByte();
        if (km == 1) kezbesitesMod = KezbesitesMod.HELYBEN;
        if (km == 2) kezbesitesMod = KezbesitesMod.FUTÁR;
        if (km == 3) kezbesitesMod = KezbesitesMod.POSTA;

        //System.out.println(vevoKod + " " + termKod + " " + mennyiseg + " " + fizMod + " " + kezbesitesMod);
        if (raktar.kivesz(termKod, mennyiseg)) {
            rendAzon = megrendelesek.ujRendeles(new Megrendeles(0,
                    vevoKod, termKod, mennyiseg, fizMod, kezbesitesMod, Status.DELIVERED));
        } else {
            System.out.println("Kifogyott a készlet, nem lett rögzítve!");
        }
        return 1;
    }

    // meghívódik a SzallitasTest-ből is.
    public static Termekek termekInit() {
        Termekek termekek = new Termekek();
        termekek.ujTermek(new Termek(1, "téliszalámi", "rúd", 2000));
        termekek.ujTermek(new Termek(2, "gumikacsa", "db", 1000));
        termekek.ujTermek(new Termek(3, "lapos elem", "db", 200));
        termekek.ujTermek(new Termek(4, "narancs", "kg", 300));
        return termekek;
    }

    // meghívódik a SzallitasTest-ből is.
    public static Raktar raktarInit() {
        Raktar raktar = new Raktar();
        raktar.termekFelvesz(1, 20);
        raktar.termekFelvesz(2, 15);
        raktar.termekFelvesz(3, 2);
        raktar.termekFelvesz(4, 5);
        return raktar;
    }

    private void adatLista() {
        System.out.println("Termékek:");
        for (Termek t : termekek.getTermekList()) {
            System.out.println(t.toString());
        }
        System.out.println();

        System.out.println("Készlet:");
        Map<Integer, Integer> keszlet = raktar.getKeszlet();
        for (Map.Entry<Integer, Integer> entry : keszlet.entrySet()) {
            Termek termek = termekek.termekKeres(entry.getKey());
            System.out.println(termek.getTermNev() + " " + entry.getValue() + " " + termek.getMenyEgys());
        }
        System.out.println();

        System.out.println("Vevők:");
        for (Vevo v : vevok.getVevok()) {
            System.out.println(v.getNev() + ", " + v.getLakcim() + ", " + v.getTelefon() + ", " + v.getEmail());
        }
        System.out.println();
    }
}
