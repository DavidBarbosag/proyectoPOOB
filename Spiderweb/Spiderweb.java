import java.lang.Math;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

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
    private int spotNumber = 0;
    private int spiderLastPath;
    //private int radio = web.getRadio();
    private Bridge bridge;
    private Spot spot;
    private int favoriteStrand = 0;
    private ArrayList<int[]> coordPuentes = new ArrayList<>();
    private HashMap<String, int[]> puentes = new HashMap<>(); 
    private ArrayList<String> unusedBridges = new ArrayList<>();
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
        araña.moveAllTo(350, 350);
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
    
    /**
     * metodo que simula la primera linea de la solucion del proyecto
     * @param strands es el numero de hilos que tendra la telararaña
     * @param radio es la longitud que tendran los hilos de la telaraña
     * @param favorite es la hebra favorita de la araña
     */
    public void create1(int strands, int radio, int favorite){
        if (strands < 3){
            JOptionPane.showMessageDialog(null, "El numero de hilos debe de ser mayor a 3");
        }else{
            for(int i = 0; i < strands; i++){
                addStrand();
            }
        }
        if (radio <= 20){
            JOptionPane.showMessageDialog(null, "El radio debe ser mayor a 20");
        
        }else{
            expand(radio - 20);
        } 
        favoriteStrand = favorite;
    }
    
    /**
     * metodo que simula la segunda linea de la solucion del proyecto
     * @param bridges son los puentes que se van a crear
     */
    public void create2(int[][] bridges){
        String color = "black";
        for (int i = 0; i < bridges.length ; i++) {
            addBridge(color, bridges[i][0], bridges[i][1]);
            System.out.println(""+  bridges[i][0]+ bridges[i][1]);
        }
    }
    
    
    /* 
    //Metodo creado para verificar que create2 funciona
    public void useCreate2(){
        int[][] matriz = {         
            {100, 1},
            {200, 2},
            {300, 3}
        };
        create2(matriz);
    }
    */
    
    /**
     * Traduce de distancia, camino a coordenadas x y
     * @param distancia a donde se movera el objeto
     * @param camino  a donde se movera el objeto
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
     * Metodo para consultar los puentes que No se han utilizado
     */
    public void unusedBridges(){
        for (int i = 0; i < unusedBridges.size(); i++) {
            System.out.println(unusedBridges.get(i));
        }
    } 
        
    
    /**
     * Metodo para consultar los puentes que No se han utilizado
     */
    public int spiderLastPath(){
        return spiderLastPath;
    }
    
    /**
     * añade puentes a la telaraña teniendo en cuenta sus coordenadas aisgnadas en la matriz de modelamiento de la telaraña
     * y añade las coordenadas a un arrayList
     * @param color que tendra el puente
     * @param distancia es la distanica donde estara ubicado el puente con respecto al centro de la telaraña
     * @param camino es el camino donde se ubicar el punto inicial del puente 
     */
    public void addBridge(String color, int distancia, int camino) {
        int[] respuesta = new int[3];
        respuesta = traductor(distancia , camino);
        int x1 = respuesta[0];
        int y1 = respuesta[1];
        int x2 = respuesta[2];
        int y2 = respuesta[3];
        bridge.addBridge(color, x1, y1, x2, y2);
        int[] coords = {x1, y1, x2, y2};
        coordPuentes.add(coords);
        unusedBridges.add(color);
    }
    
    
    /**
     * Borra puentes a la telaraña teniendo en cuenta sus coordenadas aisgnadas en la matriz de modelamiento de la telaraña
     * @param color se usa para ubicar el puente que se va a borrar
     */
    public void deleteBridge(String color){
        bridge.deleteBridge(color);
        if (unusedBridges.contains(color)){
            unusedBridges.remove(color);
        }
    }
    
    /**
     * Reubiuca puentes a la telaraña teniendo en cuenta sus coordenadas aisgnadas en la matriz de modelamiento de la telaraña
     * El procedimiento es el siguiente:
     * Borrar el puente con las coordenadas anteriores, Crear el puente con las coordenadas nuevas
     * @param color se usa para ubicar el puente que se va a reubicar
     * @param color que tendra el puente
     * @param distancia es la distanica donde estara ubicado el puente con respecto al centro de la telaraña
     * @param camino es el camino donde se ubicar el punto inicial del puente 
     */
    public void relocateBridge(String color, int distancia, int camino){
        int[] respuesta = new int[3];
        respuesta = traductor(distancia , camino);
        int x1 = respuesta[0];
        int y1 = respuesta[1];
        int x2 = respuesta[2];
        int y2 = respuesta[3];
        bridge.relocateBridge(color, x1, y1, x2, y2);
    }
    
    
    private int[] traductorSpots(int distancia, int camino){
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
    
    
    /**
     * Agrega los puntos, utilizando la matriz (spotMatriz).
     * Si hay un spot es true sino es false
     * @param color del spot
     * @param distancia es la distanica donde estara ubicado el puente con respecto al centro de la telaraña
     * @param camino es el camino donde se ubicara el spot
     */
    public void addSpot(String color, int distancia, int camino){
        int[] respuesta = new int[1];
        respuesta = traductorSpots(distancia , camino);
        spot.addSpot(color, respuesta[0] - 15, respuesta[1]);
    }
    
    
    /**
     * Elimina los puntos, utilizando la matriz (spotMatriz).
     * Si hay un spot es true sino es false
     * @param color se usa para ubicar el puente que se va a reubicar
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
        araña.spiderSit();
    }
    
    /**
     * mueve la araña de forma automatica a la coordenada elegida
     * @param coordenada x a donde se movera la araña
     * @param coordenada y a donde se movera la araña
     */
    public void spiderWalk(int x, int y) {
        araña.spiderWalk(x, y);
        
    }

    /*
    private void moveSpiderAlongPath(int x1,int y1,int camino , int distancia) {
    araña.spiderWalk(strandNumber, camino + 1, distancia, x1, y1);
    }
    */

    
    /**
     * Consulta la cantidad de spots creados
     */
    public int spots(){
        return spotNumber;
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

    
