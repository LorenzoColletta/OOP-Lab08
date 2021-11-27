package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    private final JFrame frame = new JFrame("MY first Java graphical interface");

    /**
     * builds a new {@link SimpleGUI}.
     *
     */
    public SimpleGUIWithFileChooser(final Controller fileController) {

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);

        final JPanel canvas = new JPanel();
        final JButton save = new JButton("Save");
        final JTextArea write = new JTextArea();
        final JPanel innerCanvas = new JPanel();
        final JButton browse = new JButton("Browse");
        final JTextField displayFile = new JTextField();
        final JFileChooser fileChooser = new JFileChooser(fileController.getPath());

        canvas.setLayout(new BorderLayout());
        canvas.add(write, BorderLayout.CENTER);
        canvas.add(save, BorderLayout.SOUTH);
        canvas.add(innerCanvas, BorderLayout.NORTH);
        innerCanvas.setLayout(new BorderLayout());
        innerCanvas.add(browse, BorderLayout.LINE_END);
        innerCanvas.add(displayFile, BorderLayout.CENTER);

        displayFile.setEnabled(false);
        displayFile.setText(fileController.getPath());

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        browse.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                fileChooser.setSelectedFile(fileController.getCurrentFile());
                final var result = fileChooser.showSaveDialog(fileChooser);
                switch (result) {
                    case JFileChooser.APPROVE_OPTION:
                        fileController.setCurrentFile(fileChooser.getSelectedFile());
                        displayFile.setText(fileController.getPath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, "An Error has occurred.");
                        break;
                } 
            }
        });
        save.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    fileController.writeString(write.getText());
                    write.setText("");
                } catch (IOException e1) {
                    System.out.println("Error: " + e1.getMessage());
                }
            }
        });
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        final SimpleGUIWithFileChooser simpleGui = new SimpleGUIWithFileChooser(new Controller());
        simpleGui.display();
    }
}
