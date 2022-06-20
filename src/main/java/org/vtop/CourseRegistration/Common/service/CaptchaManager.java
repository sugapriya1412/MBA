package org.vtop.CourseRegistration.Common.service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CaptchaManager {

    private int height = 40;
    private int width = 100;
    private Color mainbg = new Color(255, 255, 255);
    private Color backgroundColor = new Color(242, 244, 244);
    private boolean isUniqueColor = false;
    private Color foregroundColor = null;
    private boolean isUniqueFont = false;
    private Font fontFamily = null;
    private Color lineColor1 = new Color(215, 219, 221);
    private Color lineColor2 = new Color(202, 207, 210);
    private boolean isDrawLines = true;
    private boolean isGridLines = false;
    private Color gridColor=new Color(178, 186, 187);
    private final static BasicStroke stroke = new BasicStroke(2.0f);
    private final static BasicStroke wideStroke = new BasicStroke(3.0f);

    private String[] fontNames = { "Arial", "Arial Black", "Courier", "Garamond", "Helvetica", "Times New Roman" };

    public String createCaptcha(String randomChars) throws IOException {

        BufferedImage captchaImage = createCaptchaImage(randomChars);

        return convertCaptchaToString(captchaImage);

    }

    private BufferedImage createCaptchaImage(String captchaStr) {

        BufferedImage biImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2dImage = (Graphics2D) biImage.getGraphics();

        g2dImage.setColor(mainbg);
        g2dImage.fillRect(0, 0, width, height);

        g2dImage.setColor(backgroundColor);
        g2dImage.fillRoundRect(0, 0, width, height, 15, 15);

        Random randomNumber = new Random();

        int halfWidth = width / 2;
        int halfHeight = height / 2;

        if (isGridLines) {
            int startPoint = 5;
            g2dImage.setStroke(stroke);
            g2dImage.setColor(gridColor);
            for (int y = startPoint; y < height; y = y + 20) {
                g2dImage.drawLine(0, y, width, y);
            }

            for (int x = startPoint; x < width; x = x + 20) {
                g2dImage.drawLine(x, 0, x, height);
            }
        }

        for (int i = 0; i < captchaStr.length(); i++) {

            int xStartPoint = randomNumber.nextInt(halfWidth);
            int xEndPoint = randomNumber.nextInt(width - xStartPoint + 1) + xStartPoint;

            int yStartPoint = randomNumber.nextInt(halfHeight);
            int yEndPoint = randomNumber.nextInt(height - yStartPoint + 1) + yStartPoint;

            if (i % 2 == 0) {

                g2dImage.setColor(lineColor2);
                if (isDrawLines) {
                    g2dImage.setStroke(wideStroke);
                    g2dImage.drawLine(xStartPoint, yStartPoint, xEndPoint, yEndPoint);
                }
                g2dImage.setColor(getColor(randomNumber));
                g2dImage.setFont(getFont(randomNumber));
                g2dImage.drawString(captchaStr.substring(i, i + 1), 25 * (i + 1), 30);
            } else {
                g2dImage.drawString(captchaStr.substring(i, i + 1), 25 * (i + 1), 35);
                g2dImage.setColor(getColor(randomNumber));
                g2dImage.setFont(getFont(randomNumber));
                g2dImage.setColor(lineColor1);
                if (isDrawLines) {
                    g2dImage.setStroke(stroke);
                    g2dImage.drawLine(xStartPoint, yStartPoint, xEndPoint, yEndPoint);
                }
            }

        }

        if (isGridLines) {
            int startPoint = 15;
            g2dImage.setStroke(stroke);
            g2dImage.setColor(gridColor);
            for (int y = startPoint; y < height; y = y + 20) {
                g2dImage.drawLine(0, y, width, y);
            }

            for (int x = startPoint; x < width; x = x + 20) {
                g2dImage.drawLine(x, 0, x, height);
            }
        }


        return biImage;
    }

    private Color getColor(Random randomNumber) {
        if (isUniqueColor) {
            return foregroundColor;
        } else {
            Color choosenColor = new Color(randomNumber.nextInt(255), randomNumber.nextInt(255),
                    randomNumber.nextInt(255));
            float hsbVals[] = Color.RGBtoHSB(choosenColor.getRed(),
                    choosenColor.getGreen(),
                    choosenColor.getBlue(), null);
            Color highlight = Color.getHSBColor(hsbVals[0], hsbVals[1], 0.5f * (1f + hsbVals[2]));

            return highlight;
        }
    }

    private Font getFont(Random randomNumber) {
        if (isUniqueFont) {
            return fontFamily;
        } else {
            return (new Font(fontNames[randomNumber.nextInt(5)], Font.BOLD, 30));
        }
    }

    private String convertCaptchaToString(BufferedImage cpimg) throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(cpimg, "jpg", bos);
        byte[] byteArray = Base64.getEncoder().encode(bos.toByteArray());
        return new String(byteArray);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.isUniqueColor = true;
        this.foregroundColor = foregroundColor;
    }

    public boolean isUniqueColor() {
        return isUniqueColor;
    }

    public void setUniqueColor(boolean isUniqueColor) {
        this.isUniqueColor = isUniqueColor;
    }

    public boolean isUniqueFont() {
        return isUniqueFont;
    }

    public void setUniqueFont(boolean isUniqueFont) {
        this.isUniqueFont = isUniqueFont;
    }

    public Font getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(Font fontFamily) {
        this.isUniqueFont = true;
        this.fontFamily = fontFamily;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isDrawLines() {
        return isDrawLines;
    }

    public void setDrawLines(boolean isDrawLines) {
        this.isDrawLines = isDrawLines;
    }

    public Color getLineColor1() {
        return lineColor1;
    }

    public void setLineColor1(Color lineColor1) {
        this.lineColor1 = lineColor1;
    }

    public Color getLineColor2() {
        return lineColor2;
    }

    public void setLineColor2(Color lineColor2) {
        this.lineColor2 = lineColor2;
    }

    public boolean isGridLines() {
        return isGridLines;
    }

    public void setGridLines(boolean isGridLines) {
        if(isGridLines) {
            isDrawLines=false;
        }
        this.isGridLines = isGridLines;
    }
}
