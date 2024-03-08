
/**
 * Esta clase será el cuerpo de la araña, teniendo en cuenta los objetos de Shapes.
 *
 * @author David Barbosa y Nicolás Prieto
 * @version 1.0
 */
public class Araña
{
    // instance variables - replace the example below with your own
    private Rectangle pata1;
    private Rectangle pata2;
    private Rectangle pata3;
    private Rectangle pata4;
    private Circle cara;
    private Circle cuerpo;

    /**
     * A continuación se creará el método constructor:
     */
    public Araña()
    {
        cara();
        cuerpo();
        pata1();
        pata2();
        pata3();
        pata4();
    }
    /**
     * A continuación se creará el atributo cara del método constructor:
     */
    private void cara()
    {
        cara = new Circle();
        cara.makeVisible();
        cara.changeColor("black");
        cara.changeSize(18);
        cara.moveHorizontal(55);
        cara.moveVertical(45);
        
        
    }
    /**
     * A continuación se creará el atributo cuerpo del método constructor:
     */
    private void cuerpo()
    {
        cuerpo = new Circle();
        cuerpo.makeVisible();
        cuerpo.changeColor("black");
        cuerpo.moveHorizontal(47);
        cuerpo.moveVertical(60);
        cuerpo.changeSize(35);
    }
    /**
     * A continuación se creará el atributo pata1 del método constructor:
     */
    private void pata1()
    {
        pata1 = new Rectangle();
        pata1.makeVisible();
        pata1.changeColor("black");
        pata1.moveHorizontal(-10);
        pata1.moveVertical(65);
        pata1.changeSize(2,50);
    }
    /**
     * A continuación se creará el atributo pata2 del método constructor:
     */
    private void pata2()
    {
        pata2 = new Rectangle();
        pata2.makeVisible();
        pata2.changeColor("black");
        pata2.moveHorizontal(-10);
        pata2.moveVertical(90);
        pata2.changeSize(2,50);
    }
    /**
     * A continuación se creará el atributo pata3 del método constructor:
     */
    private void pata3()
    {
        pata3 = new Rectangle();
        pata3.makeVisible();
        pata3.changeColor("black");
        pata3.moveHorizontal(-15);
        pata3.moveVertical(74);
        pata3.changeSize(2,60);
    }
    /**
     * A continuación se creará el atributo pata4 del método constructor:
     */
    private void pata4()
    {
        pata4 = new Rectangle();
        pata4.makeVisible();
        pata4.changeColor("black");
        pata4.moveHorizontal(-15);
        pata4.moveVertical(83);
        pata4.changeSize(2,60);
    }
    /**
     * A continuación se creará el método makeVisible para la araña:
     */
    public void makeVisible(){
        cara.makeVisible();
        cuerpo.makeVisible();
        pata1.makeVisible();
        pata2.makeVisible();
        pata3.makeVisible();
        pata4.makeVisible();
    }
    /**
     * A continuación se creará el método makeInvisible para la araña:
     */
    public void makeInvisible(){
        cara.makeInvisible();
        cuerpo.makeInvisible();
        pata1.makeInvisible();
        pata2.makeInvisible();
        pata3.makeInvisible();
        pata4.makeInvisible();
    }
    /**
     * A continuación se creará el método moverTodos para la araña:
     */
    public void moverTodos(int dx, int dy) {
        cara.makeInvisible();
        cara.moveHorizontal(dx);
        cara.moveVertical(dy);
        cara.makeVisible();
        cuerpo.makeInvisible();
        cuerpo.moveHorizontal(dx);
        cuerpo.moveVertical(dy);
        cuerpo.makeVisible();
        pata1.makeInvisible();
        pata1.moveHorizontal(dx);
        pata1.moveVertical(dy);
        pata1.makeVisible();
        pata2.makeInvisible();
        pata2.moveHorizontal(dx);
        pata2.moveVertical(dy);
        pata2.makeVisible();
        pata3.makeInvisible();
        pata3.moveHorizontal(dx);
        pata3.moveVertical(dy);
        pata3.makeVisible();
        pata4.makeInvisible();
        pata4.moveHorizontal(dx);
        pata4.moveVertical(dy);
        pata4.makeVisible();
        }
    /**
     * En siguiente método, se tomó la desición de que cuando la araña está sentada cambiará
     * de color con el fin de identificar la acción.
     */
    public void spiderSit(){
        changeColor();
    }
    /**
     * Cambia el color de la araña
     */
    private void changeColor(){
        cara.changeColor("red");
        cuerpo.changeColor("red");
        pata1.changeColor("red");
        pata2.changeColor("red");
        pata3.changeColor("red");
        pata4.changeColor("red");
    }
    
    /**
     * En siguiente método, se tomó la desición de que cuando la araña está caminando cambiará
     * de color con el fin de identificar la acción.
     */
    public void spiderWalk(int nStrands, int camino, int distancia, int X,int Y){
        double angleIncrement = 360.0 / nStrands;
        int[][] coordX = new int[nStrands][];
        // guarda las coordenadas de los posibles caminos
        for (int i = 0; i < nStrands; i++) {
            double angle = i * angleIncrement;
            coordX[i] = createStrand(X, Y, angle, distancia);
        }
        moveAllTo(coordX[camino][0], coordX[camino][1]);
        
    }
    
    /**
     * calcula las coordenadas para que la araña camine
     */
    public int[] createStrand(int centerX, int centerY, double angle, int radio){
        int coordX = (int) (centerX + radio * Math.cos(Math.toRadians(angle)));
        int coordY = (int) (centerY + radio * Math.sin(Math.toRadians(angle)));
        int[] coordenadas = {coordX, coordY};
        return coordenadas;
    }
    
    
    private void changeColor2(){
        cara.changeColor("black");
        cuerpo.changeColor("black");
        pata1.changeColor("black");
        pata2.changeColor("black");
        pata3.changeColor("black");
        pata4.changeColor("black");
    }
    /**
     * A continuación se creará el método para mover la araña a una posición específica:
     */
    public void moveAllTo(int coordenadaX, int coordenadaY){
        cara.moveTo(coordenadaX, coordenadaY);
        cuerpo.moveTo(coordenadaX - 8, coordenadaY + 15);
        pata1.moveTo(coordenadaX - 15, coordenadaY + 20);
        pata2.moveTo(coordenadaX -15, coordenadaY + 45);
        pata3.moveTo(coordenadaX - 20, coordenadaY + 38);
        pata4.moveTo(coordenadaX - 20, coordenadaY + 28);
    }
   
}

    

