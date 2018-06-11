package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;

/*

Created by Fiaz Mushfeq

*/

public class MainActor extends Actor {

    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("shooter1.png")));

    public MainActor(){
        setBounds(250f,Gdx.graphics.getHeight() / 2,sprite.getWidth(),sprite.getHeight());
        setTouchable(Touchable.enabled);
        ScaleToAction scaleAction = new ScaleToAction();
        scaleAction.setScale(1.75f);
        scaleAction.setDuration(0f);
        addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (event.getTarget() instanceof MainActor) {
                    if(keycode == Input.Keys.UP && getY() <= (Gdx.graphics.getHeight() - (2 * sprite.getHeight()) - 60)) {
                        MoveToAction moveAction = new MoveToAction();
                        moveAction.setPosition(250f, getY() + 50f);
                        moveAction.setDuration(0.1f);
                        addAction(moveAction);
                    }
                    if(keycode == Input.Keys.DOWN && getY() >= ((2 * sprite.getHeight())) - 100) {
                        MoveToAction moveAction = new MoveToAction();
                        moveAction.setPosition(250f, getY() - 50f);
                        moveAction.setDuration(0.1f);
                        addAction(moveAction);
                    }
                }
                return true;
            }
        });
        /*
        setX((float)(Math.random()*1000));
        setY((float)(Math.random()*1500));
        setDebug(true);
        setOrigin(getWidth()/2,getHeight()/2);
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println(event);
                if (event.getTarget() instanceof MainActor) {
                    SequenceAction sequenceAction = new SequenceAction();

                    sequenceAction.addAction(Actions.fadeOut(1.0f));
                    sequenceAction.addAction(Actions.removeActor());

                    addAction(sequenceAction);
                }

                return false;
            }
        });
        MoveToAction moveToAction = new MoveToAction();
        moveToAction.setPosition(600,600);
        moveToAction.setDuration(4f);

        RotateByAction rotatebyAction = new RotateByAction();
        rotatebyAction.setAmount((float) (Math.random()*2000));

        rotatebyAction.setDuration((float) (Math.random()*10));

        ScaleToAction scaleAction = new ScaleToAction();
        scaleAction.setScale(2f);
        scaleAction.setDuration(5f);

        addAction(rotatebyAction);
        addAction(scaleAction);
        addAction(moveToAction);
        */
        addAction(scaleAction);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.setScale(getScaleX(),getScaleY());
        //setBounds(x,y,sprite.getWidth(),sprite.getHeight());
        //if (move)
        //    x++;
        /*
        if (getStage() != null) {

            for (Actor a : getStage().getActors()) {
                if(a instanceof EnemyActor && !getName().equals(a.getName()))
                    if ( sprite.getBoundingRectangle().overlaps(((EnemyActor)a).getRectangle()) ){
                        a.addAction(Actions.removeActor());
                        addAction(Actions.removeActor());
                    }
            }
        }
        */
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        /*
        sprite.setPosition(getX(),getY());
        sprite.setRotation(getRotation());
        sprite.setScale(getScaleX(),getScaleY());
        sprite.setColor(getColor());
        setBounds(getX(), getY(),getWidth(), getWidth());
        sprite.draw(batch,parentAlpha);
        */
        //batch.draw(texture,x,y);
        sprite.draw(batch);
    }

    public Rectangle getRectangle(){
        return sprite.getBoundingRectangle();
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }
}