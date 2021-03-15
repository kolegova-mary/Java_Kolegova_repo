package triangle.analysis.task;

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

    public static TriangleType triangleAnalysisSystem(double x, double y, double z) {
        if (x != 0 && y != 0 && z != 0 && x + y > z && x + z > y && y + z > x) {
            if (x == y && x == z) {
                System.out.println("You have equilateral triangle");
                return TriangleType.EQUILATERAL_TRIANGLE;
            } else {
                if (x == y || y == z || x == z) {
                    System.out.println("You have isosceles triangle");
                    return TriangleType.ISOSCELES_TRIANGLE;
                } else {
                    System.out.println("You have non-sided triangle");
                    return TriangleType.DEFAULT_TRIANGLE;
                }
            }
        } else {
            System.out.println("Sorry, it is impossible to make a triangle from your data");
            return TriangleType.NOT_TRIANGLE;
        }
    }

    enum TriangleType {
        EQUILATERAL_TRIANGLE,
        ISOSCELES_TRIANGLE,
        DEFAULT_TRIANGLE,
        NOT_TRIANGLE
    }
}
