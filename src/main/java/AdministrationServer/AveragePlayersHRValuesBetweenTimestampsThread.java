package AdministrationServer;

import SmartWatch.SensorManagement.HRMeasurement;

import java.util.ArrayList;
import java.util.HashMap;

public class AveragePlayersHRValuesBetweenTimestampsThread implements Runnable{
    private final ResultComputationBuffer resultBuffer;

    private final long t1;

    private final long t2;

    public AveragePlayersHRValuesBetweenTimestampsThread(ResultComputationBuffer resultBuffer, long t1, long t2){
        this.resultBuffer=resultBuffer;
        this.t1=t1;
        this.t2=t2;
    }


    public void run(){
        resultBuffer.setResult(getAveragePlayersHRValuesBetweenTimestamps());
    }


    public double getAveragePlayersHRValuesBetweenTimestamps(){
        HashMap<Integer, ArrayList<HRMeasurement>> statistics = StatisticsCollection.getInstance().getStatistics();
        ArrayList<HRMeasurement> measurements=new ArrayList<HRMeasurement>();
        if(t1>0 && t2>t1){
            for(Integer id : statistics.keySet()){
                ArrayList<HRMeasurement> playerValues=statistics.get(id);
                for(HRMeasurement measurement: playerValues){
                    long timestamp=measurement.getTimestamp();
                    if(timestamp>=t1 && timestamp<=t2){
                        measurements.add(measurement);
                    }
                }
            }
            if(!measurements.isEmpty()){
                return calculateMeasurementsAverage(measurements);
            }
        }
        return -1;

    }

    private double calculateMeasurementsAverage(ArrayList<HRMeasurement> measurements){
        double average=0;
        double sum=0;
        double tot=0;
        for(int i=0;i<measurements.size();i++){
            HRMeasurement measurement=measurements.get(i);
            ArrayList<Double> averages=measurement.getAverages();
            for(int j=0;j<averages.size();j++){
                sum+=averages.get(j);
            }
            tot+=averages.size();
        }
        average=sum/tot;

        return average;
    }
}
