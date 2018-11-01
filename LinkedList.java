
import java.lang.*;

public class LinkedList implements List {
    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        this.head = null;       // beginning of linked list
        this.tail = null;       // end of linked list
        this.size = 0;          // number of elements in linked list

    }

    public void addToFront(Object obj) throws Exception{
        // throw exception if object that is being added is a null object
        if(obj == null){
            throw new Exception("Object is invalid.");
        }
        // make new node with object value and call it the head
        head = new Node(obj, head);

        // if its the first element in the linked list, make this node the tail as well
        if (tail == null){
            tail = head;
        }
    }

    @Override
    public void add(Object obj) throws Exception {
        // if the linked list is empty, add object to the front and increase linked list counter size
        if(tail == null){
            addToFront(obj);
            size++;
            return;
        }
        // if linked list is not empty, then make new node with object value and name it the tail, make its next null
        tail.next = new Node(obj, null);
        tail = tail.next;
        size++;
        return;
    }

    @Override
    public void add(int pos, Object obj) throws Exception {
        // if index is less than 0, then throw exception
        if(pos < 0){
            throw new Exception("Position is invalid.");
        }

        // if index is 0, then add object to the front of the linked list, increase size counter
        if(pos == 0){
            addToFront(obj);
            size++;
            return;
        }

        // if the index is not 0, then make node prev and point it to the head
        Node prev = head;
        int i =0;

        // traverse through the linked list until we reach the index before the desired position
        while(prev != null && i < pos-1){
            prev = prev.next;
            i++;
        }

        // if prev is null, then throw exception
        if(prev == null){
            throw new Exception("Position is invalid.");
        }

        // else, create a new node with object value, and insert it as prev.next, connect it with the node that was previously the next
        prev.next = new Node(obj, prev.next );
        size++;
        return;
    }

    @Override
    public Object get(int pos) throws Exception {
        // if index is less than 0, throw exception
        if(pos < 0){
            throw new Exception("Position is invalid.");
        }

        // set current as the head
        Node current = head;
        int i = 0;

        // traverse through the linked list until current points to the node of the index we want
        while(current != null && i < pos){
            current = current.next;
            i++;
        }

        // if current doesn't point to a null node, then return its data
        if(current != null){
            return current.data;
        }
        // else, throw exception
        else{
            throw new Exception("Position is invalid");
        }
    }

    @Override
    public Object remove(int pos) throws Exception {
        Object obj;

        // if linked list is empty, return null
        if(head == null){
            return null;
        }

        // if the index is 0, set obj to the value of the head node, set the node following it as the new head, return obj
        if(pos == 0){
            obj = head.data;
            head = head.next;
            size--;
            return obj;
        }

        // else, create node prev and point it to head.
        Node prev = head;
        int i =0;

        //traverse through the linked list until prev is pointing to the index before the index we want to remove
        while(prev != null && i < pos-1){
            prev = prev.next;
            i++;
        }

        // if prev is pointing to a null node, throw exception
        if(prev == null || prev.next == null){
            throw new Exception("Position is invalid.");
        }

        //set obj to the data of the next node, which is in the index we want to remove
        obj = prev.next.data;

        // link back the rest of the list by setting prev.next to the next of the node that we want to remove
        prev.next = prev.next.next;
        size--;
        return obj;

    }

    @Override
    public int size() {
        // return the size of the linked list 
        return size;
    }

    private class Node{
        Node next;
        Object data;

        public Node(){
            next = null;
            data = null;
        }

        public Node(Object data, Node next){
            this.data = data;
            this.next = next;
        }
    }
}
