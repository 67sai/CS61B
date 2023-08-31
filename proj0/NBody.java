/**
 * This class will have NO constructor.
 * The goal of this class is to simulate a universe specified in one of the data files.
 */

/**
 * 使用In类从文件中读取数据
 * in.readInt();读取文件中的下一个整数，并将其赋值给变量
 *
 * TestReadRadius.java:31: 错误: 无法从静态上下文中引用非静态 方法 readRadius(String)
 *         double actualOutput = NBody.readRadius(planetsTxtPath);
 *                                    ^
 * 静态方法可以在没有创建类实例的情况下直接使用。这意味着你可以通过类名直接调用该方法，而不需要先创建该类的对象。
 */
public class NBody{
	public static double readRadius(String str){
		In in = new In(str);
		in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String str){
		In in = new In(str);
		int num=in.readInt();
		in.readDouble();

		double xxPos;
		double yyPos;
		double xxVel;
		double yyVel;
		double mass;
		String imgFileName;
		Planet p;

		Planet[] ps=new Planet[num];
		for(int i=0;i<num;i++){
			xxPos=in.readDouble();
			yyPos=in.readDouble();
			xxVel=in.readDouble();
			yyVel=in.readDouble();
			mass=in.readDouble();
			imgFileName=in.readString();
			p=new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
			ps[i]=p;
		}
		return ps;
	}

	/**
	 * Entry Point
	 * String[] args: main方法的参数是一个String类型的数组，命名为args。
	 * 这个数组用于接收命令行参数，它是程序启动时传递给程序的参数。
	 *
	 * String str = "3.14";
	 * double num = Double.parseDouble(str);
	 * System.out.println(num);  // 输出：3.14
	 * 在上面的示例中，字符串str被转换为double类型的变量num。
	 * Double.parseDouble()方法将字符串解析为对应的double值，并赋给变量num。
	 *
	 * picture(图片) (支持的图像格式为JPEG，PNG和GIF)
	 * public static void picture(double x,double y, java.lang.String filename)
	 * 绘制以（x，y）为中心的指定图像。
	 *
	 * 在Java中，每个实例方法都有一个隐含的this参数，它指向调用该方法的对象实例。
	 * 通过使用this关键字，你可以引用当前对象的成员变量和方法，以区分局部变量和方法参数。
	 * 静态方法没有隐含的this参数，因为它们与类本身相关联，而不是特定的对象实例。
	 */
	public static void main(String[] args){
		// Collecting All Needed Input
		// input ep：157788000.0 25000.0 data/planets.txt
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];

		// Drawing the Background
		// setXscale、setYscale、setScale
		// 无参数时：设置X轴、Y轴或同时设置范围默认值0-1.0
		// 有参数时：public static void setXscale(double min,double max)
		double ra=NBody.readRadius(filename);
		StdDraw.setScale(-ra,ra);
		StdDraw.clear();
		StdDraw.picture(0,0,"images/starfield.jpg");

		// Drawing More than One Body
		// readPlanets方法是静态方法，可以直接通过类名调用，而不需要创建类的实例。
		// 静态方法只能访问静态成员（静态变量和静态方法），而不能访问非静态成员。
		// 如果需要在静态方法中访问非静态成员，那么需要通过创建类的实例来实现。
		Planet[] ps=NBody.readPlanets(filename);
		for(Planet p:ps)
			p.draw();

		// Enable double buffering : a graphics technique to prevent flickering in the animation
		StdDraw.enableDoubleBuffering();

		// Set up a loop to loop until this time variable reaches (and includes) the T from above.
		for(double t=0;t<=T;t+=dt){
			// Create an xForces array and yForces array.
			double[] xForces=new double[ps.length];
			double[] yForces=new double[ps.length];

			// After calculating the net forces for every Body, call update on each of the Bodys.
			// This will update each body’s position, velocity, and acceleration.
			// update arrays for x- and y-NetForces for all planets
			for(int i=0;i<ps.length;i++){
				xForces[i]=ps[i].calcNetForceExertedByX(ps);
				yForces[i]=ps[i].calcNetForceExertedByY(ps);
			}

			// update each planet
			// do not make any calls to update
			// until all forces have been calculated and safely stored in xForces and yForces.
			for(int i=0; i<ps.length; i++)
				ps[i].update(t, xForces[i], yForces[i]);

			// Draw the background image again
			StdDraw.picture(0,0,"images/starfield.jpg");

			// Draw all of the Bodys again
			for(Planet p:ps)
				p.draw();

			// Show the offscreen buffer (see the show method of StdDraw).
			StdDraw.show();

			//Pause the animation for 10 milliseconds (see the pause method of StdDraw).
			StdDraw.pause(10);
		}

		StdOut.printf("%d\n", aP.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < aP.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					aP[i].xxPos, aP[i].yyPos,aP[i].xxVel,
					aP[i].yyVel, aP[i].mass, aP[i].imgFileName);
		}
	}
}