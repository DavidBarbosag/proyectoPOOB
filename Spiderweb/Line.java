  import java.awt.geom.Line2D;
import java.awt.BasicStroke;
/**
 * Clase dedicada a construir lineas
 */
public class Line {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private String color;
    private boolean isVisible;
    
    /**
     * Método constructor de la clase Line
     */
    public Line() {
        x1 = 1;
        y1 = 1;
        x2 = 2;
        y2 = 2;
        color = "black";
        isVisible = false;
    }

    /**
     * Hace visible la linea
     */
    public void makeVisible() {
        isVisible = true;
        draw();
    }
    
    /**
     * Hace invisible la linea
     */
    public void makeInvisible() {
        erase();
        isVisible = false;  
    }

    /**
     * Mueve la linea a unas coordenadas especificas
     * @param dx a donde se va a mover en x.
     * @param dy a donde se va a mover en y.
     */
    public void move(int dx, int dy) {
        erase();
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
        draw();
    }

    /**
     * Cambia color de la linea
     * @param newColor indica el nuevo color de la línea.
     */
    public void changeColor(String newColor) {
        color = newColor;
        draw();
        makeVisible();
    }

        
    private void draw() {
        if (isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, new Line2D.Double(x1, y1, x2, y2));
        }
    }

    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    
    /**
     * Rota la linea a un angulo en especifico
     * @angle 
     */
    public void rotate(double angle) {
        // Calcula las coordenadas relativas de (x2, y2) respecto a (x1, y1)
        double dx = x2 - x1;
        double dy = y2 - y1;

        // Convierte el ángulo a radianes
        double radians = Math.toRadians(angle);

        // Aplica una rotación alrededor del punto (0, 0)
        double newX = dx * Math.cos(radians) - dy * Math.sin(radians);
        double newY = dx * Math.sin(radians) + dy * Math.cos(radians);

        // Actualiza las coordenadas absolutas de (x2, y2)
        x2 = (int) Math.round(newX + x1);
        y2 = (int) Math.round(newY + y1);

        // Dibuja la línea con las nuevas coordenadas
        draw();
    }

    public void setX1(int x1) {
        this.x1 = x1;
        draw(); // Vuelve a dibujar la línea con las nuevas coordenadas
    }

    // Getter para x1
    public int getX1() {
        return x1;
    }

    // Setter para y1
    public void setY1(int y1) {
        this.y1 = y1;
        draw(); // Vuelve a dibujar la línea con las nuevas coordenadas
    }

    // Getter para y1
    public int getY1() {
        return y1;
    }

    // Setter para x2
    public void setX2(int x2) {
        this.x2 = x2;
        draw(); // Vuelve a dibujar la línea con las nuevas coordenadas
    }

    // Getter para x2
    public int getX2() {
        return x2;
    }

    // Setter para y2
    public void setY2(int y2) {
        this.y2 = y2;
        draw(); // Vuelve a dibujar la línea con las nuevas coordenadas
    }

    // Getter para y2
    public int getY2() {
        return y2;
    }

    // Setter para color
    public void setColor(String color) {
        this.color = color;
        draw(); // Vuelve a dibujar la línea con el nuevo color
    }

    // Getter para color
    public String getColor() {
        return color;
    }

    // Setter para isVisible
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        if (isVisible) {
            draw(); // Si se hace visible, dibuja la línea
        } else {
            erase(); // Si se hace invisible, borra la línea
        }
    }

    // Getter para isVisible
    public boolean isVisible() {
        return isVisible;
    }
    
    public void eraseLine(){
        erase();
    }
}
