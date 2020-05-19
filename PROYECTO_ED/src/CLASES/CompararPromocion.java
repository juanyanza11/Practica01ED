package CLASES;

public class CompararPromocion implements Comparable<Alumno>{
    @Override
    public int compareTo(Alumno a) {
        Alumno a1 = new Alumno();
        return a1.getPromocion().compareTo(a.getPromocion());
    }
}
