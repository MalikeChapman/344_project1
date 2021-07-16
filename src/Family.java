public class Family implements Runnable{
    private String name;
    private Student student;

    Family(Student student, int id){
        this.student = student;
        setName(student.getName() + id);

    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public void run() {

    }
}
