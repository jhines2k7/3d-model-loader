package com.hines.james;

import com.hines.james.camera.Camera;
import com.hines.james.camera.Shader;
import com.hines.james.camera.matrix.CameraMatrix4f;
import com.hines.james.input.Input;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class ModelDemo implements Runnable {
    private Thread thread;
    public boolean running = true;

    private long window;

    private int width = 1200, height = 800;

    public Camera camera = new Camera(new CameraMatrix4f());

    public static void main(String[] args) {
        ModelDemo demo = new ModelDemo();

        demo.start();
    }

    public void start(){
        running = true;
        thread = new Thread(this, "Model Loader Demo");
        thread.start();
    }

    public void init(){
        GLFWKeyCallback keyCallback;

        if(glfwInit() != true){
            System.err.println("GLFW initialization failed!");
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        window = glfwCreateWindow(width, height, "Teapot", 0L, 0L);

        glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(window, 100, 100);

        glfwSetKeyCallback(window, keyCallback = new Input());

        glfwMakeContextCurrent(window);

        glfwShowWindow(window);

        // In order to perform OpenGL rendering, a context must be "made current"
        // we can do this by using this line of code:
//        GLContext.createFromCurrent();
        GL.createCapabilities();
        // Clears color buffers and gives us a nice color background.
        glClearColor(0.3f,0.7f,0.92f,1.0f);


        glActiveTexture(GL_TEXTURE1);

        // Enables depth testing which will be important to make sure
        // triangles are not rendering in front of each other when they
        // shouldn't be.
        glEnable(GL_DEPTH_TEST);


        // Prints out the current OpenGL version to the console.
        System.out.println("OpenGL: " + glGetString(GL_VERSION));

        Shader.loadAll();

        Shader.shader1.enable();
        CameraMatrix4f pr_matrix = CameraMatrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -10.0f, 10.0f);
//		Matrix4f pr_matrix = Matrix4f.perspective(10.0f, 10.0f, 10.0f, 10.0f, -1.0f, 100.0f, 15.0f, (float)width/(float)height);
        Shader.shader1.setUniformMat4f("vw_matrix", CameraMatrix4f.translate(camera.position));
        Shader.shader1.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.shader1.setUniform1i("tex", 1);

        Shader.shader1.disable();
    }

    public void update(){
        glfwPollEvents();
    }

    public void render(){
        glfwSwapBuffers(window);
    }

    @Override
    public void run() {
        init();
        while(running){
            update();
            render();

            if(glfwWindowShouldClose(window) == true){
                running = false;
            }
        }
    }
}
