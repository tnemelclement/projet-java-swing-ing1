package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class ButtonNum extends JButton {
    public ButtonNum(String name) {
        this.setText(name);
        this.setFont(new Font("ARIAL", Font.BOLD, 50));
        setSize(100, 100);
    }
}
