package no.sparebank1.bowling;

public enum Tegn {
    STRIKE, SPARE, TALL, RENNE;

    public static Tegn tegn(String tegn) {
        switch (tegn) {
            case "-" : return Tegn.RENNE;
            case "X" : return Tegn.STRIKE;
            case "/" : return Tegn.SPARE;
            default: return Tegn.TALL;
        }
    }
}
