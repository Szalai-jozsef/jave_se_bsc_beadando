package mak;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class TermekTest {

    @Test
    public void tKeres() {
        Termekek termekek = new Termekek();
        termekek.ujTermek(new Termek(1, "téliszalámi", "rúd", 2000));
        termekek.ujTermek(new Termek(2, "gumikacsa", "db", 1000));
        String keres = termekek.termekKeres(2).getTermNev();
        assertThat(keres, equalTo("gumikacsa"));
    }
}
