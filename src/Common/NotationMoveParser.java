/**
 * Contains implementation of a move parser, a notation is parsed into a move which is then stored in a DoubleParsedNotation object.
 * Author(s): Michal Bucher (xbuche01), Karel Hanák (xhanak34)
 */

package Common;

import Pieces.PieceType;

/**
 * Class with static function to do the parsing process.
 */
public class NotationMoveParser {
    static private char type = ' ';
    static private char fromr = ' ';
    static private char fromc = ' ';
    static private char tor = ' ';
    static  private char toc = ' ';

    static private char kicked = ' ';
    static private char check = ' ';
    static private char checkmate = ' ';

    /**
     * Static function for parsing string notation to object notation.
     * @param line String line from file
     * @return Returns 2 parsed moves
     * @throws Exception throws exception if there is wrong format
     */
    static public DoubleParsedNotation Parse(String line) throws Exception {
        type = ' ';
        fromr = ' ';
        fromc = ' ';
        tor = ' ';
        toc = ' ';
        kicked = ' ';
        check = ' ';
        checkmate = ' ';

        String[] split = line.split("\\s", 3);

        int turn = 0;
        if (split[0].matches("\\d+."))
            turn = Integer.parseInt(split[0].split("\\.", 2)[0]);
        else
            return null;

        EvalNotationToValue(split[1]);
        ParsedNotation white = new ParsedNotation(getType(type),('8' - fromr),(fromc - 'a'),('8' - tor),(toc - 'a'),kicked=='x', check=='+',checkmate=='#',turn);

        type = ' ';
        fromr = ' ';
        fromc = ' ';
        tor = ' ';
        toc = ' ';
        kicked = ' ';
        check = ' ';
        checkmate = ' ';

        ParsedNotation black = null;
        if (split.length > 2) {
            EvalNotationToValue(split[2]);
            black = new ParsedNotation(getType(type),('8' - fromr),(fromc - 'a'),('8' - tor),(toc - 'a'),kicked=='x', check=='+',checkmate=='#',turn);
        }

        return new DoubleParsedNotation(white, black);
    }

    /**
     * Parses the loaded notation data and assigns processed data to their respective containers.
     * @param oneMove ParsedNotation of a single turn turn
     * @throws Exception Throws exception if there is incorrect line format
     */
    static private void EvalNotationToValue(String oneMove) throws Exception {
        if (oneMove.matches("[V|J|D|K|S][a-h][1-8][x]?[a-h][1-8][+]?[#]?")) {
            int offset = 0;

            char[] charmove = oneMove.trim().toCharArray();

            type = charmove[0];
            fromc = charmove[1];
            fromr = charmove[2];

            if (charmove[3] == 'x') {
                kicked = 'x';
                offset++;
            }

            toc = charmove[3 + offset];
            tor = charmove[4 + offset];

            if (5 + offset < charmove.length) {

                if (charmove[5 + offset] == '+')
                    check = '+';
                else if (charmove[5 + offset] == '#')
                    checkmate = '#';
            }
        } else if (oneMove.matches("[V|J|D|K|S][x]?[a-h][1-8][+]?[#]?")) {
            int offset = 0;
            char[] charmove = oneMove.trim().toCharArray();

            type = charmove[0];

            if (charmove[1] == 'x') {
                kicked = 'x';
                offset++;
            }

            toc = charmove[1 + offset];
            tor = charmove[2 + offset];

            if (3 + offset < charmove.length) {

                if (charmove[5 + offset] == '+')
                    check = '+';
                else if (charmove[5 + offset] == '#')
                    checkmate = '#';
            }
        } else if (oneMove.matches("[p][a-h][1-8][x]?[a-h][1-8][+]?[#]?")) {
            int offset = 0;
            char[] charmove = oneMove.trim().toCharArray();

            type = charmove[0];
            fromc = charmove[1];
            fromr = charmove[2];

            if (charmove[3] == 'x') {
                kicked = 'x';
                offset++;
            }

            toc = charmove[3 + offset];
            tor = charmove[4 + offset];

            if (5 + offset < charmove.length) {

                if (charmove[5 + offset] == '+')
                    check = '+';
                else if (charmove[5 + offset] == '#')
                    checkmate = '#';
            }
        } else if (oneMove.matches("[a-h][1-8][x]?[a-h][1-8][+]?[#]?")) {
            int offset = 0;
            char[] charmove = oneMove.trim().toCharArray();

            type = 'p';
            fromc = charmove[0];
            fromr = charmove[1];

            if (charmove[2] == 'x') {
                kicked = 'x';
                offset++;
            }

            toc = charmove[2 + offset];
            tor = charmove[3 + offset];

            if (4 + offset < charmove.length) {

                if (charmove[5 + offset] == '+')
                    check = '+';
                else if (charmove[5 + offset] == '#')
                    checkmate = '#';
            }
        } else if (oneMove.matches("[p][x]?[a-h][1-8][+]?[#]?")) {
            int offset = 0;
            char[] charmove = oneMove.trim().toCharArray();

            type = charmove[0];

            if (charmove[1] == 'x') {
                kicked = 'x';
                offset++;
            }

            toc = charmove[1 + offset];
            tor = charmove[2 + offset];

            if (3 + offset < charmove.length) {

                if (charmove[5 + offset] == '+')
                    check = '+';
                else if (charmove[5 + offset] == '#')
                    checkmate = '#';
            }
        } else  if (oneMove.matches("[x]?[a-h][1-8][+]?[#]?")) {
            int offset = 0;
            char[] charmove = oneMove.trim().toCharArray();

            type = 'p';

            if (charmove[0] == 'x') {
                kicked = 'x';
                offset++;
            }

            toc = charmove[offset];
            tor = charmove[1 + offset];

            if (2 + offset < charmove.length) {

                if (charmove[5 + offset] == '+')
                    check = '+';
                else if (charmove[5 + offset] == '#')
                    checkmate = '#';
            }
        } else {
            throw new Exception("spatny format");
        }

    }

    /**
     * Returns PieceType from char according to the piece identifier.
     * @param type char type
     * @return returns correct PieceType
     */
    static private PieceType getType(char type) {
        switch (type) {
            case 'p':
                return PieceType.P;
            case 'V':
                return PieceType.R;
            case 'S':
                return PieceType.B;
            case 'J':
                return PieceType.KN;
            case 'D':
                return PieceType.Q;
            case 'K':
                return PieceType.KI;
        }
        return PieceType.P;
    }

    /**
     * Converts a PieceType value into char variable.
     *
     * @param type PieceType value
     * @return  char value of pieceType or space if the specified type is unknown
     */
    static public char getSignFromPieceType(PieceType type) {
        switch (type) {
            case P:
                return 'p';
            case R:
                return 'V';
            case B:
                return 'S';
            case KN:
                return 'J';
            case Q:
                return 'D';
            case KI:
                return 'K';
        }
        return ' ';
    }
}
