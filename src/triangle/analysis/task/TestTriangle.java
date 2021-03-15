package triangle.analysis.task;

import org.junit.Test;
import triangle.analysis.task.Triangle.TriangleType;

import static org.junit.Assert.assertSame;
import static triangle.analysis.task.Triangle.triangleAnalysisSystem;

public class TestTriangle {
    @Test
    public void testNegativeValue1() {
        TriangleType triangleType = triangleAnalysisSystem(-148920, 31390, 31390);
        assertSame(triangleType, TriangleType.NOT_TRIANGLE);
    }

    @Test
    public void testNegativeValue2() {
        TriangleType triangleType = triangleAnalysisSystem(42.532, -1, -1);
        assertSame(triangleType, TriangleType.NOT_TRIANGLE);
    }

    @Test
    public void testZeroValue1() {
        TriangleType triangleType = triangleAnalysisSystem(0, 12, 3.21);
        assertSame(triangleType, TriangleType.NOT_TRIANGLE);
    }

    @Test
    public void testZeroValue2() {
        TriangleType triangleType = triangleAnalysisSystem(0, 0, 0);
        assertSame(triangleType, TriangleType.NOT_TRIANGLE);
    }

    @Test
    public void testAllEqual() {
        TriangleType triangleType = triangleAnalysisSystem(239, 239, 239);
        assertSame(triangleType, TriangleType.EQUILATERAL_TRIANGLE);
    }

    @Test
    public void testTwoEqual1() {
        TriangleType triangleType = triangleAnalysisSystem(31, 31, 26);
        assertSame(triangleType, TriangleType.ISOSCELES_TRIANGLE);
    }

    @Test
    public void testTwoEqual2() {
        TriangleType triangleType = triangleAnalysisSystem(12.6367, 7, 12.6367);
        assertSame(triangleType, TriangleType.ISOSCELES_TRIANGLE);
    }

    @Test
    public void testTwoEqual3() {
        TriangleType triangleType = triangleAnalysisSystem(43.31844, 43.31844, 28);
        assertSame(triangleType, TriangleType.ISOSCELES_TRIANGLE);
    }

    @Test
    public void testUsualTriangle() {
        TriangleType triangleType = triangleAnalysisSystem(23, 47, 39);
        assertSame(triangleType, TriangleType.DEFAULT_TRIANGLE);
    }

    @Test
    public void testNotTriangle() {
        TriangleType triangleType = triangleAnalysisSystem(324.42356, 13.23415, 34.1415);
        assertSame(triangleType, TriangleType.NOT_TRIANGLE);
    }
}
