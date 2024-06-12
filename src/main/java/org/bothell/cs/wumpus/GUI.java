import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;


public class GUI extends JFrame{

  private int height = 800;
  private int width = 800;
  private Controller c;
  
  public GUI(){
    //this.c = new Controller();
  }

  public GUI setController(Controller controller){
    this.c = controller;
    
    JButton[][]  btns = c.getRoomButtons();
    HexagonalLayout g = new HexagonalLayout (btns.length * btns[0].length);
    JPanel          p = new JPanel(g);
    
    for(JButton[] row: btns){
      for(JButton b:row) if(b != null){ 
        Location l = (Location)b;
        l.setController(c);
        p.add(b);
      }
    }
    this.add(p);
    init();
    
    return this;
  }
  
  public void init(){
    this.setSize(new Dimension(width, height));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
  
}