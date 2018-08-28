package com.base.engine;

import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL20.*;
public class Game {
    
    private Mesh mesh;
    private Shader shader;
    private Transform transform;
    private Camera camera;
    
    public Game(){
        mesh = ResourceLoader.loadMesh("box.obj");
        shader = new Shader();
        camera = new Camera();
//        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1,0)),
//                                      new Vertex(new Vector3f(0,1,0)),
//                                      new Vertex(new Vector3f(1,-1,0)),
//                                      new Vertex(new Vector3f(0, -1, 1))
//                                      };
//        
//        int[] indices = new int[]{0, 1, 3,
//                                  3, 1, 2,
//                                  2, 1, 0,
//                                  0, 2, 3};
//        
//        mesh.addVertices(vertices, indices);
        
        transform = new Transform();
        transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
        transform.setCamera(camera);
        shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs.txt"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs.txt"));
        shader.compileShader();
        
        shader.addUniform("transform");
    }
    
    public void input(){
        camera.input();
    }
    
    float tmp = 0.0f;
    public void update(){
        tmp += Time.getDelta();
        float sinTemp = (float)Math.sin(tmp);
        float cosTemp = (float)Math.cos(tmp);
        transform.setTranslation(sinTemp, cosTemp, 5);
        transform.setRotation(sinTemp * 5 , sinTemp * 5, 0);
        //transform.setScale(0.5f * sinTemp ,0.5f * sinTemp,0.5f * sinTemp);
    }
    
    public void render(){
        shader.bind();
        shader.setUniform("transform", transform.getProjectedTransformation());
        mesh.draw();
    }
      
}