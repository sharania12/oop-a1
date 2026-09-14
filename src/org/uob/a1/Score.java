package org.uob.a1;

public class Score {
    private final int PUZZLE_VALUE = 10;
    private int startingScore;
    private double currentScore;
    private int numRooms;
    private int numPuzzles;

    public Score(int startingScore){
        this.startingScore=startingScore;
        this.currentScore=0;
        this.numRooms=0;
        this.numPuzzles=0;
    }

    public void visitRoom(){
        numRooms++;
    }

    public void solvePuzzle(){
        numPuzzles++;
    }

    public double getScore(){
        currentScore=startingScore-numRooms+(numPuzzles*PUZZLE_VALUE);
        return currentScore;
    }//getScore
}//class