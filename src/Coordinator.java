public class Coordinator implements Runnable{
    private String name;
    public static long time = System.currentTimeMillis();

    Coordinator(){
        setName("CoordinatorThread- " + 1);
    }
    @Override
    public void run() {
        callStudents();
        msg("Ok test");
        signalFirstRow();
        msg("calls upon the second row of students");
//        signalSecondRow();
//        signalThirdRow();
//        signalFourthRow();
//        signalFifthRow();


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void callStudents(){
        Object convey = new Object();
        synchronized (convey){
            if (okToCallStudents(convey))
                while (true)
                    try {
                        convey.wait();
                    } catch (InterruptedException e) {
                        msg("is ready to signal the students");
                        continue;
                    }
        }
    }

    public synchronized boolean okToCallStudents(Object convey){
        boolean status;
        synchronized (convey){
            if (Graduation.currentNumStudents != 17){
                status = true;
                Graduation.getWaitingCoordinator().add(convey);
                return status;
            }
            else
            {
                status = false;
            }
        }
       return status;
    }

    public  void signalFirstRow(){

    }

    public synchronized  void signalSecondRow(){}
    public synchronized  void signalThirdRow(){}
    public synchronized  void signalFourthRow(){}
    public synchronized  void signalFifthRow(){}
    public synchronized boolean okToSignal(Object convey){
        return false;
    }



    public void msg(String m) {
        System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
    }

}
