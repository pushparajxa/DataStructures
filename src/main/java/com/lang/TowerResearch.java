
package com.lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TowerResearch {

  public static List<String> fetchItemsToDisplay(List<List<String>> items, int sortParameter,
      int sortOrder, int itemPerPage, int pageNumber) {

    //Str,int,int
    // Write your code here

    Comparator<List<String>> comparator;

    if (sortParameter == 0) {

      comparator = new Comparator<List<String>>() {
        @Override
        public int compare(List<String> o1, List<String> o2) {
          String name1 = o1.get(0);
          String name2 = o2.get(0);

          if (o1.equals(o2)) {
            return 0;
          } else {
            if (sortOrder == 1) {//descending

              return -name1.compareTo(name2);

            } else {//ascending
              return name1.compareTo(name2);
            }
          }
        }
      };

    } else if (sortParameter == 1) {
      comparator = new Comparator<List<String>>() {
        @Override
        public int compare(List<String> o1, List<String> o2) {
          Integer relevance1 = Integer.parseInt(o1.get(1));
          Integer relevance2 = Integer.parseInt(o2.get(1));

          if (o1.equals(o2)) {
            return 0;
          } else {
            if (sortOrder == 1) {//descending

              return -relevance1.compareTo(relevance2);

            } else {//ascending
              return relevance1.compareTo(relevance2);
            }
          }
        }
      };

    } else {

      comparator = new Comparator<List<String>>() {
        @Override
        public int compare(List<String> o1, List<String> o2) {
          Integer price1 = Integer.parseInt(o1.get(2));
          Integer price2 = Integer.parseInt(o2.get(2));

          if (o1.equals(o2)) {
            return 0;
          } else {
            if (sortOrder == 1) {//descending

              return -price1.compareTo(price2);

            } else {//ascending
              return price1.compareTo(price2);
            }
          }
        }
      };

    }

    Collections.sort(items, comparator);

    List<String> toReturn = new ArrayList<>();

    for (int i = pageNumber * itemPerPage; i < (pageNumber + 1) * itemPerPage; i++) {

      toReturn.add(items.get(i).get(sortParameter));

    }

    return toReturn;
  }

  }
