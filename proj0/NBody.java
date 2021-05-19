public class NBody {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please supply T dt and filename");
		}
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double rOfUniverse = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-rOfUniverse, rOfUniverse);
		StdDraw.clear();
		// draw background
		// StdDraw.picture(x, y, filename) plots the image in the given filename (either JPEG, GIF, or PNG format) on the canvas, centered on (x, y). 
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		for(Planet p: planets) {
			p.draw();
		}
		StdDraw.show();
		StdDraw.pause(10);
		double t = 0.0;
		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];
		while(t<T) {
			for(int i=0;i<planets.length;i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i=0;i<planets.length;i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "./images/starfield.jpg");
			for(int i=0;i<planets.length;i++) {
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t = t+dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", rOfUniverse);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
	}

	public static double readRadius(String imgFileName) {
		In in = new In(imgFileName);
		int numOfPlanets = in.readInt();
		double rOfUniverse = in.readDouble();
		return rOfUniverse;
	}

	public static Planet[] readPlanets(String imgFileName) {
		In in = new In(imgFileName);
		int numOfPlanets = in.readInt();
		double rOfUniverse = in.readDouble();
		Planet[] allPlanets = new Planet[numOfPlanets];
		for(int i=0;i<numOfPlanets;i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			allPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
		}
		return allPlanets;
	}
}