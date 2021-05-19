public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	static final double G = 6.67e-11;
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p2) {
		return Math.sqrt(Math.pow(xxPos - p2.xxPos, 2) + Math.pow(yyPos - p2.yyPos, 2));
	}

	public double calcForceExertedBy(Planet p2) {
		return G*(mass*p2.mass)/Math.pow(calcDistance(p2), 2);
	}

	public double calcForceExertedByX(Planet p2) {
		double F = calcForceExertedBy(p2);
		double r = calcDistance(p2);
		return F*(p2.xxPos - xxPos)/r;
	}

	public double calcForceExertedByY(Planet p2) {
		double F = calcForceExertedBy(p2);
		double r = calcDistance(p2);
		return F*(p2.yyPos - yyPos)/r;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double netForce = 0;
		for(int i=0;i<allPlanets.length;i++) {
			if(this.equals(allPlanets[i])==false) {
				netForce = netForce + calcForceExertedByX(allPlanets[i]);
			}
		}
		return netForce;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double netForce = 0;
		for(int i=0;i<allPlanets.length;i++) {
			if(this.equals(allPlanets[i])==false) {
				netForce = netForce + calcForceExertedByY(allPlanets[i]);
			}
		}
		return netForce;
	}

	public void update(double dt, double fX, double fY) {
		double xxAccel = fX/mass;
		double yyAccel = fY/mass;
		xxVel = xxVel + dt*xxAccel;
		yyVel = yyVel + dt*yyAccel;
		xxPos = xxPos + dt*xxVel;
		yyPos = yyPos + dt*yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "./images/"+imgFileName);
	}





}