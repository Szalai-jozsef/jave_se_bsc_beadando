package mak;

public class Termek {
    private int termKod;
    private String termNev;
    private String menyEgys;
    private int egysAr;

    public Termek(int termKod, String termNev, String menyEgys, int egysAr) {
        this.termKod = termKod;
        this.termNev = termNev;
        this.menyEgys = menyEgys;
        this.egysAr = egysAr;
    }

    @Override
    public String toString() {
        return termKod + " " + termNev + " " + menyEgys + " " + egysAr;
    }

    public int getTermKod() {
        return termKod;
    }

    public void setTermKod(int termKod) {
        this.termKod = termKod;
    }

    public String getTermNev() {
        return termNev;
    }

    public void setTermNev(String termNev) {
        this.termNev = termNev;
    }

    public String getMenyEgys() {
        return menyEgys;
    }

    public void setMenyEgys(String menyEgys) {
        this.menyEgys = menyEgys;
    }

    public int getEgysAr() {
        return egysAr;
    }

    public void setEgysAr(int egysAr) {
        this.egysAr = egysAr;
    }
}
