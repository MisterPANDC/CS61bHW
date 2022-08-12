public class ArrayDeque<Item> {
    private int size = 0;
    Item[] array = (Item[]) new Object[8];
    private int fptr = 3;
    private int lptr = 3;

    public ArrayDeque() {
        size = 0;
    }

    public ArrayDeque(Item x) {
        size = 1;
        array[fptr] = x;
        /**这里不移动指针，因为之后add时候会先移动*/
    }

    /**public void copy(int size) {
        item[] narray = (item[]) new Object[size];
        System.arraycopy(array, fptr, narray, 0, array.length);
        array = narray;
        /**ptr不影响
    }
     */
    public void addFirst(Item x) {
        size = size + 1;
        if (size > array.length) {
            /**copy(array.length * 2);*/
            acopy(array.length * 2);
        }
        fptr = fptr - 1;
        if (fptr < 0) {
            fptr = fptr + array.length;
        }
        array[fptr] = x;
    }

    public void addLast(Item x) {
        size = size + 1;
        if (size > array.length)
        {
            acopy(array.length * 2);
        }
        lptr = lptr + 1;
        if (lptr > array.length - 1)
        {
            lptr = lptr - array.length;
        }
        array[lptr] = x;
    }

    public boolean isEmpty() {
        if (size == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque()
    {
        int ptr = fptr;
        while(ptr != lptr)
        {
            System.out.print(array[ptr]);
            System.out.print(" ");
            ptr++;
            if(ptr>array.length-1)
                ptr=ptr-array.length;
        }
        System.out.print(array[ptr]);
    }
    /**public void ncopy(int size) {
        item[] narray = (item[]) new Object[size];
        System.arraycopy(array, fptr, narray, 0, this.size);
        array = narray;
        fptr=0;
        /**lptr=fptr+size;注意这里重名了
        lptr=fptr+this.size;
    }
     */
    public void acopy(int newsize){
        Item[] narray = (Item[]) new Object[newsize];
        int ptr = fptr;
        int i = 0;
        while(i < array.length)/**这样就解决了在lptr停住的问题,但在move的情况下并不通用*/
        {
            narray[i] = array[ptr];
            i++;
            ptr++;
            if(ptr > array.length-1)
                ptr = ptr-array.length;
        }
        array = narray;
        fptr = 0;
        lptr = i-1;/**这里的处理也应该有所不同 本应该在size上做文章，但是操作都是先size变化之后再变*/
    }
    public void rcopy(int newsize){
        Item[] narray = (Item[]) new Object[newsize];
        int ptr = fptr;
        int i = 0;
        while(i < this.size+1)/**size已经再之前被减去了1*/
        {
            narray[i] = array[ptr];
            i++;
            ptr++;
            if(ptr > array.length-1)
                ptr = ptr-array.length;
        }
        array=narray;
        fptr=0;
        lptr=i-1;/**这里的处理也应该有所不同 本应该在size上做文章，但是操作都是先size变化之后再变*/
    }
     public void removeFirst(){
        size=size-1;
        if(array.length>=16)
        if(size<array.length/4)
            rcopy(array.length/2);
        /**fptr已经重置*/
        array[fptr]=null;
        fptr++;
        if(fptr>array.length-1)
            fptr=fptr-array.length;
     }

     public void removeLast(){
        size=size-1;
         if(array.length >= 16)
             if(size<array.length/4)
                 rcopy(array.length/2);
         array[lptr]=null;
         lptr--;
         if(lptr < 0)
         {
             lptr = lptr + array.length;
         }

     }
     public static void main(String[] args)
     {
         ArrayDeque<Integer> test=new ArrayDeque<>();
         test=new ArrayDeque<Integer>(10);
         test.addFirst(5);
         test.addLast(7);
         test.addLast(8);
         test.addLast(9);
         test.addLast(10);
         test.addFirst(3);
         test.addFirst(1);
         test.addLast(9);
         test.addLast(15);
         test.addFirst(9);
         test.addFirst(1);
         test.addFirst(2);
         test.addFirst(9);
         test.removeFirst();
         test.removeFirst();
         test.removeFirst();
         test.removeFirst();
         test.removeFirst();
         test.removeFirst();
         test.removeFirst();
         test.removeFirst();
         test.removeLast();
         test.removeLast();
         test.removeLast();
         test.removeLast();
         test.removeLast();
         
     }
     
}