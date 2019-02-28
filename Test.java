import java.*;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.nio.*;

public class Test {

  public static void main(String[] args) {

    if(args.length == 0) {
      System.out.println("READ A FILE YOU IDIOT");
    }
    ArrayList<String> lines = new ArrayList<>();
    try {
      FileReader fr = new FileReader(args[0]);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while((line = br.readLine()) != null) {
        lines.add(line);
      }
      br.close();
    } catch(Exception e) {}
    
    int nPhotos = Integer.parseInt(lines.remove(0));
    Photo[] photos = new Photo[nPhotos];
    int z = 0;
    for(String line : lines) {
      String[] things = line.split(" ");
      boolean horizontal = (things[0].equals("H"));
      int i = Integer.parseInt(things[1]);
      HashSet<Integer> tags = new HashSet<>();
      for(int u = 2; u < things.length; u++) {
        tags.add(things[u].hashCode());
      }
      photos[z] = new Photo();
      photos[z].id1 = z;
      photos[z].isVertical = !horizontal;
      photos[z].tags = tags;

      z++;
    }

    ArrayList<Photo> h = new ArrayList<>();//READ THIS FROM SOMEWHERE
    ArrayList<Photo> v = new ArrayList<>();//READ THIS FROM SOMEWHERE

    Brain brain = new Brain();
    ArrayList<Photo> arr = new ArrayList<>();//XXX Get this from Brain

    for(Photo p : photos) {
      if(!p.isVertical) {
        h.add(p);
        arr.add(p);//XXX DELET THIS
      }
      else v.add(p);
    }
    //Convert to array
    Photo[] verticalPhotos = new Photo[v.size()];
    for(int i = 0; i < v.size(); i++) {
     verticalPhotos[i] = v.get(i); 
    }
    
    brain.horizontalSlides = h;
    brain.verticalPhotos = verticalPhotos;
    brain.fillCombSlides();
    brain.putStuffInAllSlides();
    brain.orderSlides();
    for(Photo p : brain.outputOrder) arr.add(p);
    //*******************************************//
    //Fix verticals
    /*
    for(int i = 1; i < v.size(); i += 2) {
      v.get(i).id2 = v.get(i-1).id1;
      arr.add(v.get(i));
    }
    */
    /*
    System.out.println(arr.size());
    for(Photo p : arr) {
      if(p.id2 == -1) System.out.println(p.id1);
      else System.out.println(p.id1 + " " + p.id2);
    }
    */
    writeToFile(arr, args[0]);

  }

  public static void writeToFile(ArrayList<Photo> arr, String filename) {
    PrintWriter pw = new PrintWriter(filename + ".out", "UTF-8");
    pw.println(arr.size());
    for(Photo p : arr) {
      if(p.id2 == -1) pw.println(p.id1);
      else pw.println(p.id1 + " " + p.id2);
    }
    pw.close();
  }

}
