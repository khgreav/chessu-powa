package Common;

import Pieces.PieceType;


public class ParsedNotation {
    private PieceType type;
    private int fromr;
    private int fromc;
    private int tor;
    private int toc;

    public PieceType getType() {
        return type;
    }

    public int getFromr() {
        return fromr;
    }

    public int getFromc() {
        return fromc;
    }

    public int getTor() {
        return tor;
    }

    public int getToc() {
        return toc;
    }

    public boolean isKicked() {
        return kicked;
    }

    public boolean isCheck() {
        return check;
    }

    public boolean isCheckmate() {
        return checkmate;
    }

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
