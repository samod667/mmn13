/**
 * Point class represents a point (x,y) (on the positive
 * quarter only) in the cartesian coordinate system.
 *
 * @author Dor Samoha
 * @version Oct 29th, 2020
 */
//Class Point
public class Point {

    //Declare variables//
    private double _radius, _alpha;
    private final int POWER_OF_TWO = 2;
    private final int DEFAULT_VAL = 0;
    private final double ALPHA_DEFAULT_VALUE = 90;
    private final double RADIAN_FACTOR = 180;
    private final double ROUND_FACTOR = 10000.0;

    /**
     * Constructs a new Point with the specified x & y coordinates
     * Both values most be positive or 0. If not, they will be started to 0.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
//CONSTRUCTORS//
    public Point(double x, double y) {
        if (x <= DEFAULT_VAL) {
            x = DEFAULT_VAL;
        }
        if (y <= DEFAULT_VAL) {
            y = DEFAULT_VAL;
        }
        //CALCULATE RADIUS AND ALPHA
        this._radius = this.getRadius(x, y);
        this._alpha = this.getAlpha(x, y);
        ////////////////////////////
    }


    /**
     * Constructs a new class Point. Copy constructors, construct a Point using another Point.
     *
     * @param other the Point which to constructs the new object from.
     */
    public Point(Point other) {
        if (other != null) {
            this._alpha = other._alpha;
            this._radius = other._radius;
        }
    }

    ///HELPER METHODS//////////////////////////////////////////////
    //CALCULATE RADIUS//
    private double getRadius(double x, double y) {
        return Math.sqrt(Math.pow(x, POWER_OF_TWO) + Math.pow(y, POWER_OF_TWO));
    }

    //CALCULATE ALPHA//
    private double getAlpha(double x, double y) {
        if (x == DEFAULT_VAL) {
            return ALPHA_DEFAULT_VALUE;
        }
        return Math.atan(y / x) * RADIAN_FACTOR / Math.PI;
    }

    //X TO RADIANS METHOD
    private double _xToRadian(double radius, double alpha) {
        return Math.round(radius * Math.cos(alpha * Math.PI / RADIAN_FACTOR) * ROUND_FACTOR) / ROUND_FACTOR;
    }

    //Y TO RADIANS METHOD
    private double _yToRadian(double radius, double alpha) {
        return Math.round(radius * Math.sin(alpha * Math.PI / RADIAN_FACTOR) * ROUND_FACTOR) / ROUND_FACTOR;
    }
    ////////////////////////////////////////////////////////////////////


    /**
     * This method return the X coordinates of the Point
     *
     * @return the x
     */
    ///GETTERS//////////////////////////////////////////////
    public double getX() {
        return this._xToRadian(this._radius, this._alpha);
    }


    /**
     * This method return the X coordinates of the Point.
     *
     * @return the y
     */
    public double getY() {
        return this._yToRadian(this._radius, this._alpha);
    }
    //////////////////////////////////////////////////////


    /**
     * This method sets an X value of a point. The new value must be positive or 0
     * otherwise it will be 0
     *
     * @param num value which will be set to the X coordinate. Must be num >= 0.
     */
    ///SETTERS/////////////////////////////////////////////
    public void setX(double num) {
        double y = getY();
        if (num >= DEFAULT_VAL) {
            this._alpha = getAlpha(num, y);
            this._radius = getRadius(num, y);
        }
    }

    /**
     * This method sets an Y value of a point. The new value must be positive or 0
     * otherwise it will be 0
     *
     * @param num value which will be set to the Y coordinate. Must be num >= 0.
     */
    public void setY(double num) {
        double x = getX();
        if (num >= DEFAULT_VAL) {
            this._alpha = getAlpha(x, num);
            this._radius = getRadius(x, num);
        }
    }
    ///////////////////////////////////////////////////////////

    ///TO STRING METHOD///////////////////////////////////////
    public String toString() {
        return "(" + Math.round(_xToRadian(this._radius, this._alpha) * ROUND_FACTOR) / (double) ROUND_FACTOR + "," + Math.round(_yToRadian(this._radius, this._alpha) * ROUND_FACTOR) / (double) ROUND_FACTOR + ")";
    }
    /////////////////////////////////////////////////////////


    /// OTHER METHODS//////////////////////////////////////////

    /**
     * Checks if a given Point is equals to this Point.
     *
     * @param other the other Point
     * @return true if this > other, false otherwise
     */
    public boolean equals(Point other) {
        return this._radius == other._radius && this._alpha == other._alpha;
    }

    /**
     * Checks if this Point is above a given Point
     *
     * @param other the other Point
     * @return true if this is above other Point, false otherwise.
     */
    public boolean isAbove(Point other) {
        return this._yToRadian(this._radius, this._alpha) > other._yToRadian(other._radius, other._alpha);
    }

    /**
     * Checks if this Point is under other Point.
     *
     * @param other the other Point
     * @return true if is under, false otherwise
     */
    public boolean isUnder(Point other) {
        return other.isAbove(this);
    }

    /**
     * Checks if this Point is left to other Point
     *
     * @param other the other Point
     * @return true if this is left to other Point, false otherwise
     */
    public boolean isLeft(Point other) {
        return this._xToRadian(this._radius, this._alpha) < other._xToRadian(other._radius, other._alpha);
    }

    /**
     * Checks if this Point is right to other Point
     *
     * @param other the other Point
     * @return true if this is right to other Point, false otherwise
     */
    public boolean isRight(Point other) {
        return other.isLeft(this);
    }

    /**
     * Measures the distance between two different Points
     *
     * @param other the other Point to check the distance from
     * @return the distance between the two points
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((other._yToRadian(other._radius, other._alpha) - this._yToRadian(this._radius, this._alpha)), POWER_OF_TWO) + Math.pow((other._xToRadian(other._radius, other._alpha) - this._xToRadian(this._radius, this._alpha)), POWER_OF_TWO));
    }

    /**
     * Moves a point. If either coordinate becomes negative the point remains unchanged.
     *
     * @param dx the difference to add to X
     * @param dy the difference to add to Y
     */
    public void move(double dx, double dy) {
        double x = _xToRadian(this._radius, this._alpha) + dx;
        double y = _yToRadian(this._radius, this._alpha) + dy;

        if (x >= DEFAULT_VAL && y >= DEFAULT_VAL) {
            this._alpha = this.getAlpha(x, y);
            this._radius = this.getRadius(x, y);
        }
    }
}
