package CLASES;
/**
 * Alumno implementa comparable para ordenar conforme al nombre
 * **/
public class Alumno implements Comparable<Alumno>{

    private String nombreEst;
    private double for1;
    private double cha1;
    private double vid1;
    private double tra1;
    private double pre1;
    private double for2;
    private double cha2;
    private double vid2;
    private double tra2;
    private double pre2;
    private double fin1;
    private double fin2;
    private double total;
    private String alerta;
    private String promocion;
    /**
     * Constructor Alumno para crear un objeto de tipo Alumno
     * **/
    public Alumno(String nombreEst, double for1, double cha1, double vid1, double tra1, double pre1, double for2,
                  double cha2, double vid2, double tra2, double pre2, double fin1, double fin2,
                  double total, String alerta, String promocion) {

        this.nombreEst = nombreEst;
        this.for1 = for1;
        this.cha1 = cha1;
        this.vid1 = vid1;
        this.tra1 = tra1;
        this.pre1 = pre1;
        this.for2 = for2;
        this.cha2 = cha2;
        this.vid2 = vid2;
        this.tra2 = tra2;
        this.pre2 = pre2;
        this.fin1 = fin1;
        this.fin2 = fin2;
        this.setTotal(total);
        this.setAlerta(alerta);
        this.setPromocion(promocion);
    }

    public Alumno() {

    }


    public String getNombreEst() {
        return nombreEst;
    }

    public void setNombreEst(String nombreEst) {
        this.nombreEst = nombreEst;
    }

    public double getFor1() {
        return for1;
    }

    public void setFor1(double for1) {
        this.for1 = for1;
    }

    public double getCha1() {
        return cha1;
    }

    public void setCha1(double cha1) {
        this.cha1 = cha1;
    }

    public double getVid1() {
        return vid1;
    }

    public void setVid1(double vid1) {
        this.vid1 = vid1;
    }

    public double getTra1() {
        return tra1;
    }

    public void setTra1(double tra1) {
        this.tra1 = tra1;
    }

    public double getPre1() {
        return pre1;
    }

    public void setPre1(double pre1) {
        this.pre1 = pre1;
    }

    public double getFor2() {
        return for2;
    }

    public void setFor2(double for2) {
        this.for2 = for2;
    }

    public double getCha2() {
        return cha2;
    }

    public void setCha2(double cha2) {
        this.cha2 = cha2;
    }

    public double getVid2() {
        return vid2;
    }

    public void setVid2(double vid2) {
        this.vid2 = vid2;
    }

    public double getTra2() {
        return tra2;
    }

    public void setTra2(double tra2) {
        this.tra2 = tra2;
    }

    public double getPre2() {
        return pre2;
    }

    public void setPre2(double pre2) {
        this.pre2 = pre2;
    }

    public double getFin1() {
        return fin1;
    }

    public void setFin1(double fin1) {
        this.fin1 = fin1;
    }

    public double getFin2() {
        return fin2;
    }

    public void setFin2(double fin2) {
        this.fin2 = fin2;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    @Override
    public int compareTo(Alumno a) {
        return nombreEst.compareTo(a.getNombreEst());
    }

}