import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/*
 * This class extends JFrame and takes a name
 * Class Sets up a window and Components/Panels in window
 */

public class Window extends JFrame{

private static final long serialVersionUID = 1L;

final int FRAMEX=900,FRAMEY=800;
final int MAINX=700,MAINY=550,TOOLX=MAINX,TOOLY=150, GRIDX=500,GRIDY=500; //Panel Sizes
private Dimension mainDimension, toolDimension, gridDimension; //Panel Dimension objects
private JPanel mainPanel, toolPanel; //Panels
private Grid gridPanel;
private JTextArea txtarea;
//Constructor
	Window(String name){
		super(name);
		this.setSize(new Dimension(FRAMEX,FRAMEY)); //Frame Size

		mainPanel = new JPanel();
		toolPanel = new JPanel();
		
		//Set Panel with stuff
		mainDimension = new Dimension(MAINX,MAINY);
		mainPanel.setMinimumSize( mainDimension );
		mainPanel.setMaximumSize( mainDimension );
		mainPanel.setPreferredSize( mainDimension );
		mainPanel.setLayout( new GridBagLayout() );
		mainPanel.setBackground( new Color(64,64,64,200));
		
		//Set Up tool Panel
		toolDimension = new Dimension(TOOLX,TOOLY);
		toolPanel.setMinimumSize( toolDimension );
		toolPanel.setMaximumSize( toolDimension );
		toolPanel.setPreferredSize( toolDimension );
		toolPanel.setLayout( new FlowLayout() );
		toolPanel.setBackground(new Color(64,64,64,200)); //Match Main Panel
		//Set up Tool Panel Components
		JPanel togglePanel,optionPanel,otherPanel;
		final JCheckBox traffic = new JCheckBox("Traffic");
		Dimension toolBarCompDimension;
		JButton startButton=new JButton("      Start     "), 
				stopButton =new JButton("      Stop      "),
				selectStart=new JButton("    Change Start Pos    "),
				selectDest =new JButton("    Change Destination    ");//Spaces add for Button Size **Workaround
		EmptyBorder buttonPadding = new EmptyBorder(5,5,5,5);
		toolBarCompDimension = new Dimension((TOOLX/3)-10,TOOLY-2);
		
		//Set up Left-Most Panel
		togglePanel = new JPanel();
		togglePanel.setMinimumSize( toolBarCompDimension );
		togglePanel.setMaximumSize(toolBarCompDimension);
		togglePanel.setPreferredSize(toolBarCompDimension);
		togglePanel.setBackground(Color.DARK_GRAY);
		togglePanel.setLayout(new BoxLayout(togglePanel,BoxLayout.Y_AXIS));
		//Set up Buttons
		startButton.setBorder(buttonPadding ); //Padding
		stopButton.setBorder(buttonPadding ); //Padding
		selectStart.setBorder(buttonPadding );
		selectDest.setBorder(buttonPadding );
		//Add them to the panel w/ margins around them
		togglePanel.add( Box.createRigidArea(new Dimension(10,10)) ); // Margin
		togglePanel.add(new JLabel("<html><font color='white'>Execute</font></html>"));
		togglePanel.add( Box.createRigidArea(new Dimension(10,10)) ); // Margin
		togglePanel.add( startButton );
		togglePanel.add( Box.createRigidArea(new Dimension(10,10)) ); //Margin
		togglePanel.add( stopButton );
		
		
		//Set UP middle Panel
		optionPanel = new JPanel();
		optionPanel.setMinimumSize( toolBarCompDimension );
		optionPanel.setMaximumSize(toolBarCompDimension);
		optionPanel.setPreferredSize(toolBarCompDimension);
		optionPanel.setSize(TOOLX/3,TOOLY-5);
		optionPanel.setBackground(Color.DARK_GRAY);
		
		traffic.setBorder(buttonPadding);
		optionPanel.add( Box.createRigidArea(new Dimension(10,10)) ); // Margin
		optionPanel.add(new JLabel("<html><font color='white'>Options</font></html>"));
		optionPanel.add( Box.createRigidArea(new Dimension(10,10)) ); // Margin
		optionPanel.add( traffic );
		optionPanel.add( Box.createRigidArea(new Dimension(10,10)) ); //Margin
		optionPanel.add(selectStart);
		optionPanel.add( Box.createRigidArea(new Dimension(10,10)) ); //Margin
		optionPanel.add(selectDest);
		optionPanel.add(txtarea=new JTextArea(5,20)); //TEXT AREA FOR WEIGHT
		txtarea.setEditable(false);
		
		//Set up Right-Most Panel Select Maps Here
		otherPanel = new JPanel();
		otherPanel.setMinimumSize( toolBarCompDimension );
		otherPanel.setMinimumSize( toolBarCompDimension );
		otherPanel.setPreferredSize( toolBarCompDimension );
		otherPanel.setSize(TOOLX/3,TOOLY-5);
		otherPanel.setBackground(Color.DARK_GRAY);
		JButton m1=new JButton("Map 1"),m2=new JButton("Map 2"),m3=new JButton("Map 3"),m4=new JButton("Map 4");
		JButton m5=new JButton("Random Map");
		m1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gridPanel.setGrid(Maps.Map1());
			}
		});
		m2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gridPanel.setGrid(Maps.Map2());
			}
		});
		m3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gridPanel.setGrid(Maps.Map3());
			}
		});
		m4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gridPanel.setGrid(Maps.Map4());
			}
		});
		m5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gridPanel.setGrid(Maps.randomMap(10, 10));
			}
		});
		otherPanel.add(m1);
		otherPanel.add(m2);
		otherPanel.add(m3);
		otherPanel.add(m4);
		otherPanel.add(m5);

		//Add Panels to Toolbar
		toolPanel.add(togglePanel);
		toolPanel.add(optionPanel);
		toolPanel.add(otherPanel);

		//Set up Grid Panel to rest child to MainPanel
		gridPanel = new Grid(GRIDX,GRIDY);
		gridDimension = new Dimension(GRIDX,GRIDY);
		gridPanel.setMinimumSize(gridDimension);
		gridPanel.setMaximumSize(gridDimension);
		gridPanel.setPreferredSize(gridDimension);
		//Add this to the Main Panel
		mainPanel.add(gridPanel,new GridBagConstraints());
		//Set up Listeners	
		startButton.addActionListener(new ActionListener() { 	//Start Button Listener
			  public void actionPerformed(ActionEvent e) {
				//Start and end time for finding runtime
				long startTime, endTime;
				startTime = System.currentTimeMillis();
				
			    System.out.println("Start Button Pressed");

			    ArrayList<Vertex> vertices =gridPanel.getVertices();
				System.out.println("Vertices");

				if (traffic.isSelected()){
					System.out.println("Traffic!\n");
					gridPanel.setEdgeSpeedTraffic();
				} else {
				gridPanel.setEdgeSpeed();
				}
				
				for(Edge edge : gridPanel.getEdges())
					System.out.printf("(%d,%d) -> (%d,%d) speed:%d\n",edge.x.x,edge.x.y,edge.y.x,edge.y.y, edge.speed);

				gridPanel.combineEV();
				gridPanel.calcDijkstra();
				gridPanel.getShortest();
				txtarea.setText(String.format("Weight: %d",gridPanel.getDestWeight()));
				
				//Get Run Time of whole Function
				endTime = System.currentTimeMillis();
				System.out.printf("\n\nTotal Time to Find Best Path = %dms \n\n", (endTime - startTime) );
			  } 
			});
		
		//LISTENERS
		stopButton.addActionListener(new ActionListener() { 	//Stop Button Listener
			  public void actionPerformed(ActionEvent e) { 
				  System.out.println("Stop Button Pressed");
				  gridPanel.clearAllLines();
			  } 
			});
		
		//Change Start Listener
		selectStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Selecting");
				gridPanel.changeStart();
			}
		});
		
		//Change Destination Listener
		selectDest.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("Selecting");
				gridPanel.changeDest();
			}
		});
		
		//Pack This Window up
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.add( mainPanel,BorderLayout.NORTH );
		this.add( toolPanel,BorderLayout.SOUTH );
		this.pack();
		this.setVisible(true);		
		
	}
	
	
	
	
	
	
	
}
