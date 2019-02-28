import java.util.*;

public class Brain {

  public Photo[] photos;


  public void doStuff() {
    Arrays.sort(photos);
  }



  public int getScore(Photo photo1, Photo photo2) {
    HashSet<String> inPhoto1 = new HashSet<>();
    for (int i = 0; i < photo1.size(); i++) {
      inPhoto1.add(photo1.get(i));
    }

    int same = 0;
    int different = photo1.size() + photo2.size();

    for (int i = 0; i < photo2.size(); i++) {
      if (inPhoto1.contains(photo2.get(i))) {
        same++;
        different -= 2;
      }
    }

    if (same < different) {
      return same;
    }
    else {
      return different;
    }
  }
}
