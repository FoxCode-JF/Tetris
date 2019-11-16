package model;

/**
 * Class representing way to score points in the game
 */
public class ScoringModel {
    /**
     * Counts the score for erased rows
     * @param scoredRows Number of erased rows
     * @return Score for current round
     */
    public long countScore(int scoredRows)
    {
        long score = 0;
        switch(scoredRows)
        {
            case 0:
                return 0;
            case 1:
                score = 40;
                break;
            case 2:
                score = 100;
                break;
            case 3:
                score = 300;
                break;
            case 4:
                score = 1200;
                break;
        }
        return score;
    }
}
