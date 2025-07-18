package AdministrationClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import AdministrationServer.Beans.ResponseGetAllPlayers;
import AdministrationServer.Beans.ResponseGetAveragePlayerHRValues;
import AdministrationServer.Beans.ResponseGetAveragePlayersHRValuesBetweenTimestamps;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class AdminClient {

    private static final String ADMINISTRATION_SERVER_HOST = "localhost";
    private static final int ADMINISTRATION_SERVER_PORT = 1337;

    private static MqttClient client;

    public static void main(String[] args){
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));

        connectToBroker();

        while(true){
            System.out.println("*****************************************************************");
            System.out.println("Available services: \n"+
                    "- Connect to the Broker (already done if the client did not disconnected previously) (0)\n"+
                    "- List of the players currently in the game (1)\n"+
                    "- Average of the last n heart rate values sent to the server by a given player (2)\n" +
                    "- Average of the heart rate values sent by all the Players to the server " +
                    "that occurred between timestamp t1 and timestamp t2 (3)\n"+
                    "- Send a message to the players (4)\n"+
                    "- Disconnect from the Broker (5)");
            System.out.print("Desired Service: ");
            try{
                int serviceId=Integer.parseInt(inFromUser.readLine());
                switch(serviceId){
                    case 0:
                        connectToBroker();
                        break;
                    case 1:
                        getListOfAllPlayers();
                        break;
                    case 2:
                        getAverageOfPlayer();
                        break;
                    case 3:
                        getAverageOfPlayersInInterval();
                        break;
                    case 4:
                        if(client!=null){
                            sendMessageToPlayers();
                        }else{
                            System.out.println("You have to connect to the Broker first (choice n. 0)!");
                        }
                        break;
                    case 5:
                        disconnectFromBroker();
                        break;
                    default:
                        System.out.println("Service unavailable");
                        break;
                }

            }catch(IOException ioe){
                System.out.println(ioe.getMessage());
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("*****************************************************************");
        }

    }

    private static void connectToBroker(){
        MqttClient client;
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        try {
            client = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);     //true=non-Persistent session
            //connOpts.setUserName(username); // optional
            //connOpts.setPassword(password.toCharArray()); // optional
            //connOpts.setWill("game/manager","End".getBytes(),1,false);
            //connOpts.setKeepAliveInterval(60);  // optional

            //Connect the client
            System.out.println(clientId + " Connecting Broker " + broker);
            client.connect(connOpts);
            System.out.println(clientId + " Connected");

            AdminClient.client=client;



        } catch (MqttException me ) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            //me.printStackTrace();
        }

    }

    private static void disconnectFromBroker(){
        try{
            if (client.isConnected()){
                client.disconnect();
            }
            System.out.println("Publisher " + client.getClientId() + " disconnected");
        }catch (MqttException me ) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            //me.printStackTrace();
        }
        client=null;

    }

    private static void getListOfAllPlayers(){
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        Client client = Client.create();
        String serverAddress = "http://"+ADMINISTRATION_SERVER_HOST+":"+ADMINISTRATION_SERVER_PORT+"/";
        ClientResponse clientResponse = null;

        String getPath = "statistics/players";
        try{
            clientResponse = getRequest(client,serverAddress+getPath);
            if(clientResponse==null || clientResponse.getStatus()!=ClientResponse.Status.OK.getStatusCode()){
                throw new Exception("Request not fulfilled.");
            }
            ResponseGetAllPlayers response=new Gson().fromJson(clientResponse.getEntity(String.class),ResponseGetAllPlayers.class);
            System.out.println();
            System.out.println(response);
            System.out.println();
        }catch(Exception e){
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }

    }

    private static void getAverageOfPlayer(){
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        Client client = Client.create();
        String serverAddress = "http://"+ADMINISTRATION_SERVER_HOST+":"+ADMINISTRATION_SERVER_PORT+"/";
        ClientResponse clientResponse = null;

        String postPath = "statistics/playerAverage";
        try{
            System.out.print("Player ID: ");
            String id=inFromUser.readLine();
            System.out.print("Number of averages: ");
            String num=inFromUser.readLine();
            clientResponse = getRequest(client,serverAddress+postPath+"/"+id+"/"+num);
            if(clientResponse==null || clientResponse.getStatus()!=ClientResponse.Status.OK.getStatusCode()){
                throw new Exception("Request not fulfilled.");
            }
            ResponseGetAveragePlayerHRValues response=new Gson().fromJson(clientResponse.getEntity(String.class),ResponseGetAveragePlayerHRValues.class);
            System.out.println();
            System.out.println(response);
            System.out.println();
        }
        catch(IOException ioe){
            //ioe.printStackTrace();
            System.out.println(ioe.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }

    }

    private static void getAverageOfPlayersInInterval(){
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        Client client = Client.create();
        String serverAddress = "http://"+ADMINISTRATION_SERVER_HOST+":"+ADMINISTRATION_SERVER_PORT+"/";
        ClientResponse clientResponse = null;

        String postPath = "statistics/globalAverage";
        try{
            System.out.print("Timestamp 1: ");
            String t1=inFromUser.readLine();
            System.out.print("Timestamp 2: ");
            String t2=inFromUser.readLine();
            clientResponse = getRequest(client,serverAddress+postPath+"/"+t1+"/"+t2);
            if(clientResponse==null || clientResponse.getStatus()!=ClientResponse.Status.OK.getStatusCode()){
                throw new Exception("Request not fulfilled.");
            }
            ResponseGetAveragePlayersHRValuesBetweenTimestamps response=new Gson().fromJson(clientResponse.getEntity(String.class),ResponseGetAveragePlayersHRValuesBetweenTimestamps.class);
            System.out.println();
            System.out.println(response);
            System.out.println();
        }
        catch(IOException ioe){
            //ioe.printStackTrace();
            System.out.println(ioe.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }

    }

    private static void sendMessageToPlayers(){

        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));

        String message="";

        System.out.println("What do you want to send? \n"+
                "- Start of the game (1)\n"+
                "- End the game (2)\n"+
                "- Other (3)");

        System.out.print("Desired message: ");

        try{
            int serviceId=Integer.parseInt(inFromUser.readLine());
            switch(serviceId){
                case 1:
                    message="Start";
                    startGame(message);
                    break;
                case 2:
                    message="End";
                    endGame(message);
                    break;
                case 3:
                    System.out.print("Write a message: ");
                    message=inFromUser.readLine();
                    sendArbitraryMessage(message);
                    break;
                default:
                    System.out.println("Message not available.");
                    break;
            }
        }catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }


    }

    private static ClientResponse getRequest(Client client, String url){
        WebResource webResource = client.resource(url);
        try {
            return webResource.type("application/json").get(ClientResponse.class);
        } catch (ClientHandlerException e) {
            System.out.println("Server not available");
            return null;
        }
    }

    private static void startGame(String message){
        String topic = "game/manager";
        int qos = 2;
        try{
            MqttMessage mqttmessage = new MqttMessage(message.getBytes());

            //Set the QoS on the Message
            mqttmessage.setQos(qos);
            mqttmessage.setRetained(true);          //Any player that will join will automatically start playing.
            String clientId=client.getClientId();
            System.out.println(clientId + " Publishing message: " + message + " ...");
            client.publish(topic, mqttmessage);
            System.out.println(clientId + " Message published");
        }catch (MqttException me ) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            //me.printStackTrace();
        }

    }

    private static void endGame(String message){
        String topic = "game/manager";
        int qos = 2;
        try{
            MqttMessage mqttmessage = new MqttMessage(new byte[0]);

            //Set the QoS on the Message
            mqttmessage.setQos(qos);
            mqttmessage.setRetained(true);          //Delete the start game retained message.
            String clientId=client.getClientId();
            System.out.println(clientId + " Publishing message: " + message + " ...");
            client.publish(topic, mqttmessage);
            System.out.println(clientId + " Message published");

            mqttmessage = new MqttMessage(message.getBytes());

            //Set the QoS on the Message
            mqttmessage.setQos(qos);
            clientId=client.getClientId();
            System.out.println(clientId + " Publishing message: " + message + " ...");
            client.publish(topic, mqttmessage);
            System.out.println(clientId + " Message published");
        }catch (MqttException me ) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            //me.printStackTrace();
        }
    }

    private static void sendArbitraryMessage(String message){
        String topic = "game/manager";
        int qos = 2;
        try{
            MqttMessage mqttmessage = new MqttMessage(message.getBytes());

            //Set the QoS on the Message
            mqttmessage.setQos(qos);
            String clientId=client.getClientId();
            System.out.println(clientId + " Publishing message: " + message + " ...");
            client.publish(topic, mqttmessage);
            System.out.println(clientId + " Message published");
        }catch (MqttException me ) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            //me.printStackTrace();
        }
    }


}
