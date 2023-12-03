package com.example.sticky_hero;

import java.io.*;

public class DeserializePoints
{


    public Points deserialize (String fileName) throws IOException , ClassNotFoundException
    {
        Points points  = null;

        ObjectInputStream oos = new ObjectInputStream(new FileInputStream(fileName));
        points = (Points) oos.readObject();

        System.out.println("deserialized");
        System.out.println(points);
        if (points!=null)
        {
            points.print_point();
        }
        return points;
    }
}
