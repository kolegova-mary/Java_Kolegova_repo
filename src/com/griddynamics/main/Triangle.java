package com.griddynamics.main;

import java.math.BigDecimal;
import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please, enter the first side of triangle: ");
        double first = in.nextDouble();
        System.out.print("Enter the second side of triangle: ");
        double second = in.nextDouble();
        System.out.print("Enter the third side of triangle: ");
        double third = in.nextDouble();
        triangleAnalysisSystem(first, second, third);

    }

    public static boolean checkIfRectangular(BigDecimal x, BigDecimal y, BigDecimal z) {
        return (x.multiply(x).add(y.multiply(y))).compareTo(z.multiply(z)) == 0 ||
                (z.multiply(z).add(y.multiply(y))).compareTo(x.multiply(x)) == 0 ||
                (x.multiply(x).add(z.multiply(z))).compareTo(y.multiply(y)) == 0;
    }

    public static boolean checkIfIsosceles(BigDecimal x, BigDecimal y, BigDecimal z) {
        return x.compareTo(y) == 0 || y.compareTo(z) == 0 || x.compareTo(z) == 0;
    }

    public static boolean checkIfNotZeroAndRealTriangle(BigDecimal x, BigDecimal y, BigDecimal z) {
        return !(x.compareTo(BigDecimal.valueOf(0)) == 0 || y.compareTo(BigDecimal.valueOf(0)) == 0 || z.compareTo(BigDecimal.valueOf(0)) == 0) &&
                z.compareTo(x.add(y)) < 0 && x.compareTo(z.add(y)) < 0 && y.compareTo(z.add(y)) < 0;
    }

    public static TriangleType triangleAnalysisSystem(double fx, double fy, double fz) throws IllegalArgumentException {
        BigDecimal x = BigDecimal.valueOf(fx);
        BigDecimal y = BigDecimal.valueOf(fy);
        BigDecimal z = BigDecimal.valueOf(fz);
        if (checkIfNotZeroAndRealTriangle(x, y, z)) {
            if (x.compareTo(y) == 0 && x.compareTo(z) == 0) {
                System.out.println("You have equilateral triangle");
                return TriangleType.EQUILATERAL_TRIANGLE;
            } else {
                if (checkIfRectangular(x, y, z)) {
                    if (checkIfIsosceles(x, y, z)) {
                        System.out.println("You have rectangular isosceles triangle");
                        return TriangleType.RECTANGULAR_ISOSCELES_TRIANGLE;
                    } else {
                        System.out.println("You have rectangular triangle");
                        return TriangleType.RECTANGULAR_TRIANGLE;
                    }
                } else {
                    if (checkIfIsosceles(x, y, z)) {
                        System.out.println("You have isosceles triangle");
                        return TriangleType.ISOSCELES_TRIANGLE;
                    } else {
                        System.out.println("You have non-sided triangle");
                        return TriangleType.DEFAULT_TRIANGLE;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Sorry, it is impossible to make a triangle from your data");
        }
    }

    public enum TriangleType {
        EQUILATERAL_TRIANGLE,
        RECTANGULAR_ISOSCELES_TRIANGLE,
        ISOSCELES_TRIANGLE,
        RECTANGULAR_TRIANGLE,
        DEFAULT_TRIANGLE
    }
}
