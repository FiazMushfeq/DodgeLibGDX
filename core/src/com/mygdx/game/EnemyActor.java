package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/*

Created by Fiaz Mushfeq

*/

public class EnemyActor extends Actor {

    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("enemy.png")));

    public EnemyActor() {
        setX(Gdx.graphics.getWidth() - 50);
        setY((float)(Math.random()*1500));

        setBounds(getX(), getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(event.getTarget() instanceof EnemyActor && sprite.getX() <= 50) {
                    SequenceAction sequenceAction = new SequenceAction();
                    sequenceAction.addAction(Actions.fadeOut(1f));
                    sequenceAction.addAction(Actions.removeActor());
                    addAction(sequenceAction);
                }
                return true;
            }
        });

        MoveToAction moveToAction = new MoveToAction();
        moveToAction.setPosition(-30,getY());
        moveToAction.setDuration(4f);

        RotateToAction rotateToAction = new RotateToAction();
        rotateToAction.setRotation((float) (Math.random()*2000));
        //rotateToAction.setRotation(720);
        //rotateToAction.setDuration(10f);
        rotateToAction.setDuration((float) (Math.random()*10));

        //addAction(rotateToAction);
        //addAction(scaleAction);
        addAction(moveToAction);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public Rectangle getRectangle(){
        return sprite.getBoundingRectangle();
    }


    @Override
    public void act(float delta) {
        super.act(delta);

        sprite.setPosition(getX(),getY());
        sprite.setRotation(getRotation());
        sprite.setScale(getScaleX(),getScaleY());



        System.out.println(getStage());
        /*
        if (getStage() != null) {
            System.out.println(getStage().getActors());
            for (Actor a : getStage().getActors()) {
                if(a instanceof MainActor && !getName().equals(a.getName())){}

                    if ( sprite.getBoundingRectangle().overlaps(((MainActor)a).getRectangle()) ){
                        a.addAction(Actions.removeActor());
                        addAction(Actions.removeActor());
                    }

            }
        }
        */
    }
}
