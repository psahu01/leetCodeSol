package com.amazon.oa2;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
public class NearestCity2{


    public static String[] fun(int noOfQueries, int noOfcities, int[] x
        , int[]y, String[] city,String[] queries)
    {
        Map<Integer,PriorityQueue<String>> xmap = new HashMap();
        Map<Integer,PriorityQueue<String>> ymap = new HashMap();
        Map<String,Integer> cityIndex = new HashMap();
        
        for(int i = 0; i < noOfcities; i++)
        {
            cityIndex.put(city[i],i);
            xmap.put(x[i],xmap.getOrDefault(x[i],new PriorityQueue()));
            xmap.get(x[i]).add(city[i]);
            ymap.put(y[i],ymap.getOrDefault(y[i],new PriorityQueue()));
            ymap.get(y[i]).add(city[i]);
        }
        String result[] = new String[noOfQueries];
        for(int i = 0; i < noOfQueries;i++)
        {
            int index = cityIndex.get(queries[i]);
            String xcity=null, ycity=null;
            // x city
            Queue<String> queue = xmap.get(x[index]);
            if(queue.peek().compareTo(queries[i])==0)
            {
                if(queue.size()>1){
                queue.poll();
                xcity = queue.peek();
                queue.add(queries[i]);
                }
            }
            else
                xcity = queue.peek();
            // y city
            queue = ymap.get(y[index]);
            if(queue.peek().compareTo(queries[i])==0)
            {
                if(queue.size()>1){
                queue.poll();
                ycity = queue.peek();
                queue.add(queries[i]);
                }
            }
            else
                ycity = queue.peek();
                
            if(xcity!=null && ycity!=null)
                result[i] = (xcity.compareTo(ycity)<0)?xcity:ycity;
            else if (xcity==null && ycity==null)
                result[i] = "NONE";
            else if(xcity==null)
                result[i] = ycity;
            else if(ycity==null)
                result[i] = xcity;  
        }
        return result;
    }
     public static void main(String []args){
        System.out.println("Hello World");
        int noOfQueries = 3;
        int noOfcities = 3;
        int[] x = new int[]{3,2,1};
        int[]y = new int[]{3,2,3};
        String[] cities = new String[]{"c1","c2","c3"};
        String[] queries = new String[]{"c1","c2","c3"};
        String[] result = fun( noOfQueries,  noOfcities, x
        , y, cities,queries);
        
        for(String s : result)
        {
            System.out.println(s);
        }
     }
}