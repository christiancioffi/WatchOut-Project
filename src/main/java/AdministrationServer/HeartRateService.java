package AdministrationServer;

import SmartWatch.SensorManagement.HRMeasurement;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("heartrate")
public class HeartRateService {
    @Path("add")
    @POST
    @Consumes({"application/json"})
    public Response addMeasurement(HRMeasurement hrmeasurement) {
        System.out.println("[HeartRate Service] Measurement received: "+hrmeasurement);
        try{
            StatisticsCollection collection=StatisticsCollection.getInstance();
            collection.addMeasurement(hrmeasurement);
            return Response.status(200).build();
        }catch(Exception e){
            e.printStackTrace();
            return Response.status(400).build();        //Bad request
        }
    }
}
