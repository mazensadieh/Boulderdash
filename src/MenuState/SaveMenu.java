package MenuState;

import main.GamePanel;
import main.GameState;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.*;

public class SaveMenu {
    private static final JFrame frame = new JFrame("SAVE OR LOAD");
    private static final JButton btn_Save = new JButton("Save");
    private static final JButton btn_delete = new JButton("Delete Selected");
    private static final JButton btn_Exit = new JButton("Exit");
    private static final JButton btn_Load = new JButton("Load Game");
    private static final DefaultTableModel model = new DefaultTableModel();

    //Erstellung und Anpassung der Save/Load Menu
    private static final JTable table = new JTable( model ){
        final DefaultTableCellRenderer ColorText = new DefaultTableCellRenderer();
        {
            ColorText.setForeground(new Color(255, 255, 255));
            ColorText.setHorizontalAlignment( JLabel.CENTER );
            ColorText.setVerticalAlignment(JLabel.CENTER);

        }
        @Override
        public TableCellRenderer getCellRenderer(int arg0, int arg1) {
            return ColorText;
        }

    };
    private static final JScrollPane tableScroller = new JScrollPane( table );

    //Erstellung der SaveMenu
    public SaveMenu(GamePanel gp) {
        model.addColumn("Name");
        model.addColumn("Score");

        //Buttons Anpassung
        btn_Save.setBounds(210, 240, 140, 30);
        btn_Load.setBounds(400, 240, 140, 30);
        btn_delete.setBounds(210, 290, 140, 30);
        btn_Exit.setBounds(400,290,140,30);

        tableScroller.setBounds(190, 50, 370, 160);

        //Frame Anpassung
        SetUpFrame();


        table.setBackground(new Color(90, 112, 141,228));
        table.setOpaque(true);
        table.setFillsViewportHeight(true);
        table.setBackground(new Color(90, 112, 141,228));


        loadtable();

        //Die Möglichkeiten, wenn ein Save-Prozess ausgeführt wird.(erfolgereich oder nicht).
        btn_Save.addActionListener(e->{
                if (gp.gs == GameState.MAINMENU)
                    JOptionPane.showMessageDialog(frame, "ERROR: YOU CAN NOT SAVE IN THE MAIN MENU", "ERROR", JOptionPane.ERROR_MESSAGE);
                else {
                    gp.saveLoad.save(model.getRowCount());
                    String[] userInfo = {gp.saveLoad.name, String.valueOf(gp.controller.score)};
                    model.addRow(userInfo);
                    savetable();
                    JOptionPane.showMessageDialog(frame, "SAVE COMPLETED", "SAVED", JOptionPane.INFORMATION_MESSAGE);
                    frame.setVisible(false);
                }
        });

        //Load-Button gedruckt.
        btn_Load.addActionListener(e->{
                int[] selectedIndices = table.getSelectedRows();
                gp.saveLoad.load(selectedIndices[0]);
                gp.utool.ChoosedPlayer();
                frame.setVisible(false);
                gp.stopMusic();
                gp.playMusic(3);
                gp.gs = GameState.PLAY;
        });

        //delete-Button gedruckt.
        btn_delete.addActionListener(e->{
            if( table.getSelectedRowCount() > 0 )
            {
                int[] selectedIndices = table.getSelectedRows();
                for( int i=selectedIndices.length-1; i>=0; i-- )
                {
                    model.removeRow( selectedIndices[0] );
                    File file = new File("save"+selectedIndices[0]+".dat");
                    file.delete();
                    savetable();
                    for(int j=i+1;j<20;j++){
                        File f = new File("save"+j+".dat");
                        if(file!=null) {
                            f.renameTo(new File("save" + (j - 1) + ".dat"));
                        }
                    }

                }
            }
        });
        //Exit-Button gedruckt.
        btn_Exit.addActionListener(e->frame.setVisible(false));
    }

    private void SetUpFrame() {

        frame.add(btn_Save);
        frame.add(btn_Exit);
        frame.add(btn_delete);
        frame.add(btn_Load);
        frame.add(tableScroller);
        frame.setSize(750, 350);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(49, 74, 102, 228));
        frame.setBackground(new Color(90, 112, 141,228));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
    }

    public void savemenu(boolean show){
        frame.setVisible(show);
    }

    //SaveTable als .txt File zu speichern.
    private void savetable(){
        try{
            File file = new File("table.txt");

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);


            for ( int i =0;i<model.getRowCount();i++){

                for ( int j =0;j<model.getColumnCount();j++){
                        bw.write(model.getValueAt(i,j)+" ");
                }
                bw.write("\n");

            }

            bw.close();
            fw.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //LoadTable auch als .txt File zu speichern.
    private void loadtable(){

        try {
            File file = new File("table.txt");
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);
                int row = 0;
                while( row < 10) {
                    String line = br.readLine();
                    String[] numbers = line.split(" ");
                    String[] num = {numbers[ 0 ],numbers[ 1 ]};
                    model.addRow(num);
                    row++;
                }
                br.close();
        } catch(Exception e) {

        }
    }

    public void update(){ }

}




