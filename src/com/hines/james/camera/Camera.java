package com.hines.james.camera;


import com.hines.james.camera.matrix.CameraMatrix4f;
import com.hines.james.math.*;
import com.hines.james.input.Input;
import static org.lwjgl.glfw.GLFW.*;

public class Camera {

    public CameraMatrix4f cameraMat = new CameraMatrix4f();
    public Vector3f position = new Vector3f();

    float rot = 0.0f;
    private float pitch;
    private float yaw;
    private float roll;

    public Camera(CameraMatrix4f cameraMat){
        this.cameraMat = cameraMat;
    }

    public CameraMatrix4f getMatrix(){
        return cameraMat;
    }

    public CameraMatrix4f getCameraMat() {
        return cameraMat;
    }

    public void setCameraMat(CameraMatrix4f cameraMat) {
        this.cameraMat = cameraMat;
    }

    public void setPosition(Vector3f pos){
        this.position = pos;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getRot() {
        return rot;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void update(){
        if(Input.isKeyDown(GLFW_KEY_W)){
            position.y += 0.05f;
        }
        if(Input.isKeyDown(GLFW_KEY_S)){
            position.y -= 0.05f;
        }
        if(Input.isKeyDown(GLFW_KEY_D)){
            position.x += 0.05f;
        }
        if(Input.isKeyDown(GLFW_KEY_A)){
            position.x -= 0.05f;
        }
    }

    public CameraMatrix4f setupViewMatrix(){
        CameraMatrix4f viewMatrix = new CameraMatrix4f();
        viewMatrix = CameraMatrix4f.identity();

        Vector3f negativeCameraPos = new Vector3f(-position.x, -position.y, -position.z);

        viewMatrix.translate(negativeCameraPos);
        return viewMatrix;
    }


    public void render(){

        Shader.shader1.enable();

        // Uncomment different lines to see different rotation effects
//		Shader.shader1.setUniformMat4f("ml_matrix", CameraMatrix4f.translate(position).multiply(CameraMatrix4f.rotateX(rot)));
//		Shader.shader1.setUniformMat4f("ml_matrix", CameraMatrix4f.translate(position).multiply(CameraMatrix4f.rotateY(rot)));
        Shader.shader1.setUniformMat4f("vw_matrix",
                CameraMatrix4f.translate(
                    new Vector3f(-position.x, -position.y, -position.z))
                        .multiply(
                            CameraMatrix4f.rotateZ(roll).multiply(
                            CameraMatrix4f.rotateY(yaw)).multiply(
                            CameraMatrix4f.rotateX(pitch))
                        )
        );

        Shader.shader1.disable();

    }

}