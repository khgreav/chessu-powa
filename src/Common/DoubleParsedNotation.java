package Common;

public class DoubleParsedNotation {
    public ParsedNotation white;
    public ParsedNotation black;

    public DoubleParsedNotation(ParsedNotation white, ParsedNotation black) {
        this.white = white;
        this.black = black;
    }

    public ParsedNotation getWhite() {
        return white;
    }

    public ParsedNotation getBlack() {
        return black;
    }
}
