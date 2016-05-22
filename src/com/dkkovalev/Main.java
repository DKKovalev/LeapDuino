package com.dkkovalev;

import com.leapmotion.leap.Controller;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        COMHandler comHandler = new COMHandler();

        //comHandler.initialize();

        //comHandler.close();

        Controller controller = new Controller();

        LeapHandler leapHandler = new LeapHandler();

        controller.addListener(leapHandler);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        controller.removeListener(leapHandler);

        System.out.println("Started");

    }
}
