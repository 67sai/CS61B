public class Planet {
	/**
	 * 关键字 "public" 表示这些变量是公共的，即可以从任何地方访问。它们对于其他类、方法和代码块都是可见的。
	 */
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	/* The name of the file that corresponds to the image that depicts the body (for example, jupiter.gif) */
	public String imgFileName;
	private double N=1.0e-11;

	/**
	 * the first constructor
	 * 第一个构造函数，用于创建一个新的Planet对象实例。
	 * 接受6个参数：xP、yP、xV、yV、m和img，用于初始化星球的位置、速度、质量和图像文件名。
	 */
	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/**
	 * the second constructor
	 * 第二个构造函数，接受一个Planet对象p作为参数，用于将一个已有的星球对象的属性复制给新创建的星球对象
	 */
	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/**
	 * calculate the distance between two planets
	 * this指代当前调用calcDistance方法的Planet对象实例。
	 * p是作为参数传递给calcDistance方法的另一个Planet对象实例。
	 */
	public double calcDistance(Planet p){
		double dx=this.xxPos-p.xxPos;
		double dy=this.yyPos-p.yyPos;
		double r=dx*dx+dy*dy;
		return Math.sqrt(r);
	}

	/**
	 * calculate the force
	 * this.calcDistance(p):计算当前对象(this)与传入对象(p)之间的距离
	 */
	public double calcForceExertedBy(Planet p){
		return 6.67*this.mass*p.mass*N/Math.pow(this.calcDistance(p),2);
	}

	/**
	 *
	 * FAIL: calcForceExertedByX(): Expected 133.4 and you gave -133.4
	 */
	public double calcForceExertedByX(Planet p){
		double dx=p.xxPos-this.xxPos;
		double fx=this.calcForceExertedBy(p)*dx/this.calcDistance(p);
		return fx;
	}

	/**
	 *
	 */
	public double calcForceExertedByY(Planet p) {
		double dy=p.yyPos-this.yyPos;
		double fy=this.calcForceExertedBy(p)*dy/this.calcDistance(p);
		return fy;
	}

	/**
	 * calculate the net X force exerted by all bodies in that array upon the current Body
	 * Objects.equals()方法：equals()方法可以用于比较两个对象的内容是否相等
	 */
	public double calcNetForceExertedByX(Planet[] ps){
		double sumForceX=0.0;
		for(Planet p:ps) {
			if(this.equals(p))
				continue;
			sumForceX+=this.calcForceExertedByX(p);
		}
		return sumForceX;
	}

	/**
	 *
	 */
	public double calcNetForceExertedByY(Planet[] ps){
		double sumForceY=0.0;
		for(int i=0;i<ps.length;i++) {
			Planet p=ps[i];
			if(this==p)
				continue;
			sumForceY+=this.calcForceExertedByY(p);
		}
		return sumForceY;
	}

	/**
	 *
	 */
	public void update(double dt,double fX,double fY){
		double ax=fX/this.mass;
		double ay=fY/this.mass;
		this.xxVel+=dt*ax;
		this.yyVel+=dt*ay;
		this.xxPos+=dt*xxVel;
		this.yyPos+=dt*yyVel;
	}
	
	public void draw(){
		StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
	}

}










