package com.epam.util;

import java.util.HashMap;

public class HashMapHelper {

    private HashMap<String, Integer> map;


    public HashMapHelper() {
        this.map = new HashMap<>();
    }

    public Integer put(String key, Integer value) {
        return map.put(key, value);
    }

    public Integer get(String key) {
        return map.get(key);
    }

    public int getMaxVal() {
        int max = 0;
        for (Integer i : map.values()) {
            if (max <= i) {
                max = i;
            }
        }
        return max;
    }

    public void getKey(){
        for (String key:map.keySet()){
            System.out.println(key+":"+map.get(key));
        }

    }
}
