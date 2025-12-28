package code;

import image.APImage;
import image.Pixel;

import javax.swing.*;

public class ImageManipulation {

    private static int getAverageColour(Pixel pixel) {
        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();

        int average = (red + green + blue) / 3;
        return average;
    }

    public static void grayScale(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        int width = image.getWidth();
        int height = image.getHeight();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                Pixel pixel = image.getPixel(column, row);

                int average = getAverageColour(pixel);

                pixel.setRed(average);
                pixel.setGreen(average);
                pixel.setBlue(average);


            }
        }
        image.draw();
    }

    public static void blackAndWhite(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        int width = image.getWidth();
        int height = image.getHeight();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                Pixel pixel = image.getPixel(column, row);

                int average = getAverageColour(pixel);

                if (average < 128) {
                    pixel.setRed(0);
                    pixel.setGreen(0);
                    pixel.setBlue(0);

                }
                if (average >= 128) {
                    pixel.setRed(255);
                    pixel.setGreen(255);
                    pixel.setBlue(255);

                }

            }

        }
        image.draw();
    }

    public static void edgeDetection(String pathToFile, int threshold) {
        APImage image = new APImage(pathToFile);
        int width = image.getWidth();
        int height = image.getHeight();

        APImage end = new APImage(width, height);

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                Pixel pixel = image.getPixel(column, row);
                int average = getAverageColour(pixel);

                boolean isEdge = false;

                if (column > 0) {
                    Pixel left = image.getPixel(column - 1, row);
                    int averageOfLeft = getAverageColour(left);
                    if (Math.abs(average - averageOfLeft) > threshold) {
                        isEdge = true;
                    }
                }

                if (row < height - 1) {
                    Pixel below = image.getPixel(column, row + 1);
                    int averageOfBelow = getAverageColour(below);
                    if (Math.abs(average - averageOfBelow) > threshold) {
                        isEdge = true;
                    }
                }

                Pixel endPixel = end.getPixel(column, row);
                if (isEdge) {
                    endPixel.setRed(0);
                    endPixel.setGreen(0);
                    endPixel.setBlue(0);
                } else {
                    endPixel.setRed(255);
                    endPixel.setGreen(255);
                    endPixel.setBlue(255);
                }
            }
        }

        end.draw();

    }

    public static void reflectImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        int width = image.getWidth();
        int height = image.getHeight();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width / 2; column++) {

                Pixel pixel = image.getPixel(column, row);
                int mirror = width - 1 - column;
                Pixel right = image.getPixel(mirror, row);

                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
                int rightRed = right.getRed();
                int rightGreen = right.getGreen();
                int rightBlue = right.getBlue();
                pixel.setRed(rightRed);
                pixel.setGreen(rightGreen);
                pixel.setBlue(rightBlue);
                right.setRed(red);
                right.setGreen(green);
                right.setBlue(blue);


            }
        }
        image.draw();
    }

    public static void rotateImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        int width = image.getWidth();
        int height = image.getHeight();
        APImage rotated = new APImage(height, width);

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {

                Pixel pixel = image.getPixel(column, row);
                int newCol = height - 1 - row;
                int newRow = column;
                Pixel end = rotated.getPixel(newCol, newRow);

                end.setRed(pixel.getRed());
                end.setGreen(pixel.getGreen());
                end.setBlue(pixel.getBlue());

            }
        }
        rotated.draw();
    }



    public static void main(String[] args) {

        APImage image = new APImage("cyberpunk2077.jpg");
        System.out.println(image);
        image.draw();

        grayScale("cyberpunk2077.jpg");
        blackAndWhite("cyberpunk2077.jpg");
        edgeDetection("cyberpunk2077.jpg",20);
        reflectImage("cyberpunk2077.jpg");
        rotateImage("cyberpunk2077.jpg");

    }


}
