package quadtree;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO: make this parse the command line arguments
		ArrayList<String> fileLines;

		//loops through all the sub arrays
		for(int i = 0; i < args.length; i++){

			//
			if(args[i].equals("-i")){
				fileLines = createFile(args[i+1]);
			} else if(args[i].equals("-o")){

			}
		}
		if(search("-e", args)){
			Quadtree compressTree = new Quadtree(fileLines);
			compressTree
			compressTree.compression();

		}

		for(int i = 0; i < args.length; i++){
			if(args[i].compareTo("-i") == 0){
				process(args[i+1]);
			}
		}
		for(String arg : args){
			switch(arg){
				case "-e":

				case "-c":

				case "-x":

				case "-o":



			}
		}


		int i = 0;
		int rows = 0;
		int columns = 0;
		int colorDepth = 0;
		while(i < fileLines.size()){
			try{
				Integer.parseInt(fileLines.get(i));
			} catch(Exception e){
				i++;
				continue;
			}
			int temp = Integer.parseInt(fileLines.get(i));
			if(temp<=255 && temp>=0){
				rows = Integer.parseInt(fileLines.get(i));
				columns = Integer.parseInt(fileLines.get(i+1)) * 3;
				colorDepth = Integer.parseInt(fileLines.get(i+2));
				fileLines.subList(0, i+3).clear();
				break;
			} else{
				i++;
			}
		}
		//System.out.println(rows + "" + columns + "" + colorDepth);
		//for(String lines : fileLines){
		//	System.out.println(lines);
		//}
		System.out.println(fileLines.size());
		ArrayList<Integer[]> image = new ArrayList<>();
		for(int j = 0; j < rows; j++){
			Integer[] temp = new Integer[columns];
			for(int k = 0; k < columns; k++){
				System.out.println(j*columns+k);
				temp[k] = Integer.parseInt(fileLines.get(j*columns+k));
			} image.add(temp);
		}
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
		 **/
		BufferedWriter writer = new BufferedWriter(new FileWriter("test.ppm"));
		writer.write("P3\n" + columns/3 + " " + rows + "\n255\n");
		for(String[] array : image){
			for(int j = 0; j < columns ; j+=3)
				writer.write("\n" + array[j] + " " + array[j+1] + " " + array[j+2]);
		}
		writer.close();
	}

	private static boolean search(String string, String[] args){
		for(String arg : args){
			return arg.equals(string);
		}

	}

	private static ArrayList<String> createFile(String filename) throws IOException {
		ArrayList<String> fileLines = new ArrayList<String>(); BufferedReader br =
				new BufferedReader(new FileReader(new File(filename)));
		String line;
		while ((line = br.readLine()) != null) {
			String[] lineArr = line.split("\\s+");
			Collections.addAll(fileLines, lineArr);
		} br.close();
		return fileLines;
	}
}
