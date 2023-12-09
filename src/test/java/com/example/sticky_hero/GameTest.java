package com.example.sticky_hero;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    @Test
    public void testIncrementScore(){
        int currentScore = 5;
        Points points = new Points(0,5,0);
        points.addCurrentScore(2);
        int newCurrentScore = currentScore + 2;
        assertEquals(newCurrentScore, points.getCurrentScore());
    }

    @Test
    public void testGoldenAndNormalCherry(){
        Cherry cherry = new Cherry("golden", null, null);
        Cherry cherry2 = new Cherry("normal", null, null);

        assertEquals(5, cherry.getReward());
        assertEquals(1, cherry2.getReward());
    }




}