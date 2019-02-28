import java.util.*;

public class Photo implements Comparable<Photo> {

  // ids are only valid if they don't equal -1
  public int id1 = -1;
  public int id2 = -1;

  public boolean isVertical; // true if vertical
  public HashSet<Integer> tags = new HashSet<>();


  public int compareTo(Photo other) {
    if (this.tags.size() > other.tags.size()) {
      return 1;
    }
    if (this.tags.size() < other.tags.size()) {
      return -1;
    }
    return 0;
  }
}



static void writeToFile()throws Exception {
    BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\tobia\\Desktop\\KaggleChristmas2018\\out.txt"));
    writer.write("Path\n");
    for (int i = 0; i < orderOfVisits.size(); i++) {
      writer.write(orderOfVisits.get(i) + "\n");
    }
    writer.close();
  }
