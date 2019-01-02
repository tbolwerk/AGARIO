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
    private Color color;
    public Blob(int x, int y, int radius, Color color){
        this.location = new PVector();
        this.location.add(x,y);
        this.radius=radius;
        this.color=color;
    }


    public void show(PApplet app, Color color){
        app.stroke(255);
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

    public Blob divide(PVector mouseLocation){
        this.radius=radius/2;
        return new Blob((int)mouseLocation.x,(int)mouseLocation.y,this.radius/2,this.color);
    }

    public void moveToCenter(PApplet app,Blob parent, Blob child, int magnitude){
        PVector velocity = new PVector();
        int distance= (int) PApplet.dist(this.location.x,this.location.y,child.getLocation().x,child.getLocation().y);
        if(distance>this.radius+child.getRadius()) {


            velocity.add(parent.location.x - Main.getWidth() / 2, parent.location.y - Main.getHeight() / 2);
            velocity.setMag(magnitude);

            if (child.location != parent.location) {
                child.location.add(velocity);
            }
        }
    }

    public void grow(Blob meal) {
        int sum = (int) (PI *this.radius * this.radius + PI * meal.getRadius()*meal.getRadius());
        this.radius= (int) sqrt(sum/PI);
    }

    public void name(PApplet app,String name) {
        app.fill(255);
        app.textSize(this.radius/2);
        app.text(name,this.location.x-this.radius,this.location.y);
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

    public Color getColor() {
        return color;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
