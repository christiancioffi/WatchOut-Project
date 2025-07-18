package AdministrationServer;

import SmartWatch.SensorManagement.HRMeasurement;
import SmartWatch.Game.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsCollection {

    private HashMap<Integer, ArrayList<HRMeasurement>> statistics;
    private static StatisticsCollection instance;

    private StatisticsCollection(){
        this.statistics=new HashMap<Integer,ArrayList<HRMeasurement>>();
    }

    public static synchronized StatisticsCollection getInstance(){
        if(instance==null){
            instance=new StatisticsCollection();
        }
        return instance;
    }

    public synchronized void addPlayer(Player player){
        statistics.put(player.getId(),new ArrayList<HRMeasurement>());
    }

    public synchronized void addMeasurement(HRMeasurement hrmeasurement){
        ArrayList<HRMeasurement> playerValues=statistics.get(hrmeasurement.getId());
        playerValues.add(hrmeasurement);
    }

    public synchronized HashMap<Integer, ArrayList<HRMeasurement>> getStatistics(){     //Returns a copy of the statistics data structure
        HashMap<Integer, ArrayList<HRMeasurement>> copy = new HashMap<Integer, ArrayList<HRMeasurement>>();
        for (Map.Entry<Integer, ArrayList<HRMeasurement>> entry : this.statistics.entrySet())
        {
            copy.put(entry.getKey(),new ArrayList<HRMeasurement>(entry.getValue()));
        }
        return copy;
    }


}
