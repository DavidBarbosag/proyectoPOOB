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
     * Agrega los puntos donde la araña duerme
     */
    public void addSpot(String color, int X, int Y){
         Coordenadas coordenadas = new Coordenadas(X, Y);
         spots.put(color, coordenadas);
         Circle spot = new Circle();
         spot.setXPosition(X);
         spot.setYPosition(Y);
         spot.changeColor(color);
         spot.makeVisible();
         spotsGraficos.add(spot);
    }
    
    
    /**
     * Elimina los puntos
     */
    public void deleteSpot(String color){
        spots.remove(color);
        borrarSpotGrafico(color);
    }
    
    /**
     * Borra los spots.
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
        private int x;
        private int y;

        public Coordenadas(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
