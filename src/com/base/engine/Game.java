package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game {
    
    public Game(){
        
    }
    
    public void input(){
        if(Input.getKeyDown(Keyboard.KEY_UP))
            System.out.println("We typed up");
        if(Input.getKeyUp(Keyboard.KEY_UP))
            System.out.println("We released up");
        if(Input.getMouseDown(1))
            System.out.println("We clicked RMB at " + Input.getMousePosition().toString());
        if(Input.getMouseUp(1))
            System.out.println("We released RMB at " + Input.getMousePosition().toString());
    }
    
    public void update(){
        
    }
    
    public void render(){
        
    }
    
    
    
}
