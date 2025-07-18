package AdministrationServer;

public class ResultComputationBuffer {

    private double result;

    public ResultComputationBuffer(){
        result=-2;
    }

    public synchronized void setResult(double result){
        this.result=result;
        notify();
    }

    public synchronized double getResult(){
        while(result==-2){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
