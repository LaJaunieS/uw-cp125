package cp120.assignments.geo_shape;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import cp120.assignments.geo_shape.GeoPlane.DrawingSurface;

public class GeoLineTest {

    GeoPoint point1 = new GeoPoint(110,175);
    GeoPoint point2 = new GeoPoint(245,175);
    GeoLine line = new GeoLine(point1,point2);
    GeoLine otherLine = new GeoLine(point1,Color.CYAN,point2);
    GeoPlane plane = new GeoPlane();
    
    @Test
    public void testGeoLineTwoParamConstructor() {
        assertTrue(line instanceof GeoLine);
    }
    
    @Test
    public void testGeoLineThreeParamConstructor() {
        assertTrue(otherLine instanceof GeoLine);
    }
    
    @Test
    public void testEquals() {
        double epsilon = .000001;
        /*Test returns false if other is not an instance of GeoLine*/
        GeoLine gl = new GeoLine(new GeoPoint(0,0),new GeoPoint(150,150));
        assertFalse(gl.equals(new GeoRectangle(45,45)));
        
        /*Test returns true if endpoint coordinates are equal*/
        assertTrue(gl.equals(new GeoLine(new GeoPoint(0,0),
                                new GeoPoint(150,150)),
                                .00001));
    }

    @Test
    public void testGetStart() {
        assertEquals(point1,line.getStart());
    }
    
    @Test
    public void testSetStart() {
        GeoPoint point3 = new GeoPoint(15,15);
        line.setStart(point3);
        assertEquals(point3,line.getStart());
    }

    @Test
    public void testGetEnd() {
        assertEquals(point2,line.getEnd());
    }
    
    @Test 
    public void testSetColor() {
        GeoLine line = new GeoLine(point1,point2);
        line.setColor(Color.CYAN);
        /*Should override shape getColor method, set edgeColor instead*/
        assertEquals(Color.CYAN,line.getEdgeColor());
    }

    @Test
    public void testSetEnd() {
        GeoPoint point4 = new GeoPoint(275,275);
        line.setEnd(point4);
        assertEquals(point4,line.getEnd());
    }

    @Test
    public void testLength() {
        assertEquals(135,line.length(),.00001);
    }

    @Test
    public void testSlope() {
        assertEquals(0.0,line.slope(),.00001);
    }
    
    
    @Test
    public void testDraw() {
        GeoPlane gp = new GeoPlane();
        GeoLine gl = new GeoLine(new GeoPoint(0,0),Color.RED,new GeoPoint(150,150));
        /*Shape2D object to be rendered*/
        Line2D line = new Line2D.Double(gl.getOrigin().getXco(),
                                        gl.getOrigin().getYco(),
                                        gl.getEnd().getXco(),
                                        gl.getEnd().getYco());
        /*Graphics context (created with gp.show())*/
        Graphics2D gtx = null;
        Line2D ln = null;
        double epsilon = .000001;
        gp.addShape(gl);
        gp.show();
        /*create the shape to be tested*/
        gtx = (Graphics2D) gp.getDrawingSurface().getGraphics();
        ln = (Line2D) gl.draw(gtx);
        
        /*Test properties of the rendered Shape object*/
        /*X and Y coordinates of origin point correctly applied from 
        * constructor
        */
        assertEquals(0.0,ln.getX1(),epsilon);
        assertEquals(150.0,ln.getX2(),epsilon);
        assertEquals(0.0,ln.getY1(),epsilon);
        assertEquals(150.0,ln.getY2(),epsilon);
        /*Color correctly applied from constructor*/
        assertEquals(Color.RED,gtx.getColor());
                
    }
 
}
