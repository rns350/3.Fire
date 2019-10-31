public class CircleQueue<E extends Comparable<E>> implements CirclePriorityQueue<E>{
    private class Node<E>{
        E data;
        Node<E> next;
        public Node(E data){
            this.data = data;
        }
        public Node(E data, Node<E> next){
            this.data = data;
            this.next = next;
        }
    }
    Node<E> tail;
    Node<E> trueTail;
    int size;

    public int add(E incoming) throws NullPointerException{
        //check if the element is null
        if(incoming == null){
            throw new NullPointerException();
        }
        //case for if the list is empty
        if(size == 0){
            tail = new Node<E>(incoming);
            tail.next = tail;
            trueTail = tail;
            size ++;
            return 0;
        }
        //cases for if the element is the new head, or equal to the head
        int compare = trueTail.next.data.compareTo(incoming);
        if(compare > 0){
            trueTail.next = new Node<E>(incoming, trueTail.next);
            size++;
            return 0;
        }
        if(compare == 0){
            return -1;
        }
        //cases for all elements between head and tail.
        int index = 1;
        Node<E> temp = trueTail.next;
        while(temp.next != trueTail.next){
            compare = temp.next.data.compareTo(incoming);
            if(compare > 0){
                temp.next = new Node<E>(incoming, temp.next);
                size ++;
                return index;
            }
            if(compare == 0){
                return -1;
            }
            index ++;
            temp = temp.next;
        }
        //case for if the element is the new tail
        trueTail.next = new Node<E>(incoming, trueTail.next);
        if(trueTail == tail){
            tail = tail.next;
        }
        trueTail = trueTail.next;
        size++;
        return index;
    }

    public E front(){
        if(size == 0){
            return null;
        }
        return tail.next.data;
    }

    public E rear(){
        if(size == 0){
            return null;
        }
        return tail.data;
    }

    public String toString(){
        StringBuilder toReturn = new StringBuilder("[");
        Node<E> temp = tail.next;
        for(int i = 0; i < size; i++, temp = temp.next){
            toReturn.append(temp.data.toString() + ", ");
        }
        toReturn.delete(toReturn.length() - 2, toReturn.length());
        toReturn.append("]");
        return toReturn.toString();
    }

    public String priorityToString(){
        StringBuilder toReturn = new StringBuilder("[");
        Node<E> temp = trueTail.next;
        for(int i = 0; i < size; i++, temp = temp.next){
            toReturn.append(temp.data.toString() + ", ");
        }
        toReturn.delete(toReturn.length() - 2, toReturn.length());
        toReturn.append("]");
        return toReturn.toString();
    }

    public E poll(){
        if(size == 0){
            return null;
        }
        if(size == 1){
            E temp = tail.data;
            tail = null;
            trueTail = null;
            size --;
            return temp;
        }
        if(trueTail == tail.next){
            trueTail = tail;
        }
        E temp = tail.next.data;
        tail.next = tail.next.next;
        size --;
        return temp;
    }

    public int size(){
        return size;
    }

    public E rotate(){
        tail = tail.next;
        return tail.next.data;
    }

    public static void main(String [] args){
        CircleQueue<Integer> a = new CircleQueue<Integer>();
        for(int i = 3; i > 0; i --){
            System.out.println(a.add(new Integer(i)));
        }
        System.out.println(a.toString());
        System.out.println(a.priorityToString());
        a.rotate();
        a.rotate();
        System.out.println(a.toString());
        System.out.println(a.priorityToString());
        a.poll();
        System.out.println(a.toString());
        System.out.println(a.priorityToString());
        a.poll();
        System.out.println(a.toString());
        System.out.println(a.priorityToString());
    }
}