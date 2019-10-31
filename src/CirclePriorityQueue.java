public interface CirclePriorityQueue<E extends Comparable<E>>{

    /**This method must add an element to the Circle Queue
     * Based on it's priority, defined by it's compareTo
     * method.  Smaller objects will be placed at the front
     * of the Queue, and larger elements will be placed at the
     * back.  The Queue will not accept multiple of the same
     * obj.  The method will return an integer denoting the index
     * of the element after placement, or -1 if an equal object
     * already exists in the queue.  The Queue will not accept
     * null elements, and will throw an exception if passed one.
     * if the Queue has rotated, elements must be placed in original
     * priority order
     * 
     * @param incoming - the element to be added
     * @return - the index of the element after insertion, or -1
     *           if it is a repeat element.
     * @throws NullPointerException - if the Queue is passed a null argument
     */
    public int add(E incoming) throws NullPointerException;

    /**This method will return the element at the front of the
     * Queue without removing it.
     * 
     * @return - the element at the front of the Queue.
     */
    public E front();

    /**This method will return the element at the rear of the
     * Queue without removing it.
     * 
     * @return - the element at the rear of the Queue.
     */
    public E rear();

    /**This method will return a string representation of the
     * Queue.  The String will contain every element in the list,
     * printed with it's toString method, separated by commas, and
     * in between brackets.
     * 
     * @return - a String representation of the Queue.
     */
    public String toString();

    /**This method will return AND remove the element at the front
     * of the Queue, or null if the Queue is empty.
     * 
     * @return - The element at the front of the Queue
     */
    public E poll();

    /**This method will rotate the circular queue so that the head
     * will become the element in front of it.  Note that after this,
     * while the elements will remain ordered by priority, the
     * front is not necessarily holding the element with the highest
     * priority.
     * 
     * @return the new front of the list.
     */
    public E rotate();

    /**This method will return the size of the Queue
     * 
     * @return - the size of the Queue.
     */
    public int size();
}