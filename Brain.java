import java.util.*;

public class Brain {

  public static int getScore(Photo photo1, Photo photo2) {
    HashSet<String> inPhoto1 = new HashSet<>();
    for (int i = 0; i < photo1.tags.size(); i++) {
      inPhoto1.add(photo1.tags.get(i));
    }

    int same = 0;
    int different = photo1.tags.size() + photo2.tags.size();

    for (int i = 0; i < photo2.tags.size(); i++) {
      if (inPhoto1.contains(photo2.tags.get(i))) {
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
