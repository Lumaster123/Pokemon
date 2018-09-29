package pokemon.visualisation.text;

import engine.Time;
import engine.components.KeyChangedListener;
import engine.console.ConsoleManager;
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
    private String[] selectBoxItems;
    private String visibleBoxItem1;
    private String visibleBoxItem2;
    private String visibleBoxItem3;
    private int visibleBoxItemIndex1;
    private int visibleBoxItemIndex2;
    private int visibleBoxItemIndex3;
    private int selectedItem;
    
    private boolean displayForwardSymbol;
    private boolean displaySelectBox;
    private boolean pressedForward;

    private boolean needUpdate;

    private Font font = new Font("MS Serif", 0, 40);

    public static int TEXT_SPEED_SLOW = 50;
    public static int TEXT_SPEED_MEDIUM = 100;
    public static int TEXT_SPEED_FAST = 150;
    public static int TEXT_SPEED_FASTEST = 250;
    public static int TEXT_SPEED_INSTANT = 10000;

    private BufferedImage img;
    private BufferedImage selectBoxImg;

    public TextBox(Dimension panelSize) {
        super(Renderer.Layer.LAYER_5, 0, panelSize.height - panelSize.height / 5, panelSize.width - 10, panelSize.height / 5 - 10);
        win_size = panelSize;

        text = null;
        visibleTextRow1 = "";
        visibleTextRow2 = "";
        visibleTextRow3 = "";
        selectBoxItems = null;
        visibleBoxItem1 = "";
        visibleBoxItem2 = "";
        visibleBoxItem3 = "";
        visibleBoxItemIndex1 = 0;
        visibleBoxItemIndex2 = 1;
        visibleBoxItemIndex3 = 2;
        selectedItem = 0;
        displayForwardSymbol = false;
        displaySelectBox = false;
        pressedForward = false;
        needUpdate = true;

        img = new BufferedImage((int) win_size.width - 10, (int) win_size.height / 5 - 10, BufferedImage.TYPE_INT_ARGB);
        selectBoxImg = new BufferedImage(190, (int) win_size.height / 5 - 10 - 30, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Used to set the Text that should be displayed after.
     * It makes nextRow when in the text is written \n or when the line is to long.
     * 
     * @param text the text that should be displayed after.
     */
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
    
    
    /**
     * This method splits a line wich is to long for the TextBox
     * 
     * @param s this String represents a line splited by the user using \n
     * @return return a ordered list of lines
     */
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
    
    /**
     * Helpmethod to convert an ArrayList to an normal Array
     * 
     * @param list ArrayList to convert
     * @return normal Array
     */
    private String[] convertListToArray(ArrayList<String> list){
        String[] arr = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            arr[i] = list.get(i);
        }
        return arr;
    }

    public int writeTextWithAnswer(int charPerSecond, String[] list){
        if(list.length == 0){
            ConsoleManager.writeOnConsole("[TextBox] ", "The length of the array at writeTextWithAnswer(int charPerSecond, String[] list) must be greater then 0");
            return -1;
        }
        selectBoxItems = list;
        writeTextToVisible(charPerSecond);
        
        selectedItem = 0;
        if(list.length >= 1)
            visibleBoxItem1 = selectBoxItems[0];
        if(list.length >= 2)
            visibleBoxItem2 = selectBoxItems[1];
        if(list.length >= 3)
            visibleBoxItem3 = selectBoxItems[2];
        
        displaySelectBox = true;
        needUpdate = true;
        while (!pressedForward) {
            Time.sleep(5);
        }
        displaySelectBox = false;
        needUpdate = true;
        pressedForward = false;
        clearText();
        
        return selectedItem;
    }
    
    
    
    public void writeText(int charPerSecond) {
        writeTextToVisible(charPerSecond);
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
    
    private void writeTextToVisible(int charPerSecond){
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
            if (needMoreRows) {
                displayForwardSymbol = true;
                needUpdate = true;
                while (!pressedForward) {
                    Time.sleep(5);
                }
                needMoreRows = false;
                displayForwardSymbol = false;
                needUpdate = true;
                pressedForward = false;
                clearText();
            }
        }
    }

    public void clearText() {
        visibleTextRow1 = "";
        visibleTextRow2 = "";
        visibleTextRow3 = "";
        displayForwardSymbol = false;
        needUpdate = true;
    }

    public void update() {
        updateTextBox();
        updateSelectBox();
                
        needUpdate = false;
    }

    private void updateTextBox(){
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
    }
    
    private void updateSelectBox(){
        Graphics2D g = selectBoxImg.createGraphics();
        
        if(displaySelectBox){
            // 1. border, 2. scrollbalken, 3. di 3 items, 4. highlight selected item
            int width = 180, height = (int)this.height - 30;
            g.setColor(Color.blue);
            g.fillRect(0, 0, width, height);
            g.setColor(Color.white);
            g.fillRect(3, 3, width -6, height -6);
            
            Color itemBorderColor = Color.gray;
            Color itemBackColor = Color.white;
            Color itemFontColor = Color.black;
            Color selectedBackColor = new Color(210, 210, 210);
            Color selectedFontColor = Color.magenta;
            
            if(selectBoxItems.length == 1){
                g.setColor(itemBorderColor);
                g.fillRect(4, 4, width - 8, height - 8);
                g.setColor(selectedBackColor);
                g.fillRect(6, 6, width - 12, height - 12);
                g.setColor(selectedFontColor);
                g.setFont(font);
                g.drawString(visibleBoxItem1, (7) + (width - 14)/2 - (g.getFontMetrics(font).stringWidth(visibleBoxItem1))/2, (7) + (height - 14)/2 + (28)/2);
            }else if(selectBoxItems.length == 2){
                g.setColor(itemBorderColor);
                g.fillRect(4, 4, width - 8, height - 8);
                
                if(selectedItem == 0)
                    g.setColor(selectedBackColor);
                else
                    g.setColor(itemBackColor);
                g.fillRect(6, 6, (width - 12), (height - 12)/2);
                if(selectedItem == 0)
                    g.setColor(selectedFontColor);
                else
                    g.setColor(itemFontColor);
                g.setFont(font);
                g.drawString(visibleBoxItem1, (7) + (width - 14)/2 - (g.getFontMetrics(font).stringWidth(visibleBoxItem1))/2, (7) + (height - 14)/4 + (28)/2);
                
                if(selectedItem == 1)
                    g.setColor(selectedBackColor);
                else
                    g.setColor(itemBackColor);
                g.fillRect(6, 6 + (height - 12)/2 + 1, (width - 12), (height - 12)/2 - 1);
                if(selectedItem == 1)
                    g.setColor(selectedFontColor);
                else
                    g.setColor(itemFontColor);
                g.setFont(font);
                g.drawString(visibleBoxItem2, (7) + (width - 14)/2 - (g.getFontMetrics(font).stringWidth(visibleBoxItem2))/2, (7 + (height - 14)/2) + (height - 14)/4 + (28)/2);
            }else if(selectBoxItems.length == 3){
                g.setColor(itemBorderColor);
                g.fillRect(4, 4, width - 8, height - 8);
                
                if(selectedItem == 0)
                    g.setColor(selectedBackColor);
                else
                    g.setColor(itemBackColor);
                g.fillRect(6, 6, (width - 12), (height - 12)/3);
                if(selectedItem == 0)
                    g.setColor(selectedFontColor);
                else
                    g.setColor(itemFontColor);
                g.setFont(font);
                g.drawString(visibleBoxItem1, (7) + (width - 14)/2 - (g.getFontMetrics(font).stringWidth(visibleBoxItem1))/2, (7) + (height - 14)/6 + (28)/2);
                
                if(selectedItem == 1)
                    g.setColor(selectedBackColor);
                else
                    g.setColor(itemBackColor);
                g.fillRect(6, 6 + (height - 12)/3 +1, (width - 12), (height - 12)/3 - 1);
                if(selectedItem == 1)
                    g.setColor(selectedFontColor);
                else
                    g.setColor(itemFontColor);
                g.setFont(font);
                g.drawString(visibleBoxItem2, (7) + (width - 14)/2 - (g.getFontMetrics(font).stringWidth(visibleBoxItem2))/2, (7 + (height - 14)/3 + 2) + (height - 14)/6 + (28)/2);
                
                if(selectedItem == 2)
                    g.setColor(selectedBackColor);
                else
                    g.setColor(itemBackColor);
                g.fillRect(6, 6 + (height - 12)/3*2 + 1, (width - 12), (height - 12)/3);
                if(selectedItem == 2)
                    g.setColor(selectedFontColor);
                else
                    g.setColor(itemFontColor);
                g.setFont(font);
                g.drawString(visibleBoxItem3, (7) + (width - 14)/2 - (g.getFontMetrics(font).stringWidth(visibleBoxItem3))/2, (7 + (height - 14)/3*2 + 4) + (height - 14)/6 + (28)/2);
            }else{
                g.setColor(itemBorderColor);
                g.fillRect(4, 4, width - 8, height - 8);
                
                if(selectedItem < visibleBoxItemIndex1){
                    visibleBoxItemIndex1 = selectedItem;
                    visibleBoxItem1 = selectBoxItems[selectedItem];
                    visibleBoxItemIndex2 = selectedItem+1;
                    visibleBoxItem2 = selectBoxItems[selectedItem+1];
                    visibleBoxItemIndex3 = selectedItem+2;
                    visibleBoxItem3 = selectBoxItems[selectedItem+2];
                }else if(selectedItem > visibleBoxItemIndex3){
                    visibleBoxItemIndex1 = selectedItem-2;
                    visibleBoxItem1 = selectBoxItems[selectedItem-2];
                    visibleBoxItemIndex2 = selectedItem-1;
                    visibleBoxItem2 = selectBoxItems[selectedItem-1];
                    visibleBoxItemIndex3 = selectedItem;
                    visibleBoxItem3 = selectBoxItems[selectedItem];
                }
                
                g.setColor(Color.blue);
                g.fillRect(180, 0, 10, height);
                g.setColor(Color.gray);
                g.drawRect(178, 2, 10, height - 4);
                g.setColor(Color.white);
                g.fillRect(180, 4 + (height-8) / selectBoxItems.length * visibleBoxItemIndex1, 6, (height-8) / selectBoxItems.length * 3);
                
                if(selectedItem == visibleBoxItemIndex1)
                    g.setColor(selectedBackColor);
                else
                    g.setColor(itemBackColor);
                g.fillRect(6, 6, (width - 12), (height - 12)/3);
                if(selectedItem == visibleBoxItemIndex1)
                    g.setColor(selectedFontColor);
                else
                    g.setColor(itemFontColor);
                g.setFont(font);
                g.drawString(visibleBoxItem1, (7) + (width - 14)/2 - (g.getFontMetrics(font).stringWidth(visibleBoxItem1))/2, (7) + (height - 14)/6 + (28)/2);
                
                if(selectedItem == visibleBoxItemIndex2)
                    g.setColor(selectedBackColor);
                else
                    g.setColor(itemBackColor);
                g.fillRect(6, 6 + (height - 12)/3 +1, (width - 12), (height - 12)/3 - 1);
                if(selectedItem == visibleBoxItemIndex2)
                    g.setColor(selectedFontColor);
                else
                    g.setColor(itemFontColor);
                g.setFont(font);
                g.drawString(visibleBoxItem2, (7) + (width - 14)/2 - (g.getFontMetrics(font).stringWidth(visibleBoxItem2))/2, (7 + (height - 14)/3 + 2) + (height - 14)/6 + (28)/2);
                
                if(selectedItem == visibleBoxItemIndex3)
                    g.setColor(selectedBackColor);
                else
                    g.setColor(itemBackColor);
                g.fillRect(6, 6 + (height - 12)/3*2 + 1, (width - 12), (height - 12)/3);
                if(selectedItem == visibleBoxItemIndex3)
                    g.setColor(selectedFontColor);
                else
                    g.setColor(itemFontColor);
                g.setFont(font);
                g.drawString(visibleBoxItem3, (7) + (width - 14)/2 - (g.getFontMetrics(font).stringWidth(visibleBoxItem3))/2, (7 + (height - 14)/3*2 + 4) + (height - 14)/6 + (28)/2);
            }
        }
    }
    
    @Override
    public void draw(Graphics2D g, float x, float y) {
        if (needUpdate) {
            update();
        }
        if (img != null) {
            g.drawImage(img, (int) this.x + 5, (int) this.y + 5, null);
        }
        if (displaySelectBox && selectBoxImg != null){
            g.drawImage(selectBoxImg, (int) this.width - 200, (int) this.y - (int) this.height + 30, null);
        }
    }

    @Override
    public void keyChanged(int keyCode, boolean keyState) {
        if (keyCode == Controls.KEY_A && keyState == false && displayForwardSymbol) {
            pressedForward = true;
        }else if(displaySelectBox){
            if(keyCode == Controls.KEY_A && keyState == false)
                pressedForward = true;
            else if(keyCode == Controls.KEY_UP && keyState == false){
                if(selectedItem > 0){
                    selectedItem--;
                    needUpdate = true;
                }
            }else if(keyCode == Controls.KEY_DOWN && keyState == false){
                if(selectedItem < selectBoxItems.length-1){
                    selectedItem++;
                    needUpdate = true;
                }
            }
        }
    }

}
