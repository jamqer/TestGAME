package com.mygdx.game.Stance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Tube;

/**
 * Created by Piotras on 2015-12-09.
 */
public class PlayState extends State {
    public static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT = 4;


    private Bird bird;
    private Texture bg;
    private Tube tube;
    private Array<Tube> tubeArray;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        camera.setToOrtho(false, MyGdxGame.WIDTH/2, MyGdxGame.HEIGHT/2);
        bg= new Texture("bg.png");
        tubeArray = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i++){
            tubeArray.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH )));

        }

    }

    @Override
    public void handleInput() {

        if(Gdx.input.justTouched()){
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 80;

        for(Tube tube : tubeArray) {
            if (camera.position.x - camera.viewportWidth / 2 > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if(tube.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm));
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube: tubeArray) {

            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x,tube.getPosBotTube().y);
        }

        sb.end();
    }

    @Override
    public void dispose() {

    }
}
