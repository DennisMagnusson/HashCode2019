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
      boolean horizontal = (things[0] == "H");
      int i = Integer.parseInt(things[1]);
      ArrayList<String> tags = new ArrayList<>();
      for(int u = 0; u < i; i++) {
        tags.add(things[i+2]);
      }
      photos[z] = new Photo();
      photos[z].id1 = z;
      photos[z].isVertical = !horizontal;
      photos[z].tags = tags;

      z++;
    }

    ArrayList<Photo> arr = new ArrayList<>();//READ THIS FROM SOMEWHERE
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
