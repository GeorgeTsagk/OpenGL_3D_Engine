package com.base.engine;

import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL20.*;
public class Game {
    
    private Mesh mesh;
    private Shader shader;
    private Transform transform;
    public Game(){
        mesh = new Mesh();
        shader = new Shader();
        Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1,-1,0)),
                                      new Vertex(new Vector3f(0,1,0)),
                                      new Vertex(new Vector3f(1,-1,0)),
                                      };
        
        mesh.addVertices(data);
        
        transform = new Transform();
        
        shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs.txt"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs.txt"));
        shader.compileShader();
        
        shader.addUniform("transform");
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
    
    float tmp = 0.0f;
    public void update(){
        tmp += 0.01;
        
        transform.setTranslation((float)Math.sin(tmp), 0, 0);
    }
    
    public void render(){
        shader.bind();
        shader.setUniform("transform", transform.getTransformation());
        mesh.draw();
    }
    
    
    
}
