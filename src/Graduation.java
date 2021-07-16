import java.util.Formattable;
import java.util.Random;
import java.util.Vector;

public class Graduation {
    public static final int NUM_STUDENT = 17;
    private static int currentRow = 0;
    public static final int rowCapacity = 4;
    public static final int  numRow = 10;
    public static int currentNumStudents = 0;
    private static Vector<Object> row1 = new Vector<>();
    private static Vector<Object> row2 = new Vector<>();
    private static Vector<Object> row3 = new Vector<>();
    private static Vector<Object> row4 = new Vector<>();
    private static Vector<Object> row5 = new Vector<>();
    private static Vector<Object> row6 = new Vector<>();
    private static Vector<Object> row7 = new Vector<>();
    private static Vector<Object> row8 = new Vector<>();
    private static Vector<Object> row9 = new Vector<>();
    private static Vector<Object> row10 = new Vector<>();
    private static Vector<Family> hallway = new Vector<>();
    private static Vector<Object> waitingChairman = new Vector<>();
    private static Vector<Object> waitingCoordinator = new Vector<>();

    public static Vector<Object> getRow6() {
        return row6;
    }

    public static Vector<Object> getRow7() {
        return row7;
    }

    public static Vector<Object> getRow8() {
        return row8;
    }

    public static Vector<Object> getRow9() {
        return row9;
    }

    public static Vector<Object> getRow10() {
        return row10;
    }

    public static Vector<Object> getWaitingCoordinator() {
        return waitingCoordinator;
    }

    public static Vector<Object> getWaitingChairman() {
        return waitingChairman;
    }




    public static Vector<Object> getRow1() {
        return row1;
    }

    public static Vector<Object> getRow2() {
        return row2;
    }

    public static Vector<Object> getRow3() {
        return row3;
    }

    public static Vector<Object> getRow4() {
        return row4;
    }

    public static Vector<Object> getRow5() {
        return row5;
    }

    public static Vector<Family> getHallway() {
        return hallway;
    }
    public static int getCurrentRow() {
        return currentRow;
    }

    public static void setCurrentRow(int currentRow) {
        Graduation.currentRow = currentRow;
    }

    public static void main(String[] args) {
        Graduation graduation = new Graduation();
        Coordinator coordinator = new Coordinator();
        Chairman chairman = new Chairman();
        Thread t1 = new Thread(chairman);
        t1.start();
        Thread t2 = new Thread(coordinator);
        t2.start();
        Thread t3;
        for (int i = 0; i < NUM_STUDENT; i++){
            Student student = new Student(i);
            t3 = new Thread(student);
            t3.start();
        }

    }
}
