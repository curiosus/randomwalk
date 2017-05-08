package me.curiosus.randomwalk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Copyright 2017, John E Peterson, All rights reserved.
 * jepeterson@gmail.com
 */
public class GameScreen extends ScreenAdapter {

    public static final int GRID_CELL = 128;
    public static final float MOVE_TIME = .25f;
    public static final float WORLD_WIDTH = 1408.0f;
    public static final float WORLD_HEIGHT = 2048.0f;

    private Viewport viewport;
    private Camera camera;
    private ShapeRenderer shapeRenderer;
    private float timer = MOVE_TIME;
    private float x, y = 0.0f;


    @Override
    public void show() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(WORLD_WIDTH / 2.0f, WORLD_HEIGHT / 2.0f, 0);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        camera.update();
    }

    @Override
    public void render(float delta) {
        if (x == 0.0f && y == 0.0f) {
            x = WORLD_WIDTH / 2.0f;
            y = WORLD_HEIGHT / 2.0f;
        }

        timer -= delta;
        if (timer <= 0.0f) {
            timer = MOVE_TIME;
            float f1 = MathUtils.random(0, 1);
            float f2 = MathUtils.random(0, 1);

            if (f1 >= 0.5f && f2 > 0.5f) {
                y += GRID_CELL;
            } else if (f1 >= 0.5f) {
                x += GRID_CELL;
            } else if (f2 >= 0.5f) {
                x -= GRID_CELL;
            } else {
                y -= GRID_CELL;
            }
        }

        if (x > WORLD_WIDTH) {
           x = 0.0f;
        } else if (x < 0) {
           x = WORLD_WIDTH;
        }

        if (y > WORLD_HEIGHT) {
           y = 0.0f;
        } else if (y < 0.0f) {
           y = WORLD_HEIGHT;
        }

        shapeRenderer.begin();
        shapeRenderer.setColor(Color.GOLDENROD);
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.rect(x, y, GRID_CELL, GRID_CELL);
        shapeRenderer.end();
    }
}
