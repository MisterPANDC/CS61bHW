public class LinkedListDeque<item>{
    private int size=0;
    node sentinel= new node();
    public class node{
        node pre;
        item con;
        node next;
        public node(item x)
        {
            con=x;
        }
        public node()/**如果不加这个的话，会报错，（结合上面sentinel必须new）*/
        {
            pre=null;
            con=null;
            next=null;
        }
        public node(node x)
        {
            this.next=x.next;
            this.pre=x.pre;
            this.con=x.con;
        }
        public item getRecursivehelper(int index)
        {
            if(index==0) return this.con;
            else return this.next.getRecursivehelper(index-1);
        }
    }
    private LinkedListDeque(item x)
    {
        size=1;
        sentinel.next=new node(x);
        sentinel.pre=sentinel.next;
        sentinel.next.pre=sentinel;
        sentinel.next.next=sentinel;
    }
    public LinkedListDeque()
    {
        size=0;
        this.sentinel.next=this.sentinel;
        this.sentinel.pre=this.sentinel;
    }
    public LinkedListDeque(LinkedListDeque other)
    {
        this.size= other.size();/**private 的影响？*/
        this.sentinel=new node(other.sentinel);
        node ptr1=other.sentinel.next;
        node ptr2=this.sentinel;
        while(ptr1!=other.sentinel)
        {
            ptr2.next=new node(ptr1);
            ptr2.next.pre=ptr2;    /**是否可以定义一种方法来实现pre和next的添加*/
            ptr1=ptr1.next;
            ptr2=ptr2.next;
        }
        ptr2.next=this.sentinel;
        ptr2.next.pre=ptr2;/**再来一次，实现sentinel的初始化*/
    }
    public void addFirst(item x)
    {
        node temp=new node(x);
        temp.next=this.sentinel.next;
        this.sentinel.next.pre=temp;
        this.sentinel.next=temp;
        temp.pre=this.sentinel;

        /**这里是否this加不加无所谓,因为名称不矛盾？*/
        size=size+1;
    }
    public void addLast(item x)
    {
        size=size+1;
        node temp=new node(x);
        temp.pre=sentinel.pre;
        sentinel.pre.next=temp;
        sentinel.pre=temp;
        temp.next=sentinel;
    }
    public boolean isEmpty()
    {
        if(size==0) return true;
        else return false;
    }
    public int size()
    {
        return size;
    }
    public void printDeque()
    {
        node ptr=sentinel.next;
        while(ptr!=sentinel)
        {
            if(ptr!=sentinel.next) System.out.print(" ");/**特判*/
            System.out.print(ptr.con);
            ptr=ptr.next;
        }
        return;
    }
    public item removeFirst()
    {
        size=size-1;
        node temp=new node(sentinel.next);
        sentinel.next.next.pre=sentinel;
        sentinel.next=sentinel.next.next;
        return temp.con;
    }
    public item removeLast()
    {
        size=size-1;
        node temp=new node(sentinel.pre);
        sentinel.pre.pre.next=sentinel;
        sentinel.pre=sentinel.pre.pre;
        return temp.con;
    }
    public item get(int index)
    {
        if(index+1>size) return null;
        else {
            int i=0;
            node ptr=sentinel.next;
            while(i!=index)
            {
                i++;
                ptr=ptr.next;
            }
            return ptr.con;
        }
    }
    public item getRecursive_(int index)
    {
        if(index+1>size) return null;
        /**else return getRecursive(index-1).next
         这里对递归的用法想错了*/
        /**else return this.next.get(index-1)
         这样也不对，因为LLD中加入了sentinel,或许应该在node中使用*/
        else return null;
    }
    public item getRecursive(int index)
    {
        if(index+1>size) return null;
        else return this.sentinel.next.getRecursivehelper(index);
    }
    public static void main(String[] args)
    {
      LinkedListDeque<Integer> test=new LinkedListDeque<>(10);
      test.addFirst(5);
      test.addLast(11);
      LinkedListDeque<Integer> test2= new LinkedListDeque<>(test);
      System.out.println(test.get(0));
        System.out.println(test.getRecursive(0));
        System.out.println(test.get(5));
        System.out.println(test.get(2));
        System.out.println(test.getRecursive(2));
      test.printDeque();
      test.removeFirst();
      test.printDeque();
      test.removeLast();
      test.printDeque();
    }
}