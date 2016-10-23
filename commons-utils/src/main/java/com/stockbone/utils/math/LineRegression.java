package com.stockbone.utils.math;

import java.awt.geom.Point2D;

/**
 * 散点图线性回归
 */
public class LineRegression {

	public static float regressionSlope(Point2D.Float[] points) {
		// 斜率
		float slope;
		// 常数
		float constant;
		float sumx = 0, sumy = 0, sumxy = 0, sumxx = 0, sumyy = 0;
		for (int i = 0; i < points.length; i++) {
			sumx += points[i].getX();
			sumy += points[i].getY();
			sumxy += points[i].getX() * points[i].getY();
			sumxx += points[i].getX() * points[i].getX();
			sumyy += points[i].getY() * points[i].getY();
		}
		float n = points.length;
		constant = ((sumy * sumxx) - sumx * sumxy) / (n * sumxx - (sumx * sumx));
		slope = (n * sumxy - (sumx * sumy)) / (n * sumxx - (sumx * sumx));
		return slope;
	}

}
