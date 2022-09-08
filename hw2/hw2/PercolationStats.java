package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private int n;
    private int t;
    private PercolationFactory p;
    private int [] xs = new int [10000];
    public PercolationStats(int N, int T, PercolationFactory pf) {  // perform T independent experiments on an N-by-N grid
        n = N;
        t = T;
        //p = pf;//对于help method 的使用仍有问题
        //Percolation p = pf.make(n);
        for(int i = 1 ; i <= t ; i = i + 1){
            Percolation p = pf.make(n);
            //对pf进行随机，直至percolation，记录下数据
            while(!p.percolates()){
                //StdRandom.setSeed();
                int row = StdRandom.uniform(n);//需不需要重新设置随机数种子
                int col = StdRandom.uniform(n);
                p.open(row,col);
            }
            xs[i] = p.numberOfOpenSites();
        }
    }
    public double mean(){ // sample mean of percolation threshold
        double ans = StdStats.mean(xs);
        return ans;
    }
    public double stddev() {// sample standard deviation of percolation threshold
        double ans = StdStats.stddev(xs);
        return ans;
    }
    public double confidenceLow() {                                 // low endpoint of 95% confidence interval
        double mean = this.mean();
        double stddev = this.stddev();
        double ans = mean - 1.96*stddev/(Math.sqrt(t));
        return ans;
    }
    public double confidenceHigh() {                                 // high endpoint of 95% confidence interval
        double mean = this.mean();
        double stddev = this.stddev();
        double ans = mean + 1.96*stddev/(Math.sqrt(t));
        return ans;
    }
}
