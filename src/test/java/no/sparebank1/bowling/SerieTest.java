package no.sparebank1.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("En serie")
class SerieTest {

    @Test
    @DisplayName("skal summere alle ruter hver for seg dersom det ikke er strikes eller spares")
    void vanligSerie() {
        Serie serie = new Serie("-9 18 27 36 45 54 63 72 81 9-");

        assertThat(serie.poeng()).isEqualTo(90);
    }

    @Test
    @DisplayName("skal summere maksimum poengscore")
    void kunStrike() {
        Serie serie = new Serie("X X X X X X X X X XXX");

        assertThat(serie.poeng()).isEqualTo(300);
    }

    @Test
    @DisplayName("skal summere flere spares etter hverandre")
    void spares() {
        Serie serie = new Serie("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5");

        assertThat(serie.poeng()).isEqualTo(150);
    }
}