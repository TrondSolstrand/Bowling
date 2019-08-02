package no.sparebank1.bowling;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

class RuteLeser {

    private final List<Integer> kastene = new ArrayList<>();

    RuteLeser(String rutekast) {
        String[] hvertEnkeltKast = rutekast.split("");
        for (int i = 0; i < hvertEnkeltKast.length; i++) {

            Tegn tegn = Tegn.tegn(hvertEnkeltKast[i]);

            if (tegn == Tegn.STRIKE) {
                kastene.add(10);
            } else if (tegn == Tegn.RENNE) {
                kastene.add(0);
            } else if (tegn == Tegn.SPARE) {
                kastene.add(10 - parseInt(hvertEnkeltKast[i - 1]));
            } else {
                kastene.add(parseInt(hvertEnkeltKast[i]));
            }
        }
    }

    List<Integer> kast() {
        return kastene;
    }
}
