public class Program {
    public static void main(String[] args) {
        System.out.println("start");
        Polygon polygon = new Polygon();
        polygon.addVertex(2.0, 1.0);
        polygon.addVertex(5.0, 0.0);
        polygon.addVertex(7.0, 5.0);
        polygon.addVertex(4.0, 6.0);
        polygon.addVertex(1.0, 4.0);

        Point highest = polygon.highestVertex();
        String highestStr = highest.toString();
        if (highestStr.equals("(4.0,6.0)"))
            System.out.println("\nhighestVertex - OK");
        else
            System.out.println("\nError in highestVertex. Your result " + highestStr + " Expected result (4.0,6.0)");

        double perimeter = polygon.calcPerimeter();
        if (Math.abs(perimeter-18.47754906310363)<=0.01)
            System.out.println("\ncalcPerimeter - OK");
        else
            System.out.println("\nError in calcPerimeter. Your result " + perimeter + " Expected result 18.478");

        double area = polygon.calcArea();
        if (Math.abs(area-22.5)<=0.01)
            System.out.println("\ncalcArea - OK");
        else
            System.out.println("\nError in calcArea. Your result " + area + " Expected result 22.5");

        Polygon biggerPolygon = new Polygon();
        biggerPolygon.addVertex(2.0, 1.0);
        biggerPolygon.addVertex(5.0, 0.0);
        biggerPolygon.addVertex(7.0, 5.0);
        biggerPolygon.addVertex(4.0, 7.0);
        biggerPolygon.addVertex(1.0, 4.0);

        boolean flag = polygon.isBigger(biggerPolygon);
        if (flag)
            System.out.println("\nError in isBigger. Your result is " + flag + " Expected result is false");
        else
            System.out.println("\nisBigger - OK");

        Point point = new Point(4.0, 6.0);
        int index = polygon.findVertex(point);
        if (index==3)
            System.out.println("\nfindVertex - OK");
        else
            System.out.println("\nError in findVertex. Your result = " + index + " Expected result = 3");

        Point nextVertex = polygon.getNextVertex(point);
        String nextVStr = nextVertex.toString();
        if (nextVStr.equals("(1.0,4.0)"))
            System.out.println("\nnextVertex - OK");
        else
            System.out.println("\nError in nextVertex. Your result " + nextVStr + " Expected result (1.0,4.0)");

        Polygon boundingBox = polygon.getBoundingBox();
        String boundingBoxStr = boundingBox.toString();
        if (boundingBoxStr.equals("The polygon has 4 vertices:\n((1.0,0.0),(7.0,0.0),(7.0,6.0),(1.0,6.0))"))
            System.out.println("\ngetBoundingBox - OK");
        else
        {
            System.out.println("\nError in getBoundingBox or in toString");
            System.out.println("Your bounding box is:\n" + boundingBox + "\nExpected bounding box is:\nThe polygon has 4 vertices:\n((1.0,0.0),(7.0,0.0),(7.0,6.0),(1.0,6.0))");
        }
        System.out.println("\nNote that this is only a basic test. Make sure you test all cases!");
        System.out.println("end");
    }
}
