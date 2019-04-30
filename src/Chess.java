import common.Board;
import common.Game;
import common.GameFactory;

public class Chess {
    public static void main(String[] args) {
        Game game = GameFactory.createChessGame(new Board());
    }
}