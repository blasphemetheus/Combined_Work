package cs3500.music.view;


import java.awt.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events
import javax.swing.*;

import cs3500.music.controller.ButtonListener;
import cs3500.music.controller.KeyboardListener;
import cs3500.music.model.ModelOperations;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class VisualView extends javax.swing.JFrame implements ViewOperations {
  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel
  ModelOperations model;
  // Fossils from the MVC Class Example
  private JLabel display;
  private JButton echoButton, exitButton;
  private JTextField input;


  /**
   * Default public constructor, creates new VisualView.
   */
  public VisualView(ModelOperations model) {
    super("Music Visualizer");

    this.model = model;

    // FOSSIL:
    //    setSize(500, 300);
    //    setLocation(200, 200);
    //    this.setResizable(false);
    //		this.setMinimumSize(new Dimension(300,300));

    this.displayPanel = new ConcreteGuiViewPanel();
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(displayPanel);

    //FOSSIL UNTIL PACK() ------------------------------
    this.setLayout(new FlowLayout());

    display = new JLabel("To be displayed");
    //label = new JLabel(new ImageIcon("Jellyfish.JPG"));

    this.add(display);

    //the textfield
    input = new JTextField(10);
    this.add(input);

    //echobutton
    echoButton = new JButton("Echo");
    echoButton.setActionCommand("Echo Button");
    this.add(echoButton);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);

    // END OF FOSSIL ------------------------------------


    this.pack();
    setVisible(true);
  }

  @Override
  public void render() {

  }

  @Override
  public void addKeyListener(KeyboardListener kbd) {

  }

  @Override
  public void addActionListener(ButtonListener buttonListener) {

  }

  @Override
  public Dimension getPreferredSize(){
    return new Dimension(100, 100);
  }

  // THIS STUFF SHOULD BE IN SEPARATE CLASSES I THINK, LIKE USE SWING TO DO THAT SCHNAT
  /**
   * A method that deals with showing that keyboard yo.
   */
  public void showKeyboard() {
    //shows keyboard

    for (int i = 0; i < 10; i++) {
      this.buildOctave();

    }

  }

  private void buildOctave() {

  }

  /**
   *
   */
  public void buildNatural() {

  }

  /**
   * Puts a block not
   */
  public void buildAccidental() {

  }

//  @Override
//  public void resetFocus() {
//    this.setFocusable(true);
//    this.requestFocus();
//  }
//
//  @Override
//  public void setListeners(ActionListener clicks, KeyListener keys) {
//    this.addKeyListener(keys);
//    this.echoButton.addActionListener(clicks);
//    this.exitButton.addActionListener(clicks);
//  }



}
