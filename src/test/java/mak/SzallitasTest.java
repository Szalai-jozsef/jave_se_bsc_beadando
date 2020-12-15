package mak;

import org.junit.Test;

import static mak.Status.DELIVERED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SzallitasTest {
    Termekek termekek = TermekMain.termekInit();
    Raktar raktar = TermekMain.raktarInit();
    Vevok vevok = new Vevok();
    Megrendelesek megrendelesek = new Megrendelesek();

    @Test
    public void vasarlas() {
        vevok.ujVevo(new Vevo(1, "Piripócs, Petőfi út 11",
                "123456", "Kovács Béla", "bejla@freemail.hu"));
        //assertThat(termekek.termekKeres(2).getTermNev(), equalTo("gumikacsa"));
        //assertThat(raktar.mennyiseg(1), equalTo(15));

        // Az alábbiak majd konzolon lesznek bekérve
        // Ha a vevoKod létezik a vevok-ben nem kell a többi vevő adat
        int vevoKod = 1;
        String lakcim = "Szolnok, Kossuth tér 2";
        String telefon = "20/344-0443";
        String nev = "Kocsis Imre";
        String email = "imre123@gmail.com";

        int rendAzon;
        int termKod = 1;
        int mennyiseg = 2;
        FizMod fizMod = FizMod.KÉSZPÉNZ;
        KezbesitesMod kezbesitesMod = KezbesitesMod.FUTÁR;
        Status status = Status.DELIVERED;

        if (raktar.kivesz(termKod, mennyiseg)) {
            Vevo vevo = vevok.vevoKeres(vevoKod);
            if (vevo == null) {
                vevok.ujVevo(new Vevo(vevoKod, lakcim, telefon, nev, email));
            } else {
                lakcim = vevo.getLakcim();
                telefon = vevo.getTelefon();
                nev = vevo.getNev();
                email = vevo.getEmail();
            }
            rendAzon = megrendelesek.ujRendeles(new Megrendeles(0,
                    vevoKod, termKod, mennyiseg, fizMod, kezbesitesMod, status));
            System.out.println("Rendelés tárolva. " + megrendelesek.rendelesMegjelen(rendAzon));
            System.out.println("Új készlet mennyiség: " + raktar.mennyiseg(termKod));
        } else {
            throw new RuntimeException("Nincs elég mennyiség a raktárban!");
        }

        megrendelesek.rendelesStatusMod(rendAzon, Status.FAILED_DELIVERY);
        megrendelesek.rendelesKommentMod(rendAzon, "nem vették át");
        System.out.println();
        System.out.println("Rendelés módosítva. " + megrendelesek.rendelesMegjelen(rendAzon));
        System.out.println();
        System.out.println("Vevő adatok: " + vevok.vevoAdatok(vevoKod));
    }
}
