package view;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
public class IntegrationView extends JFrame{
    JTabbedPane tabbedPane; //用选项卡集成MazeView视图
    public IntegrationView(){
        tabbedPane= new JTabbedPane(JTabbedPane.LEFT);//卡在左侧 
        tabbedPane.validate();
        add(tabbedPane,BorderLayout.CENTER); 
        setBounds(300,150,1350,720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public void addMazeView(String cardName,MazeView view){
       tabbedPane.add(cardName,view);
       validate();
    }
}