package Pieces;

public enum PieceColor {
    BLACK {
        @Override
        public String toString() {
            return "BLACK";
        }
    },
    WHITE {
        @Override
        public String toString() {
            return "WHITE";
        }
    }
}
