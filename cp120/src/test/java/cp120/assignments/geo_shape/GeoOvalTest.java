package cp120.assignments.geo_shape;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import cp120.assignments.geo_shape.GeoOval;

public class GeoOvalTest {
    /*test each new constructor*/
    
    //constructing using w, h parameters should pass through all constructors?
    //test coverage
    GeoOval go = new GeoOval(45.12345,45.12345);
    
    @Test
    public void testTwoParamConstructor() {
        GeoOval go = new GeoOval(45.12345,45.12345);
        assertTrue(go instanceof GeoOval);
    }
    
    @Test
    public void testThreeParamConstructor() {
        GeoPoint gp = new GeoPoint(0,0);
        GeoOval go = new GeoOval(gp,45.12345,45.12345);
        assertTrue(go instanceof GeoOval);
    }
    
    @Test
    public void testFourParamConstructor() {
        GeoPoint gp = new GeoPoint(0,0);
        GeoOval go = new GeoOval(gp,45.12345,45.12345);
        assertTrue(go instanceof GeoOval);
    }
    
    @Test
    public void testEquals() {
        /*if other not an instance of GeoOval, return false*/
        GeoOval go = new GeoOval(45,45);
        assertFalse(go.equals(new GeoRectangle(45,45),.00001));
        
        /*if fields match, return true*/
        assertTrue(go.equals(new GeoOval(45,45),.00001));
    }
    
    @Test
    public void testToString() {
        /*Condition if color is null*/
        String expString = "origin=(0.0000,0.0000),color=null," 
    + "edgeColor=#0000ff,edgeWidth=1.0000,width=45.1235,height=45.1235";
        assertEquals(expString,go.toString());
        
        /*Condition if color is given*/
        GeoOval go2 = new GeoOval(new GeoPoint(0,0),Color.BLUE,45,45);
        String expStringNotNull = "origin=(0.0000,0.0000),color=#0000ff," 
        + "edgeColor=#0000ff,edgeWidth=1.0000,width=45.0000,height=45.0000";
        assertEquals(expStringNotNull,go2.toString());
    }
    @Test
    public void testDraw() {
        GeoPlane gp = new GeoPlane();
        GeoOval go = new GeoOval(new GeoPoint(0,0),Color.RED,45.12345,45.12345);
        /*Shape2D object to be rendered*/
        Arc2D arc = new Arc2D.Double();
        /*Graphics context (created with gp.show())*/
        Graphics2D gtx = null;
        double epsilon = .000001;
        gp.addShape(go);
        gp.show();
        /*capture the Graphics context to be tested*/
        gtx = (Graphics2D) gp.getDrawingSurface().getGraphics();
        arc = (Arc2D.Double) go.draw(gtx);
        gtx.setColor(Color.RED);
        
        /*Test properties of the rendered Shape object*/
        /*X and Y coordinates of origin point correctly applied from 
        * constructor
        */
        assertEquals(0.0,arc.getX(),epsilon);
        assertEquals(0.0,arc.getY(),epsilon);
        /*Height and width values correctly applied from constructor*/
        assertEquals(45.12345,arc.getHeight(),epsilon);
        assertEquals(45.12345,arc.getWidth(),epsilon);
        /*Color correctly applied from constructor*/
        assertEquals(Color.RED,gtx.getColor());        
    }

    @Test
    public void testPerimeter() {
        GeoOval go = new GeoOval(new GeoPoint(50,50),Color.RED,20,6);
        String perimeter = String.format("%.4f", go.perimeter());
        assertEquals(new String("46.3851"),perimeter);
    }
    
    @Test 
    public void testArea() {
        GeoOval go = new GeoOval(new GeoPoint(50,50),Color.RED,20,6);
        String area = String.format("%.4f", go.area());
        assertEquals(new String("94.2478"),area);
    }
}
