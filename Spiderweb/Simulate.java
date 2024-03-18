import java.lang.Math;
import javax.swing.JOptionPane;
import java.util.*;
/**
 * Clase encargada de la simulacion.
 */
public class Simulate
{
    private Spiderweb spiderweb;
    private int favoriteStrand;
    private HashMap<Integer, Integer> coordenadasPuentes = new HashMap<>();
    
    /**
     * Constructor de Simulate.
     */
    public Simulate(){
        spiderweb = new Spiderweb();
    }
    
    /**
     * Este metodo nos da las coordenadas de los puentes usados.
     */
    private void accessCoordenadasPuentes() {
        coordenadasPuentes = spiderweb.getCoordenadasPuentes();
    }
    
    /**
     * Método que simula la primera linea de la solucion del proyecto.
     * @param strands es el numero de hilos que tendra la telararaña.
     * @param radio es la longitud que tendran los hilos de la telaraña.
     * @param favorite es la hebra favorita de la araña.
     */
    public void create1(int strands, int radio, int favorite){
        spiderweb.create1(strands, radio, favorite);
        favoriteStrand = favorite;
    }
        
     /**
     * Método que simula la segunda linea de la solucion del proyecto.
     * @param bridges son los puentes que se van a crear.
     */
    public void create2(int[][] bridges){
        spiderweb.create2(bridges);
    }
    
    /**
     * Pasa de coordenadas de puentes a matriz.
     */
    private int[][] DisCam(){
        int[][] respuesta = new int[coordenadasPuentes.size()][3];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : coordenadasPuentes.entrySet()) {
            respuesta[index][0] = entry.getKey();
            respuesta[index][1] = (entry.getValue()) + 1; 
            respuesta[index][2] = entry.getValue();
            index++;
        }
        return respuesta;
    }
    
    /**
     * Método que hace la simulación final.
     */
    public void simulation(){
        accessCoordenadasPuentes();
        int[][] datosPuentes = DisCam();
        int ultimoCamino = -1;
        
        for (int i = 0; i < datosPuentes.length; i++) {
            int distancia = datosPuentes[i][0];
            int camino = datosPuentes[i][1];
            int[] coordenadas = traductor(distancia, camino, spiderweb.getStrandNumber());
            int x = coordenadas[0];
            int y = coordenadas[1];
            spiderweb.spiderWalk(x, y);
            
            try {
                Thread.sleep(1500); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ultimoCamino = camino;
        }
        int solucion =  Math.abs(ultimoCamino - favoriteStrand);
        System.out.println("" + solucion); 
    }

    
    /**
     * Traduce distancia y camino en coordenadas x,y.
     * @param distancia es un vector que se va a traducir.
     * @param camino es un vector que se va a traducir.
     * @param strandNumber sirve para ubicar las líneas que hay y ver los ángulos.
     */    
    private int[] traductor(int distancia, int camino, int strandNumber){
        double angleIncrement = 360.0 / strandNumber;
        double angle = angleIncrement * camino;
        double angle2 = angleIncrement * (camino + 1);
    
        int x1 = (int) ((distancia * Math.cos(Math.toRadians(angle))));
        int x1Corregido = x1 + 350;
   
        int y1 = (int) ((distancia * Math.sin(Math.toRadians(angle))));
        int y1Corregido =  350 - y1;
        
        int[] respuesta = {x1Corregido, y1Corregido};
        return respuesta;
    }
}
