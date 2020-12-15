package mak;

import java.util.ArrayList;
import java.util.List;

public class Vevok {
    List<Vevo> vevok = new ArrayList<>();

    public void ujVevo(Vevo vevo) {
        vevok.add(vevo);
    }

    public Vevo vevoKeres(int vevoKod) {
        for (Vevo v : vevok) {
            if (v.getVevoKod() == vevoKod)
                return v;
        }
        return null;
    }

    public String vevoAdatok(int vevoKod) {
        for (Vevo v : vevok) {
            if (v.getVevoKod() == vevoKod)
                return v.getNev() + ", " + v.getLakcim() + ", " + v.getTelefon() + ", " + v.getEmail();
        }
        return null;
    }

    public List<Vevo> getVevok() {
        return vevok;
    }

    public void setVevok(List<Vevo> vevok) {
        this.vevok = vevok;
    }
}
