package com.example.sticky_hero;

import java.io.Serializable;

public class Points implements Serializable {
    public int getCherry_count() {
        return cherry_count;
    }

    public void setCherry_count(int cherry_count)
    {

        System.out.println("setCherry_count");
        System.out.println(cherry_count);
        this.cherry_count = cherry_count;
    }

    private int cherry_count;

    public int getCurrent_score()
    {
        return current_score;
    }

    public void setCurrent_score(int current_score)
    {
        System.out.println("setCurrent_score");
        System.out.println(current_score);
        this.current_score = current_score;
    }

    private int current_score;

    public int getBest_score() {
        return best_score;
    }

    public void setBest_score(int best_score) {
        this.best_score = best_score;
    }

    private int best_score;

    public Points (int cherry_count, int current_score, int best_score) {

        this.cherry_count = cherry_count;
        this.current_score = current_score;
        this.best_score = best_score;

    }

     public void print_point()
    {
        System.out.printf("cherry_count:%d ", cherry_count);
        System.out.printf("current_score:%d ", current_score);
        System.out.printf("best_score:%d\n", best_score);
    }
}
