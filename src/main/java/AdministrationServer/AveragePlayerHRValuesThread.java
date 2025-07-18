package AdministrationServer;

import SmartWatch.SensorManagement.HRMeasurement;

import java.util.ArrayList;
import java.util.HashMap;

public class AveragePlayerHRValuesThread implements Runnable{

    private final ResultComputationBuffer resultBuffer;

    private final int id;

    private final int n;

    public AveragePlayerHRValuesThread(ResultComputationBuffer resultBuffer, int id, int n){
        this.resultBuffer=resultBuffer;
        this.id=id;
        this.n=n;
    }

    public void run(){
        resultBuffer.setResult(getAveragePlayerHRValues());
    }



    public double getAveragePlayerHRValues(){
        HashMap<Integer, ArrayList<HRMeasurement>> statistics = StatisticsCollection.getInstance().getStatistics();
        ArrayList<HRMeasurement> playerMeasurements=statistics.get(id);
        //System.out.println(playerMeasurements);
        if(playerMeasurements!=null && n>0 && n<=playerMeasurements.size()){
            try{
                ArrayList<HRMeasurement> measurements= new ArrayList<HRMeasurement>(playerMeasurements.subList(playerMeasurements.size()-n,playerMeasurements.size()));
                return calculateMeasurementsAverage(measurements);
            }catch(Exception e){
                //e.printStackTrace();
                System.out.println(e.getMessage());
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
