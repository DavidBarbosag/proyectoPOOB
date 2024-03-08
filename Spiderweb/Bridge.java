import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


/**
 * Clase dedicada a crear los puentes
 */
public class Bridge {
    private HashMap<String, Coordenadas[]> puentes;
    private List<Line> puentesGraficos;
    
    /**
     * Constructor de la clase Bridge
     */
    public Bridge() {
        puentes = new HashMap<>();
        puentesGraficos = new ArrayList<>();
    }

    /**
     * añade puentes a la telaraña teniendo en cuenta sus coordenadas aisgnadas en la matriz de modelamiento de la telarañá
     */
    public void addBridge(String color, int x1, int y1, int x2, int y2){
        Coordenadas[] coordenadas = {new Coordenadas(x1, y1), new Coordenadas(x2, y2)};
        puentes.put(color, coordenadas);
        Line puente = new Line();
        puente.setX1(x1);
        puente.setY1(y1);
        puente.setX2(x2);
        puente.setY2(y2);
        puente.changeColor(color);
        puente.makeVisible();
        puentesGraficos.add(puente);
    }

    /**
     * Parte logica del metodo addBridge
     */
    public void deleteBridge(String color){
        puentes.remove(color);
        borrarPuenteGrafico(color);
    }
    
    /**
     * Borra puentes a la telaraña teniendo en cuenta sus coordenadas aisgnadas en la matriz de modelamiento de la telarañá
     */
    private void borrarPuenteGrafico(String color) {
        for (Line puente : puentesGraficos) {
            if (puente.getColor().equals(color)) {
                puente.eraseLine();
                puentesGraficos.remove(puente);
                break; 
            }
        }
    }
    
    /**
     * Reubiuca puentes a la telaraña teniendo en cuenta sus coordenadas aisgnadas en la matriz de modelamiento de la telarañá
     * El procedimiento es el siguiente:
     * Borrar el puente con las coordenadas anteriores, Crear el puente con las coordenadas nuevas
     */
    public void relocateBridge(String color, int nuevoX1, int nuevoY1, int nuevoX2, int nuevoY2) {
        deleteBridge(color);
        borrarPuenteGrafico(color);
        addBridge(color, nuevoX1, nuevoY1, nuevoX2, nuevoY2);
    }

    /**
     * Vuelve visibles los puentes
     */
    public void makeVisible(){
        for(Line puente : puentesGraficos){
           puente.makeVisible(); 
        }
    
    }
    
     /**
     * Vuelve invisibles los puentes
     */
    public void makeInvisible(){
        for(Line puente : puentesGraficos){
           puente.makeInvisible(); 
        }
    }
    
    private Coordenadas[] obtenerCoordenadas(String color) {
        return puentes.get(color);
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