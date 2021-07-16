import java.util.Vector;

public class Chairman implements Runnable{
    private String name;
    public static long time = System.currentTimeMillis();



    public Chairman() {
        setName("ChairmanThread- " + 1);
    }



    @Override
    public void run() {
        try {
            startSpeech();
            msg("has started the graduation speech");
            synchronized (Graduation.getWaitingCoordinator().get(0)){
                Graduation.getWaitingCoordinator().get(0).notify();
                Graduation.getWaitingCoordinator().remove(0);
            }

            callStudent();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void startSpeech() throws InterruptedException {
        Object convey = new Object();
        synchronized (convey){
            if(okToSpeech(convey))
                while (true)
                    try {
                        convey.wait();
                        break;
                    } catch (InterruptedException e) {
                        Thread.sleep(1000);
                        msg("acknowledged that the last student has been seated and has started the ceremony.");
                        continue;
                    }
        }
    }
    public void callStudent(){
        Object convey = new Object();
        synchronized (convey){
            while (Graduation.getCurrentRow() != 5){
                if (okToCallStudent(convey))
                    while (true)
                        try {
                            convey.wait();
                        } catch (InterruptedException e) {
                            continue;
                        }
            }


        }
    }
    public synchronized boolean okToCallStudent(Object convey){
        boolean status;
        synchronized (convey){
            if (Graduation.getRow1().size() != 0){
                status = true;
                Graduation.getWaitingChairman().add(convey);
                synchronized (Graduation.getRow1().get(0)){
                    Graduation.getRow1().get(0).notify();
                    Graduation.getRow1().remove(0);
                }
             return status;
            } else {
                status = false;

            }
        }
        return status;
    }

    public synchronized boolean okToSpeech(Object convey){
        boolean status;
        if (Graduation.currentNumStudents != Graduation.NUM_STUDENT){
            status = true;
            msg(" cannot start the speech yet. Waiting for students to be seated.");
            Graduation.getWaitingChairman().add(convey);

            return status;
        } else {
            status = false;

        }
        return status;
    }

    public void msg(String m) {
        System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
    }

}
