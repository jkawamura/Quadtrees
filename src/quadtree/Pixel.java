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

    public void EdgeDetection(Pixel pixelTLeft, Pixel pixelTop, Pixel pixelTRight, Pixel pixelLeft, Pixel pixelRight,
                              Pixel pixelBLeft, Pixel pixelBot, Pixel pixelBRight){
        PixelMultiply(8);
        PixelSubtraction(pixelTLeft);
        PixelSubtraction(pixelTop);
        PixelSubtraction(pixelTRight);
        PixelSubtraction(pixelLeft);
        PixelSubtraction(pixelRight);
        PixelSubtraction(pixelBLeft);
        PixelSubtraction(pixelBot);
        PixelSubtraction(pixelBRight);
        PixelRound();
    }

    private void PixelSubtraction(Pixel pixel){
        this.red -= pixel.red;
        this.green -= pixel.green;
        this.blue -= pixel.blue;
    }

    private void PixelMultiply(Integer mult){
        this.red *= mult;
        this.green *= mult;
        this.blue *= mult;
    }

    private void PixelRound(){
        if(this.red + this.green + this.blue > 127){
            this.red = 255;
            this.green = 255;
            this.blue = 255;
        } else if(this.red + this.green + this.blue <= 127){
            this.red =  0;
            this.green = 0;
            this.blue = 0;
        }/**if(this.red > 255){
            this.red = 255;
        } else if(this.red < 0){
            this.red = 0;
        } if(this.green > 255){
            this.green = 255;
        } else if(this.green < 0){
            this.green = 0;
        } if(this.blue > 255){
            this.blue = 255;
        } else if(this.blue < 0){
            this.blue = 0;
        }**/
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
    //outputRed = (inputRed * .393) + (inputGreen *.769) + (inputBlue * .189)
    //outputGreen = (inputRed * .349) + (inputGreen *.686) + (inputBlue * .168)
    //outputBlue = (inputRed * .272) + (inputGreen *.534) + (inputBlue * .131)
    public void Sepia(){
        double red = .393*this.red + .769*this.green + .189*this.blue;
        double green = .349*this.red + .686*this.green + .168*this.blue;
        double blue = .272*this.red + .534*this.green + .131*this.blue;
        this.red = (int) red;
        this.green = (int) green;
        this.blue = (int) blue;
        if(this.red > 255){
            this.red = 255;
        } if(this.green > 255){
            this.green = 255;
        } if(this.blue > 255){
            this.blue = 255;
        }
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
