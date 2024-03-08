import java.lang.Math;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Es la Clase fachada del proyecto, donde se hace la simulacion.
 * Para el modelamiento de la matriz se tomaron los siguientes valores:
 */
public class Spiderweb
{

    private Araña araña;
    private int contadorPuentes = 0;
    private Web web;
    private int strandNumber = 0;
    //private int radio = web.getRadio();
    private Bridge bridge;
    private Spot spot;
    private ArrayList<int[]> coordPuentes = new ArrayList<>();;
        

    /**
     * Constructor for objects of class Spiderweb
     */
    public Spiderweb()
    {
        araña();
        web();
        bridge();
        spot();
    }
    
    
    /**
     * subMetodo del constructor
     */
    private void araña()
    {
        araña = new Araña();
        araña.moveAllTo(340, 350);
        araña.makeVisible();
    }
    
    /**
     * subMetodo del constructor
     */
    private void web(){
        web = new Web();
    }
    
    /**
     * subMetodo del constructor
     */
    private void bridge()
    {
        bridge = new Bridge();
    }
    
    /**
     * subMetodo del constructor
     */
    private void spot(){
        spot = new Spot();
    }
    
    public void setStrandNumber(int strandNumber) {
        this.strandNumber = strandNumber;
    }

    public int getStrandNumber() {
        return strandNumber;
    }
    
    
    
    /**
     * Añade un nuevo hilo a la telaraña
     */
    public void addStrand(){
        web.addStrand();
        strandNumber++;
    }
    
    /**
     * Expande el radio de la telaraña y sus hilos en un numero determinado(extra).
     */
    public void expand(int extra){
        web.expand(extra);
    }
    
    public void create(int n, int m, int s){

        for(int i = 0; i < n; i++){
            addStrand();
        }
        expand(300);
        Scanner scanner = new Scanner(System.in); 
        for(int y = 0; y < m; y++){
            
            System.out.println("Digite el color");
            String color = scanner.nextLine();
            
            System.out.println("Digite la distancia");
            int distancia = scanner.nextInt();
            
            System.out.println("Digite el camino");
            int camino = scanner.nextInt();
            
            int[] valores = traductor(distancia, camino);
            addBridge(color, valores[0], valores[1], valores[2], valores[3]);
            
        }
        scanner.close(); 
    }

    
    
    
    /**
     * Traduce de distancia, camino a coordenadas x y
     */
    private int[] traductor(int distancia, int camino){
        double angleIncrement = 360.0 / strandNumber;
        double angle = angleIncrement * camino;
        double angle2 = angleIncrement * (camino + 1);
        
        int x1 = (int) ((distancia * Math.cos(Math.toRadians(angle))));
        int x1Corregido = x1 + 350;
   
        int y1 = (int) ((distancia * Math.sin(Math.toRadians(angle))));
        int y1Corregido =  350 - y1;
 
        int x2 = (int) ((distancia * Math.cos(Math.toRadians(angle2))));
        int x2Corregido = x2 + 350;

        int y2 = (int) ((distancia * Math.sin(Math.toRadians(angle2))));
        int y2Corregido =  350 - y2;
        
        int[] respuesta = {x1Corregido, y1Corregido, x2Corregido, y2Corregido};
        
        return respuesta;
    }
    
    
    
    /**
     * añade puentes a la telaraña teniendo en cuenta sus coordenadas aisgnadas en la matriz de modelamiento de la telarañá
     * y añade las coordenadas a un arrayList
     */
    public void addBridge(String color, int x1, int y1, int x2, int y2) {
        bridge.addBridge(color, x1, y1, x2, y2);
        int[] coords = {x1, y1, x2, y2};
        coordPuentes.add(coords);
    }
    
    
    /**
     * Borra puentes a la telaraña teniendo en cuenta sus coordenadas aisgnadas en la matriz de modelamiento de la telarañá
     */
    public void deleteBridge(String color){
        bridge.deleteBridge(color);
    }
    
    /**
     * Reubiuca puentes a la telaraña teniendo en cuenta sus coordenadas aisgnadas en la matriz de modelamiento de la telarañá
     * El procedimiento es el siguiente:
     * Borrar el puente con las coordenadas anteriores, Crear el puente con las coordenadas nuevas
     */
    public void relocateBridge(String color, int nuevoX1, int nuevoY1, int nuevoX2, int nuevoY2){
        bridge.relocateBridge(color, nuevoX1, nuevoY1, nuevoX2, nuevoY2);
    }
    
    /**
     * Agrega los puntos, utilizando la matriz (spotMatriz).
     * Si hay un spot es true sino es false
     */
    public void addSpot(String color, int X, int Y){
        spot.addSpot(color, X, Y);
    }
    
    
    /**
     * Elimina los puntos, utilizando la matriz (spotMatriz).
     * Si hay un spot es true sino es false
     */
    public void deleteSpot(String color){
        spot.deleteSpot(color);
    }

    /**
     * Hace visible todo lo que este en el canvas
     */
    public void makeVisible(){
        web.makeVisible();
        araña.makeVisible();
        bridge.makeVisible();
        spot.makeVisible();
    }

    
    /**
     * Hace invisible todo lo que este en el canvas
     */
    public void makeInvisible(){
        araña.makeInvisible();
        bridge.makeInvisible();
        web.makeInvisible();
        spot.makeInvisible();
    }
    
    /**
     * Sienta a la araña en el centro de la telaraña 
     * y le cambia el color para identificar su estado
     */
    public void spiderSit(){
        araña.moveAllTo(340, 350);
        araña.spiderSit();
    }
    
    /**
     * mueve la araña de forma automatica al camino elegido
     */
    public void spiderWalk(int camino, int distancia) {
        int nStrands = strandNumber;
        double angleIncrement = 360.0 / nStrands;
        int[][] coordX = new int[nStrands][];
        for (int i = 0; i < nStrands; i++) {
            double angle = i * angleIncrement;
            coordX[i] = araña.createStrand(350, 350, angle, distancia);
        }
        
        int[] targetCoordinates = coordX[camino]; 
        for (int[] coords : coordPuentes) {
            int[] startPoint = {coords[0], coords[1]}; 
            int[] endPoint = {coords[2], coords[3]}; 
            if (isInsideBridge(targetCoordinates, startPoint, endPoint)) {
                araña.moveAllTo(endPoint[0], endPoint[1]);
                int remainingDistance = distancia - distanceBetweenPoints(targetCoordinates, endPoint);
                moveSpiderAlongPath(endPoint[0], endPoint[1],camino, remainingDistance);
                return;
            }
        }
        araña.moveAllTo(targetCoordinates[0], targetCoordinates[1]);
        
    }

    
    /**
     * Verifica si las coordenadas objetivo están dentro de un puente.
     */
    private boolean isInsideBridge(int[] target, int[] start, int[] end) {
        
        if ((target[0] >= start[0] && target[0] <= end[0]) || (target[0] <= start[0] && target[0] >= end[0])) {
            double slope = (double) (end[1] - start[1]) / (end[0] - start[0]);
            double yIntercept = start[1] - slope * start[0];
            double yOnBridgeLine = slope * target[0] + yIntercept;
            return Math.abs(yOnBridgeLine - target[1]) <= 1; 
        } else {
            return false;
        }
    }

    
    /**
     * Calcula la distancia entre dos puntos.
     */
    private int distanceBetweenPoints(int[] point1, int[] point2) {
        int dx = point2[0] - point1[0];
        int dy = point2[1] - point1[1];
        return (int) Math.sqrt(dx * dx + dy * dy);
    }
    
    private void moveSpiderAlongPath(int x1,int y1,int camino , int distancia) {
    araña.spiderWalk(strandNumber, camino + 1, distancia, x1, y1);
    }    

    
    /**
     * Consulta la cantidad de spots creados
     */
    public int spots(){
        int contador = 0;
        return contador;
    }
    
    /**
     * Consulta la cantidad de spots creados
     */
    public int bridges(){
        return contadorPuentes;
    }
    
    /**
     * Cierra el programa.
     */
    public void finish(){
        System.exit(0);
    }
    
}

    
