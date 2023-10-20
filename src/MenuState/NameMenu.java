package MenuState;

import main.GamePanel;
import main.GameState;
import javax.swing.*;
import java.awt.*;

public record NameMenu(GamePanel gp) {

    private final static JFrame frame = new JFrame();
    private final static JLabel label_1 = new JLabel("Enter Your Name:");
    private final static JTextField textField_1 = new JTextField();
    private final static JButton btn_Save = new JButton("Save & Start");
    private final static JButton btn_clear = new JButton("Clear");
    private final static JButton btn_Exit = new JButton("Exit");


    public NameMenu(GamePanel gp) {
        this.gp = gp;

        label_1.setBounds(70, 90, 120, 20);
        label_1.setForeground(new Color(66, 222, 222));

        textField_1.setBounds(70, 120, 150, 20);

        btn_Save.setBounds(70, 180, 150, 30);
        btn_clear.setBounds(70, 220, 70, 30);
        btn_Exit.setBounds(150, 220, 70, 30);

        FrameModify();

        btn_Save.addActionListener(e->{
            if(!textField_1.getText().equals("")) {
                gp.saveLoad.name = textField_1.getText();
                frame.setVisible(false);
                gp.gs = GameState.Choose_Character;
                // gp.controller.NewGame();
                textField_1.setText("");

            }
        });


        btn_clear.addActionListener(e->textField_1.setText(""));

        btn_Exit.addActionListener(e->frame.setVisible(false));
    }

    private void FrameModify() {

        frame.add(label_1);
        frame.add(textField_1);
        frame.add(btn_Save);
        frame.add(btn_Exit);
        frame.add(btn_clear);
        frame.setSize(300, 280);

        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(49, 74, 102, 228));
        frame.setBackground(new Color(90, 112, 141,228));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

    }

    public void nameMenusetVisible(boolean show) {
        frame.setVisible(show);
    }
}
