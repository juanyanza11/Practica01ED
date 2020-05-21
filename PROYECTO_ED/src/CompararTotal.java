import java.util.Comparator;

public class CompararTotal implements Comparator<Alumno> {
    @Override
    public int compare(Alumno a1, Alumno a2) {
        if (a1.getTotal() > a2.getTotal()){
            return -1;
        }else if (a1.getTotal() > a2.getTotal()){
            return 0;
        }else{
            return 1;
        }
    }
}
