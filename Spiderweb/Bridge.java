import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


/**
 * Clase dedicada a crear los puentes
 */
public class Bridge {
    private HashMap<String, Coordenadas> puentes;
    private List<Line> puentesGraficos;
    
    /**
     * Constructor de la clase Bridge
     */
    public Bridge() {
        puentes = new HashMap<>();
        puentesGraficos = new ArrayList<>();
    }

    /**
     * añade puentes a la telaraña teniendo en cuenta sus coordenadas aisgnadas en la matriz de modelamiento de la telaraña
     * y añade las coordenadas a un arrayList
     * @param color que tendra el puente
     * @param distancia es la distanica donde estara ubicado el puente con respecto al centro de la telaraña
     * @param camino es el camino donde se ubicar el punto inicial del puente
     * @param strandNumber es el numero de hebras que hay en la Web
     */
    public void addBridge(String color, int distancia, int camino, int strandNumber){
        int[] respuesta = new int[3];
        respuesta = traductor(distancia , camino, strandNumber);
        Coordenadas coordenadas = new Coordenadas(distancia, camino);
        puentes.put(color, coordenadas);
        Line puente = new Line();
        puente.setX1(respuesta[0]);
        puente.setY1(respuesta[1]);
        puente.setX2(respuesta[2]);
        puente.setY2(respuesta[3]);
        puente.changeColor(color);
        puente.makeVisible();
        puentesGraficos.add(puente);
    }
    
    
    
    
    
    /**
     * Traduce de distancia, camino a coordenadas x y
     * @param distancia a donde se movera el objeto
     * @param camino  a donde se movera el objeto
     */
    private int[] traductor(int distancia, int camino, int strandNumber){
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
    public void relocateBridge(String color, int distancia, int camino, int strandNumber) {
        deleteBridge(color);
        borrarPuenteGrafico(color);
        addBridge(color, distancia, camino, strandNumber);
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
    
    private Coordenadas obtenerCoordenadas(String color) {
        return puentes.get(color);
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