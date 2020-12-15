package mak;

import java.util.HashMap;
import java.util.Map;

public class Megrendelesek {
    private HashMap<Integer, Megrendeles> rendelesek = new HashMap<>();

    public int ujRendeles(Megrendeles megrendeles) {
        int maxAzon = 0;
        for (Map.Entry<Integer, Megrendeles> entry : rendelesek.entrySet()) {
            if (entry.getKey() > maxAzon) {
                maxAzon = entry.getKey();
            }
        }
        maxAzon++;
        megrendeles.setRendAzon(maxAzon);
        rendelesek.put(maxAzon, megrendeles);
        return maxAzon;
    }

    public Megrendeles rendelesKeres(int rendAzon) {
        if (rendelesek.containsKey(rendAzon)) {
            return rendelesek.get(rendAzon);
        } else {
            return null;
        }
    }

    public boolean rendelesStatusMod(int rendAzon, Status status) {
        if (rendelesek.containsKey(rendAzon)) {
            Megrendeles rend = rendelesek.get(rendAzon);
            rend.setStatus(status);
            rendelesek.replace(rendAzon, rend);
            return true;
        } else {
            return false;
        }
    }

    public boolean rendelesKommentMod(int rendAzon, String komment) {
        if (rendelesek.containsKey(rendAzon)) {
            Megrendeles rend = rendelesek.get(rendAzon);
            rend.setKomment(komment);
            rendelesek.replace(rendAzon, rend);
            return true;
        } else {
            return false;
        }
    }

    public String rendelesMegjelen(int rendAzon) {
        if (rendelesek.containsKey(rendAzon)) {
            Megrendeles rend = rendelesek.get(rendAzon);
            return String.format("Rendelés azonosító: %d \nvevő kód: %d  \ntermék kód: %d \nmennyiség: %d \nFizetés mód: %s \nKézbesítés %s \nstátusz: %s \nkomment: %s",
                    rendAzon, rend.getVevoKod(), rend.getTermKod(), rend.getMennyiseg(), rend.getFizMod(), rend.getKezbesitesMod(), rend.getStatus(), rend.getKomment());
        } else {
            throw new RuntimeException("Nem létező rendelés azon: " + rendAzon);
        }

    }

    public HashMap<Integer, Megrendeles> getRendelesek() {
        return rendelesek;
    }
}
