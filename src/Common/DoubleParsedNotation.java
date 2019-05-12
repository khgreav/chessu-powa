package Common;

/**
 * Helper class for parser.
 */
public class DoubleParsedNotation {
    public ParsedNotation white;
    public ParsedNotation black;

    /**
     *
     * @param white white's parsed turn.
     * @param black black's parsed turn.
     */
    public DoubleParsedNotation(ParsedNotation white, ParsedNotation black) {
        this.white = white;
        this.black = black;
    }

    /**
     * Gets the White turn notation.
     *
     * @return ParserNotation of white player.
     */
    public ParsedNotation getWhite() {
        return white;
    }

    /**
     * Gets the black turn notation.
     *
     * @return ParserNotation of black player.
     */
    public ParsedNotation getBlack() {
        return black;
    }
}
