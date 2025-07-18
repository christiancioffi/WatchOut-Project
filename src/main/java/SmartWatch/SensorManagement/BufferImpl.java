package SmartWatch.SensorManagement;
import SmartWatch.Sensor.Buffer;
import SmartWatch.Sensor.Measurement;

import java.util.ArrayList;
import java.util.List;

public class BufferImpl implements Buffer{

    private ArrayList<Measurement> buffer;

    private int windowLength=8;
    private double overlappingFactor=0.5;       //50/100

    private static BufferImpl instance;

    private BufferImpl(){
        buffer=new ArrayList<Measurement>();
    }

    public static synchronized BufferImpl getInstance(){
        if(instance==null){
            instance=new BufferImpl();
        }
        return instance;
    }

    public synchronized void addMeasurement(Measurement m){
        while(buffer.size()==windowLength){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer.add(m);
        notify();
    }

    public synchronized List<Measurement> readAllAndClean(){
        //System.out.println(buffer.size());
        while(buffer.size()<windowLength){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //System.out.println("ThreadId: "+Thread.currentThread().getId()+", Buffer: "+buffer);
        ArrayList<Measurement> window= new ArrayList<Measurement>(buffer.subList(0,windowLength));
        //System.out.println(buffer.subList(0,windowLength));
        int i=0;
        //System.out.println(windowLength+"*"+overlappingFactor+"="+windowLength*overlappingFactor);
        while(i<windowLength*overlappingFactor){
            buffer.remove(0);
            i++;
        }
        /*System.out.println("ThreadId: "+Thread.currentThread().getId()+", Buffer: "+buffer);
        System.out.println("ThreadId: "+Thread.currentThread().getId()+", Window: "+window);
        //try{Thread.sleep(5000);}catch(Exception e){}*/
        notify();
        return window;
    }
}
