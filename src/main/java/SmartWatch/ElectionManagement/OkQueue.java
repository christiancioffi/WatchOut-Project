package SmartWatch.ElectionManagement;

public class OkQueue {
    private boolean okReceived;
    private int totOk;

    private int okCounter;

    public OkQueue(int totOk){
        this.totOk=totOk;
    }

    public synchronized void okMessageReceived(){
        this.okCounter++;
        this.okReceived=true;
        notify();
    }

    public synchronized void okMessageNotReceived(){
        this.okCounter++;
        notify();
    }

    public synchronized boolean waitOks(){
        while(!okReceived && okCounter<totOk){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return okReceived;
    }

}
