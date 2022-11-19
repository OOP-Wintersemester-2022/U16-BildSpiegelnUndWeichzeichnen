package ImageProcessing;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

public class ImageProcessing extends GraphicsApp {

    /* Private Konstanten */
    private static final int FRAME_RATE = 1;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;

    private Image image;

    /*
     * Die initialize-Methode wird einmalig zum Start des Programms
     * aufgerufen.
     */

    @Override
    public void initialize() {
        setupImages();

        setupCanvas((int) image.getWidth(), (int) image.getHeight());
    }

    /*
     * Die draw-Methode wird so lange wiederholt aufgerufen, bis das Programm
     * beendet wird.
     */
    public void draw() {
        drawBackground(BACKGROUND_COLOR);

        //image = flipImageHorizontal(image);
        image = blurImage(image);

        image.draw();
    }

    private void setupCanvas(int width, int height) {
        setCanvasSize(width, height);
        setFrameRate(FRAME_RATE);
    }

    private void setupImages() {
        //image = new Image(0, 0, "data/assets/sopranos.jpg");
        image = new Image(0, 0, "data/assets/the_office_orig.png");
    }

    private Image flipImageHorizontal(Image img) {
        int[][] pixels = img.getPixelArray();

        int numLineTotal = pixels.length;
        for (int lineNum = 0; lineNum < numLineTotal; lineNum++) {
            int[] flippedLine = flipLine(pixels[lineNum]);
            pixels[lineNum] = flippedLine;
        }
        img.setPixelArray(pixels);
        return img;
    }

    private Image blurImage(Image img) {
        int[][] sourcePixels = img.getPixelArray();
        int[][] targetPixels = new int[sourcePixels.length][sourcePixels[0].length];

        for(int y=0; y<sourcePixels.length; y++){
            for(int x=0; x<sourcePixels[y].length; x++){
                targetPixels[y][x] = blurPixel(x,y, sourcePixels);
            }
        }

        img.setPixelArray(targetPixels);

        return img;
    }

    private int blurPixel(int x, int y, int[][] sourcePixels) {
        Color[] colors = new Color[5];
        // color of pixel
        colors[0] = getColor(x, y, sourcePixels);
        // color of pixel to the right
        colors[1] = getColor(x + 1, y, sourcePixels);
        // color of pixel at bottom
        colors[2] = getColor(x, y + 1, sourcePixels);
        // color of pixel to the left
        colors[3] = getColor(x - 1, y, sourcePixels);
        // color of pixel at top
        colors[4] = getColor(x, y - 1, sourcePixels);

        Color result = mergeColors(colors);
        return result.toInt();
    }

    private Color getColor(int x, int y, int[][]sourcePixels){
        if(y >= 0 && y < sourcePixels.length){
            if(x >= 0 && x < sourcePixels[y].length){
                return new Color(sourcePixels[y][x]);
            }
        }
        return null;
    }

    private Color mergeColors(Color[] colors) {
        int red = 0;
        int green = 0;
        int blue = 0;

        int colorCount = 0;

        for(int i=0; i<colors.length; i++){
            if(colors[i] != null){
                red += colors[i].red();
                green += colors[i].green();
                blue += colors[i].blue();
                colorCount++;
            }
        }
        red = red / colorCount;
        green = green / colorCount;
        blue = blue / colorCount;

        Color mergedColor = new Color(red, green, blue);

        return mergedColor;
    }

    private int[] flipLine(int[] imageLine) {
        int[] flippedLine = new int[imageLine.length];
        for (int i = 0; i < imageLine.length; i++) {
            flippedLine[imageLine.length - 1 - i] = imageLine[i];
        }
        return flippedLine;
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}