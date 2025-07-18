package AdministrationServer;

import AdministrationServer.Beans.ResponseGetAllPlayers;
import AdministrationServer.Beans.ResponseGetAveragePlayerHRValues;
import AdministrationServer.Beans.ResponseGetAveragePlayersHRValuesBetweenTimestamps;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("statistics")
public class AdministrationClientService {
    @Path("players")
    @GET
    @Produces({"application/json"})
    public Response getAllPlayers() {
        System.out.println("[AdminClient Service] Request for retrieving all the players.");
        try{
            Lobby lobby = Lobby.getInstance();
            return Response.ok(new ResponseGetAllPlayers(lobby)).build();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return Response.status(400).build();        //Bad request
        }
    }

    @Path("playerAverage/{idPlayer}/{number}")
    @GET
    @Produces({"application/json"})
    public Response getAllPlayers(@PathParam("idPlayer") int idPlayer,@PathParam("number") int n) {
        System.out.println("[AdminClient Service] Request for retrieving "+n+" averages for player "+idPlayer);
        try{
            ResultComputationBuffer resultBuffer=new ResultComputationBuffer();
            Thread computationThread=new Thread(new AveragePlayerHRValuesThread(resultBuffer, idPlayer, n));
            computationThread.start();
            double average=resultBuffer.getResult();
            if(average!=-1){
                return Response.ok(new ResponseGetAveragePlayerHRValues(average)).build();
            }else{
                throw new Exception("[AdminClient Service] Input error.");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return Response.status(400).build();        //Bad request
        }
    }

    @Path("globalAverage/{timestamp1}/{timestamp2}")
    @GET
    @Produces({"application/json"})
    public Response getAllPlayers(@PathParam("timestamp1") long timestamp1,@PathParam("timestamp2") long timestamp2) {
        System.out.println("[AdminClient Service] Request for retrieving the averages for all players between "+timestamp1+" and "+timestamp2);
        try{
            ResultComputationBuffer resultBuffer=new ResultComputationBuffer();
            Thread computationThread=new Thread(new AveragePlayersHRValuesBetweenTimestampsThread(resultBuffer, timestamp1,timestamp2));
            computationThread.start();
            double average=resultBuffer.getResult();
            if(average!=-1){
                return Response.ok(new ResponseGetAveragePlayersHRValuesBetweenTimestamps(average)).build();
            }
            else{
                throw new Exception("[AdminClient Service] Input error.");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return Response.status(400).build();        //Bad request
        }
    }



}
