import common.Board;
import common.Game;
import common.GameFactory;

public class Chess {

    public static class GameThread extends Thread {
        public GameThread (){

        }

        public void run() {
            Game game = GameFactory.createChessGame(new Board());
        }
    }

    public static void main(String[] args) {
        new GameThread().start();
    }
}