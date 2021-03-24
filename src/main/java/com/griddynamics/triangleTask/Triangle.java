package com.griddynamics.triangleTask;
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
    public static boolean doubleEquals(double a, double b) {
        final double EPS = 0.00001;
        return Math.abs(a - b) < EPS;
    }

    public static boolean checkIfRectangular(double x, double y, double z) {
        return doubleEquals(x * x + y * y, z * z) || doubleEquals(x * x + z * z, y * y) || doubleEquals(z * z + y * y, x * x);
    }

    public static boolean checkIfIsosceles(double x, double y, double z) {
        return doubleEquals(x, y) || doubleEquals(x, z) || doubleEquals(y, z);
    }

    public static TriangleType triangleAnalysisSystem(double x, double y, double z) {
        if (!(doubleEquals(x, 0) || doubleEquals(y, 0) || doubleEquals(z, 0)) && x + y > z && x + z > y && y + z > x) {
            if (doubleEquals(x, y) && doubleEquals(x, z)){
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
