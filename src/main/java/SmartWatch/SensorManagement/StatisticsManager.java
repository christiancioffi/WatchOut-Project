package SmartWatch.SensorManagement;

import SmartWatch.GlobalDataStructures.PlayerInfo;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.ArrayList;

public class StatisticsManager implements Runnable{

    private AveragesList averages;
    private int interval=10000;     //10 seconds
    private final String ADMINISTRATION_SERVER_HOST = "localhost";
    private final int ADMINISTRATION_SERVER_PORT = 1337;

    public StatisticsManager(AveragesList averages){
        this.averages=averages;
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(interval);
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
            sendList();
        }
    }

    private void sendList(){
        ArrayList<Double> list=averages.getAndClear();
        String GREEN = "\u001B[32m";
        String RESET = "\u001B[0m";

        int id = PlayerInfo.getInstance().getPlayer().getId();
        long timestamp=System.currentTimeMillis();

        //Uncomment for showing messages with the REST Server
        System.out.println(GREEN+"[Statistic Manager] Sending the following list of averages: "+list+", computed at "+timestamp+RESET);  //This!s

        Client client = Client.create();
        String serverAddress = "http://"+ADMINISTRATION_SERVER_HOST+":"+ADMINISTRATION_SERVER_PORT+"/";
        ClientResponse clientResponse = null;

        String postPath = "heartrate/add";
        try{
            HRMeasurement hrrequest=new HRMeasurement(id,timestamp,list);
            clientResponse = sendHRRequest(client,serverAddress+postPath,hrrequest);
            if(clientResponse.getStatus()!=ClientResponse.Status.OK.getStatusCode()){
                throw new Exception("HeartRate update failed.");
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private ClientResponse sendHRRequest(Client client, String url, HRMeasurement hrreq){
        WebResource webResource = client.resource(url);
        //System.out.println(hrreq);
        String input = new Gson().toJson(hrreq);
        try {
            return webResource.type("application/json").post(ClientResponse.class, input);
        } catch (ClientHandlerException e) {
            System.out.println("Server not available");
            return null;
        }
    }
}
