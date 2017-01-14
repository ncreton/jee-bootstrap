package Game;

import Exception.GameException;
import Model.Board;
import Model.Cell;
import Model.QueenDirection;

import static java.lang.Math.abs;

/**
 * Created by nicolas on 13/01/2017.
 */
public class QueenMove extends Move {

    public QueenMove(Board board) {
        super(board);
    }

    public QueenDirection getDiagonalDirection(int originRow, int originCol, int destRow, int destCol) {
        QueenDirection queenDirection = null;

        if (destRow < originRow && destCol > originCol || destRow > originRow && destCol < originCol)
            queenDirection = QueenDirection.RIGHT_DIAGONAL;

        if(destRow < originRow && destCol < originCol || destRow > originRow && destCol > originCol) {
            queenDirection = QueenDirection.LEFT_DIAGONAL;
        }
        return queenDirection;
    }

    /**
     * Method to move the queen
     *
     * @param originCell
     * @param destCell
     * @throws GameException
     */
    public void move(Cell originCell, Cell destCell) throws GameException {

        int originRow = originCell.getRowIndex();
        int originCol = originCell.getColIndex();
        int destRow = destCell.getRowIndex();
        int destCol = destCell.getColIndex();

        if (isMoveAuthorized(originCell, destCell)) {
            if (getDiagonalDirection(originRow, originCol, destRow, destCol) == QueenDirection.RIGHT_DIAGONAL) {
                if (isOtherTeamPawns(originCell, destCell, QueenDirection.RIGHT_DIAGONAL)) {
                    //Remove opponents pawns
                    removeRangePawns(originCell, destCell, QueenDirection.RIGHT_DIAGONAL);
                    board.swapPawn(originCell, destCell);
                }
            } else if (getDiagonalDirection(originRow, originCol, destRow, destCol) == QueenDirection.LEFT_DIAGONAL) {
                if (isOtherTeamPawns(originCell, destCell, QueenDirection.LEFT_DIAGONAL)) {
                    //Remove opponents pawns
                    removeRangePawns(originCell, destCell, QueenDirection.LEFT_DIAGONAL);
                    board.swapPawn(originCell, destCell);
                }
            } else {
                throw new GameException("Problem moving the queen");
            }
        }
    }

    @Override
    public boolean isMoveAuthorized(Cell originCell, Cell destCell) throws GameException {
        int originRow = originCell.getRowIndex();
        int originCol = originCell.getColIndex();
        int destRow = destCell.getRowIndex();
        int destCol = destCell.getColIndex();

        if(abs(destRow - originRow) == abs(destCol - originCol)){
            return true;
        }
        System.out.println("Not possible");
        return false;
    }
}
