package quadtree;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Integer.parseInt;

public class Main {

	//-t outline quadtree
	//-x running personal filter
	//-e edge detection
	//-c image compression
	//-o <filename> root name of output file to write to
	//-i <filename> root name of input file


	public static void main(String[] args) throws IOException {
		//TODO: make this parse the command line arguments
		Quadtree image = null;
		String fileOut = null;
		boolean outline = false;
		boolean compress = false;
		boolean write = false;
		for (int i = 0; i < args.length; i++) {
			if(args[i].equals("-i") || args[i].equals("--input")) {
				image = createImage(args[i + 1]);
			}
		}

		//searches for relevant flags
		assert image!=null;
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
				case "-x":
					image.Filter(args[i + 1]);
					break;
				case "-c":
				case "--compression":
					compress = true;
					image.Compression(parseInt(args[i+1]));
					break;
				case "-e":
				case "--edge":
					image.EdgeDetection(parseInt(args[i+1]));
					break;
				case "-t":
				case "--outline":
					outline = true;
					break;
				case ("-o"):
				case ("--output"):
					write = true;
					fileOut = args[i+1];
					break;
			}
		}
		if(outline && compress){
			image.Outline(image);
		}

		if(write){
			writeImage(fileOut, image);
		} else{
			writeImage("output.ppm", image);
		}
	}

	private static Quadtree createImage(String fileIn) throws IOException {
		//reads in
		int rows = 0;
		int columns = 0;

		ArrayList<String> fileLines = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(new File(fileIn)));
		String line;
		while ((line = br.readLine()) != null) {
			String[] lineArr = line.split("\\s+");
			Collections.addAll(fileLines, lineArr);
		}
		br.close();

		//cleans input
		for (int j = 0; j < fileLines.size(); j++) {
			if (fileLines.get(j).equals("255")) {
				rows = parseInt(fileLines.get(j - 1));
				columns = parseInt(fileLines.get(j - 2));
				fileLines.subList(0, j + 1).clear();
				break;
			}
		}
		for (int j = 0; j < fileLines.size(); j++) {
			if (fileLines.get(j).equals("")) {
				fileLines.remove(j);
			}
		}


		//creates the image arraylist containing pixel objects
		ArrayList<Pixel[]> image = new ArrayList<Pixel[]>();
		int count = 0;
		for (int j = 0; j < rows; j++) {
			Pixel[] imageRow = new Pixel[columns];
			for (int k = 0; k < columns * 3; k += 3) {
				//System.out.println(fileLines.get(k+j*columns*3));
				//System.out.println(fileLines.get(k+ 1 + j*columns*3));
				//System.out.println(fileLines.get(k+ 2 + j*columns*3));
				//1209516
				Pixel temp = new Pixel(parseInt(fileLines.get(k + j * columns * 3)),
						parseInt(fileLines.get(k + 1 + j * columns * 3)),
						parseInt(fileLines.get(k + 2 + j * columns * 3)));
				imageRow[k / 3] = temp;
			}
			image.add(imageRow);
		}
		Quadtree test = new Quadtree(image, 0, 0, rows, columns);
		return test;
		/**&System.out.println(test.right.topLeftRow);
		 System.out.println(test.right.topLeftCol);
		 System.out.println(test.right.width);
		 System.out.println(test.right.height);**/
		//test.Compression();
		//test.Outline(test);
		//test.EdgeDetection();
		//test.Filters("negative");
		//image = test.image;
	}

	private static void writeImage(String fileOut, Quadtree tree) throws IOException {
		//takes the image and converts it back to a ppm image
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));
				writer.write("P3\n" + tree.width + " " + tree.height + "\n255 ");
				for(int j = 0; j < tree.image.size(); j++){
					for(int k = 0; k < tree.image.get(0).length; k++){
						writer.write("\n" + tree.image.get(j)[k].red + " " + tree.image.get(j)[k].green + " "
								+ tree.image.get(j)[k].blue + " ");
					}
				}
				writer.close();
	}







	private static ArrayList<String> createFile(String filename) throws IOException {
		ArrayList<String> fileLines = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		String line;
		while ((line = br.readLine()) != null) {
			String[] lineArr = line.split("\\s+");
			Collections.addAll(fileLines, lineArr);
		} br.close();
		return fileLines;
	}

	private static ArrayList<Pixel> cleanFile(ArrayList<String> fileLines) {
		int rows = 0;
		int columns = 0;
		int colorDepth = 0;
		for (int i = 0; i < fileLines.size(); i++) {
			if(fileLines.get(i).equals("255")){
				colorDepth = parseInt(fileLines.get(i));
				rows = parseInt(fileLines.get(i - 1));
				columns = parseInt(fileLines.get(i - 2));
				fileLines.subList(0, i + 1).clear();
				break;
			}
		}

		ArrayList<Pixel> image = new ArrayList<>();
		for (int j = 0; j < fileLines.size(); j+=3) {
			Pixel pixel = new Pixel(parseInt(fileLines.get(j)), parseInt(fileLines.get(j+1)), parseInt(fileLines.get(j+2)));
			image.add(pixel);
			/**
			Integer[] temp = new Integer[columns];
			for (int k = 0; k < columns; k++) {
				System.out.println(j * columns + k);
				temp[k] = parseInt(fileLines.get(j * columns + k));
			}**/
			//image.add(temp);
		} return image;
	}
}
