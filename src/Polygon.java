/**
 * Polygon class represents a polygon on the first positive quarter only in the cartesian system.
 *
 * @author Dor Samoha
 * @version Nov 6th, 2020
 */
public class Polygon {
///DECLARING VARIABLES///////
    Point[] _vertices;
    int _noOfVertices;

    private final int MAX_NUM_OF_VERTICES = 10;
    private final double POWER_OF_TWO = 2.0;
    /////////////////////////////////////
    /**
     * Instantiates a new Polygon with default values.
     */
    ///CONSTRUCTORS///////////////////////
    public Polygon() {
        this._vertices = new Point[MAX_NUM_OF_VERTICES];
        this._noOfVertices = 0;
    }
    ///////////////////////////////////////

    ///HELPER PRIVATE METHODS///////////////////////

    private double calculateTrianglePerimeter(Point a, Point b, Point c){
        double aLength = calculateSegmentLength(a, b);
        double bLength = calculateSegmentLength(b,c);
        double cLength = calculateSegmentLength(c, a);

        return aLength + bLength + cLength;
    }

    private double calculateSegmentLength(Point a, Point b){
        if(a.getY() == b.getY()){
            return Math.abs(a.getX() - b.getX());
        } else {
            return Math.sqrt(Math.pow(a.getX() - b.getX(), POWER_OF_TWO) + Math.pow(a.getY() - b.getY(), POWER_OF_TWO));
        }

    }

    private Point lowestPoint(){
        Point p = _vertices[0];
        for (int i = 0; i < _noOfVertices; i++) {
            if(_vertices[i].isUnder(p)){
                p = _vertices[i];
            }
        }
        return new Point(p);
    }

    private Point leftestPoint(){
        Point p = _vertices[0];

        for (int i = 0; i < _noOfVertices; i++) {
            if(_vertices[i].isLeft(p)){
                p = _vertices[i];
            }
        }
        return new Point(p);
    }

    private Point rightestPoint(){
        Point p = _vertices[0];

        for (int i = 0; i < _noOfVertices; i++) {
            if(_vertices[i].isRight(p)){
                p = _vertices[i];
            }
        }
        return new Point(p);
    }
    ////////////////////////////////////////

    ///METHODS/////////////////////////////

    /**
     * Add vertex point in the next available place in array
     *
     * @param x the x parameter of point
     * @param y the y parameter of point
     * @return true if vertex was added, false if array if full and vertex was not added
     */
    public boolean addVertex(double x, double y) {
        if (_noOfVertices == MAX_NUM_OF_VERTICES) {
            return false;
        }
        _vertices[_noOfVertices++] = new Point(x, y);
        return true;
    }

    /**
     * Get the highest point in Polygon
     *
     * @return The highest point. 0 if no point were found.
     */
    public Point highestVertex() {
        if (_noOfVertices == 0) {
            return null;
        } else {
            Point highestYPoint = _vertices[0];
            for (int i = 1; i < _noOfVertices; i++) {
                if (_vertices[i].isAbove(highestYPoint)) {
                    highestYPoint = _vertices[i];
                }
            }
            return new Point(highestYPoint);
        }
    }

    public String toString() {
        if(_noOfVertices != 0){
            String counterString = "("+_vertices[0].toString();
            for (int i = 1; i < _noOfVertices; i++) {
                counterString += "," + _vertices[i].toString();
            }
            return "The polygon has " + _noOfVertices + " vertices: \n" + counterString + ")";
        } else {
            return "The polygon has " + _noOfVertices + " vertices.";
        }
    }

    /**
     * Calculate the Polygon Perimeter
     *
     * @return 0 if Polygon has 1-2 points. If Polygon has 2 points value return is the length of the segment. Otherwise the whole perimeter of Polygon will be returned
     */
    public double calcPerimeter(){
        if(_noOfVertices == 0 || _noOfVertices == 1){
            return 0;
        } else if(_noOfVertices == 2){
            return calculateSegmentLength(_vertices[0], _vertices[1]);
        } else {
            double perimeter = 0;
            for (int i = 1; i < _noOfVertices; i++) {
              perimeter += calculateSegmentLength(_vertices[i - 1], _vertices[i]);
            }
            return perimeter;
        }
    }

    /**
     * Calculates the area of the Polygon
     *
     * @return The area of the Polygon. If the Polygon has less then 3 point, 0 will be returned.
     */
    public double calcArea(){
        double area = 0;

        if(_noOfVertices < 3){
            return 0;
        } else{

            for (int i = 1; i < _noOfVertices - 1; i++) {
                Point a = _vertices[0];
                Point b = _vertices[i];
                Point c = _vertices[i + 1];

                double ab = calculateSegmentLength(a,b);
                double bc = calculateSegmentLength(b,c);
                double ca = calculateSegmentLength(c,a);

                double s = calculateTrianglePerimeter(a, b ,c) / POWER_OF_TWO;


                area += Math.sqrt(s*(s-ab)*(s-bc)*(s-ca));
            }
        }
        return area;
    }

    /**
     * Checking if this polygon is bigger then the other Polygon
     *
     * @param other the other Polygon
     * @return true if this Polygon is bigger. if other Polygon is bigger false will be returned
     */
    public boolean isBigger(Polygon other){
        return this.calcArea() > other.calcArea();
    }

    /**
     * Find a vertex in Polygon
     *
     * @param p the reference vertex to look for
     * @return the index number of the vertex in the vertices array
     */
    public int findVertex(Point p){
        for (int i = 0; i < _noOfVertices; i++) {
            if(_vertices[i].equals(p)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the next vertex in the array. If the reference point is the last vertex in the array, the first vertex will be returned. If there is only one vertex in array, it will be returned
     *
     * @param p the reference point
     * @return The next vertex in the array
     */
    public Point getNextVertex(Point p){
        if(_noOfVertices == 1){
            return new Point(_vertices[0]);
        }else{
            for (int i = 0; i < _noOfVertices; i++) {
                if(_vertices[i].equals(p)){
                    if(i == _noOfVertices - 1){
                        return new Point(_vertices[0]);
                    } else{
                        return new Point(_vertices[i + 1]);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Get bounding box Polygon.
     *
     * @return the bounding square as Polygon. If this Polygon has less then 3 vertices, return null
     */
    public Polygon getBoundingBox(){
        Polygon boundingBox = new Polygon();

        if(_noOfVertices < 3){
            return null;
        }

        boundingBox.addVertex(leftestPoint().getX(), lowestPoint().getY());
        boundingBox.addVertex(rightestPoint().getX(), lowestPoint().getY());
        boundingBox.addVertex(rightestPoint().getX(), highestVertex().getY());
        boundingBox.addVertex(leftestPoint().getX(), highestVertex().getY());

        return boundingBox;
    }

}
