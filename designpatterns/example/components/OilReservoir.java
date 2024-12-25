package designpatterns.example.components;

public class OilReservoir {
    
    // Volume of the oil in litres
    private float volume;

    public OilReservoir(float volume) {
        this.volume = volume;
    }

    public float getVolume() {
        return volume;
    }
}
