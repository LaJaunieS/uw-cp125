package cp120.assignments.geo_shape;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

import abbot.finder.ComponentNotFoundException;
import abbot.finder.Matcher;
import abbot.finder.MultipleComponentsFoundException;
import abbot.finder.matchers.ClassMatcher;
import cp120.assignments.geo_shape.GeoPlane.DrawingSurface;
import cp120.d_list.DList;
import cp120.d_list.DNode;
import junit.extensions.abbot.ComponentTestFixture;

public class GeoPlaneTest extends ComponentTestFixture {
    /*instance to test constructor*/
    private GeoPlane gp = new GeoPlane();
    
    /*Instance to test methods*/
    private GeoPlane plane = new GeoPlane();
    
    private DList list = new DList();
    
    @Test
    public void testGeoPlane() {
        assertTrue(gp instanceof GeoPlane);
    }
    
    @Test
    public void testRun() {
        /*Frame created in GeoPlane context should be visible*/
        ClassMatcher m = new ClassMatcher(GeoPlane.DrawingSurface.class);
        System.out.println(m.matches(plane.getDrawingSurface()));
        
        plane.show();
        assertTrue(plane.getFrame().isVisible());
        /*Frame created in GeoPlane context should have Default Close Operation set to exit*/
        assertEquals(JFrame.EXIT_ON_CLOSE,plane.getFrame().getDefaultCloseOperation());
        /*Frame's content pane should equal Drawing Surface instance created by GeoPlane instance*/
        assertEquals(plane.getDrawingSurface(),plane.getFrame().getContentPane());
        
    }
    
    @Test
    public void testAddShape() {
        DNode node = new DNode();
        GeoOval go = new GeoOval(45,45);
        gp.addShape(go);
        assertEquals(go,gp.getList().getTail().getData());
    }

    @Test
    public void testRemoveShape() {
        DList list = gp.getList();
        GeoOval shape = new GeoOval(45,45);
        //adding second shape to test iterator where first match is false
        GeoOval secondShape = new GeoOval(45,45);
        gp.addShape(shape);
        gp.addShape(secondShape);
        //removes shape, returns true
        assertTrue(gp.removeShape(secondShape));
        //tries to remove already-removed shapes, so returns false
        assertFalse(gp.removeShape(secondShape));
    }

    @Test
    public void testRedraw() {
        //test if list is empty ending while condition?
        //come back to this one
        GeoPlane geoPlane = new GeoPlane(); 
        GeoOval geoOval= new GeoOval(45,45);
        DNode node = new DNode(geoOval);
        
        //clear the list
        geoPlane.getList().removeAll();
        //add shape to list head
        geoPlane.addShape(new GeoRectangle(45,45));
        //capture temporary return value- should return true since list is not empty
        assertFalse(geoPlane.getList().isEmpty());
        
        //clear the list again- now redraw should return false
        geoPlane.getList().removeAll();
        assertTrue(geoPlane.getList().isEmpty());
   }
    
    @Test
    public void testGetAndSetFrame() {
        JFrame frame = new JFrame();
        plane.setFrame(frame);
        assertEquals(frame, plane.getFrame());
    }
    
    @Test public void testGetAndSetDrawingSurface() {
        GeoPlane newPlane = new GeoPlane();
        DrawingSurface surface = newPlane.new DrawingSurface();
        newPlane.setDrawingSurface(surface);
        assertEquals(newPlane.getDrawingSurface(),surface);
    }
    
    @Test
    public void testPaintComponent() {
        GeoPoint origin = new GeoPoint(0,0);
        GeoPoint origin2 = new GeoPoint(450,450);
        GeoRectangle gr = new GeoRectangle(origin,Color.GREEN,125,75);
        GeoOval go = new GeoOval(origin2,125,444);
        go.setColor(Color.CYAN);
        plane.addShape(go);
        plane.addShape(gr);
        plane.show();
        //add assert
    }
    
    
}

    

