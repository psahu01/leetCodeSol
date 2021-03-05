package com.amazon.oa2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

class City {
   String name;
   int x;
   int y;
}

public class NearestCities {
   public static Comparator<? super City> Y_COMPARATOR = (a, b) -> {
      int v = Integer.compare(a.y, b.y);
      if (v == 0) {
         v = a.name.compareTo(b.name);
      }

      return v;
   };
   public static Comparator<? super City> X_COMPARATOR = (a, b) -> {
      int v = Integer.compare(a.x, b.x);
      if (v == 0) {
         v = a.name.compareTo(b.name);
      }

      return v;
   };

   public static String[] findNearestCities(int numOfPoints, String[] points, int[] xCoordinates, int[] yCoordinates,
         int numOfQueries, String[] queries) {
      HashMap<Integer, TreeSet<City>> xToYs = new HashMap<>();
      HashMap<Integer, TreeSet<City>> yToXs = new HashMap<>();
      HashMap<String, City> nameToCity = new HashMap<>();

      for (int i = 0; i < numOfPoints; i++) { // O(p log(p))
         City c = new City();
         c.name = points[i];
         c.x = xCoordinates[i];
         c.y = yCoordinates[i];

         // Add to maps
         nameToCity.put(c.name, c);
         xToYs.putIfAbsent(c.x, new TreeSet<>(Y_COMPARATOR));
         xToYs.get(c.x).add(c); // O (log p) in adding to TreeSet
         yToXs.putIfAbsent(c.y, new TreeSet<>(X_COMPARATOR));
         yToXs.get(c.y).add(c); // O (log p) in adding to TreeSet
      }

      String[] result = new String[numOfQueries];
      for (int i = 0; i < numOfQueries; i++) { // O (q log p)
         City c = nameToCity.get(queries[i]);
         if (c == null)
            continue;

         ArrayList<City> neighbors = new ArrayList<City>(); 
         neighbors.add(xToYs.get(c.x).lower(c)); // O(log p)
         neighbors.add(xToYs.get(c.x).higher(c)); // O(log p)
         neighbors.add(yToXs.get(c.y).lower(c)); // O(log p)
         neighbors.add(yToXs.get(c.y).higher(c)); // O(log p)

         result[i] = getClosestCity(c, neighbors); // At max 4 so O(1)

      }
      return result;
   }

   private static String getClosestCity(City origin, List<City> neighbors) {
      City closestCity = null;
      int closestDistance = Integer.MAX_VALUE;
      for (City c : neighbors) {
         if (c != null) {
            int d = getDistance(origin, c);
            if (d < closestDistance) {
               closestCity = c;
               closestDistance = d;
            }
         }
      }

      return closestCity != null ? closestCity.name : null;
   }

   private static int getDistance(City a, City b) {
      return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
   }

   public static void test(int numOfPoints, String[] points, int[] xCoordinates, int[] yCoordinates, int numOfQueries,
         String[] queries) {
      String[] out = findNearestCities(numOfPoints, points, xCoordinates, yCoordinates, numOfQueries, queries);
      System.out.println(Arrays.toString(out));
   }

   public static void main(String[] arg) {
      System.out.println("NearestCities");
      test(3, new String[] { "c1", "c2", "c3" }, new int[] { 3, 2, 1 }, new int[] { 3, 2, 3 }, 3,
            new String[] { "c1", "c2", "c3" });
      test(5, new String[] { "p1", "p2", "p3", "p4", "p5" }, new int[] { 10, 20, 30, 40, 50 },
            new int[] { 10, 20, 30, 40, 50 }, 5, new String[] { "p1", "p2", "p3", "p4", "p5" });
      test(5, new String[] { "p1", "p2", "p3", "p4", "p5" }, new int[] { 10, 20, 10, 40, 50 },
            new int[] { 10, 20, 10, 10, 50 }, 5, new String[] { "p1", "p2", "p3", "p4", "p5" });
   }
}
