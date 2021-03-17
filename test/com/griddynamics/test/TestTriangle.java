package com.griddynamics.test;

import com.griddynamics.main.Triangle.TriangleType;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static com.griddynamics.main.Triangle.triangleAnalysisSystem;
import static org.testng.Assert.assertSame;

public class TestTriangle {
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeValue1() throws IllegalArgumentException{
        triangleAnalysisSystem(-148920, 31390, 31390);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeValue2() throws IllegalArgumentException{
        triangleAnalysisSystem(42.532, -1, -1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testZeroValue1() throws IllegalArgumentException{
        triangleAnalysisSystem(0, 12, 3.21);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testZeroValue2() throws IllegalArgumentException{
        triangleAnalysisSystem(0, 0, 0);
    }

    @Test
    public void testAllEqual() throws IllegalArgumentException{
        TriangleType triangleType = triangleAnalysisSystem(239, 239, 239);
        assertSame(triangleType, TriangleType.EQUILATERAL_TRIANGLE);
    }

    @Test
    public void testRecEqual1() throws IllegalArgumentException{
        TriangleType triangleType = triangleAnalysisSystem(Math.sqrt(2), Math.sqrt(2), 2);
        assertSame(triangleType, TriangleType.RECTANGULAR_ISOSCELES_TRIANGLE);
    }

    @Test
    public void testRecEqual2() throws IllegalArgumentException{
        TriangleType triangleType = triangleAnalysisSystem(12.5, 12.5, 12.5 * Math.sqrt(2));
        assertSame(triangleType, TriangleType.RECTANGULAR_ISOSCELES_TRIANGLE);
    }

    @Test
    public void testTwoEqual1()throws IllegalArgumentException {
        TriangleType triangleType = triangleAnalysisSystem(31, 31, 26);
        assertSame(triangleType, TriangleType.ISOSCELES_TRIANGLE);
    }

    @Test
    public void testTwoEqual2() throws IllegalArgumentException{
        TriangleType triangleType = triangleAnalysisSystem(12.6367, 7, 12.6367);
        assertSame(triangleType, TriangleType.ISOSCELES_TRIANGLE);
    }

    @Test
    public void testTwoEqual3()throws IllegalArgumentException {
        TriangleType triangleType = triangleAnalysisSystem(43.31844, 43.31844, 28);
        assertSame(triangleType, TriangleType.ISOSCELES_TRIANGLE);
    }

    @Test
    public void testRec1()throws IllegalArgumentException {
        TriangleType triangleType = triangleAnalysisSystem(13, 18, Math.sqrt(493));
        assertSame(triangleType, TriangleType.RECTANGULAR_TRIANGLE);
    }

    @Test
    public void testRec2() throws IllegalArgumentException{
        TriangleType triangleType = triangleAnalysisSystem(5, 3, 4);
        assertSame(triangleType, TriangleType.RECTANGULAR_TRIANGLE);
    }

    @Test
    public void testRec3() throws IllegalArgumentException{
        TriangleType triangleType = triangleAnalysisSystem(3.54, Math.sqrt(38.746), 5.12);
        assertSame(triangleType, TriangleType.RECTANGULAR_TRIANGLE);
    }

    @Test
    public void testUsualTriangle()throws IllegalArgumentException {
        TriangleType triangleType = triangleAnalysisSystem(23, 47, 39);
        assertSame(triangleType, TriangleType.DEFAULT_TRIANGLE);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNotTriangle()throws IllegalArgumentException {
        triangleAnalysisSystem(324.42356, 13.23415, 34.1415);
    }
}
