package AdministrationServer;

import SmartWatch.Game.Player;
import SmartWatch.Game.Position;
import AdministrationServer.Beans.ResponseAddPlayer;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("player")
public class PlayerService {

    @Path("add")
    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response addPlayer(Player player) {
        System.out.println("Player arrived! "+player);
        Lobby lobby = Lobby.getInstance();
        Position startPosition = lobby.addPlayer(player);
        if (startPosition!=null) {
            System.out.println("[Player Service] Player added! "+player);
            return Response.ok(new ResponseAddPlayer(lobby,player)).build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).build();        //Bad request
        }
    }

}
