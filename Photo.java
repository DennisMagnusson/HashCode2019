import java.util.*;

public class Photo implements Comparable<Photo> {

  // ids are only valid if they don't equal -1
  public int id1 = -1;
  public int id2 = -1;

  public boolean isVertical; // true if vertical
  public HashSet<String> tags = new HashSet<>();


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
