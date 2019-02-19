package Pieces;

public enum PieceType {
    KING {
        @Override
        public String toString() {
            return "KING";
        }
    },
    QUEEN {
        @Override
        public String toString() {
            return "QUEEN";
        }
    },
    BISHOP {
        @Override
        public String toString() {
            return "BISHOP";
        }
    },
    KNIGHT {
        @Override
        public String toString() {
            return "KNIGHT";
        }
    },
    ROOK {
        @Override
        public String toString() {
            return "ROOK";
        }
    },
    PAWN {
        @Override
        public String toString() {
            return "PAWN";
        }
    }
}
