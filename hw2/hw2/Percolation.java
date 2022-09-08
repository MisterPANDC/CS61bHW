package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private int open_number = 0;
    int[] squar = new int[100000];
    WeightedQuickUnionUF dset ;
    public Percolation(int N){ //用disjoint set 表示 row i column j 表示为 N*i+j
        int n = N;
        if(n <= 0){
            throw new java.lang.IllegalArgumentException();
        }
        for (int i = 0; i < N * N - 1 ; i = i + 1) {
            squar[i] = 0;
        }//0 is not open
        dset = new WeightedQuickUnionUF(n * n + 2);//n^2 is virtual top site n^2+1=virtual bottom site
    } // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col) {       // open the site (row, col) if it is not open already
        open_number = open_number + 1;
        int index = n * row + col;
        squar[index] = 1;// open
        if(row == 0){
            //squar[index] = 2;//full
            dset.union(index,n * n);
        }
        if(row == n - 1){
            dset.union(index,n * n + 1);
        }
        if(row - 1 >= 0 && row - 1 < n && col >=0 && col <n){
            if(isOpen(row - 1 , col)){
                int index2 = n* (row - 1) + col;
                dset.union(index,index2);//下面已经传递了，还需要连接吗-- 需要，传递不能保证，因为如果 full的情况后面出现，不能保证全部传递赋值
            /*if(squar[index] == 2 || squar[index2] == 2)
            {
                squar[index] = 2;
                squar[index2] = 2;
            }*/
            }
        }
        if(row + 1 >= 0 && row + 1 < n && col >=0 && col <n){
            if(isOpen(row + 1 , col)){
                dset.union(index,n*(row + 1) + col);
            }
        }
        if(row >= 0 && row < n && col - 1 >=0 && col - 1 <n){
            if(isOpen(row,col - 1)){
                dset.union(index,n * row + col - 1);
            }
        }
        if(row >= 0 && row < n && col + 1 >=0 && col + 1 <n){
            if(isOpen(row,col +1)){
                dset.union(index,n * row + col + 1);
            }
        }
    }
    public boolean isOpen(int row, int col) {  // is the site (row, col) open?
        if(row > n-1 || col > n-1){
            throw new java.lang.IndexOutOfBoundsException();
        }
        if(row < 0 || col < 0){
            throw new java.lang.IllegalArgumentException();
        }
        int index = n * row + col;
        if(squar[index] == 1) return true;
        else return false;
    }
    public boolean isFull(int row, int col) {  // is the site (row, col) full?
        int index = n * row + col;
        if(dset.connected(index,n * n)){
            return true;
        }
        else{
            return false;
        }
    }
    public int numberOfOpenSites(){// number of open sites
        return open_number;
    }
    public boolean percolates(){// does the system percolate?
        if(dset.connected(n * n,n * n + 1)){
            return true;
        }
        else {
            return false;
        }
    }
    public static void main(String[] args){ // use for unit testing (not required)
        return ;
    }
}
