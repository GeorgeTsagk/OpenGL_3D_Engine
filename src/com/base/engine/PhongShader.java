package com.base.engine;

public class PhongShader extends Shader{
    private static final PhongShader instance = new PhongShader();
    
    public static PhongShader getInstance(){
        return instance;
    }
    
    private static Vector3f ambientLight = new Vector3f(0.7f, 0.7f, 0.7f);
    private static DirectionalLight directionalLight = new DirectionalLight(new BaseLight(new Vector3f(1, 1, 1), 0), new Vector3f(0, 0, 0));
    private PhongShader(){
        super();
        
        addVertexShader(ResourceLoader.loadShader("phongVertex.vs.txt"));
        addFragmentShader(ResourceLoader.loadShader("phongFragment.fs.txt"));
        compileShader();
        
        addUniform("transform");
        addUniform("baseColor");
        addUniform("transformProjected");
        addUniform("ambientLight");
        
        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");
        
        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
    }
    
    @Override
    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material){
        
        if(material.getTexture() != null){
            material.getTexture().bind();
        }else{
            RenderUtil.unbindTextures();
        }
        setUniform("transformProjected", projectedMatrix);
        setUniform("transform", worldMatrix);
        setUniform("baseColor", material.getColor());
        setUniform("ambientLight", ambientLight);
        setUniform("directionalLight", directionalLight);
        setUniformf("specularIntensity", material.getSpecularIntensity());
        setUniformf("specularPower", material.getSpecularPower());
        setUniform("eyePos", Transform.getCamera().getPos());
        
    }
    
    
    public static DirectionalLight getDirectionalLight() {
        return directionalLight;
    }

    
    public static Vector3f getAmbientLight() {
        return ambientLight;
    }

    public static void setAmbientLight(Vector3f ambientLight) {
        PhongShader.ambientLight = ambientLight;
    }
    
    public static void setDirectionalLight(DirectionalLight directionalLight) {
        PhongShader.directionalLight = directionalLight;
    }
    
    public void setUniform(String uniformName, BaseLight baseLight){
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
    }
    
    public void setUniform(String uniformName, DirectionalLight directionalLight){
        setUniform(uniformName + ".base", directionalLight.getBase());
        setUniform(uniformName + ".direction", directionalLight.getDirection());
    }
}
