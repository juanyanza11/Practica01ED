
import java.io.*;
import java.util.*;

public class EjecutaAlumnoReporte {
    /*
    Para generar el reporte en .csv se quemó datos obtenidos de datosabiertos para no ingresar nada por teclado
    DATOS NOMBRES Y APELLIDOS Recuperados de http://catalogo.datosabiertos.gob.ec/dataset/direcctorio-de-la-institucion*/
    static String paraCsv = "";
    public static void main(String[] args) {
        String linea = "";
        Scanner entrada = new Scanner(System.in);
        String nombresAp = "nombresApellidos.csv";
        File archivo1 = new File("nombresApellidos.csv");
        FileReader archivoLectura;
        String[] nombres = new String[119];

        // LECTURA DE NOMBRES Y APELLIDOS
        try {
            archivoLectura = new FileReader(archivo1);
            BufferedReader buffer = new BufferedReader(archivoLectura);
            int i = 0;
            while (buffer.ready()){
                if (!(linea = buffer.readLine()).equals("\000")){
                    nombres[i] = linea;
                    i++;
                }
                //System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String alerta = "";
        String promocion = "";
        String paraCsv = "";

        // Separación por pipes debido a existencia de datos reales
        String indices = "NOMBRE|FOR1|CHA1|VID1|TRA1|PRE1|FOR2|CHA2" +
                "|VID2|TRA2|PRE2|FIN1|FIN2|TOTAL|ALERTA|PROMOCIÓN";


        Alumno[] alumno = new Alumno[nombres.length];

        int totalApr = 0;
        int totalRep = 0;
        int totalRendir1 = 0;
        int totalRendir2 = 0;
        int totalRendir12 = 0;
        int totalNorinde = 0;
        int totalFaltaTrabajo = 0;

        for (int i = 0; i < alumno.length; i++) {
            String nombreEst = "";
            nombreEst = nombres[i];

            // CALIFICACIONES GENERADAS ALEATORIAMENTE
            double for1, cha1, vid1, tra1, pre1, for2, cha2, vid2, tra2, pre2;
            for1 = Math.random()*1; // foro
            cha1 = Math.random()*1; // chat
            vid1 = Math.random()*1; // videocolaboracion
            tra1 = Math.random()*6; //
            pre1 = Math.random()*14; // presencial1
            for2 = Math.random()*1; // foro
            cha2 = Math.random()*1; // chat
            vid2 = Math.random()*1; // videocolaboracion
            tra2 = Math.random()*6;
            pre2= Math.random()*14; // presencial2
            double fin2 = 0; // evf2
            double fin1 = 0; // evf1

            fin1 = for1 + cha1 + vid1 + tra1 + pre1;
            fin2 = for2 + cha2 + vid2 +tra2 +pre2;

            if (pre1 < 8){
                alerta = "Rendir Final 1";
                totalRendir1++;
            }else{
                if (pre2 < 8){
                    alerta = "Rendir Final 2";
                    totalRendir2++;
                }else{
                    if (pre1 < 8 && pre2 < 8){
                        alerta = "Rendir Final 1 y 2";
                        totalRendir12++;
                    }else{
                        alerta = " ";
                        totalNorinde++;
                    }
                }
            }
            double total = 0;
            total = fin1 + fin2;

            promocion = (total >= 28)? "APROBADO": "REPROBADO";

            if (total >= 28){
                totalApr++;
            }else{
                totalRep++;
            }

            if (total < 28){
                totalFaltaTrabajo++;
            }

            // CREACIÓN DEL OBJETO ALUMNO CON TODOS SUS PARÁMETROS
            alumno[i] = new Alumno(nombreEst,for1,cha1,vid1,tra1,pre1,for2,cha2,vid2,tra2,pre2,fin1,
                    fin2,total, alerta,promocion);
        }
        double porcAp = (totalApr*100)/nombres.length;
        double porcRep = (totalRep*100)/nombres.length;
        double porcRen1 = (totalRendir1*100)/nombres.length;
        double porcRen2 = (totalRendir2*100)/nombres.length;
        double porcRen12 = (totalRendir12*100)/nombres.length;
        double porcFaltaT = (totalFaltaTrabajo*100)/nombres.length;

        System.out.println("REPORTE ESTUDIANTES");
        System.out.printf("PORCENTAJE APROBADOS %20.2f\nPORCENTAJE REPROBADOS %19.2f\n" +
                "PORCENTAJE RENDIR FINAL 1 %15.2f\nPORCENTAJE RENDIR FINAL 2 %15.2f\nPORCENTAJE RENDIR FINAL 1 Y 2 %10.2f" +
                "\nPORCENTAJE FALTA DE TRABAJO %13.2f\n",porcAp,porcRep,porcRen1,porcRen2,porcRen12,porcFaltaT);

        List<Alumno> alumno1 = new ArrayList<Alumno>();
        List<Alumno> alumno2 = new ArrayList<Alumno>();
        List<Alumno> alumno3 = new ArrayList<Alumno>();

        for (int i = 0; i < alumno.length; i++) {
            alumno1.add(alumno[i]);
            alumno2.add(alumno[i]);
            alumno3.add(alumno[i]);

        }
        /**
         * Uso de collections para ordenar en base a columnas NOMBRES/TOTAL/PROMOCION
         * https://docs.oracle.com/javase/6/docs/api/java/util/Collections.html
         */
        Collections.sort(alumno1);
        Collections.sort(alumno2, new CompararTotal());
        Collections.sort(alumno3, new CompararTotal());
        /**
         * Rutas donde se guardarán los .csv
         */
        String ruta1Nombres = "archivos-creados\\jjyanza1BdEst_OrdenNomb.csv";
        String ruta2Total = "archivos-creados\\jjyanza1BdEst_OrdenTOTAL.csv";
        String ruta3Promocion = "archivos-creados\\jjyanza1BdEst_OrdenPromo.csv";

        /**
         * Contenido que guardará el csv envio listas para
         * recorrerlas y diseñar un formato en la clase cadenaCsv
         */
        String contenidoNombres = cadenaCsv(alumno1);
        String contenidoTotal = cadenaCsv(alumno2);
        String contenidoPromo = cadenaCsv(alumno3);

        /**
         * Envío Rutas y contenidos al método crearCsv |CREATE|
         */
        crearCsv(ruta1Nombres, contenidoNombres);
        crearCsv(ruta2Total, contenidoTotal);
        crearCsv(ruta3Promocion, contenidoPromo);

    }

    /**
     * Método que crea el contenido del csv con String.Format
     * @param alumnos
     * @return paraCsv
     */
    public static String cadenaCsv(List<Alumno> alumnos){
        paraCsv = String.format("NOMBRE|FOR1|CHA1|VID1|TRA1|PRE1|FOR2|CHA2|VID2|TRA2|PRE2|FIN1|FIN2|TOTAL|ALERTA|PROMOCIÓN\n");
        for (Alumno elemento: alumnos) {
            paraCsv = String.format("%s%s|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%s\n"
                    ,paraCsv, elemento.getNombreEst(), elemento.getFor1(),elemento.getCha1(),elemento.getVid1(),
                    elemento.getTra1(),elemento.getPre1(), elemento.getFor2(), elemento.getCha2(),
                    elemento.getVid2(),elemento.getTra2(), elemento.getPre2(),elemento.getFin1(),elemento.getFin2()
                    , elemento.getTotal(),elemento.getAlerta(),elemento.getPromocion());
        }
        return paraCsv;
    }

    /**
     * Recibe parametros para especificar el contenido del csv y la ruta a guardar
     * @param ruta
     * @param contenido
     */
    public static void crearCsv(String ruta, String contenido){

        try {
            OutputStream archivo = new FileOutputStream(ruta);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(archivo, "UTF-8"));

            pw.print(contenido);

            pw.flush();
            pw.close();
        }catch (Exception e){

        }
    }
}
