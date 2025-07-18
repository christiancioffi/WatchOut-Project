package SmartWatch.SensorManagement;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


@XmlRootElement(name="response")
public class HRMeasurement {
    private int id;
    private long timestamp;

    private ArrayList<Double> averages;
    public HRMeasurement(){

    }
    public HRMeasurement(int id, long timestamp, ArrayList<Double> averages){
        this.id=id;
        this.timestamp=timestamp;
        this.averages=averages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<Double> getAverages() {
        return averages;
    }

    public void setAverages(ArrayList<Double> averages) {
        this.averages = averages;
    }

    @Override
    public String toString(){
        return "{ Player ID: "+Integer.toString(id)+", Timestamp: "+Long.toString(timestamp)+", Averages: "+averages+" }";
    }

}
