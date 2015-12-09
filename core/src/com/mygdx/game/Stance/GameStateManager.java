package com.mygdx.game.Stance;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Piotras on 2015-12-09.
 */
public class GameStateManager {
    private Stack<State> stateStack;

    public GameStateManager(){
        stateStack =  new Stack<State>();
    }

    public void push(State state){

        stateStack.push(state);
    }

    public void pop(){
        stateStack.pop();
    }
    public void set(State state){

        stateStack.pop();
        stateStack.push(state);
    }
    public void update(float dt){
        stateStack.peek().update(dt);
    }
    public void render(SpriteBatch sb){

        stateStack.peek().render(sb);
    }

}
