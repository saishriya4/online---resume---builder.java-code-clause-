
/*
 Hello guys.
 my name is Shreyas Khansole.
 I am Completed Code Close Goldan task 2.
 Project Name is Reusable CAPTCHA security engine Project using Java.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class ReusableCaptchaEngine {
    private static final int CAPTCHA_LENGTH = 6;
    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = 50;
    private static final int FONT_SIZE = 24;
    private static final int NOISE_LINES = 100;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static void main(String[] args) throws IOException {
        String captcha = generateCaptcha();
        BufferedImage captchaImage = generateCaptchaImage(captcha);
        saveCaptchaImage(captchaImage);
        System.out.println("Captcha string: " + captcha);
    }

    private static String generateCaptcha() {
        StringBuilder captchaBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            captchaBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return captchaBuilder.toString();
    }

    private static BufferedImage generateCaptchaImage(String captcha) {
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        Random random = new Random();

        // Set background color
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

        // Draw captcha string on the image
        graphics.setColor(new Color(0, 0, 0));
        graphics.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        graphics.drawString(captcha, 40, 30);

        // Generate noise to make captcha harder to read
        for (int i = 0; i < NOISE_LINES; i++) {
            int x1 = random.nextInt(IMAGE_WIDTH);
            int y1 = random.nextInt(IMAGE_HEIGHT);
            int x2 = x1 + random.nextInt(10);
            int y2 = y1 + random.nextInt(10);
            graphics.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            graphics.drawLine(x1, y1, x2, y2);
        }

        return image;
    }

    private static void saveCaptchaImage(BufferedImage image) throws IOException {
        File captchaFile = new File("captcha.png");
        ImageIO.write(image, "png", captchaFile);
    }
}

