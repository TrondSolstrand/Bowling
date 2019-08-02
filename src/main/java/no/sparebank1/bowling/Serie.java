package no.sparebank1.bowling;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class Serie {

    private final List<Rute> ruter;

    Serie(String serie) {
        ruter = Stream.of(serie.split(" "))
                .map(Rute::new)
                .collect(toList());

        settRuteneTilAPekePaaNesteISerien();
    }

    private void settRuteneTilAPekePaaNesteISerien() {
        IntStream.range(0, ruter.size() - 1).forEach(i -> ruter.get(i).nesteRute(ruter.get(i + 1)));
    }

    int poeng() {
        return ruter.stream().mapToInt(Rute::poeng).sum();
    }
}
