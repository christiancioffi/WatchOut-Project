package SmartWatch.Initialization;

import SmartWatch.Game.Player;
import AdministrationServer.Beans.ResponseAddPlayer;
import SmartWatch.GlobalDataStructures.GameSession;
import SmartWatch.GlobalDataStructures.PlayerInfo;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RegistrationToServerThread implements Runnable{
    private static final String ADMINISTRATION_SERVER_HOST = "localhost";
    private static final int ADMINISTRATION_SERVER_PORT = 1337;

    private final String HOST = "localhost";
    private int PORT;

    private PlayerInfo info;
    private GameSession gameSession;

    public RegistrationToServerThread(){
        this.info=PlayerInfo.getInstance();
        this.gameSession = GameSession.getInstance();
    }


    public void run(){

        while(!registerToRESTServer());

    }

    private boolean registerToRESTServer(){
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        Client client = Client.create();
        String serverAddress = "http://"+ADMINISTRATION_SERVER_HOST+":"+ADMINISTRATION_SERVER_PORT+"/";
        ClientResponse clientResponse = null;

        String postPath = "player/add";
        try{
            System.out.print("Player ID: ");
            int id=Integer.parseInt(inFromUser.readLine());
            System.out.print("Player PORT (Max: 65535): ");
            PORT=Integer.parseInt(inFromUser.readLine());
            /*if(PORT>65535){
                throw new Exception("PORT not accepted.");
            }*/
            Player player = new Player(id,HOST,PORT);
            clientResponse = initializationRequest(client,serverAddress+postPath,player);
            if(clientResponse==null || clientResponse.getStatus()!=ClientResponse.Status.OK.getStatusCode()){
                //System.out.println(clientResponse.getStatus());
                throw new Exception("Registration not fulfilled.");
            }
            ResponseAddPlayer response=new Gson().fromJson(clientResponse.getEntity(String.class),ResponseAddPlayer.class);
            System.out.println(response);
            player.setInitialPosition(response.getStartPosition());
            info.setPlayer(player);
            ArrayList<Player> peers=response.getPlayers();
            for(Player p: peers){
                gameSession.addOtherPlayer(p);
            }
            return true;
        }
        catch(IOException ioe){
            //ioe.printStackTrace();
            System.out.println(ioe.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
        return false;
    }

    private ClientResponse initializationRequest(Client client, String url, Player p){
        WebResource webResource = client.resource(url);
        String input = new Gson().toJson(p);
        try {
            return webResource.type("application/json").post(ClientResponse.class, input);
        } catch (ClientHandlerException e) {
            System.out.println("Server not available");
            return null;
        }
    }
}
