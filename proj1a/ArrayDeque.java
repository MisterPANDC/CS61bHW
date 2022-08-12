public class ArrayDeque<T> {
    private int size = 0;
    private T[] array = (T[]) new Object[8];
    private int fptr = 3;
    private int lptr = 3;

    public ArrayDeque() {
        size = 0;
    }

    private ArrayDeque(T x) {
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
    public void addFirst(T x) {
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
     public T get(int index) {
        return array[fptr + index];
     }
}