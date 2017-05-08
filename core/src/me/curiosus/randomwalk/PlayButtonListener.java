package me.curiosus.randomwalk;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/**
 * Copyright 2017, John E Peterson, All rights reserved.
 * jepeterson@gmail.com
 */
public class PlayButtonListener extends ActorGestureListener {

    private final Main game;

    public PlayButtonListener(Main main) {
        game = main;
    }

    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {
        super.tap(event, x, y, count, button);
        game.setScreen(new GameScreen());
    }
}
