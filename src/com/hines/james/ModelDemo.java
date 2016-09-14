package com.hines.james;

public class ModelDemo implements Runnable {
    private Thread thread;
    public boolean running = true;

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

    }

    public void update(){

    }

    public void render(){

    }

    @Override
    public void run() {
        init();
        while(running){
            update();
            render();
        }
    }
}
