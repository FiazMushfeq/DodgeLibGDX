package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;


/*

Created by Fiaz Mushfeq

*/

public class MyGdxGame extends ApplicationAdapter {

	private Stage stage;
	private SpriteBatch batch;
	private TextureAtlas textureAtlas;
	private Animation<TextureAtlas.AtlasRegion> animation;
	private float timePassed = 0f;
	private MainActor mainActor1;
	private EnemyActor enemyActor1;
	BitmapFont font;
	String text;
	public static int points = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		stage = new Stage();
		font = new BitmapFont(Gdx.files.internal("myFont.fnt"));
		textureAtlas = new TextureAtlas(Gdx.files.internal("Shoot.pack"));
		animation = new Animation<TextureAtlas.AtlasRegion>(1/10f, textureAtlas.getRegions());
		Gdx.input.setInputProcessor(stage);
		mainActor1 = new MainActor();
		stage.addActor(mainActor1);
		mainActor1.setName("1");
		enemyActor1 = new EnemyActor();
		enemyActor1.setName("1");
		stage.addActor(enemyActor1);
		stage.setKeyboardFocus(mainActor1);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(mainActor1.getRectangle().overlaps(enemyActor1.getRectangle())) {
			//enemyActor1.sprite.draw((TextureRegion) animation.getKeyFrame(timePassed, true));
			enemyActor1.remove();
			enemyActor1 = new EnemyActor();
			enemyActor1.setName("1");
			stage.addActor(enemyActor1);
			points += 1;
		}
		if(stage.getActors().size == 1) {
			enemyActor1.remove();
			enemyActor1 = new EnemyActor();
			enemyActor1.setName("1");
			stage.addActor(enemyActor1);
			points -= 1;
		}
		text = "Score: " + String.valueOf(points);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.begin();
		font.draw(batch, text, 500, 50);
		timePassed += Gdx.graphics.getDeltaTime();
		//batch.draw((TextureRegion) animation.getKeyFrame(timePassed, true), 500, 500);
		//batch.draw(textureRegion, mainActor1.getX(), mainActor1.getY());
		TextureRegion textureRegion = animation.getKeyFrame(timePassed, true);
		batch.draw(textureRegion, mainActor1.sprite.getX() - 40, mainActor1.sprite.getY() - 45, 1.75f * mainActor1.getWidth(), 1.75f * mainActor1.getHeight());
		ScaleToAction scaleAction = new ScaleToAction();
		scaleAction.setScale(1.75f);
		scaleAction.setDuration(0f);
		mainActor1.addAction(scaleAction);
		batch.end();
	}

	@Override
	public void dispose () {
		stage.dispose();
		batch.dispose();
	}
}