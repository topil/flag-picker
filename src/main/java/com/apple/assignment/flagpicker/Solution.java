package com.apple.assignment.flagpicker;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {


    public static void main(String[] args) {
        Map<String, Map<String, Integer>> map = new HashMap<>();
        Map<String, Integer> x1 = new HashMap<>();
        x1.put("A.1", 5);
        x1.put("B.1", 5);
        x1.put("A.3", 6);
        map.put("X1", x1);

        Map<String, Integer> x2 = new HashMap<>();
        x2.put("A.1", 3);
        x2.put("A.2", 2);
        x2.put("B.2", 4);
        x2.put("C.2", 7);
        x2.put("B.1", 3);
        map.put("X2", x2);


        Map transformedMap = map.entrySet().stream().collect(Collectors.toMap(
                entry -> entry.getKey(),
                entry -> entry.getValue().entrySet().stream().map(e->new Tuple(e)).collect(Collectors.groupingBy(Tuple::getKey, Collectors.summingInt(Tuple::getValue)))
        ));

        System.out.println(transformedMap);

    }

    private static class Tuple{
        String key;
        int value;

        public Tuple(Map.Entry<String, Integer> e){
            this.key = e.getKey().substring(0, e.getKey().indexOf("."));
            this.value = e.getValue();
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
