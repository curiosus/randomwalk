package me.curiosus.randomwalk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Copyright 2017, John E Peterson, All rights reserved.
 * jepeterson@gmail.com
 */
public class StartScreen extends ScreenAdapter {

    private Main game;
    private Stage stage;
    private Texture background;
    private Texture playArrow;


    public StartScreen(Main main) {
        game = main;
    }


    @Override
    public void show() {
        stage = new Stage(new FitViewport(GameScreen.WORLD_WIDTH, GameScreen.WORLD_HEIGHT));
        background = new Texture(Gdx.files.internal("randomwalk.png"));
        playArrow = new Texture(Gdx.files.internal("playarrow.png"));

        Image backgroundImage = new Image(background);
        stage.addActor(backgroundImage);

        ImageButton playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(playArrow)));
        playButton.setPosition(GameScreen.WORLD_WIDTH / 2, GameScreen.WORLD_HEIGHT / 4, Align.center);
        playButton.addListener(new PlayButtonListener(game));
        stage.addActor(playButton);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }



}
