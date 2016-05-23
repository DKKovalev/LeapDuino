package com.dkkovalev;

import com.leapmotion.leap.Controller;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller();
        LeapHandler leapHandler = new LeapHandler();
        controller.addListener(leapHandler);

        try{
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(leapHandler.getPalmX());

        controller.removeListener(leapHandler);
    }
}
