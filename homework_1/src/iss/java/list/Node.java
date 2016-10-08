package iss.java.list;

/**
 * Created by wenke on 2016/9/16.
 * TODO: Modify this class to meet requirement A and C.
 */
public class Node {
    private int data;
    private Node prev;
    private Node next;
    private boolean isAlive= false;

    public int getData() {
        return data;
    }
    public boolean getStatus(){
    	return isAlive;
    }
    public void setStatus(boolean status){
    	this.isAlive=status;
    }
    public Node setData(int data) {
        this.data = data;
        return this;
    }

    public Node getPrev() {
        return prev;
    }

    Node setPrev(Node prev) {
        this.prev = prev;
        return this;
    }

    public Node getNext() {
        return next;
    }

    Node setNext(Node next) {
        this.next = next;
        return this;
    }
}
