package com.example.sticky_hero;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializePoints
{
    public void serialize(String fileName, Points points)
    {

        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(points);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("serialized");
        System.out.println(points);
        if (points!=null) {
            points.printPoint();
        }

    }


}
