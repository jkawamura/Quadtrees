package quadtree;

import java.util.ArrayList;

public class Quadtree {

    String[] topLeft;
    String[] bottomRight;

    ArrayList<Integer[]> image;
    int height;
    int width;
    int rowCor;
    int colCor;
    int area = length*width;
    int colorDepth;
    int C;
    Quadtree right;
    Quadtree midRight;
    Quadtree left;
    Quadtree midLeft;


    public Quadtree(ArrayList<Integer[]> image){
        height = image.size();
        width = image.get(0).length/3;
        constructTree();
    }

    public void constructTree(){

        right = Quadtree();
        right.constructTree();
        left.constructTree();
        midRight.constructTree();
        midLeft.constructTree();
    }

    public void compression(){

    }

    private void compressionHelper(Integer[] coordinates){
        int counterR = 0;
        long meanColorR = 0;
        for(int i = coordinates[0]; i <= coordinates[2]; i++){
            for(int j = coordinates[1]; i <= coordinates[3] - 2; i+=3){
                meanColorR += image.get(i)[j];
                counterR ++;
            }
        }
        meanColorR = meanColorR/counterR;

        int counterG = 0;
        long meanColorG = 0;
        for(int i = coordinates[0]; i <= coordinates[2]; i++){
            for(int j = coordinates[1] + 1; i <= coordinates[3] - 1; i+=3){
                meanColorG += image.get(i)[j];
                counterG ++;
            }
        }
        meanColorG = meanColorG/counterG;


        int counterB = 0;
        long meanColorB = 0;
        for(int i = coordinates[0]; i <= coordinates[2]; i++){
            for(int j = coordinates[1] + 2; i <= coordinates[3]; i+=3){
                meanColorB += image.get(i)[j];
                counterB ++;
            }
        }
        meanColorB = meanColorB/counterB;

        int counterTotal = 0;
        long squaredError = 0;
        for(int i = coordinates[0]; i <= coordinates[2]; i++){
            for(int j = coordinates[1]; i <= coordinates[3] - 2; i+=3){
                squaredError += (Math.pow((image.get(i)[j] - meanColorR), 2) +
                        Math.pow((image.get(i)[j + 1] - meanColorG), 2) +
                        Math.pow((image.get(i)[j + 2] - meanColorB), 2));
                counterTotal ++;
            }
        }
        squaredError = squaredError/counterTotal;

        if(squaredError > threshold){
            right = Quadtree(Integer());

        }

        compressionHelper()
    }


}
