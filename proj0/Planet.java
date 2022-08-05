public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double G=6.67e-11;/**科学计数法？*/
	public Planet(double xP,double yP,double xV,double yV,double m,String img)
	{
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	public Planet(Planet p)
	{
		this.xxPos=p.xxPos;
		this.yyPos=p.yyPos;
		this.xxVel=p.yyVel;
		this.yyVel=p.yyVel;
		this.mass=p.mass;
		this.imgFileName=p.imgFileName;
	}
	public boolean equals(Planet p)
	{
		if(this.xxPos==p.xxPos)
			if(this.yyPos==p.yyPos)
		         {
		         	return true;
		         }
		return false;
	}
	public double calcDistance(Planet p)
	{
		double d=(this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos);
		double ans=Math.sqrt(d);
		return ans;
	}
	public double calcForceExertedBy(Planet p)
	{
		double r=this.calcDistance(p);
		double f=(G*this.mass*p.mass)/(r*r);
		return f;
	}
	public double calcForceExertedByX(Planet p)
	{
		double x=this.xxPos-p.xxPos;
		double f=this.calcForceExertedBy(p);
		double r=this.calcDistance(p);
		double ans=(-f)*x/r;/**处理符号，受力方向向左为负，在右的物体受力为负*/
		return ans;
	}
	public double calcForceExertedByY(Planet p)
	{
		double y=this.yyPos-p.yyPos;
		double f=this.calcForceExertedBy(p);
		double r=this.calcDistance(p);
		double ans=(-f)*y/r;/**处理符号，受力方向向左为负，在右的物体受力为负*/
		return ans;
	}
	/**注意这里的小问题，0被显示为-0*/
	/**这里传递一个数组，注意格式*/
    public double calcNetForceExertedByX(Planet[] p)
    {
     int len=p.length;
     double sum=0;
     int i=0;
     while(i<len)
     {
     	if(this.equals(p[i]))
     	{
     		i=i+1;
     		continue;
     	}
     	double temp=this.calcForceExertedByX(p[i]);
     	sum=sum+temp;
     	i=i+1;
     }
     return sum;
    }
    public double calcNetForceExertedByY(Planet[] p)
    {
     int len=p.length;
     double sum=0;
     int i=0;
     while(i<len)
     {
     	if(this.equals(p[i])) 
     	{
     		i=i+1;
     		continue;
     	}
     	double temp=this.calcForceExertedByY(p[i]);
     	sum=sum+temp;
     	i=i+1;
     }
     return sum;
    }
    public void update(double dt,double fX,double fY)/*注意变量名保持一致，才能检测成功？*/
    {
       double ax=fX/this.mass;
       double ay=fY/this.mass;
       double vx=this.xxVel+dt*ax;
       double vy=this.yyVel+dt*ay;
       double px=this.xxPos+dt*vx;
       double py=this.yyPos+dt*vy;
       this.xxPos=px;
       this.yyPos=py;
       this.xxVel=vx;
       this.yyVel=vy;
    }
    public void draw()
    {
    	StdDraw.enableDoubleBuffering();
    	String fn="images/" + this.imgFileName;
    	StdDraw.picture(this.xxPos,this.yyPos,fn);
    	StdDraw.show();
    }
}