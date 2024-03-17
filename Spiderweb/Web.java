import java.util.ArrayList;

public class Web {
    private ArrayList<Line> strands;
    private int numStrands = 0;
    private int radio = 20;
    
    
    /**
     * Crea una telaraña
     */
    public Web(){
        strands = new ArrayList<>();
    }
    
    /**
     * get radio
     */
    public int getRadio() {
        return radio;
    }
    /**
     * set radio
     * @param radio Se crea el set del radio de la telaraña.
     */
    public void setRadio(int radio) {
        this.radio = radio;
    }
    

    /**
     * Añade un nuevo hilo a la telaraña, manteniendo su simetria
     */
    public void addStrand(){
        eraseAllStrands();
        int centerX = 350;
        int centerY = 350;
        numStrands++; 
        double angleIncrement = 360.0 / numStrands;

        for (int i = 0; i < numStrands; i++) {
            double angle = i * angleIncrement;
            Line newStrand = createStrand(centerX, centerY, angle);
            strands.add(newStrand);
        }

    }

    /**
     * Añade un hilo
     * @param centerX se pone el centro en x.
     * @param centerY se pone el centro en y.
     * @param angle se determina el ángulo que tendrá este hilo de los otros.
     */
    private Line createStrand(int centerX, int centerY, double angle){
        Line strand = new Line();
        strand.setX1(centerX);
        strand.setY1(centerY);
        strand.setX2((int) (centerX + radio * Math.cos(Math.toRadians(angle))));
        strand.setY2((int) (centerY + radio * Math.sin(Math.toRadians(angle))));
        strand.makeVisible();
        return strand;
    }
    
    /**
     * borra todos los hilos creados
     */
    private void eraseAllStrands() {
        for (Line strand : strands) {
            strand.eraseLine();
        }
        strands.clear(); 
    }
    
    
    /**
     * Expande el radio de la telaraña y sus hilos en un número determinado (extra).
     * @param extra es la cantidad adicional que se va a expandir la telaraña.
     */
    public void expand(int extra){
        eraseAllStrands();
        radio = radio + extra;
        int centerX = 350;
        int centerY = 350;
        double angleIncrement = 360.0 / numStrands;

        for (int i = 0; i < numStrands; i++) {
            double angle = i * angleIncrement;
            Line newStrand = createStrand(centerX, centerY, angle);
            strands.add(newStrand);
        }

    }
    
    
    /**
     * Vuelve visibles los hilos
     */
    public void makeVisible(){
        for(Line puente : strands){
           puente.makeVisible(); 
        }
    
    }
    
     /**
     * Vuelve invisibles los hilos
     */
    public void makeInvisible(){
        for(Line puente : strands){
           puente.makeInvisible(); 
        }
    }

}
