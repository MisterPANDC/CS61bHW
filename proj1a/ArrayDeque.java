public class ArrayDeque<T> {
    private int size = 0;
    private T[] array = (T[]) new Object[8];
    private int fptr = 4;
    private int lptr = 2;
/**这种设计保证在空集中加第一个时，两个指针指向一样*/
    public ArrayDeque() {
        size = 0;
    }

    private ArrayDeque(T x) {
        size = 1;
        fptr=3;
        lptr=3;
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
    public void addFirst(T x) {
        if (size == 0) {
            fptr = 4;
            lptr = 3;
        }
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

    public void addLast(T x) {
        if (size == 0) {
            lptr = 2;
            fptr = 3;
        }
        size = size + 1;
        if (size > array.length) {
            acopy(array.length * 2);
        }
        lptr = lptr + 1;
        if (lptr > array.length - 1) {
            lptr = lptr - array.length;
        }
        array[lptr] = x;
    }

    public boolean isEmpty() {
        return this.size == 0; /**simplified*/
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int ptr = fptr;
        while (ptr != lptr) {
            System.out.print(array[ptr]);
            System.out.print(" ");
            ptr++;
            if (ptr > array.length - 1) {
                ptr = ptr - array.length;
            }

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
    private void acopy(int newsize) {
        T[] narray = (T[]) new Object[newsize];
        int ptr = fptr;
        int i = 0;
        while (i < array.length) {
            narray[i] = array[ptr];
            i++;
            ptr++;
            if (ptr > array.length - 1) {
                ptr = ptr - array.length;
            }
        }
        array = narray;
        fptr = 0;
        lptr = i - 1; /**这里的处理也应该有所不同 本应该在size上做文章，但是操作都是先size变化之后再变*/
    }
    private void rcopy(int newsize) {
        T[] narray = (T[]) new Object[newsize];
        int ptr = fptr;
        int i = 0;
        while (i < this.size + 1) { /**size已经再之前被减去了1*/
            narray[i] = array[ptr];
            i = i + 1;
            ptr = ptr + 1;
            if (ptr > array.length - 1) {
                ptr = ptr - array.length;
            }
        }
        array = narray;
        fptr = 0;
        lptr = i - 1; /**这里的处理也应该有所不同 本应该在size上做文章，但是操作都是先size变化之后再变*/
    }
     public T removeFirst() { /**错误在于空集不能再减了*/
        if (size == 0) {
            return null;
        }
        size = size - 1;
        if (array.length >= 16) {
            if (size < array.length / 4) {
                rcopy(array.length / 2);
            }
            /**fptr已经重置*/
        }
        T ans = array[fptr];
        array[fptr] = null;
        fptr++;
        if (fptr > array.length - 1) {
            fptr = fptr - array.length;
        }
        return ans;
     }

     public T removeLast() {
         if (size == 0) {
             return null;
         }
        size = size - 1;
         if (array.length >= 16) {
             if (size < array.length / 4) {
                 rcopy(array.length / 2);
             }
         }
         T ans = array[lptr];
         array[lptr] = null;
         lptr--;
         if (lptr < 0) {
             lptr = lptr + array.length;
         }
         return ans;
     }
     public T get(int index) { /**没考虑加出范围要回归的问题*/
        if (index >= size) {
            return null;
        }
        int ans = fptr + index;
        if (ans >= this.array.length)
            ans = ans - this.array.length;
        return array[ans];
        //return array[fptr + index];
     }
     private static void main(String[] args)
     {
         ArrayDeque<Integer> test = new ArrayDeque<>();
         test.addLast(1);
         test.addLast(2);
         test.addLast(3);
         test.addLast(4);
         test.addLast(5);
         test.addLast(6);
         test.addLast(7);
         test.addLast(8);
         int a = test.get(0);
         test.addLast(0);
         a = test.get(0);
         test.addFirst(11);
         test.addFirst(12);
         test.removeFirst();
         test.removeFirst();
         test.removeFirst();
         a = test.get(3);
         test.removeFirst();
         test.removeFirst();
         test.removeFirst();
         a = test.get(5);
         test.removeFirst();
         test.removeFirst();
         test.removeFirst();
         a = test.get(1);
         test.removeFirst();
         test.removeFirst();
     }
}