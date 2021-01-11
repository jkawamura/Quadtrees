package quadtree;

import java.util.ArrayList;

public class Quadtree {

    ArrayList<Pixel[]> image;
    int topLeftRow;
    int topLeftCol;
    int width;
    int height;
    Quadtree left = null;
    Quadtree midLeft = null;
    Quadtree right = null;
    Quadtree midRight = null;

    public Quadtree(ArrayList<Pixel[]> image, int topLeftRow, int topLeftCol, int height, int width){
        this.image = image;
        this.topLeftRow = topLeftRow;
        this.topLeftCol = topLeftCol;
        this.width = width;
        this.height = height;
    }

    /**
     * Compression method repeatedly subdivides the image into
     */
    public void Compression(){
        Pixel meanPixel = MeanColor();
        double meanError = 0;
        //finds the squared error of each pixel
        for(int j = topLeftRow; j < height + topLeftRow; j++){
            for(int i = topLeftCol; i < width + topLeftCol; i++){
                meanError += image.get(j)[i].SquaredError(meanPixel);
            }
        } meanError = meanError/(height*width);

        if(height <= 1 || width <= 1){
            fillColor(meanPixel);
        } else if(meanError > 500){

            this.left = new Quadtree(image, topLeftRow, topLeftCol, height/2, width/2);
            left.Compression();

            this.midLeft = new Quadtree(image, topLeftRow + height/2 - 1, topLeftCol,
                    height - height/2, width/2);
            midLeft.Compression();

            this.right = new Quadtree(image,  topLeftRow, topLeftCol +
                    width/2 - 1,height/2, width - width/2);
            right.Compression();

            this.midRight = new Quadtree(image, topLeftRow + height/2 - 1, topLeftCol +
                    width/2 - 1, height - height/2, width - width/2);
            midRight.Compression();

        } else {
            fillColor(meanPixel);
        }
    }

    /**
     * fills a node with the mean color of that node
     * @param meanPixel the mean color
     */
    private void fillColor(Pixel meanPixel){
        for(int j = topLeftRow; j < height + topLeftRow; j++){
            for(int i = topLeftCol; i < width + topLeftCol; i++){
                image.get(j)[i].red = meanPixel.red;
                image.get(j)[i].green = meanPixel.green;
                image.get(j)[i].blue = meanPixel.blue;
            }
        }
    }

    /**
     * calculates the mean color of a given node
     * @return the mean color
     */
    private Pixel MeanColor(){
        int meanR = 0;
        int meanG = 0;
        int meanB = 0;

        //loops through all the pixels in the node and sums the rgb values
        for(int j = topLeftRow; j < height + topLeftRow;  j++){
            for(int i = topLeftCol; i < width + topLeftCol; i++){
                Pixel pixel = image.get(j)[i];
                meanR += pixel.red;
                meanG += pixel.green;
                meanB += pixel.blue;
            }
        }

        //averages the color value totals
        Pixel meanPixel = new Pixel(meanR/(height * width),
                meanG/(height * width), meanB/(height * width));

        return meanPixel;
    }

    /**
     * outlines each leaf in the quadtree
     * @param root the root of the quadtree
     */
    public void Outline(Quadtree root){
        for(int j = root.topLeftRow; j < root.height + root.topLeftRow; j++){
            for(int i = root.topLeftCol; i < root.width + root.topLeftCol; i++){
                if(i == root.topLeftCol || i == root.width + root.topLeftCol - 1 ||
                        j == root.topLeftRow || j == root.height + root.topLeftRow - 1){
                    image.get(j)[i].outline();
                }
            }
        }
        if(root.left == null && root.right == null && root.midLeft == null && root.midRight == null){
            return;
        } else if(root.left != null){
            Outline(root.left);
        } if(root.right != null){
            Outline(root.right);
        } if(root.midLeft != null){
            Outline(root.midLeft);
        } if(root.midRight != null){
            Outline(root.midRight);
        }
    }
}
