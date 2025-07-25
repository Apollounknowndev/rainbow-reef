package net.thevaliantsquidward.rainbowreef.client.render.state;

public class TailBearerRenderState extends ReefIdleableRenderState {

    public float lastTilt;
    public double[] lastTailYaws;
    public double[] lastTailPitches;

    public float currentTilt;
    public double[] currentTailYaws;
    public double[] currentTailPitches;

    public Runnable returnYaws;
    public Runnable returnPitches;
}
