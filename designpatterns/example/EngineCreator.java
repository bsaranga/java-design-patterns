package designpatterns.example;

import designpatterns.example.components.Cylinder;
import designpatterns.example.components.Flywheel;
import designpatterns.example.components.OilReservoir;
import designpatterns.example.components.TimingBelt;

public class EngineCreator {
    
    private Engine engine;

    public EngineCreator(Cylinder[] cylinders, Flywheel flywheel, TimingBelt timingBelt, OilReservoir oilReservoir) {
        
        this.engine = new Engine();

        this.engine.cyclinders = cylinders;
        this.engine.flyWheel = flywheel;
        this.engine.timingBelt = timingBelt;
        this.engine.oilReservoir = oilReservoir;
    }

    public Engine getEngine() {
        return this.engine;
    }
}
