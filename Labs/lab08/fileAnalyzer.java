import java.awt.Point;
import java.io.File;
import java.io.*;
import java.util.Collection.stream.Collector;
import java.util.logging.Formatter;
import java.util.Collection.stream.Collection;

import java.util.Collection.stream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class fileAnalyzer {

		Formatter writerFile;

        private List <Point> points;

        public List<Point> getPoints(){return points;}

		public static  void processLine(String line, List <Point> points )
      {
        
		String[] words = line.split(",\\s"); //split on commas
   //if no words
         if (words.length == 0)
         {
            return;
         }

        points.add(new Point(words[0],words[1],words[2]));
      }

   public static void processFile(Scanner input, List<Point> points)
   {
      while (input.hasNextLine())
      {
         processLine(input.nextLine(), points);
      }
   }


      //called from main - mostly just pass through important data structures
   public static void populateDataStructures( String filename,List <Point> points)
      throws FileNotFoundException
   {
      try (Scanner input = new Scanner(new File(filename))) {

         processFile(input, points);
      }
   }



   public static  String getFilename(String[] args)
   {
      if (args.length < 1)
      {
         System.err.println("Log file not specified.");
         System.exit(1);
      }

      return args[0];
   }


   public void openWriterFile()
   {
	
	try{
       writerFile = new Formatter("drawMe.txt");
	}
	catch(Exception e){

	System.out.println("something went wrong");
	
	}

   } 

public void writeToFile()
{
		points = points.stream()
			.filter(p->p.getZ() > 2.0)
			.map(p->p.scalePoint())
			.map(p -> p.translate(new Point(150.0, -37,0)))
			.collect(Collector.toList());

	for (int i = 0; i< points.size(); i++)
	{
	//just going to write to a file
	writerFile.format("%s,%s,%s%n", points.get(i).getX(),   points.get(i).getY(),  points.get(i).getZ());
	}

	return filteredData;
}


   public static void main(String[] args)
   {

                List<Point> points = new ArrayList<Point>();


       String filename = getFilename(args);



      try
      {
         populateDataStructures(filename, points);
          //after maybe write to the file

      }
      catch (FileNotFoundException e)
      {
         System.err.println(e.getMessage());
      }
   }
}
