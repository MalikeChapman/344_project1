import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student implements Runnable{
    private String name;
    public static long time = System.currentTimeMillis();
    private int family;
    private List<Family> familyList;


    Student(int id){
        setName("StudentThread-" + id);
        Random rand = new Random();
        int upperbound = 3;
        setFamily(rand.nextInt(upperbound));
        setFamilyList();
        msg("has just arrived");
    }
    @Override
    public void run() {
        try {
            startCeromony();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    public void setFamilyList() {
        if (family != 0){
             familyList = new ArrayList<>(family);
            for (int i = 0; i < family; i++){
                Family family = new Family(this, i);
                familyList.add(family);
            }
        }
    }

    public void startCeromony() throws InterruptedException {
        Object convey = new Object();
        synchronized (convey){
            if (cannotWalk(convey))
                while (true)
                    try {
                        convey.wait();
                        break;
                    } catch (InterruptedException e) {
                        continue;
                    }

        }
    }

    public synchronized boolean cannotWalk(Object convey) throws InterruptedException {
        boolean status;
        synchronized (convey){
            Graduation.currentNumStudents++;
            System.out.println(Graduation.currentNumStudents + "Current number of students");

            if (Graduation.currentNumStudents != 17){
            status = false;
            if (Graduation.getRow1().size() != 4){
                Graduation.getRow1().add(convey);
                msg("was seated in row 1 seat " + Graduation.getRow1().size());
            }
            else if (Graduation.getRow2().size() != 4){
                Graduation.getRow2().add(convey);
                msg("was seated in row 2 seat " + Graduation.getRow2().size());

            } else if (Graduation.getRow3().size() != 4){
                Graduation.getRow3().add(convey);
                msg("was seated in row 3 seat " + Graduation.getRow3().size());
            } else if (Graduation.getRow4().size() != 4){
                Graduation.getRow4().add(convey);
                msg("was seated in row 4 seat " + Graduation.getRow4().size());
            }
            return status;
        }
        else {

                Graduation.getRow5().add(convey);
                msg("was seated in row 5 seat " + Graduation.getRow5().size());
                System.out.println(this.getName() + "is the last student");


                synchronized (Graduation.getWaitingChairman().get(0)){
                    Graduation.getWaitingChairman().get(0).notify();
                    Graduation.getWaitingChairman().remove(0);
                }
                status = true;
        }}
        return status;
    }

    public void msg(String m) {
        System.out.println("["+(System.currentTimeMillis()-time)+"] "+getName()+": "+m);
    }

}
