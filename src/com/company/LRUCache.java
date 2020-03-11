package com.company;

import java.util.HashMap;
import java.util.LinkedList;

public class LRUCache {
    private int capacity;
    private LinkedList linkedList;
    private HashMap cache;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.linkedList = new LinkedList();
        this.cache = new HashMap<Integer, ListNode>();
    }

    public int get(int key) {
       int value = -1;
       if(this.cache.containsKey(key)) {
           ListNode node = (ListNode) this.cache.get(key);
           this.linkedList.remove(node);
           this.linkedList.push(node);
           value = node.getValue();
       }
       return value;
    }

    public void put(int key, int value) {
        if(isOverCapacity()){
            ListNode node = (ListNode) this.linkedList.removeLast();
            this.cache.remove(node.getKey());
        }

        var newNode = new ListNode(key, value);
        if(this.cache.containsKey(key)) this.linkedList.remove(this.cache.get(key));
        this.cache.put(key, newNode);
        this.linkedList.push(newNode);

    }

    private boolean isOverCapacity(){
        return this.cache.size() >= this.capacity;
    }
}
