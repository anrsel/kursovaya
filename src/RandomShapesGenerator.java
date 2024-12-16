import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class RandomShapesGenerator extends JPanel {
    private int numLines;
    private int numCircles;
    private int numRectangles;
    private int numTriangles;
    private int numParabolas;
    private int numTrapezoids;
    private int width;
    private int height;
    private Random random;

    public RandomShapesGenerator(int width, int height, int numLines, int numCircles, int numRectangles, int numTriangles, int numParabolas, int numTrapezoids) {
        this.width = width;
        this.height = height;
        this.random = new Random();
        this.numLines = numLines;
        this.numCircles = numCircles;
        this.numRectangles = numRectangles;
        this.numTriangles = numTriangles;
        this.numParabolas = numParabolas;
        this.numTrapezoids = numTrapezoids;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < numLines; i++) {
            drawRandomLine(g2d);
        }
        for (int i = 0; i < numCircles; i++) {
            drawRandomCircle(g2d);
        }
        for (int i = 0; i < numRectangles; i++) {
            drawRandomRectangle(g2d);
        }
        for (int i = 0; i < numTriangles; i++) {
            drawRandomTriangle(g2d);
        }
        for (int i = 0; i < numParabolas; i++) {
            drawRandomParabola(g2d);
        }
        for (int i = 0; i < numTrapezoids; i++) {
            drawRandomTrapezoid(g2d);
        }
    }

    private void drawRandomLine(Graphics2D g2d) {
        int x1 = random.nextInt(width);
        int y1 = random.nextInt(height);
        int x2 = random.nextInt(width);
        int y2 = random.nextInt(height);
        g2d.drawLine(x1, y1, x2, y2);
    }

    private void drawRandomCircle(Graphics2D g2d) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int radius = random.nextInt(Math.min(width, height) / 4);
        g2d.drawOval(x, y, radius, radius);
    }

    private void drawRandomRectangle(Graphics2D g2d) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int w = random.nextInt(width / 4);
        int h = random.nextInt(height / 4);
        g2d.drawRect(x, y, w, h);
    }

    private void drawRandomTriangle(Graphics2D g2d) {
        int[] xPoints = {random.nextInt(width), random.nextInt(width), random.nextInt(width)};
        int[] yPoints = {random.nextInt(height), random.nextInt(height), random.nextInt(height)};
        g2d.drawPolygon(xPoints, yPoints, 3);
    }

    private void drawRandomParabola(Graphics2D g2d) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int widthParabola = random.nextInt(width / 4);
        int heightParabola = random.nextInt(height / 4);
        g2d.draw(new QuadCurve2D.Float(x, y, x + widthParabola / 2, y - heightParabola, x + widthParabola, y));
    }

    private void drawRandomTrapezoid(Graphics2D g2d) {
        int x1 = random.nextInt(width);
        int y1 = random.nextInt(height);
        int topWidth = random.nextInt(width / 4);
        int bottomWidth = topWidth + random.nextInt(width / 4);
        int heightTrapezoid = random.nextInt(height / 4);
        int[] xPoints = {x1, x1 + topWidth, x1 + bottomWidth, x1};
        int[] yPoints = {y1, y1, y1 + heightTrapezoid, y1 + heightTrapezoid};
        g2d.drawPolygon(xPoints, yPoints, 4);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numLines = 0, numCircles = 0, numRectangles = 0, numTriangles = 0, numParabolas = 0, numTrapezoids = 0;

        try {
            System.out.print("Введите количество линий: ");
            numLines = scanner.nextInt();

            System.out.print("Введите количество окружностей: ");
            numCircles = scanner.nextInt();

            System.out.print("Введите количество прямоугольников: ");
            numRectangles = scanner.nextInt();

            System.out.print("Введите количество треугольников: ");
            numTriangles = scanner.nextInt();

            System.out.print("Введите количество парабол: ");
            numParabolas = scanner.nextInt();

            System.out.print("Введите количество трапеций: ");
            numTrapezoids = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Ошибка: Введите целое число.");
            return;
        }

        JFrame frame = new JFrame("Random Shapes Generator");
        RandomShapesGenerator panel = new RandomShapesGenerator(800, 600, numLines, numCircles, numRectangles, numTriangles, numParabolas, numTrapezoids);
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        scanner.close();
    }
}