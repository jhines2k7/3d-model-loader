package com.hines.james;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class ModelDemo implements Runnable {
    private Thread thread;
    public boolean running = true;

    private long window;

    private int width = 1200, height = 800;

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
        if(glfwInit() != true){
            System.err.println("GLFW initialization failed!");
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        window = glfwCreateWindow(width, height, "Teapot", 0L, 0L);

        glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(window, 100, 100);

        glfwMakeContextCurrent(window);

        glfwShowWindow(window);
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
