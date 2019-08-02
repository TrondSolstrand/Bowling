package no.sparebank1.bowling;

import java.util.List;

class Rute {

    private final List<Integer> kastene;
    private Rute nesteRute;

    Rute(String rute) {
        kastene = new RuteLeser(rute).kast();
    }

    private int kast(int pos) {
        if (pos < kastene.size()) {
            return kastene.get(pos);
        } else {
            return nesteRute.kast(pos - kastene.size());
        }
    }

    int poeng() {
        if (erStrikeEllerSpare()) {
            return kast(0) + kast(1) + kast(2);
        } else {
            return kast(0) + kast(1);
        }
    }

    private boolean erStrikeEllerSpare() {
        return kastene.size() == 1 || kastene.size() == 3 || kastene.get(0) + kastene.get(1) == 10;
    }

    void nesteRute(Rute rute) {
        nesteRute = rute;
    }
}
