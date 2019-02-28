import java.util.*;

public class Brain {

  public Photo[] verticalPhotos;
  public ArrayList<Photo> combSlides = new ArrayList<>();
  public ArrayList<Photo> allSlides = new ArrayList<>();


  public void putStuffInAllSlides() {

  }

  public void orderSlides() {

  }


  public void fillCombSlides() {
    Arrays.sort(verticalPhotos);

    int indexWithBestPairing = -1;
    int lowestSimilar = Integer.MAX_VALUE;
    int temp;
    boolean[] usedUp = new boolean[verticalPhotos.length];
    for (int i = verticalPhotos.length - 1; i >= 1; i--) {
      if (usedUp[i]) {
        continue;
      }
      indexWithBestPairing = -1;
      for (int j = 0; j < verticalPhotos.length; j++) {
        if (i == j || usedUp[j]) {
          continue;
        }
        temp = getSimilar(verticalPhotos[i], verticalPhotos[j]);
        if (temp < lowestSimilar || indexWithBestPairing == -1) {
          indexWithBestPairing = j;
          lowestSimilar = temp;
        }
      }
      if (indexWithBestPairing == -1) {
        break;
      }
      usedUp[i] = true;
      usedUp[indexWithBestPairing] = true;
      Photo newPhoto = new Photo();
      newPhoto.id1 = verticalPhotos[i].id1;
      newPhoto.id2 = verticalPhotos[indexWithBestPairing].id1;
      newPhoto.isVertical = false;
      combSlides.add(newPhoto);
    }
  }


  public int getSimilar(Photo photo1, Photo photo2) {
    int same = 0;
    if (same == 0) {
      HashSet<String> newSet = new HashSet<>();
      newSet.addAll(photo1.tags);
      newSet.addAll(photo2.tags);
      return newSet.size();
    }
    int different = photo1.tags.size() + photo2.tags.size();

    Iterator iterator = photo2.tags.iterator();

    while (iterator.hasNext()) {
      String temp = (String)iterator.next();
      if (photo1.tags.contains(temp)) {
        same++;
        different -= 2;
      }
    }

    return same;
  }



  public int getScore(Photo photo1, Photo photo2) {
    int same = 0;
    if (same == 0) {
      HashSet<String> newSet = new HashSet<>();
      newSet.addAll(photo1.tags);
      newSet.addAll(photo2.tags);
      return newSet.size();
    }
    int different = photo1.tags.size() + photo2.tags.size();

    Iterator iterator = photo2.tags.iterator();

    while (iterator.hasNext()) {
      String temp = (String)iterator.next();
      if (photo1.tags.contains(temp)) {
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
