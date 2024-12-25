package designpatterns.example;

import designpatterns.example.components.Cylinder;
import designpatterns.example.components.Flywheel;
import designpatterns.example.components.OilReservoir;
import designpatterns.example.components.TimingBelt;

public class Main {
    public static void main(String[] args) {
        
        Cylinder[] cylinders = new Cylinder[4];
        cylinders[0] = new Cylinder(1);
        cylinders[1] = new Cylinder(2);
        cylinders[2] = new Cylinder(3);
        cylinders[3] = new Cylinder(4);
        
        Flywheel flywheel = new Flywheel(5.6f);
        TimingBelt timingBelt = new TimingBelt(23.1f);
        OilReservoir oilReservoir = new OilReservoir(56.5f);
        
        EngineCreator engineCreator = new EngineCreator(cylinders, flywheel, timingBelt, oilReservoir);
        
        Engine engine = engineCreator.getEngine();
        
        System.out.println(engine.flyWheel.getRadius());
    }
}
