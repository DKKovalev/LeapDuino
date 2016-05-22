package com.dkkovalev;

import com.leapmotion.leap.*;

/**
 * Created by DKKovalev on 21.05.2016.
 */
public class LeapHandler extends Listener {
    private Hand hand;
    private HandList hands;

    private Vector velocity;

    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();

        hands = frame.hands();
        hand = hands.rightmost();
        velocity = hand.palmVelocity();
        System.out.println(velocity.getX());
    }
}
