package com.matejcrkvenac.flappybird.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<state> states;
    public GameStateManager(){
        states = new Stack<state>();
    }
    public void push(state state1){
        states.push(state1);
    }
    public void pop(state state1){
        states.pop().dispose();
    }
    public void set(state state1){
        states.pop().dispose();
        states.push(state1);
    }
    public void update(float dt){
        states.peek().update(dt);
    }
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
