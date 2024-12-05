package pt.up.fe.ldts.pacman.model.menu;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.pacman.model.menu.element.TextBox;

import java.util.ArrayList;

public abstract class Menu {
    private ArrayList<TextBox> options;
    private int selectedOption;
    public Menu(ArrayList<TextBox> options){
        this.options = options;
        selectedOption = 0;
        setOptions(options);
    }

    public ArrayList<TextBox> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<TextBox> options) {
        this.options = options;
        if(!options.isEmpty()){
            options.forEach(textBox -> textBox.setColor(new TextColor.RGB(255,255,255)));
            options.getFirst().setColor(new TextColor.RGB(255,255,0));
            selectedOption = 0;
        }
    }

    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void selectNextOption(){
        options.get(selectedOption).setColor(new TextColor.RGB(255,255,255));
        selectedOption = (selectedOption + 1) % options.size();
        options.get(selectedOption).setColor(new TextColor.RGB(255,255,0));
    }

    public void selectPreviousOption(){
        options.get(selectedOption).setColor(new TextColor.RGB(255,255,255));
        selectedOption = (selectedOption - 1) % options.size();
        options.get(selectedOption).setColor(new TextColor.RGB(255,255,255));
    }
}
