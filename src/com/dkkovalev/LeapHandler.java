package com.dkkovalev;

import com.leapmotion.leap.*;

/**
 * Created by DKKovalev on 21.05.2016.
 */
public class LeapHandler extends Listener {
    private Hand hand;
    private HandList hands;

    private Vector velocity;

    private COMHandler comHandler;

    private int i;

    public LeapHandler() {
        comHandler = new COMHandler();
        comHandler.initialize();
    }

    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();

        hands = frame.hands();
        hand = hands.rightmost();
        velocity = hand.palmPosition();
        i = (int) velocity.getX();

        comHandler.sendData(i);
        try{
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getPalmX(){
        return i;
    }
}
