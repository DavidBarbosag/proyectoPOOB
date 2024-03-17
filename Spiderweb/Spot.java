import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/**
 * Clase dedicada a crear los spots
 */
public class Spot
{
    private HashMap<String, Coordenadas> spots;
    private List<Circle> spotsGraficos;

    /**
     * Constructor for objects of class Spot
     */
    public Spot()
    {
        spots = new HashMap<>();
        spotsGraficos = new ArrayList<>();
    }
    
    /**
     * Agrega los puntos, utilizando la matriz (spotMatriz).
     * Si hay un spot es true sino es false
     * @param color del spot
     * @param distancia es la distanica donde estara ubicado el puente con respecto al centro de la telaraña
     * @param camino es el camino donde se ubicara el spot
     * @param strandNumber es el numero de hebras que hay en la Web
     */
    public void addSpot(String color, int distancia , int camino, int strandNumber){
         int[] respuesta = new int[1];
         respuesta = traductorSpots(distancia , camino, strandNumber);
         Coordenadas coordenadas = new Coordenadas(distancia, camino);
         spots.put(color, coordenadas);
         Circle spot = new Circle();
         spot.setXPosition(respuesta[0] - 16);
         spot.setYPosition(respuesta[1]);
         spot.changeColor(color);
         spot.makeVisible();
         spotsGraficos.add(spot);
    }
    
    /**
     * Traduce distancia y camino en coordenadas x,y
     * @param distancia es un vector que se va a traducir.
     * @param camino es un vector que se va a traducir.
     * @param strandNumber sirve para ubicar las líneas que hay y ver los ángulos.
     */    
    private int[] traductorSpots(int distancia, int camino, int strandNumber){
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
     * Elimina los puntos
     * @param color es el indicador del spot que se va a eliminar.
     */
    public void deleteSpot(String color){
        spots.remove(color);
        borrarSpotGrafico(color);
    }
    
    /**
     * Borra los spots.
     * @param color es el indicador del spot que se va a eliminar.
     */
    private void borrarSpotGrafico(String color) {
        for (Circle spot : spotsGraficos) {
            if (spot.getColor().equals(color)) {
                spot.eraseCircle();
                spotsGraficos.remove(spot);
                break; 
            }
        }
    }
    
    /**
     * Vuelve visibles los puentes
     */
    public void makeVisible(){
        for(Circle spot : spotsGraficos){
           spot.makeVisible(); 
        }
    
    }
    
     /**
     * Vuelve invisibles los puentes
     */
    public void makeInvisible(){
        for(Circle spot : spotsGraficos){
           spot.makeInvisible(); 
        }
    }
    
    
    /**
     * Clase para almacenar las coordenadas de los puentes
     */
    private class Coordenadas {
        private int distancia;
        private int camino;

        public Coordenadas(int distancia, int y) {
            this.distancia = distancia;
            this.camino = camino;
        }

        public int getDistancia() {
            return distancia;
        }

        public int getCamino() {
            return camino;
        }
    }

}
