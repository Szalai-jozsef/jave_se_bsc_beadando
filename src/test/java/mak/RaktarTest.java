package mak;

import java.text.Format;
import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RaktarTest {
    @Test
    public void termekKivesz() {

        Raktar raktar = new Raktar();
        raktar.termekFelvesz(1,10);
        raktar.termekFelvesz(2,20);

        int termKod = 2;
        int mennyiseg = 5;
        if (raktar.kivesz(termKod, mennyiseg)) {
            //System.out.println(String.format("Termékkód: %d, kivett mennyiség: %d, új mennyiség: %d",
            //        termKod, mennyiseg, raktar.mennyiseg(termKod)));
            assertThat(raktar.mennyiseg(termKod), equalTo(15));
        } else {
            System.out.println("Nincs a raktárban ennyi mennyiség!");
        }
    }
}