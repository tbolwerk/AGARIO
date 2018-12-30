package TPN;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main extends PApplet {
    private Blob blob;
    private CopyOnWriteArrayList<Blob> blobs;
    private int numberOfBlobs=50;
    private static int  width = 800;
    private static int  height = 800;

    float zoom;

    private int max = width;
    private int min = -width;

    public static void main(String args[]){
        PApplet.main("TPN.Main");
    }

    public void settings(){
        size(width,height);
    }

    public void setup(){

        zoom = 1;
        blob = new Blob(0,0,64);
        blobs = new CopyOnWriteArrayList<Blob>();
        for (int i = 0; i < numberOfBlobs; i++) {
            Random random = new Random();
            int randomX = random.nextInt(max + 1 -min) + min;
            int randomY = random.nextInt(max + 1 -min) + min;

            blobs.add(new Blob(randomX,randomY,16));
        }
    }

    public void draw(){
        background(0);
        translate(width/2,height/2);
        float newZoom=blob.getRadius()/64;
        zoom = lerp(zoom,newZoom,0.1f);
        scale(zoom);
        translate(-blob.getLocation().x,-blob.getLocation().y);
        for (Blob spawnedBlob:blobs) {
            spawnedBlob.show(this,Color.WHITE);
        }

        for (Blob spawnedBlob: blobs){
            blob.eat(spawnedBlob);
            if(spawnedBlob.isEaten()){
                blobs.remove(spawnedBlob);
            }
        }

        blob.show(this, Color.WHITE);
        blob.controll(new PVector(mouseX,mouseY),3);


    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}
