package com.company;

public class PitchModel {
    private double timeStamp;
    private float pitch;
    private float probability ;

    public PitchModel(double timeStamp, float pitch, float probability) {
        this.timeStamp=timeStamp;
        this.pitch=pitch;
        this.probability=probability;
    }

    public double getTimeStamp() {
        return timeStamp;
    }

    public float getPitch() {
        return pitch;
    }

    public float getProbability() {
        return probability;
    }

    @Override
    public String toString() {
        return "Timestamp: "+getTimeStamp()+" Pitch: "+getPitch()+ " Probability: "+getProbability();
    }
}
