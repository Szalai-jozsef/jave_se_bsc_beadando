package mak;

import java.util.ArrayList;
import java.util.List;

public class Termekek {
    private List<Termek> termekList = new ArrayList<Termek>();

    public void ujTermek(Termek termek) {
        termekList.add(termek);
    }

    public Termek termekKeres(int termKod) {
        for (Termek t : termekList) {
            if (t.getTermKod() == termKod) {
                return t;
            }
        }
        return null;
    }

    public List<Termek> getTermekList() {
        return termekList;
    }

    public void setTermekList(List<Termek> termekList) {
        this.termekList = termekList;
    }
}
