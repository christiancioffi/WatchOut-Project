package AdministrationServer.Beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class ResponseGetAveragePlayerHRValues {

    private double average;

    public ResponseGetAveragePlayerHRValues(){

    }

    public ResponseGetAveragePlayerHRValues(double average){
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
