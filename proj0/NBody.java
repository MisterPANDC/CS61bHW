public class NBody{
	public static double readRadius(String address)
	{
		/**前面还要多读一个数字*/

		In in =new In(address);
		int num=in.readInt();
		double ans=in.readDouble();
		return ans;
	}
	public static Planet[] readPlanets(String address)
	{
		In in=new In(address);
		int num=in.readInt();
		double res=in.readDouble();

		/**注意这里的语法*/
		Planet[] ans= new Planet[num];
        for(int i=0;i<num;i+=1)
        {
        	/*ans[i].xxPos=in.readDouble();
        	ans[i].yyPos=in.readDouble();
        	ans[i].xxVel=in.readDouble();
        	ans[i].yyVel=in.readDouble();
        	ans[i].mass=in.readDouble();
        	ans[i].imgFileName=in.readString();
        	*/
        	double xp = in.readDouble();
			double yp = in.readDouble();
			double vx = in.readDouble();
			double vy = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			ans[i] = new Planet(xp, yp, vx, vy, m, img);
			/**为何前一种赋值方式不行？*/
        }
        return ans;
	}
	public static void main(String[] args)
	{
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		double radius=readRadius(filename);
		Planet[] planets=readPlanets(filename);

		/**drawing*/
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(-radius,radius);
		StdDraw.setYscale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        StdDraw.show();
        /**draw planets*/
        for(int i=0;i<planets.length;i=i+1)
        {
        	planets[i].draw();
        }
        /**animation*/
        double time=0;
        while(time < T)
        {

        	for(int i=0;i<planets.length;i=i+1)
        	{
        		double xforce=planets[i].calcNetForceExertedByX(planets);
        		double yforce=planets[i].calcNetForceExertedByY(planets);
        		planets[i].update(dt,xforce,yforce);
        	}
        	StdDraw.picture(0,0,"images/starfield.jpg");
            for(int i=0;i<planets.length;i=i+1)
            {
        	planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        	time+=dt;
        }
        /**results*/
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++)
         {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
         }
	}
}