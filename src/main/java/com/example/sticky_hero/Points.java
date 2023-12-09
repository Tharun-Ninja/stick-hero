package com.example.sticky_hero;

import java.io.Serializable;

public class Points implements Serializable {
    public int getCherryCount() {
        return cherryCount;
    }
    public void setCherryCount(int cherryCount)
    {

        System.out.println("setCherry_count");
        System.out.println(cherryCount);
        this.cherryCount = cherryCount;
    }
    private int cherryCount;

    public void addCurrentScore(int x)
    {
        this.setCurrentScore(this.currentScore + x);
    }

    public int getCurrentScore()
    {
        return currentScore;
    }
    public void setCurrentScore(int currentScore)
    {
        System.out.println("setCurrent_score");
        System.out.println(currentScore);
        this.currentScore = currentScore;
    }
    private int currentScore;

    public int getBestScore() {
        return bestScore;
    }
    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
    private int bestScore;

    public Points (int cherryCount, int currentScore, int bestScore) {

        this.cherryCount = cherryCount;
        this.currentScore = currentScore;
        this.bestScore = bestScore;

    }

     public void printPoint()
    {
        System.out.printf("cherry_count:%d ", cherryCount);
        System.out.printf("current_score:%d ", currentScore);
        System.out.printf("best_score:%d\n", bestScore);
    }
}
