package quadtree;

public class Pixel {
    int red;
    int green;
    int blue;

    public Pixel(int red, int green, int blue){
        this.red=red;
        this.green=green;
        this.blue=blue;
    }

    //applies a negative filter to the pixel
    public void Negative(){
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
    }

    //applies grayscale filter to the pixel
    public void GrayScale(){
        double c = .3*red + .59*green + .113*blue;
        red = (int) c;
        green = (int) c;
        blue = (int) c;
    }

    public double SquaredError(Pixel meanColor){
        double error = Math.pow((red - meanColor.red), 2) +
                Math.pow((green - meanColor.green), 2) +
                Math.pow((blue - meanColor.blue), 2);
        return error;
    }

    public void outline(){
        red = 0;
        green = 0;
        blue = 0;
    }
    /**
     * ((r−Ci.r)2+ (g−Ci.g)2+ (b−Ci.b)2
     */
    /**
    grayscale - change each pixel(r, g, b)to(c, c, c)
    wherec = r*0.3+g*0.59+b*0.113.  tint - given a tint
    color(R, G, B), scale each pixel color(r, g, b)
     to(r/255*R, g/255*G, b/255*B).  Note  that  full
     white(255, 255, 255)will  be-come exactly(R, G, B),
     and everything else will be scaled proportionally
     between0 and R/G/B.
    **/
}
