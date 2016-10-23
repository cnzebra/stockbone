package com.stockbone.utils.math;

import java.awt.geom.Point2D;

import org.junit.Test;

public class LineRegressionTest {

	@Test
	public void testRegression() {
		Point2D.Float[] points = new Point2D.Float[] { new Point2D.Float(43, 99), new Point2D.Float(21, 65), new Point2D.Float(25, 79), new Point2D.Float(42, 75), new Point2D.Float(57, 87), new Point2D.Float(59, 81) };
		float slope = LineRegression.regressionSlope(points);
		System.out.println(slope);
	}
}
