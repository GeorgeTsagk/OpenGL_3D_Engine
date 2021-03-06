package game;

import core.Time;
import core.Transform;
import core.Vector2f;
import core.Vector3f;
import rendering.Attenuation;
import rendering.BaseLight;
import rendering.Camera;
import rendering.DirectionalLight;
import rendering.Material;
import rendering.Mesh;
import rendering.PhongShader;
import rendering.PointLight;
import rendering.RenderUtil;
import rendering.Shader;
import rendering.SpotLight;
import rendering.Texture;
import rendering.Vertex;
import rendering.Window;

public class TestGame implements Game{
    private Mesh mesh;
	private Shader shader;
	private Material material;
	private Transform transform;
	private Camera camera;
	
	PointLight pLight1 = new PointLight(new BaseLight(new Vector3f(1,0.5f,0), 0.2f), new Attenuation(0,0.2f,0.01f), new Vector3f(-3,0,1), 45);
	PointLight pLight2 = new PointLight(new BaseLight(new Vector3f(0,0.5f,1), 0.1f), new Attenuation(0,0.2f,0.01f), new Vector3f(2,0,1), 45);
        PointLight pLight3 = new PointLight(new BaseLight(new Vector3f(0,1,0), 0.1f), new Attenuation(0,0.1f,0), new Vector3f(4,0,1), 100);
	SpotLight sLight1 = new SpotLight(new PointLight(new BaseLight(new Vector3f(1f,1f,1f), 0.5f), new Attenuation(0.5f,0.02f,0.001f), new Vector3f(-3,0,1), 100),
                                          new Vector3f(1, 1, 1),
                                          0.9f);
	public TestGame(){
        }
	
        public void init(){
            
		material = new Material(new Texture("lisa.jpg"), new Vector3f(1,1,1), 1, 8);
		shader = PhongShader.getInstance();
		camera = new Camera();
		transform = new Transform();
		
		float fieldDepth = 35.0f;
		float fieldWidth = 35.0f;
		
		Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
							new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
							new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
							new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};
		
		int indices[] = { 0, 1, 2,
					      2, 1, 3};
		
		mesh = new Mesh(vertices, indices, true);
		
		Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
		Transform.setCamera(camera);
		
		PhongShader.setAmbientLight(new Vector3f(0.05f,0.05f,0.05f));
		PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.1f), new Vector3f(1,1,1)));
	
		PhongShader.setPointLight(new PointLight[]{pLight1, pLight2,pLight3});
                //PhongShader.setSpotLight(new SpotLight[]{sLight1});
	
        }
        
	public void input()
	{
		camera.input();
		
//		if(Input.getKeyDown(Input.KEY_UP))
//			System.out.println("We've just pressed up!");
//		if(Input.getKeyUp(Input.KEY_UP))
//			System.out.println("We've just released up!");
//		
//		if(Input.getMouseDown(1))
//			System.out.println("We've just right clicked at " + Input.getMousePosition().toString());
//		if(Input.getMouseUp(1))
//			System.out.println("We've just released right mouse button!");
	}
	
	float temp = 0.0f;
	
	public void update()
	{
		temp += Time.getDelta();
		
		float sinTemp = (float)Math.sin(temp);
		
		transform.setTranslation(0, -1, 5);
		//transform.setRotation(0, sinTemp * 180, 0);
		
		pLight1.setPosition(new Vector3f(8*(float)Math.sin(temp),2*(float)Math.sin(temp)+2,8.0f * (float)(Math.sin(temp) + 1.0/2.0) + 10));
		pLight2.setPosition(new Vector3f(5*(float)Math.sin(temp),1,8.0f * (float)(Math.cos(temp) + 1.0/2.0) + 10));
                pLight3.setPosition(new Vector3f(5,2,25*(float)Math.sin(0.25f*temp)));
		sLight1.getPointLight().setPosition(camera.getPos());
                sLight1.setDirection(camera.getForward());
		//transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);
	}
	
	public void render()
	{
		RenderUtil.setClearColor(Transform.getCamera().getPos().div(2048f).abs());
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}
}
