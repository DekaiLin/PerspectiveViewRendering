package client.testPages;

import geometry.Vertex3D;
import polygon.Polygon;
import polygon.PolygonRenderer;
import polygon.Shader;
import windowing.drawable.Drawable;
import windowing.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class StarburstPolygonTest {

    private static final int NUM_RAYS = 90;
    private static final double FRACTION_OF_PANEL_FOR_DRAWING = 0.9;

    private final PolygonRenderer renderer;
    private final Drawable panel;
    Vertex3D center;

    private Vertex3D vertices[];

    public StarburstPolygonTest(Drawable panel, PolygonRenderer polygonRenderer) {
        this.panel = panel;
        this.renderer = polygonRenderer;

        makeCenter();
        render();
    }

    private void render() {
        double radius = computeRadius();
        double angleDifference = (2.0 * Math.PI) / NUM_RAYS;
        double angle = 0.0;

        for(int ray = 0; ray < NUM_RAYS; ray++) {
            Vertex3D radialPoint1 = radialPoint(radius, angle);
            angle = angle + angleDifference;
            Vertex3D radialPoint2 = radialPoint(radius, angle);

            vertices = new Vertex3D[3];
            vertices[0] = center;
            vertices[1] = radialPoint1;
            vertices[2] = radialPoint2;

            Polygon polygon = Polygon.makeEnsuringClockwise(vertices);

            renderer.drawPolygon(polygon, panel, null);
            }


//        Vertex3D p1 = new Vertex3D(50.0, 201.0, 0.0, Color.WHITE);// top left
//        Vertex3D p2 = new Vertex3D(230.0, 201.0, 0.0, Color.WHITE); // top right
//        Vertex3D p3 = new Vertex3D(150, 10, 0.0, Color.WHITE); // bottom
//
//        vertices = new Vertex3D[3];
//
//        vertices[2] = p1;
//        vertices[0] = p2;
//        vertices[1] = p3;
//
//        Polygon polygon = Polygon.makeEnsuringClockwise(vertices);
//
//        System.out.println("_++++++++++: ");
//
//        System.out.println("left.get(0) is is is: " + polygon.leftChain().get(0));
//        System.out.println("right.get(0) is is is: " + polygon.rightChain().get(0));
//
//        System.out.println("left.get(1) is is is: " + polygon.leftChain().get(1));
//        System.out.println("right.get(1) is is is: " + polygon.rightChain().get(1));
//
//        renderer.drawPolygon(polygon, panel, null);
    }


    private void makeCenter() {
        int centerX = panel.getWidth() / 2;
        int centerY = panel.getHeight() / 2;
        center = new Vertex3D(centerX, centerY, 0, Color.BLACK);
    }

    private Vertex3D radialPoint(double radius, double angle) {
        double x = center.getX() + radius * Math.cos(angle);
        double y = center.getY() + radius * Math.sin(angle);
        return new Vertex3D(x, y, 0, Color.BLACK);
    }

    private double computeRadius() {
        int width = panel.getWidth();
        int height = panel.getHeight();

        int minDimension = width < height ? width : height;

        return (minDimension / 2.0) * FRACTION_OF_PANEL_FOR_DRAWING;
    }
}
