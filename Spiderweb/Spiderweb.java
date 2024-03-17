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
    //private int radio = web.getRadio();
    private Bridge bridge;
    private Spot spot;
    private ArrayList<int[]> coordPuentes = new ArrayList<>();
    private HashMap<String, int[]> puentes = new HashMap<>(); 

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
        scanner.nextLine();
        System.out.println("Digite el camino");
        int camino = scanner.nextInt();
        scanner.nextLine();
        
        puentes.put(color, new int[]{distancia, camino}); // Agrega el puente al HashMap
        
        int[] valores = traductor(distancia, camino);
        // Usa el color como clave para obtener los datos del puente del HashMap
        addBridge(color, valores[0], valores[1], valores[2], valores[3]);
    }
    scanner.close(); 
}


    
    private void Addpks(String color, int a, int b, int c, int d){
        addBridge(color, a, b, c, d);
    }
    
    
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
     * añade puentes a la telaraña teniendo en cuenta sus coordenadas aisgnadas en la matriz de modelamiento de la telaraña
     * y añade las coordenadas a un arrayList
     * @param color que tendra el puente
     * @param x1 es la coordenada x inicial
     * @param y1 es la coordenada y inicial
     * @param x2 es la coordenada x final
     * @param y2 es la coordenada y final
     */
    public void addBridge(String color, int x1, int y1, int x2, int y2) {
        bridge.addBridge(color, x1, y1, x2, y2);
        int[] coords = {x1, y1, x2, y2};
        coordPuentes.add(coords);
    }
    
    
    /**
     * Borra puentes a la telaraña teniendo en cuenta sus coordenadas aisgnadas en la matriz de modelamiento de la telaraña
     * @param color se usa para ubicar el puente que se va a borrar
     */
    public void deleteBridge(String color){
        bridge.deleteBridge(color);
    }
    
    /**
     * Reubiuca puentes a la telaraña teniendo en cuenta sus coordenadas aisgnadas en la matriz de modelamiento de la telaraña
     * El procedimiento es el siguiente:
     * Borrar el puente con las coordenadas anteriores, Crear el puente con las coordenadas nuevas
     * @param color se usa para ubicar el puente que se va a reubicar
     * @param x1 es la coordenada x inicial
     * @param y1 es la coordenada y inicial
     * @param x2 es la coordenada x final
     * @param y2 es la coordenada y final
     */
    public void relocateBridge(String color, int nuevoX1, int nuevoY1, int nuevoX2, int nuevoY2){
        bridge.relocateBridge(color, nuevoX1, nuevoY1, nuevoX2, nuevoY2);
    }
    
    /**
     * Agrega los puntos, utilizando la matriz (spotMatriz).
     * Si hay un spot es true sino es false
     * @param color del spot
     * @param x es la coordenada x 
     * @param y es la coordenada y
     */
    public void addSpot(String color, int X, int Y){
        spot.addSpot(color, X, Y);
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
        spotNumber++;
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

    
