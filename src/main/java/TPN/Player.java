package TPN;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player {
    private int x,y;
    private Blob startingBlob;
    private String name;
    private PApplet app;
    private float zoom;
    private CopyOnWriteArrayList<Blob> playerBlobs;
    private int magnitude =3;
    private boolean isDivided=false;

    public Player(PApplet app,int x, int y, String name){
        this.app=app;
        this.x=x;
        this.y=y;
        this.name=name;
        this.playerBlobs = new CopyOnWriteArrayList<Blob>();
    }
    public void setup(){
        this.startingBlob = new Blob(x,y,Main.getInititialSizeOfBlob(), Color.GRAY);
        playerBlobs.add(this.startingBlob);
        this.zoom = 1;
    }
    public void draw(){
        PVector mouseLocation = new PVector(app.mouseX,app.mouseY);
        app.translate(app.width/2,app.height/2);
        float newZoom = (float)Main.getInititialSizeOfBlob()/startingBlob.getRadius();
        zoom = app.lerp(zoom,newZoom,0.1f);
        app.scale(zoom);
        app.translate(-startingBlob.getLocation().x, -startingBlob.getLocation().y);

        for (Blob blob:playerBlobs) {
            blob.show(app,Color.GREEN);
            blob.controll(mouseLocation,magnitude);
            blob.name(app,name);


            }
        }

    public void keyReleased() {
        PVector mouseLocation = new PVector(app.mouseX,app.mouseY);
        for (Blob blob : playerBlobs) {
            if (!isDivided) {
                isDivided = true;
                playerBlobs.add(blob.divide(mouseLocation));
                if (!blob.equals(startingBlob)) {
                    blob.moveToCenter(app, startingBlob, blob, magnitude);
                }
            } else if (isDivided) {
                isDivided = false;
                if (!blob.equals(startingBlob)) {
                    startingBlob.setRadius(startingBlob.getRadius() * 2);
                    playerBlobs.remove(blob);
                }
            }
        }
    }

    public Blob getStartingBlob() {
        return startingBlob;
    }
}
