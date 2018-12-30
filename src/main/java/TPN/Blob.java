package TPN;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

public class Blob implements Controllable{
    private int x,y, radius;
    private PVector location;
    private boolean eaten=false;
    public Blob(int x, int y, int radius){
        this.location = new PVector();
        this.location.add(x,y);
        this.radius=radius;
    }


    public void show(PApplet app, Color color){
        app.fill(color.getRGB());
        app.ellipse(location.x,location.y,radius*2,radius*2);
    }

    public void controll(PVector mouseLocation, int magnitude){
        PVector velocity = new PVector();
        velocity.add(mouseLocation.x-Main.getWidth()/2,mouseLocation.y-Main.getHeight()/2);
        velocity.setMag(magnitude);
        location.add(velocity);
    }

    public void eat(Blob meal) {
        int distance= (int) PApplet.dist(this.location.x,this.location.y,meal.getLocation().x,meal.getLocation().y);
        if(distance<this.radius+meal.getRadius()){
            meal.setEaten(true);
            this.grow(meal);
        }
    }

    public void grow(Blob meal) {
        int sum = (int) (PI *this.radius * this.radius + PI * meal.getRadius()*meal.getRadius());
        this.radius= (int) sqrt(sum/PI);
    }

    public PVector getLocation() {
        return location;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }
}
