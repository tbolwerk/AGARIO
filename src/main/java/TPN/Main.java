package TPN;

import javafx.scene.input.KeyCode;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main extends PApplet {
    private Blob blob;
    private static int inititialSizeOfBlob= 64;
    private CopyOnWriteArrayList<Blob> blobs;
    private int numberOfBlobs=50;
    private static int  width = 800;
    private static int  height = 800;
    private int currentRadius;
    private float zoom;

    private int maxWidth = width;
    private int minWidth = -width;

    private int maxHeight = height;
    private int minHeight = -height;

    private int maxRadius = 32;
    private int minRadius = 16;

    private Player player;

    public static void main(String args[]){
        PApplet.main("TPN.Main");
    }

    public void settings(){
        size(width,height);
    }

    public void setup(){
        currentRadius=inititialSizeOfBlob;
        player = new Player(this,0,0,"Twan");
        player.setup();


        blobs = new CopyOnWriteArrayList<Blob>();
        for (int i = 0; i < numberOfBlobs; i++) {
            blobs.add(randomBlob());
        }
    }

    public void draw(){
        background(0);
        player.draw();

        for (Blob spawnedBlob:blobs) {
            spawnedBlob.show(this,spawnedBlob.getColor());
        }

        for (Blob spawnedBlob: blobs){
            player.getStartingBlob().eat(spawnedBlob);
            if(spawnedBlob.isEaten()){
                blobs.remove(spawnedBlob);
                blobs.add(randomBlob());
            }
        }
    }

    @Override
    public void keyReleased() {
        System.out.println("klik");
        player.keyReleased();
    }

    public Blob randomBlob(){
        Random random = new Random();
        int randomX = random.nextInt(maxWidth + 1 -minWidth) + minWidth;
        int randomY = random.nextInt(maxHeight + 1 -minHeight) + minHeight;
        int randomRadius = random.nextInt(maxRadius + 1 -minRadius) + minRadius;

        int red =random.nextInt(255);
        int green =random.nextInt(255);
        int blue =random.nextInt(255);

        Color color = new Color(red,green,blue);

        return new Blob(randomX,randomY,randomRadius,color);

    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static int getInititialSizeOfBlob() {
        return inititialSizeOfBlob;
    }
}
