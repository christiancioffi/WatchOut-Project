package SmartWatch.SensorManagement;

import java.util.ArrayList;

public class AveragesList {

    private ArrayList<Double> averagesList;

    private static AveragesList instance;

    private AveragesList(){
        this.averagesList=new ArrayList<Double>();
    }

    public static synchronized AveragesList getInstance(){
        if(instance==null){
            instance=new AveragesList();
        }
        return instance;
    }

    public synchronized ArrayList<Double> getAndClear() {
        while(averagesList.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ArrayList<Double> list=new ArrayList<Double>(averagesList);
        averagesList.clear();
        return list;
    }

    public synchronized void addAverage(double average){
        averagesList.add(average);
        notify();
    }


}
