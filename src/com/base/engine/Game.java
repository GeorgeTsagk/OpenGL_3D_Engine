package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game {
    
    private Mesh mesh;
    
    public Game(){
        mesh = new Mesh();
        Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1,-1,0)),
                                      new Vertex(new Vector3f(0,1,0)),
                                      new Vertex(new Vector3f(1,-1,0)),
                                      };
        mesh.addVertices(data);
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
        mesh.draw();
    }
    
    
    
}
