package client;

import java.awt.BorderLayout;
import worldmap.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.InetAddress;
import java.net.URI;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import javax.swing.filechooser.FileFilter;

import ui.SwingUtil;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;

public class Jframe extends Client implements ActionListener {

   public static JMenuItem menuItem;
   public JFrame frame;
   public static JMenu donatorMenu;
   public static JMenu serverMenu;
   public JPanel e;

   public Jframe(String[] args) {
      try {
		try{
			SignLink.startpriv(InetAddress.getByName(server));
		 } catch (Exception e) {
			server = serverIp2;
			SignLink.startpriv(InetAddress.getByName(server));
		 }
         this.initUI();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }
   
   void setTransparent(JButton button){
	   button.setOpaque(false);
	   button.setContentAreaFilled(false);
	   button.setBorderPainted(false);
   }
   
   void setTransparent(JMenu button){
	   button.setOpaque(false);
	   button.setContentAreaFilled(false);
	   button.setBorderPainted(false);
   }
   
   int pX, pY;
   
   public void updateTitle(){
	   this.frame.setTitle(Client.NAME+" ["+(Client.isEasyAeonClient ? "World 2" : "World 1")+"]");
   }
   
   public void initUI() {
	      try {
			 JFrame.setDefaultLookAndFeelDecorated(true);
			 UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel"); //Substance<NAME>LookAndFeel
	         JPopupMenu.setDefaultLightWeightPopupEnabled(false);
	         this.frame = new JFrame();
//	         setFocusTraversalKeysEnabled(false);
	         this.frame.setLayout(new BorderLayout());
	         setFocusTraversalKeysEnabled(false);
	         this.frame.setResizable(false);
	         //this.frame.setDefaultCloseOperation(3);
			 this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.frame.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	            		if(Client.loggedIn)
							JOptionPane.showMessageDialog(frame, "Please log out first before closing the client.");
	            		else
							System.exit(0);
	            }
			});
			
	         e = new JPanel();
	         e.setLayout(new BorderLayout());
	         e.add(this);
	         e.setPreferredSize(new Dimension(765, 503));
			 JButton btnForums = new JButton("Forums");
			 btnForums.addActionListener(this);
			 JButton btnWorldmap = new JButton("World Map");
			 btnWorldmap.addActionListener(this);
			 JButton btnScreenshot = new JButton("Screenshot");
			 btnScreenshot.addActionListener(this);
			 JButton btnHighscore = new JButton("Highscores");
			 btnHighscore.addActionListener(this);
			 /*JButton btnExit = new JButton("Exit");
			 btnExit.addActionListener(this);*/
			 JMenuBar menuBar = new JMenuBar();
			 serverMenu = new JMenu("Server Menu");
			 JMenuItem menuItem21 = new JMenuItem("World 1");
			 JMenuItem menuItem22 = new JMenuItem("World 2");
			 menuItem21.addActionListener(this);
			 menuItem22.addActionListener(this);
			 serverMenu.add(menuItem21);
			 serverMenu.add(menuItem22);
			 donatorMenu = new JMenu("Donator Menu");
			 JMenuItem menuItem11 = new JMenuItem("317 Gameframe");
			 JMenuItem menuItem12 = new JMenuItem("459 Gameframe");
			 JMenuItem menuItem13 = new JMenuItem("474 Gameframe");
			 JMenuItem menuItem14 = new JMenuItem("Ashy [Theme]");
			 JMenuItem menuItem15 = new JMenuItem("Snow [Theme]");
			 JMenuItem menuItem16 = new JMenuItem("Normal [Theme]");
			 menuItem11.addActionListener(this);
			 menuItem12.addActionListener(this);
			 menuItem13.addActionListener(this);
			 menuItem14.addActionListener(this);
			 menuItem15.addActionListener(this);
			 menuItem16.addActionListener(this);
			 donatorMenu.add(menuItem11);
			 donatorMenu.add(menuItem12);
			 donatorMenu.add(menuItem13);
			 donatorMenu.add(menuItem14);
			 donatorMenu.add(menuItem15);
			 donatorMenu.add(menuItem16);
			 menuBar.add(btnForums);
			 menuBar.add(btnWorldmap);
			 menuBar.add(btnScreenshot);
			 menuBar.add(btnHighscore);
			 menuBar.add(serverMenu);
			 menuBar.add(donatorMenu);
			 //menuBar.add(btnExit);
			 donatorMenu.setEnabled(false);
	         SwingUtil.setIcons(this.frame);
			 this.frame.getContentPane().add(menuBar, "North");
	         this.frame.getContentPane().add(e, "Center");
	         this.frame.pack();
	         this.frame.setLocationRelativeTo((Component)null);
	         this.frame.setVisible(true);
	         this.frame.setResizable(false);
	         this.init();
	         updateTitle();
	      } catch (Exception var2) {
	         var2.printStackTrace();
	      }

	   }
   
   /*public void initUI() {
      try {
		 //JFrame.setDefaultLookAndFeelDecorated(true);
		 UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel"); //Substance<NAME>LookAndFeel
         JPopupMenu.setDefaultLightWeightPopupEnabled(false);
         this.frame = new JFrame(Client.NAME);
         this.frame.setUndecorated(true);
         this.frame.setLayout(new BorderLayout());
         this.frame.setResizable(false);
         //this.frame.setDefaultCloseOperation(3);
         this.frame.addMouseListener(new MouseAdapter() {
             public void mousePressed(MouseEvent me) {
                 // Get x,y and store them
                 pX = me.getX();
                 pY = me.getY();

             }

              public void mouseDragged(MouseEvent me) {

                 frame.setLocation(frame.getLocation().x + me.getX() - pX,
                         frame.getLocation().y + me.getY() - pY);
             }
         });

         this.frame.addMouseMotionListener(new MouseMotionAdapter() {
             public void mouseDragged(MouseEvent me) {

                 frame.setLocation(frame.getLocation().x + me.getX() - pX,
                         frame.getLocation().y + me.getY() - pY);
             }
         });
		 this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            		if(Client.loggedIn)
						JOptionPane.showMessageDialog(frame, "Please log out first before closing the client.");
            		else
						System.exit(0);
            }
		});
         e = new JPanel();
         e.setLayout(new BorderLayout());
         e.add(this);
         e.setPreferredSize(new Dimension(765, 503));
		 JButton btnForums = new JButton("Forums");
		 btnForums.addActionListener(this);
		 
		 Font font = btnForums.getFont();
		 Map attributes = font.getAttributes();
		 attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		 btnForums.setFont(font.deriveFont(attributes));
		 
		 JButton btnWorldmap = new JButton("World Map");
		 btnWorldmap.addActionListener(this);
		 btnWorldmap.setFont(font.deriveFont(attributes));
		 JButton btnScreenshot = new JButton("Screenshot");
		 btnScreenshot.addActionListener(this);
		 btnScreenshot.setFont(font.deriveFont(attributes));
		 JButton btnExit = new JButton("Exit");
		 btnExit.addActionListener(this);
		 btnExit.setFont(font.deriveFont(attributes));
		 //JMenuBar menuBar = new JMenuBar();
		 final int forumsX = 94;
		 final int worldmapX = 178;
		 final int screenShotX = 300;
		 final int donatorX = 400;
		 JMenuBar menuBar=new JMenuBar(){

			  public void paintComponent(Graphics g)

			  {
			  g.drawImage(Client.cacheSprite[45].toImage(),0,0,this);//background
			  g.drawImage(Client.cacheSprite[46].toImage(),10,2,this);//logo
			  g.drawImage(Client.cacheSprite[47].toImage(),forumsX,2,this);//forums arrow
			  g.drawImage(Client.cacheSprite[48].toImage(),worldmapX,1,this);//forums arrow
			  g.setColor(Color.black);
			  g.drawLine(0, 25, 765, 25);
			  }

			 };
		 donatorMenu = new JMenu("Donator Menu");
		 donatorMenu.setFont(font.deriveFont(attributes));
		 JMenuItem menuItem11 = new JMenuItem("317 Gameframe");
		 JMenuItem menuItem12 = new JMenuItem("459 Gameframe");
		 JMenuItem menuItem13 = new JMenuItem("474 Gameframe");
		 menuBar.setPreferredSize(new Dimension(765, 26));
		 menuItem11.addActionListener(this);
		 menuItem12.addActionListener(this);
		 menuItem13.addActionListener(this);
		 donatorMenu.add(menuItem11);
		 donatorMenu.add(menuItem12);
		 donatorMenu.add(menuItem13);
		 setTransparent(btnForums);
		 setTransparent(btnWorldmap);
		 setTransparent(btnScreenshot);
		 setTransparent(donatorMenu);
		 setTransparent(btnExit);
		 menuBar.setLayout(null);
		 menuBar.add(btnForums).setBounds(forumsX+13, 0, 66, 25);
		 menuBar.add(btnWorldmap).setBounds(worldmapX+23, 0, 66, 25);
		 menuBar.add(btnScreenshot).setBounds(screenShotX, 0, 66, 25);
		 menuBar.add(donatorMenu).setBounds(donatorX, 0, 81, 25);
		 menuBar.add(btnExit).setBounds(699, 0, 66, 25);
		 donatorMenu.setEnabled(false);
		 this.frame.getContentPane().add(menuBar, "North");
         this.frame.getContentPane().add(e, "Center");
         this.frame.pack();
         this.frame.setLocationRelativeTo((Component)null);
         this.frame.setVisible(true);
         this.frame.setResizable(false);
         this.init();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }*/

   public static void openUpWebSite(String url) {
      Desktop d = Desktop.getDesktop();

      try {
         d.browse(new URI(url));
      } catch (Exception var3) {
         ;
      }

   }

   public void actionPerformed(ActionEvent evt) {
      String cmd = evt.getActionCommand();

      try {
         if(cmd != null) {
            if(cmd.equalsIgnoreCase("Exit")) {
            	if(Client.loggedIn)
					JOptionPane.showMessageDialog(frame, "Please log out first before closing the client.");
        		else
					System.exit(0);
            }

			if(cmd.equals("317 Gameframe")) {
				try{
					Client.gameframeVersion = 317;
					Client.updateGameframeSprites();
				}catch(Exception ex){
					System.out.println(ex);
				}
            }
			
			if(cmd.equals("459 Gameframe")) {
				try{
					Client.gameframeVersion = 459;
					Client.updateGameframeSprites();
				}catch(Exception ex){
					System.out.println(ex);
				}
            }
			
			if(cmd.equals("474 Gameframe")) {
				try{
					Client.gameframeVersion = 474;
					Client.updateGameframeSprites();
				}catch(Exception ex){
					System.out.println(ex);
				}
            } 
			
			if(cmd.equals("Highscores")) {
				try{
					if (!loggedIn) {
						JOptionPane.showMessageDialog(frame, "You must be logged in to view the highscores.");
						return;//knwo how? i've never done it before w8
					}
					stream.createFrame(243); //we only need to send the packet now
				
				}catch(Exception ex){
					System.out.println(ex);
				}
            }
			//method60(18788)
			
			if(cmd.equals("Ashy [Theme]")) {
				try{
					if(Client.loggedIn){
						JOptionPane.showMessageDialog(frame, "Please log out first before changing your theme of your client.");
						return;
					}
					Client.isWinter = false;
					Client.isHween = true;
		            reloadConfig();
		            dropClient();
		            System.out.println("h");
				}catch(Exception ex){
					System.out.println(ex);
				}
            }
			
			if(cmd.equals("Snow [Theme]")) {
				try{
					if(Client.loggedIn){
						JOptionPane.showMessageDialog(frame, "Please log out first before changing your theme of your client.");
						return;
					}
					Client.isHween = false;
					Client.isWinter = true;
		            reloadConfig();
		            dropClient();
					System.out.println("c");
				}catch(Exception ex){
					System.out.println(ex);
				}
            }
			
			if(cmd.equals("Normal [Theme]")) {
				try{
					if(Client.loggedIn){
						JOptionPane.showMessageDialog(frame, "Please log out first before changing your theme of your client.");
						return;
					}
					Client.isHween = false;
					Client.isWinter = false;
		            reloadConfig();
		            dropClient();
					System.out.println("normal");
				}catch(Exception ex){
					System.out.println(ex);
				}
            }
			
			if(cmd.equals("Original")) {
				try{
					if(Client.loggedIn)
						JOptionPane.showMessageDialog(frame, "Please log out first before server change.");
	        		else{
	        			Client.isEasyAeonClient = false;
	        			Client.writeSettings();
						updateTitle();
	        		}
				}catch(Exception ex){
					System.out.println(ex);
				}
            }
			if (cmd.equals("World 2")) {
				if(!Client.world2){
					JOptionPane.showMessageDialog(frame, "World 2 is currently unavailable.");
				return;	
				}
			}
			if(cmd.equals("Easy (x40)")) {

					
				try{
					if(Client.loggedIn)
						JOptionPane.showMessageDialog(frame, "Please log out first before server change.");
	        		else{
	        			Client.isEasyAeonClient = true;
	        			Client.writeSettings();
	        			updateTitle();
	        		}
				}catch(Exception ex){
					System.out.println(ex);
				}
            }
			
            if(cmd.equalsIgnoreCase("Forums")) {
               openUpWebSite("http://OSRSPK.com/");
            }
            
            if(cmd.equalsIgnoreCase("World Map")) {
            	if(Client.finished)
             	worldmap.Main.main(new String[]{""});
            	else
            		JOptionPane.showMessageDialog(frame, "Please wait for the game to finish loading.");
             }
			
			if(cmd.equalsIgnoreCase("Screenshot")) {
               takeScreenshot(true);
            }
         }
      } catch (Exception var4) {
         ;
      }

   }
   
   public void takeScreenshot(boolean flag)
    {
        BufferedImage bufferedimage;
        try
        {
            Robot robot = new Robot();
            Point point = e.getLocationOnScreen();
            Rectangle rectangle = new Rectangle(point.x, point.y, e.getWidth(), e.getHeight());
            bufferedimage = robot.createScreenCapture(rectangle);
        }
        catch(Throwable throwable)
        {
             JOptionPane.showMessageDialog(frame, "An error occured while trying to create a screenshot!", "Screenshot Error", 0);
            return;
        }
        String s = null;
        try
        {
            s = getNearestScreenshotFilename();
        }
        catch(IOException ioexception)
        {
            if(flag)
            {
                 JOptionPane.showMessageDialog(frame, "A screenshot directory does not exist, and could not be created!", "No Screenshot Directory", 0);
                return;
            }
        }
        if(s == null && flag)
        {
             JOptionPane.showMessageDialog(frame, "There are too many screenshots in the screenshot directory!\n"+"Delete some screen\n" +"shots and try again." , "Screenshot Directory Full", 0);
            return;
        }
        if(!flag)
        {
            final JFileChooser fileChooser = new JFileChooser();
            final JDialog fileDialog = createFileChooserDialog(fileChooser, "Save Screenshot", this);
            final BufferedImage si = bufferedimage;
            JFileChooser _tmp = fileChooser;
            fileChooser.setFileSelectionMode(0);
            fileChooser.addChoosableFileFilter(new imageFileFilter());
            File file = new File(SignLink.findcachedir() + "/screenshots/");
            if (!file.exists()) {
            	try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            fileChooser.setCurrentDirectory(file);
            fileChooser.setSelectedFile(new File(s));
            JFileChooser _tmp1 = fileChooser;
            fileChooser.setDialogType(1);
            fileChooser.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent actionevent)
                {
                    String s1 = actionevent.getActionCommand();
                    if(s1.equals("ApproveSelection"))
                    {
                        File file = fileChooser.getSelectedFile();
                        if(file != null && file.isFile())
                        {
                            int i = JOptionPane.showConfirmDialog(frame, (new StringBuilder()).append(file.getAbsolutePath()).append(" already exists.\n"+"Do you want to replace it?").toString(), "Save Screenshot", 2);
                            if(i != 0)
                            {
                                return;
                            }
                        }
                        try
                        {
                            ImageIO.write(si, "png", file);
                        }
                        catch(IOException ioexception2)
                        {
                             JOptionPane.showMessageDialog(frame, "An error occured while trying to save the screenshot!\n"+"Please make sure you have\n"+" write access to the screenshot directory." , "Screenshot Error", 0);
                        }
                        fileDialog.dispose();
                    } else
                    if(s1.equals("CancelSelection"))
                    {
                        fileDialog.dispose();
                    }
                }


            {

            }
            });
            fileDialog.setVisible(true);
        } else
        {
            try
            {
                ImageIO.write(bufferedimage, "png", new File((new StringBuilder()).append(SignLink.findcachedir() + "/screenshots/").append(s).toString()));
                pushMessage("You took a screenshot.", 2, "[CLIENT]");System.out.println("You took a nice screenshot.");
            }
            catch(IOException ioexception1)
            {
                 JOptionPane.showMessageDialog(frame, "An error occured while trying to save the screenshot!\n"+"Please make sure you have\n"+" write access to the screenshot directory." , "Screenshot Error", 0);
            }
        }
    }
	
	public static String getNearestScreenshotFilename()
        throws IOException
    {
        File file = new File("./screenshots");
        int i = 0;
        do
        {
            String s = "Pic .png";
            if(i < 10)
            {
                s = s.replaceFirst(" ", (new StringBuilder()).append(" 000").append(i).toString());
            } else
            if(i < 100)
            {
                s = s.replaceFirst(" ", (new StringBuilder()).append(" 00").append(i).toString());
            } else
            if(i < 1000)
            {
                s = s.replaceFirst(" ", (new StringBuilder()).append(" 0").append(i).toString());
            } else
            if(i < 10000)
            {
                s = s.replaceFirst(" ", (new StringBuilder()).append(" ").append(i).toString());
            }
            if((new File(file, s)).isFile())
            {
                i++;
            } else
            {
                return s;
            }
        } while(i < 10000);
        return null;
    }
	
	public JDialog createFileChooserDialog(JFileChooser jfilechooser, String s, Container container)
	    {
	        JDialog jdialog = new JDialog(frame, s, true);
	        jdialog.setDefaultCloseOperation(2);
	        jdialog.add(jfilechooser);
	        jdialog.pack();
	        jdialog.setLocationRelativeTo(container);
	        return jdialog;
    }
	
	class imageFileFilter extends FileFilter
	{

		imageFileFilter()
		{
		}

		public boolean accept(File file)
		{
			String s = file.getName();
			return file.isDirectory() || s.toLowerCase().endsWith(".png") && s.indexOf("$") == -1;
		}

		public String getDescription()
		{
			return "PNG (*.png)";
		}
	}
   
}
