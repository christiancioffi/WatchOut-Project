package AdministrationServer.Beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class ResponseGetAveragePlayersHRValuesBetweenTimestamps {

    private double average;

    public ResponseGetAveragePlayersHRValuesBetweenTimestamps(){

    }

    public ResponseGetAveragePlayersHRValuesBetweenTimestamps(double average){
        this.average=average;
    }


    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public String toString(){
        return "Average: "+average;
    }

}
