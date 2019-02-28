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

    brain.verticalPhotos = verticalPhotos;
    brain.fillCombSlides();
    for(Photo p : brain.combSlides) arr.add(p);
    //*******************************************//
    //Fix verticals
    /*
    for(int i = 1; i < v.size(); i += 2) {
      v.get(i).id2 = v.get(i-1).id1;
      arr.add(v.get(i));
    }
    */

    System.out.println(arr.size());
    for(Photo p : arr) {
      if(p.id2 == -1) System.out.println(p.id1);
      else System.out.println(p.id1 + " " + p.id2);
    }

  }

  //DELET THIS?
  public static void writeToFile(ArrayList<Integer>[] a) {
    int q = 1;
    for(ArrayList<Integer> k: a) {
      System.out.print(k.size());
      for(int i : k) {
        System.out.print(" "+i);
      }
      q++;
      System.out.println();
    }
  }
}
