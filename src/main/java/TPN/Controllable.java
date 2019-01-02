package TPN;

import processing.core.PApplet;
import processing.core.PVector;

public interface Controllable {
     void controll(PVector mouseLocation, int magnitude);
     void eat(Blob meal);
     void grow(Blob meal);
     void name(PApplet app,String name);
}
