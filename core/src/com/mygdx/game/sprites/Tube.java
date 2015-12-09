package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by Piotras on 2015-12-09.
 */
public class Tube {
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;
    private static final int fluctuation = 130;
    private static final int tubeGap = 100;
    private static final int lowest_opening= 120;
    public static final int TUBE_WIDTH = 52;

    private Rectangle boundsTOP,boundsBOTTOM;


    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();



        posTopTube = new Vector2(x, rand.nextInt(fluctuation)+ tubeGap + lowest_opening);
        posBotTube = new Vector2(x, posTopTube.y - tubeGap - bottomTube.getHeight());
        boundsTOP = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(),topTube.getHeight());
        boundsBOTTOM = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(),bottomTube.getHeight());
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Texture getTopTube() {
        return topTube;
    }


    public void reposition(float x){
        posTopTube.set(x, rand.nextInt(fluctuation)+ tubeGap + lowest_opening);
        posBotTube.set(x, posTopTube.y - tubeGap - bottomTube.getHeight());
        boundsTOP.setPosition(posTopTube.x,posTopTube.y);
        boundsBOTTOM.setPosition(posBotTube.x,posBotTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTOP) || player.overlaps(boundsBOTTOM);
    }
}
