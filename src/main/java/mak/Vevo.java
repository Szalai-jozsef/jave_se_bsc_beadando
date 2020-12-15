package mak;

public class Vevo {
    private int vevoKod;
    private String lakcim;
    private String telefon;
    private String nev;
    private String email;

    public Vevo() {
    }

    public Vevo(int vevoKod, String lakcim, String telefon, String nev, String email) {
        this.vevoKod = vevoKod;
        this.lakcim = lakcim;
        this.telefon = telefon;
        this.nev = nev;
        this.email = email;
    }

    public int getVevoKod() {
        return vevoKod;
    }

    public String getLakcim() {
        return lakcim;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getNev() {
        return nev;
    }

    public String getEmail() {
        return email;
    }
}
