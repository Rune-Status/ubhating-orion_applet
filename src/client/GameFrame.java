package client;

import client.Client;
import client.SignLink;
import client.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;

public class GameFrame extends Client implements ActionListener {

    private static JFrame frame;

    public GameFrame(String args[]) {
        super();
        try {
            SignLink.startpriv(InetAddress.getByName(server));
            initUI();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void initUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);// Windows decoration
            frame = new JFrame("Oldschool 2006");
            setLogo();
            Container gamePanel = frame.getContentPane();
            gamePanel = new JPanel();
            frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
            frame.setSize(765, 503);
            frame.setLayout(new BorderLayout());
            frame.setFocusTraversalKeysEnabled(true);
            setFocusTraversalKeysEnabled(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            addDummyLabel();
            gamePanel.setLayout(new BorderLayout());
            gamePanel.add(this);
            gamePanel.setPreferredSize(new Dimension(770, 515));
            gamePanel.setMinimumSize(new Dimension(770, 515));
            gamePanel.setBackground(Color.black);
            frame.setBackground(Color.black);
            frame.setPreferredSize((new Dimension(900, 600)));
            frame.setMinimumSize(new Dimension(765, 530));
            frame.setSize(new Dimension(900, 600));
            frame.pack();
            frame.setVisible(true);
            frame.setResizable(true);
            frame.setLocationRelativeTo(null);

            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLogo() {
        String imgURL = (Sprite.location + "icon.png");
        frame.setIconImage(new ImageIcon(imgURL).getImage());
    }

    public static void refreshFrameSize(boolean fixed, boolean resizable) {
        if (fixed) {
            frame.setResizable(false);
            frame.setPreferredSize(new Dimension(765 + 6, 503 + 50));
            frame.setMinimumSize(new Dimension(765 + 6, 503 + 50));
        } else if (resizable) {
            frame.setResizable(true);
            frame.setPreferredSize(new Dimension(781, 600));
            frame.setMinimumSize(new Dimension(781, 600));
        }
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void addDummyLabel() {
        Container gamePanel = frame.getContentPane();// New component
        gamePanel.setBackground(Color.black);// IndexedImage color
        gamePanel.setLayout(new FlowLayout());// Layout
    }


    @Override
    public URL getCodeBase() {
        try {
            return new URL("http://" + server + "/cache");
        } catch (Exception e) {
            return super.getCodeBase();
        }
    }

    @Override
    public URL getDocumentBase() {
        return getCodeBase();
    }

    public void loadError(String s) {
        System.out.println("loadError: " + s);
    }

    @Override
    public String getParameter(String key) {
        return "";
    }

    @SuppressWarnings("unused")
    private static void openUpWebSite(String url) {
        Desktop d = Desktop.getDesktop();
        try {
            d.browse(new URI(url));
        } catch (Exception e) {
        }
    }

    public Client c;

    @Override
    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        try {
            if (cmd != null) {
                if (cmd.equalsIgnoreCase("exit")) {
                    System.exit(0);
                }
            }
            if (cmd.equals("Homepage")) {
                //launchURL("http://valor-ps.org");
            }
            if (cmd.equals("Forums")) {
                //launchURL("http://www.community.valor-ps.org/");
            }
            if (cmd.equals("Web Client")) {
                // launchURL("http://valor-ps.org");
            }
            if (cmd.equals("Vote")) {
                // launchURL("http://valor-ps.org");
            }
            if (cmd.equals("Report A Bug")) {
                //launchURL("http://www.community.valor-ps.org/index.php?/forum/15-bug-report/");
            }
        } catch (Exception e) {
        }
    }
}