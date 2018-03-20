package cp120.assignments.geo_shape;
/*Driver used for demo-ing various functionality of the package*/

import java.awt.Color;

public class Driver {

    public static void main(String[] args) {
        /*Instantiate a bunch of shapes*/
        GeoPlane gp = new GeoPlane();
        GeoLine lineY = new GeoLine(new GeoPoint(250,0),Color.ORANGE,new GeoPoint(250,500));
        GeoLine lineX = new GeoLine(new GeoPoint(0,250),Color.GREEN,new GeoPoint(500,250));
        GeoLine lineCross1 = new GeoLine(new GeoPoint(0,0),Color.BLUE,new GeoPoint(500,500));
        GeoLine lineCross2 = new GeoLine(new GeoPoint(500,0),Color.BLUE, new GeoPoint(0,500));
        GeoRectangle gr = new GeoRectangle(new GeoPoint(200,200),Color.CYAN,100,100);
        GeoRectangle goCtr = new GeoOval(new GeoPoint(200,200),Color.RED,100,100);
        GeoOval goCross1 = new GeoOval(new GeoPoint(242,0),Color.MAGENTA,16,500);
        GeoOval goCross2 = new GeoOval(new GeoPoint(0,242),Color.MAGENTA,500,16);
        
        /*Configure shapes as desired*/
        lineCross1.setEdgeWidth(10);
        lineCross2.setEdgeWidth(10);
        
        /*Add the shapes to the GeoPlane's linked list- will iterate through linked list 
         * containing the GeoShapes*/
        GeoShape[] arrOfShapes = { lineCross1, lineCross2, goCross1, goCross2,
                gr, goCtr, lineY, lineX };

        for (GeoShape shape: arrOfShapes)
            gp.addShape(shape);
        
        /*Instantiate the drawing surface and render Shape components*/
        gp.show();
        
    }

}
