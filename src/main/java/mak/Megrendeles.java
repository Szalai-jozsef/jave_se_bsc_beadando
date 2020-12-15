package mak;

public class Megrendeles {
    private int rendAzon;
    private int vevoKod;
    private int termKod;
    private int mennyiseg;
    private FizMod fizMod;
    private KezbesitesMod kezbesitesMod;
    private Status status;
    private String komment;

    public Megrendeles(int rendAzon, int vevoKod, int termKod, int mennyiseg, FizMod fizMod, KezbesitesMod kezbesitesMod, Status status) {
        this.rendAzon = rendAzon;
        this.vevoKod = vevoKod;
        this.termKod = termKod;
        this.mennyiseg = mennyiseg;
        this.fizMod = fizMod;
        this.kezbesitesMod = kezbesitesMod;
        this.status = status;
    }

    public int getRendAzon() {
        return rendAzon;
    }

    public int getVevoKod() {
        return vevoKod;
    }

    public int getTermKod() {
        return termKod;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public FizMod getFizMod() {
        return fizMod;
    }

    public KezbesitesMod getKezbesitesMod() {
        return kezbesitesMod;
    }

    public Status getStatus() {
        return status;
    }

    public String getKomment() {
        return komment;
    }

    public void setRendAzon(int rendAzon) {
        this.rendAzon = rendAzon;
    }

    public void setKomment(String komment) {
        this.komment = komment;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
