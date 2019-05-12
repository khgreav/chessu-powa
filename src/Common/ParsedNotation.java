package Common;

import Pieces.PieceType;

/**
 * Object to carry parsed infomation.
 */
public class ParsedNotation {
    private PieceType type;
    private int fromr;
    private int fromc;
    private int tor;
    private int toc;

    /**
     *
     * @return returns type.
     */
    public PieceType getType() {
        return type;
    }

    /**
     *
     * @return returns from row.
     */
    public int getFromr() {
        return fromr;
    }

    /**
     *
     * @return returns from column.
     */
    public int getFromc() {
        return fromc;
    }

    /**
     *
     * @return returns to row.
     */
    public int getTor() {
        return tor;
    }

    /**
     *
     * @return returns to column.
     */
    public int getToc() {
        return toc;
    }

    /**
     *
     * @return returns if piece was kicked
     */
    public boolean isKicked() {
        return kicked;
    }

    /**
     *
     * @return returns if is check.
     */
    public boolean isCheck() {
        return check;
    }

    /**
     *
     * @return returns if is checkmate.
     */
    public boolean isCheckmate() {
        return checkmate;
    }

    /**
     *
     * @return returns turn value.
     */
    public int getTurn() {
        return turn;
    }

    private boolean kicked ;
    private boolean check;
    private boolean checkmate;

    private int turn = 0;

    public ParsedNotation(PieceType type, int fromr, int fromc, int tor, int toc, boolean kicked, boolean check, boolean checkmate, int turn) {
        this.type = type;
        this.fromr = fromr;
        this.fromc = fromc;
        this.tor = tor;
        this.toc = toc;
        this.kicked = kicked;
        this.check = check;
        this.checkmate = checkmate;
        this.turn = turn;
    }
}
