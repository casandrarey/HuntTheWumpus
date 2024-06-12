import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.util.Arrays;

public class Location extends HexagonButton implements ActionListener{
  private Controller c;
  private boolean[] walls;
  private Hazard h;
  private Player p;

  public Location(JSONObject o){
    super(o.toString());
    JSONArray wallList = (JSONArray) o.get("walls");
    this.walls = new boolean[wallList.size()];
    
    for(int i = 0; i < walls.length; i++) 
      this.walls[i] = (boolean) wallList.get(i);

    this.addActionListener(this);
  }

  public Location setController(Controller controller){  
    this.c = controller;
    return this;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    System.out.println(c);
    if(c!=null && c.locationClick(this)) p = c.getPlayer();
  }
  
  @Override
  public String toString(){
    return Arrays.toString(walls);
  }
}