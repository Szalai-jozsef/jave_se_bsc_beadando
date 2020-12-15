package mak;

import java.util.HashMap;
import java.util.Map;

public class Raktar {
    private HashMap<Integer, Integer> keszlet = new HashMap<>();

    public Raktar() {
    }

    public Raktar(HashMap<Integer, Integer> keszlet) {
        this.keszlet = keszlet;
    }

    public void termekFelvesz(int termKod, int mennyiseg) {
        if (keszlet.containsKey(termKod)) {
            keszlet.replace(termKod, mennyiseg);
        } else {
            keszlet.put(termKod, mennyiseg);
        }
    }

    public int mennyiseg(int termKod) {
        if (keszlet.containsKey(termKod)) {
            return keszlet.get(termKod);
        }
        throw new RuntimeException("Nem létező termék kód a raktárban: " + termKod);
    }

    public boolean kivesz(int termKod, int mennyiseg) {
        if (keszlet.containsKey(termKod)) {
            if (keszlet.get(termKod) < mennyiseg) {
                return false;
            }
            keszlet.replace(termKod, keszlet.get(termKod) - mennyiseg);
            //keszlet.put(termKod, keszlet.get(termKod) + mennyiseg);
            return true;
        }
        throw new RuntimeException("Nincs ennyi mennyiség a raktárban: " + mennyiseg);
    }

    public Map<Integer, Integer> getKeszlet() {
        return keszlet;
    }

    public void setKeszlet(HashMap<Integer, Integer> keszlet) {
        this.keszlet = keszlet;
    }
}
