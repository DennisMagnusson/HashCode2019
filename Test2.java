import java.*;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.nio.*;

public class Test2 {

  public static void main(String[] args) {

    if(args.length == 0) {
      System.out.println("READ A FILE YOU IDIOT");
    }
    //READ FIRST FILE
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

    //READ SECOND FILE
    lines = new ArrayList<>();
    try {
      FileReader fr = new FileReader(args[1]);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while((line = br.readLine()) != null) {
        lines.add(line);
      }
      br.close();
    } catch(Exception e) {}

    ArrayList<Photo> h = new ArrayList<>();//READ THIS FROM SOMEWHERE
    ArrayList<Photo> v = new ArrayList<>();//READ THIS FROM SOMEWHERE

    //int nPhotos = Integer.parseInt(lines.remove(0));
    z = 0;
    lines.remove(0);//Remove first line
    ArrayList<Photo> arr = new ArrayList<>();

    for(String line : lines) {
      String[] things = line.split(" ");
      if(things.length == 2) {
        photos[Integer.parseInt(things[0])].id2 = Integer.parseInt(things[1]);
        for(Integer i : photos[Integer.parseInt(things[1])].tags)
          photos[Integer.parseInt(things[0])].tags.add(i); 

      } else {
        //h.add(photos[Integer.parseInt(things[0])]);
      }
      arr.add(photos[Integer.parseInt(things[0])]);
      z++;
    }
    
    //Convert v to array
    /*
    Photo[] verticalPhotos = new Photo[v.size()];
    for(int i = 0; i < v.size(); i++) {
     verticalPhotos[i] = v.get(i); 
    }
    */

    Brain brain = new Brain();
    //ArrayList<Photo> arr = new ArrayList<>();//XXX Get this from Brain

    brain.horizontalSlides = new ArrayList<>(h);
    //brain.verticalPhotos = verticalPhotos;
    brain.combSlides = new ArrayList<>(v);
    brain.outputOrder = new ArrayList<>(arr);
    //brain.fillCombSlides();
    //brain.putStuffInAllSlides();
    //brain.orderSlides();
    for(int i = 0; ; i++) {
      brain.improve();
      System.out.println("ì=" + i);
      if(i%5000 == 0) {
        try {
          writeToFile(brain.outputOrder, args[0]+i+".out");
        } catch(Exception e) {};

      }


    }
    //for(Photo p : brain.outputOrder) arr.add(p);
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
  }

  static void writeToFile(ArrayList<Photo> arr, String filename)throws Exception {
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
    writer.write(arr.size()+"\n");
    for(Photo p : arr) {
      if(p.id2 == -1) writer.write(p.id1+"\n");
      else writer.write(p.id1 + " " + p.id2 + "\n");
    }
    writer.close();
  }


}
