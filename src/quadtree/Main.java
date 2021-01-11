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
	//-0 <filename> root name of output file to write to
	//-i <filename> root name of input file

	public static void main(String[] args) throws IOException {
		//TODO: make this parse the command line arguments
		int rows = 0;
		int columns = 0;
		int colorDepth = 0;
		ArrayList<Pixel[]> image = null;

		//searches for -i flag
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-i")) {
				//reads in
				ArrayList<String> fileLines = new ArrayList<String>();
				BufferedReader br = new BufferedReader(new FileReader(new File(args[i + 1])));
				String line;
				while ((line = br.readLine()) != null) {
					String[] lineArr = line.split("\\s+");
					Collections.addAll(fileLines, lineArr);
				} br.close();


				//cleans input
				for (int j = 0; j < fileLines.size(); j++) {
					if(fileLines.get(j).equals("255")){
						colorDepth = parseInt(fileLines.get(j));
						rows = parseInt(fileLines.get(j - 1));
						columns = parseInt(fileLines.get(j - 2));
						fileLines.subList(0, j + 1).clear();
						break;
					}
				}

				//creates the image arraylist containing pixel objects
				image = new ArrayList<Pixel[]>();
				int count = 0;
				for(int j = 0; j < rows; j++){
					Pixel[] imageRow = new Pixel[columns];
					count++;
					for(int k = 0; k < columns*3; k+=3){
						Pixel temp = new Pixel(parseInt(fileLines.get(k+j*columns*3)),
								parseInt(fileLines.get(k+1+j*columns*3)),
								parseInt(fileLines.get(k+2+j*columns*3)));
						imageRow[k/3] = temp;
					} System.out.println(count);
					image.add(imageRow);

				}
				Quadtree test = new Quadtree(image, 0, 0, rows, columns);
				/**&System.out.println(test.right.topLeftRow);
				System.out.println(test.right.topLeftCol);
				System.out.println(test.right.width);
				System.out.println(test.right.height);**/
				test.Compression();
				//test.Outline(test);
				image = test.image;

			}
		}

		//takes the image and converts it back to a ppm image
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-o")) {
				BufferedWriter writer = new BufferedWriter(new FileWriter("test.ppm"));
				writer.write("P3\n" + columns + " " + rows + "\n255 ");
				for(int j = 0; j < image.size(); j++){
					for(int k = 0; k < image.get(0).length; k++){
						writer.write("\n" + image.get(j)[k].red + " " + image.get(j)[k].green + " "
								+ image.get(j)[k].blue + " ");
					}
				}
				writer.close();
			}
		}
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


		/**
		for(int i = 0; i < args.length; i++){
			if(args[i].equals("-0")){
				//reads in
				fileLines = createFile(args[i+1]);
			}
		}


		if(search("-e", args)){
			Quadtree compressTree = new Quadtree(fileLines);
			compressTree
			compressTree.compression();
		}

		else if(args[i].equals("-o")){

		}


		for(int i = 0; i < args.length; i++){
			if(args[i].compareTo("-i") == 0){
				process(args[i+1]);
			}
		}
		for(String arg : args){
			switch(arg){
				case "-e":
					//edge detection program
				case "-c":
					//image compression program
				case "-x":
					//
				case "-o":



			}**/



		//System.out.println(rows + "" + columns + "" + colorDepth);
		//for(String lines : fileLines){
		//	System.out.println(lines);
		//}
		//int count = 0;
		//for(String[] array : image){
			//System.out.println(count + "" + array.length);
			//count++;
			//for(String element : array){
				//System.out.println(element);
			//}
		//}
		/**
		BufferedWriter writer = new BufferedWriter(new FileWriter("test.ppm"));
		writer.write("P3\n128 128\n255\n");
			for(int j = 0; j < fileLines.size(); j+=3){
				writer.write("\n" + fileLines.get(j) + " " + fileLines.get(j+1)  + " " + fileLines.get(j+2));
			}
		writer.close();
		 *
		BufferedWriter writer = new BufferedWriter(new FileWriter("test.ppm"));
		writer.write("P3\n" + columns/3 + " " + rows + "\n255\n");
		for(String[] array : image){
			for(int j = 0; j < columns ; j+=3)
				writer.write("\n" + array[j] + " " + array[j+1] + " " + array[j+2]);
		}
		writer.close();
	}**/
	/**
	private static boolean search(String string, String[] args){
		for(String arg : args){
			return arg.equals(string);
		}
		return true;
	}**/
}
