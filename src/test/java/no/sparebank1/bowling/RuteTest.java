package no.sparebank1.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("En rute")
@SuppressWarnings("InnerClassMayBeStatic")
class RuteTest {

    @Nested
    @DisplayName("når man ikke får strike eller spare")
    class UtenStrikeEllerSpare {

        @Test
        @DisplayName("skal gi 0 dersom begge er rennekast")
        void toRennekast() {
            assertThat(new Rute("--").poeng()).isEqualTo(0);
        }

        @Test
        @DisplayName("skal kun telle poenget for kastet som ikke havner i renna")
        void forsteRennekaste() {
            assertThat(new Rute("1-").poeng()).isEqualTo(1);
        }

        @Test
        @DisplayName("skal summere begge kast dersom de ikke havner i renna")
        void toLavePoeng() {
            assertThat(new Rute("12").poeng()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("når man får en spare")
    class Spare {

        @Test
        @DisplayName("skal inkludere neste kast i summen")
        void spareOgVanlig() {
            assertThat(ruter("7/", "5-").poeng()).isEqualTo(15);
        }

        @Test
        @DisplayName("skal inkludere neste strike i summen, ")
        void spareOgStrike() {
            assertThat(ruter("7/", "X").poeng()).isEqualTo(20);
        }

    }

    @Nested
    @DisplayName("når man får en strike")
    class EnStrike {

        @Test
        @DisplayName("skal summere de to neste kastene")
        void strikeOgVanlig() {
            assertThat(ruter("X", "53").poeng()).isEqualTo(18);
        }

        @Test
        @DisplayName("skal summere de to neste kastene inklusiv spare")
        void strikeOgSpare() {
            assertThat(ruter("X", "5/").poeng()).isEqualTo(20);
        }
    }

    @Nested
    @DisplayName("når man får to strikes")
    class ToStrike {

        @Test
        @DisplayName("skal summere begge strike og påfølgende kast ")
        void toStrikesOgVanlig() {
            assertThat(ruter("X", "X", "1-").poeng()).isEqualTo(21);
        }

        @Test
        @DisplayName("skal summere tre strikes")
        void treStrikes() {
            assertThat(ruter("X", "X", "X").poeng()).isEqualTo(30);
        }
    }

    @Nested
    @DisplayName("når det er den siste i serien")
    class SisteRute {

        @Test
        @DisplayName("skal kunne ha tre kast hvor alle er strikes")
        void strikes() {
            assertThat(new Rute("XXX").poeng()).isEqualTo(30);
        }

        @Test
        @DisplayName("skal kunne starte med strike")
        void strikeOgVanlig() {
            assertThat(new Rute("X7-").poeng()).isEqualTo(17);
        }

        @Test
        @DisplayName("skal kunne inneholde en spare")
        void spares() {
            assertThat(new Rute("1/5").poeng()).isEqualTo(15);
        }
    }

    @Nested
    @DisplayName("når man skal summere niende rute og tiende rute har tre kast")
    class NiendeRute {

        @Test
        @DisplayName("skal ta med påfølgende spare i tiende rute")
        void enStrikesOgEnSpare() {
            assertThat(ruter("X", "7/-").poeng()).isEqualTo(20);
        }

        @Test
        @DisplayName("skal ta med påfølgende strike og vanlig kast")
        void toStrikesOgVanlig() {
            assertThat(ruter("X", "X1-").poeng()).isEqualTo(21);
        }

        @Test
        @DisplayName("skal ta med påfølgende to strikes")
        void treStrikesOgRenne() {
            assertThat(ruter("X", "XX-").poeng()).isEqualTo(30);
        }
    }

    private Rute ruter(String forsteRute, String andreRute) {
        Rute rute = new Rute(forsteRute);
        rute.nesteRute(new Rute(andreRute));
        return rute;
    }

    @SuppressWarnings("SameParameterValue")
    private Rute ruter(String forsteRute, String andreRute, String tredjeRute) {
        Rute rute = new Rute(forsteRute);
        rute.nesteRute(ruter(andreRute, tredjeRute));

        return rute;
    }
}