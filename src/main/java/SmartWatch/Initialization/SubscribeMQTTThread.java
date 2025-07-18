package SmartWatch.Initialization;

import SmartWatch.GlobalDataStructures.PlayerInfo;
import org.eclipse.paho.client.mqttv3.*;

import java.sql.Timestamp;

public class SubscribeMQTTThread implements Runnable{

    public SubscribeMQTTThread(){

    }
    public void run(){
        subscribe();
    }

    private void subscribe(){
        MqttClient client;
        String broker = "tcp://localhost:1883";
        String clientId = MqttClient.generateClientId();
        String topic = "game/manager";
        int qos = 2;
        try {
            client = new MqttClient(broker, clientId);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);     //Non-Persistent session
            //connOpts.setUserName(username); // optional
            //connOpts.setPassword(password.toCharArray()); // optional
            //connOpts.setWill("this/is/a/topic","will message".getBytes(),1,false);  // optional
            //connOpts.setKeepAliveInterval(60);  // optional

            //Connect the client
            System.out.println(clientId + " Connecting Broker " + broker);
            client.connect(connOpts);
            System.out.println(clientId + " Connected");
            client.setCallback(new MqttCallback() {

                public void messageArrived(String topic, MqttMessage message) {
                    //Called when a message arrives from the broker that matches any subscription made by the client
                    String time = new Timestamp(System.currentTimeMillis()).toString();
                    String receivedMessage = new String(message.getPayload());
                    /*System.out.println(clientId +" Received a Message! - Callback - Thread PID: " + Thread.currentThread().getId() +
                            "\n\tTime:    " + time +
                            "\n\tTopic:   " + topic +
                            "\n\tMessage: " + receivedMessage +
                            "\n\tQoS:     " + message.getQos() + "\n");*/
                    if(receivedMessage.equals("Start")){
                        startGame();
                    }
                    else if(receivedMessage.equals("End")){
                        System.out.println("**************************");
                        System.out.println();
                        System.out.println("[GameManager Message] Game ended!");
                        System.out.println();
                        System.out.println("**************************");
                    }else if(!receivedMessage.isEmpty()){
                        System.out.println("**************************");
                        System.out.println();
                        System.out.println("[GameManager Message] "+receivedMessage);
                        System.out.println();
                        System.out.println("**************************");
                    }
                }

                public void connectionLost(Throwable cause) {
                    System.out.println(clientId + " Connection lost! cause:" + cause.getMessage()+ "-  Thread PID: " + Thread.currentThread().getId());
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    //Not used here
                }

            });

            System.out.println(clientId + " Subscribing ... - Thread PID: " + Thread.currentThread().getId());
            client.subscribe(topic,qos);
            System.out.println(clientId + " Subscribed to topics : " + topic);

            /*Thread mqttThread=new Thread(new MQTTThread(client));
            mqttThread.start();*/

            /*System.out.println("\n ***  Press a random key to exit *** \n");
            Scanner command = new Scanner(System.in);
            command.nextLine();
            client.disconnect();*/


        } catch (MqttException me ) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            //me.printStackTrace();
        }
    }

    private void startGame(){
        System.out.println("**************************");
        System.out.println();
        System.out.println("[GameManager Message] Game started!");
        System.out.println();
        System.out.println("**************************");
        PlayerInfo.getInstance().getPlayer().getStatus().setPlayerReady();
    }

}
