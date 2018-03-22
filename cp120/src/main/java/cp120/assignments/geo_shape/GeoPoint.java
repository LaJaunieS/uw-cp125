
package cp120.assignments.geo_shape;

import java.awt.Graphics2D;
import java.util.Objects;

import cp120.d_list.*;

/**Encapsulates a graphical representation of a point (xco,yco)
 * on an instance of <code>DrawingSurface</code>
 * @author slajaunie
 *
 */
public class GeoPoint {
    /*The x coordinate of the GeoPoint*/
    private double xco = 0;
    /*The y coordinate of the GeoPoint*/
    private double yco = 0;
    
    /**Constructs a new GeoPoint, setting x-coordinate and y-coordinate to the given
     * double parameters.
     * @param xco a <code>double</code> that will be the x-coordinate of the newly-
     * constructed <code>GeoPoint</code>
     * @param yco a <code>double</code> that will be the y-coordinate of the newly-
     * constructed <code>GeoPoint</code>
     */
    public GeoPoint(double xco,double yco) {
        this.xco = xco;
        this.yco = yco;
    }
    
    /**Tests if the given <code>GeoPoint</code>s is equal to this 
     * <code>GeoPoint</code>
     * @param other the <code>GeoPoint</code> to be compared to this <code>
     * GeoPoint</code>
     * @param epsilon a <code>double</code> used to compare the <code>double</code>
     * fields.
     * @return boolean <code>true</code> if the <code>GeoPoint</code>s are equal, 
     * <code>false</code> if they are not.
     */
    public boolean equals(GeoPoint other,double epsilon) {
        boolean eq = false;
        /*if other is null*/
        if (other == null)
            eq=false;
        /*if fields point to same object*/
        else if (this == other)
            eq=true;
        /*test if fields are equal*/
        else {
            GeoPoint test = other;
            boolean fieldsEqual = (Math.abs((this.xco -test.getXco())) < epsilon) 
                    && (Math.abs((this.yco -test.getYco())) < epsilon);
            if (fieldsEqual)
                eq=true;
        } 
        return eq;
    }
    
    /**Setter method for the y-coordinate of the <code>GeoPoint</code>
     * @param yco a <code>double</code> representing the y-coordinate.
     */
    public void setYco(double yco) {
        this.yco = yco;
    }
    
    /**Setter method for the x-coordinate of the <code>GeoPoint</code>
     * @param xco a <code>double</code> representing the x-coordinate
     */
    public void setXco(double xco) {
        this.xco = xco;
    }
    
    /**Getter method for the y-coordinate of the <code>GeoPoint</code>
     * @return the <code>double</code> y-coordinate
     */
    public double getYco() {
        return yco;
    }
    
    /**Getter method for the x-coordinate of the <code>GeoPoint</code>
     * @return the <code>double</code> x-coordinate
     */
    public double getXco() {
        return xco;
    }
    
    /**Calculates the distance between this instance of <code>GeoPoint</code> and 
     * another <code>GeoPoint</code>.
     * @param other the other instance of <code>GeoPoint</code>
     * @return a <code>double</code> that is the distance between the two instances of
     * <code>GeoPoint</code>
     */
    public double distance(GeoPoint other) {
        double distance = 0;
        double otherXco = other.getXco();
        double otherYco = other.getYco();
        if (other != null) {
            double d1 = (Math.pow((this.xco - otherXco),2)) + 
                    (Math.pow((this.yco - otherYco),2));
            distance = Math.sqrt(d1);
        }
            
        return distance;
    }
    
    /**Overrides the <code>toString()</code> method on <code>Object</code>.  
     * @see java.lang.Object#toString()
     * @return a String in the format "(xco,yco)", with each <code>double</code>
     * formatted to 4 decimal places.
     */
    @Override
    public String toString() {
        return String.format("(%.4f,%.4f)",this.xco,this.yco);
     }
}
