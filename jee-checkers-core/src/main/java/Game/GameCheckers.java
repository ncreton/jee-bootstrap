package Game;

import Exception.GameException;

/**
 * Created by Nicolas on 10/01/2017.
 */
public interface GameCheckers {

    void play(int originRow, int originCol, int destRow, int destCol) throws GameException;
}
