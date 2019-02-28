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
    Photo[] photos = new Photos[nPhotos];
    int z = 0;
    for(String line : lines) {
      String[] things = line.split(" ");
      boolean horizontal = things[0] == 'H';
      int i = Integer.parseInt(things[1]);
      ArrayList<Strings> tags = new ArrayList<>();
      for(int u = 0; u < i; i++) {
        tags.add(things[i+2]);
      }
      photos[z] = new Photo(horizontal, tags, z);
      z++;
    }

    /*
    ArrayList<Photo> arr = new ArrayList<>();
    System.out.println(arr.size());
    for(Photo i : arr) {

    }
    for(int i = 0; i < arr.size(); i++) {
      
    }
    */

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
