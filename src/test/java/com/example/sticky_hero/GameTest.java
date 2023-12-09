package com.example.sticky_hero;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void testIncrementScore(){
        int currentScore = 5;
        Points points = new Points(0,5,0);
        points.addCurrentScore(2);
        int newCurrentScore = currentScore + 2;
        assertEquals(newCurrentScore, points.getCurrentScore());
    }


}