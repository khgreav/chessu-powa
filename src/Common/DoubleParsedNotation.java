/**
 * Contains implementation of a parsed notation object. Each object stores information about both moves in a single round.
 * Author(s): Michal Bucher (xbuche01), Karel Hanák (xhanak34)
 */

package Common;

/**
 * Represents a single round of the game.
 */
public class DoubleParsedNotation {
    public ParsedNotation white;
    public ParsedNotation black;

    /**
     * Creates a new DoubleParsedNotation object.
     * @param white white player's parsed turn.
     * @param black black player's parsed turn.
     */
    public DoubleParsedNotation(ParsedNotation white, ParsedNotation black) {
        this.white = white;
        this.black = black;
    }

    /**
     * Returns the notation object of white player's turn.
     *
     * @return Parsed turn notation of white player.
     */
    public ParsedNotation getWhite() {
        return white;
    }

    /**
     * Returns the notation object of black player's turn.
     *
     * @return Parsed turn notation of black player.
     */
    public ParsedNotation getBlack() {
        return black;
    }
}
