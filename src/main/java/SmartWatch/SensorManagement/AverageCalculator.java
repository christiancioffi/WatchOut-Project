package SmartWatch.SensorManagement;

import SmartWatch.Sensor.Measurement;

import java.util.ArrayList;

public class AverageCalculator implements Runnable{

    private BufferImpl buffer;
    private AveragesList averages;

    public AverageCalculator(BufferImpl buffer, AveragesList averages){
        this.buffer=buffer;
        this.averages=averages;
    }

    public void run(){
        while(true){
            calculateAverage();
        }
    }

    private void calculateAverage(){
        ArrayList<Measurement> window;
        double average=0;
        window=new ArrayList<Measurement>(buffer.readAllAndClean());
        //System.out.println(window);
        for(int i=0;i<window.size();i++){
            average+=window.get(i).getValue();
        }
        average=average/window.size();
        averages.addAverage(average);
        //System.out.println("[Average Calculator] Average added to the list of averages: "+average);
    }
}
