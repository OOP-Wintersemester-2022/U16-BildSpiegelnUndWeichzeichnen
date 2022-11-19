package ImageProcessing;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.graphics.Image;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

public class ImageProcessing extends GraphicsApp {

    private static final int FRAME_RATE = 1;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;

    private Image imageSopranos;
    private Image imageOffice;

    @Override
    public void initialize() {
        setupImages();

        setupCanvas((int) imageSopranos.getWidth(), (int) imageSopranos.getHeight());
    }

    public void draw() {
        drawBackground(BACKGROUND_COLOR);

        imageSopranos.draw();
    }

    private void setupCanvas(int width, int height) {
        setCanvasSize(width, height);
        setFrameRate(FRAME_RATE);
    }

    private void setupImages() {
        imageSopranos = new Image(0, 0, "data/assets/sopranos.jpg");
        imageOffice = new Image(0, 0, "data/assets/the_office_orig.png");
    }

    private Image flipImageHorizontal(Image img) {
        return img;
    }

    private Image blurImage(Image img) {
      return img;
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}