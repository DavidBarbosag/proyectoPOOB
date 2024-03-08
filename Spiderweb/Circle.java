import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle{

    public static final double PI=3.1416;
    
    private double diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    

    public Circle(){
        diameter = 30;
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }
 
    
    public Circle makeNewCircle(int xPosition, int yPosition) {
        Circle newCircle = new Circle(); 
        newCircle.setXPosition(xPosition);
        newCircle.setYPosition(yPosition);
        newCircle.setDiameter(50); 
        newCircle.setColor("black"); 
        newCircle.setVisible(true);
        draw();
        return newCircle;
    }

    
    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }
    
    public double getDiameter() {
    return this.diameter;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }
    
    public int getXPosition() {
        return this.xPosition;
    }    
    
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }
    
    public int getYPosition() {
        return this.yPosition;
    } 

    public void setColor(String color) {
        this.color = color;
    }
    
    public String getColor(){
        return this.color;
    }
    
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Shows the area of the Circle
     */
    public double area(){
        double area;
        return area = (diameter * diameter * PI) / 4;   
    }
    
    /**
     * Duplicates the area of the Circle
     */
    public void duplicate(){
        erase();
        double newArea = area() * 2;
        double newDiameter = 2 * (Math.sqrt(newArea / PI));
        this.setDiameter(newDiameter);
        draw();
    }
    
    /**
     * Divides the area of the Circle
     */
    public void divide(){
        erase();
        double newArea = area() / 2;
        double newDiameter = 2 * (Math.sqrt(newArea / PI));
        this.setDiameter(newDiameter);
        draw();
    }
    
    /**
     * makes the Circle bounce a specific times and bounce to a specific height, that height is randomly choosen
     * and is lower than the height you specify.
     */
    public void bounce(int times, int height){
        Random random = new Random();
        int bounceHeight;
        bounceHeight = random.nextInt(height);
        for(int i = 0; i < times; i++){
            slowMoveVertical(bounceHeight);
            slowMoveVertical(- bounceHeight);
        }
        
    }

       
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    

    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    public void eraseCircle(){
        erase();
    }

    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }

    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    public void moveTo(int coordenadaX, int coordenadaY){
        erase();
        yPosition = coordenadaY;
        xPosition = coordenadaX;
        draw();
    }



}
