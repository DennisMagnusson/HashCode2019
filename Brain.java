import java.util.*;

public class Brain {

  public Photo[] verticalPhotos;
  public ArrayList<Photo> combSlides = new ArrayList<>();
  public ArrayList<Photo> horizontalSlides = new ArrayList<>();

  public ArrayList<Photo> allSlides = new ArrayList<>();
  public ArrayList<Photo> outputOrder = new ArrayList<>();

  public void putStuffInAllSlides() {
    for (int i = 0; i < horizontalSlides.size(); i++) {
      allSlides.add(horizontalSlides.get(i));
    }
    for (int i = 0; i < combSlides.size(); i++) {
      allSlides.add(combSlides.get(i));
    }
  }

  public void orderSlides() {
    // add 2 slides
    outputOrder.add(allSlides.remove(0));
    outputOrder.add(allSlides.remove(0));

    Random rng = new Random();

    Photo tempSlide = null;
    int bestInsert = -1;
    int bestImprove = -1;
    int tempImprove = -1;
    while (allSlides.size() != 0) {
      tempSlide = allSlides.remove(0);
      System.out.println(allSlides.size());

      bestInsert = -1;
      bestImprove = -1;
      for (int i = 0; i <= outputOrder.size(); i++) {
        //int insertIndex = rng.nextInt(outputOrder.size() + 1);
        int insertIndex = i;

        if (insertIndex == 0) {
          // insert at start
          tempImprove = getScore(tempSlide, outputOrder.get(0));
        }
        else if (insertIndex == outputOrder.size()) {
          // insert at end
          tempImprove = getScore(tempSlide, outputOrder.get(outputOrder.size()-1));
        }
        else {
          // insert somewhere in middle
          //System.out.println(insertIndex + " " + outputOrder.size());
          tempImprove = getScore(tempSlide, outputOrder.get(insertIndex-1));
          tempImprove += getScore(tempSlide, outputOrder.get(insertIndex));
          tempImprove -= getScore(outputOrder.get(insertIndex), outputOrder.get(insertIndex-1));
        }

        if (tempImprove > bestImprove) {
          bestImprove = tempImprove;
          bestInsert = insertIndex;
        }
      }
      
      if(bestInsert == -1) bestInsert = 0;
      outputOrder.add(bestInsert, tempSlide);
    }
  }


  public void fillCombSlides() {
    Arrays.sort(verticalPhotos);

    Random rng = new Random();

    int indexWithBestPairing = -1;
    int lowestSimilar = Integer.MAX_VALUE;
    int temp;
    boolean[] usedUp = new boolean[verticalPhotos.length];
    for (int i = verticalPhotos.length - 1; i >= 1; i--) {
      System.out.println(i);
      if (usedUp[i]) {
        continue;
      }
      indexWithBestPairing = -1;
      for (int k = 0; k < 1000; k++) {
        int j = rng.nextInt(i);
        if (usedUp[j]) {
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
    Iterator iterator = photo2.tags.iterator();

    while (iterator.hasNext()) {
      int temp = (int)iterator.next();
      if (photo1.tags.contains(temp)) {
        same++;
      }
    }

    return same;
  }


  public int getScore(Photo photo1, Photo photo2) {
    int same = 0;
    int different = photo1.tags.size() + photo2.tags.size();

    Iterator iterator = photo2.tags.iterator();

    while (iterator.hasNext()) {
      int temp = (int)iterator.next();
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
