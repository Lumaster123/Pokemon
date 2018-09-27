package pokemon.visualisation.text;

import engine.Time;
import engine.components.KeyChangedListener;
import engine.components.Keyboard;
import engine.rendering.RenderableObject;
import engine.rendering.Renderer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import pokemon.data.Controls;

public class TextBox extends RenderableObject implements KeyChangedListener {

    private Dimension win_size;

    private String[] text;
    private String visibleTextRow1;
    private String visibleTextRow2;
    private String visibleTextRow3;

    private boolean displayForwardSymbol;
    private boolean pressedForward;

    private boolean needUpdate;

    private Font font = new Font("MS Serif", 0, 40);

    public static int TEXT_SPEED_SLOW = 10;
    public static int TEXT_SPEED_MEDIUM = 18;
    public static int TEXT_SPEED_FAST = 30;
    public static int TEXT_SPEED_FASTEST = 50;
    public static int TEXT_SPEED_INSTANT = 1000;

    private BufferedImage img;

    public TextBox(Dimension panelSize) {
        super(Renderer.Layer.LAYER_5, 0, panelSize.height - panelSize.height / 5, panelSize.width - 10, panelSize.height / 5 - 10);
        win_size = panelSize;

        text = null;
        visibleTextRow1 = "";
        visibleTextRow2 = "";
        visibleTextRow3 = "";
        displayForwardSymbol = false;
        pressedForward = false;
        needUpdate = true;

        img = new BufferedImage((int) win_size.width - 10, (int) win_size.height / 5 - 10, BufferedImage.TYPE_INT_ARGB);
    }

    public void setText(String text) {
        String[] manualSplit = text.split("\n");
        ArrayList<String> automaticSplit = new ArrayList<>();
        for(String s : manualSplit){
            String[] arr = splitRow(s);
            for(String string : arr){
                automaticSplit.add(string);
            }
        }
        this.text = convertListToArray(automaticSplit);
    }
    
    private String[] splitRow(String s){
        Graphics2D g = (Graphics2D)img.getGraphics();
        ArrayList<String> newRows = new ArrayList<>();
        if(g.getFontMetrics(font).stringWidth(s) > width - 80){
            String[] words = s.split(" ");
            int rowStart = 0;
            String row = ""; 
            while(rowStart < words.length-1){
                int count = 0;
                for(int i = 0; i < words.length; i++){
                    row += words[count]+" ";
                    count++;
                    if(!(g.getFontMetrics(font).stringWidth(row) < width - 80)){
                        newRows.add(row);
                        rowStart = i+1;
                        count = rowStart;
                        row = "";
                    }
                }
                if(!row.equals("")){
                    newRows.add(row);
                    break;
                }
            }
        }else{
            String[] arr = {s};
            return arr;
        }
        
        return convertListToArray(newRows);
    }
    
    private String[] convertListToArray(ArrayList<String> list){
        String[] arr = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            arr[i] = list.get(i);
        }
        return arr;
    }

    public void writeText(int charPerSecond) {
        boolean needMoreRows = false;
        for (int j = 0; j < text.length; j++) {
            for (int i = 0; i < text[j].length(); i++) {
                switch (j % 3) {
                    case 0:
                        visibleTextRow1 += text[j].charAt(i);
                        break;
                    case 1:
                        visibleTextRow2 += text[j].charAt(i);
                        break;
                    case 2:
                        visibleTextRow3 += text[j].charAt(i);
                        if (j + 1 < text.length) {
                            needMoreRows = true;
                        }
                        break;
                    default:
                        break;
                }

                needUpdate = true;

                Time.sleep(1000 / charPerSecond);
            }
            if (needMoreRows || j == text.length-1) {
                displayForwardSymbol = true;
                needUpdate = true;
                while (!pressedForward) {
                    Time.sleep(5);
                }
                displayForwardSymbol = false;
                needUpdate = true;
                pressedForward = false;
                clearText();
            }
        }
        clearText();
    }

    public void clearText() {
        visibleTextRow1 = "";
        visibleTextRow2 = "";
        visibleTextRow3 = "";
        displayForwardSymbol = false;
        needUpdate = true;
    }

    public void update() {
        Graphics2D g = (Graphics2D) img.createGraphics();

        g.setColor(new Color(130, 130, 130));
        g.fillRoundRect(0, 0, (int) width, (int) height, 20, 20);
        g.setColor(new Color(45, 160, 175));
        g.fillRoundRect(2, 2, (int) width - 4, (int) height - 4, 20, 20);
        g.setColor(Color.cyan);
        g.fillRoundRect(7, 7, (int) width - 14, (int) height - 14, 20, 20);
        g.setColor(Color.black);
        g.fillRoundRect(9, 9, (int) width - 18, (int) height - 18, 20, 20);
        g.setColor(Color.white);
        g.fillRoundRect(10, 10, (int) width - 20, (int) height - 20, 20, 20);

        g.setColor(Color.black);
        g.setFont(font);
        g.drawString(visibleTextRow1, 12 + 10, 15 + 40);
        g.drawString(visibleTextRow2, 12 + 10, 15 + 40 + 50);
        g.drawString(visibleTextRow3, 12 + 10, 15 + 40 + 100);

        if(displayForwardSymbol){
            g.setColor(Color.red);
            g.fillRoundRect((int)width - 47, (int)height - 47, 30, 30, 8, 8);
            g.setColor(Color.orange);
            Point pos = new Point((int)width - (int)(49/1.5), (int)height - (int)(47/1.5));
            int mult = 10;
            int[] xPoints = {pos.x+(-1*mult), pos.x+(1*mult), pos.x+(0*mult)};
            int[] yPoints = {pos.y+(-1*mult), pos.y+(-1*mult), pos.y+(1*mult)};
            g.fillPolygon(xPoints, yPoints, 3);
        }
        
        needUpdate = false;
    }

    @Override
    public void draw(Graphics2D g, float x, float y) {
        if (needUpdate) {
            update();
        }
        if (img != null) {
            g.drawImage(img, (int) this.x + 5, (int) this.y + 5, null);
        }
    }

    @Override
    public void keyChanged(int keyCode, boolean keyState) {
        if (keyCode == Controls.KEY_A && keyState == false && displayForwardSymbol) {
            pressedForward = true;
        }
    }

}
