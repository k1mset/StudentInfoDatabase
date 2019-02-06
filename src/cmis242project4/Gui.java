/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmis242project4;

/**
 * File: Gui.Java
 * Date: 4/30/2018
 * Author: Dillan Cobb
 * Purpose: To define the java, and handle creation of the gui to the user. As 
 * well as handle any actions performed by the user and creation of the database
 * while handling all of the database interaction with inserting data, finding 
 * data, deleting data, and updating data.
 */

// Imports
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

// Main class displays the gui
public class Gui {
    public static void main(String[] args) {
        GuiFrame app = new GuiFrame();
        app.display();
    }
}

// Class for creating the window frame for the gui
class GuiFrame extends JFrame {
    // Static variables fro the frame of the gui
    static final int WIDTH = 350, HEIGHT = 225;
    static final String FRAMETITLE = "Project 4";
    
    // Default constructor
    public GuiFrame() {
        super(FRAMETITLE);
        setFrame(WIDTH, HEIGHT);
        add(new GuiPanel());
    }
    
    // display method shows the frame on the screen
    public void display() {
        setVisible(true);
    }
    
    // setFrame method allows for adjusting the frame's size, centering the 
    // frame in the screen as well as the frames closing procedures.
    public void setFrame(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// Class for creating the gui panels and functions
class GuiPanel extends JPanel implements ActionListener {
    // String for the combo box
    String[] requestString = {"Insert", "Delete", "Find", "Update"};
    
    // All the labels for the gui
    private JLabel idLbl = new JLabel("Id:");
    private JLabel nameLbl = new JLabel("Name:");
    private JLabel majorLbl = new JLabel("Major:");
    private JLabel selectionLbl = new JLabel("Choose Selection:");
    
    // All the textfields for the gui
    private JTextField idTxtField = new JTextField();
    private JTextField nameTxtField = new JTextField();
    private JTextField majorTxtField = new JTextField();
    
    // All the buttons for the gui
    private JButton processRequestBtn = new JButton("Process Request");
    
    // All the dropdownlists for the gui
    private JComboBox requestComboBox = new JComboBox(requestString);
    
    // Create the HashMap for the database
    private HashMap<Integer, String> dataBase = new HashMap<Integer, String>();
    
    // Creates the option pane used for display
    private JOptionPane displayMessage = new JOptionPane();
    
    // Creates the array of studentInfo
    private Student[] studentInfo = new Student[1000];
    
    // ActionListener used for the ProcessRequest button.
    public void actionPerformed(ActionEvent e) {
        String currentSelected = (String) requestComboBox.getSelectedItem();
        int idContents = Integer.parseInt(idTxtField.getText());
        String nameContents = nameTxtField.getText();
        String majorContents = majorTxtField.getText();
              
        // Results of having Insert selected in the combobox
        if (currentSelected.toLowerCase().equals("insert")) {
            if (dataBase.containsKey(idContents)) {
                displayMessage.showMessageDialog(null, "There is already saved "
                        + "content for the id you have entered.", "Insert Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if (idContents <= 0 || idContents > 999) {
                displayMessage.showMessageDialog(null, "Invalid id, please sele"
                        + "ct an id from 1-999.", "Insert Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                dataBase.put(idContents, nameContents + "," + majorContents);
                
                studentInfo[idContents] = new Student(nameContents, majorContents);
                displayMessage.showMessageDialog(null, "You have successfully u"
                        + "pdated the records.");
            }
        }
        
        // Results of having delete selcted in the combobox
        else if (currentSelected.toLowerCase().equals("delete")) {
            if (!dataBase.containsKey(idContents)) {
                displayMessage.showMessageDialog(null, "There is no content for"
                        + " the selected id.", "Delete Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if (idContents <= 0 || idContents > 999) {
                displayMessage.showMessageDialog(null, "Invalid id, please sele"
                        + "ct an id from 1-999.", "Delete Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                dataBase.remove(idContents);
                displayMessage.showMessageDialog(null, "You have successfully "
                        + "remove the contents of id: " + idContents);
            }
        }
        
        // Results of having find selected in the combobox
        else if (currentSelected.toLowerCase().equals("find")) {
            if (!dataBase.containsKey(idContents)) {
                displayMessage.showMessageDialog(null, "There is no content for"
                        + " the selected id.", "Find Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if (idContents <= 0 || idContents > 999) {
                displayMessage.showMessageDialog(null, "Invalid id, please sele"
                        + "ct an id from 1-999.", "Find Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                displayMessage.showMessageDialog(null, 
                        studentInfo[idContents].toString());
            }            
        }
        
        // Results of having update selected in the combo box
        else if (currentSelected.toLowerCase().equals("update")) {
            if (!dataBase.containsKey(idContents)) {
                displayMessage.showMessageDialog(null, "There is no content for"
                        + " the selected id.", "Update Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if (idContents <= 0 || idContents > 999) {
                displayMessage.showMessageDialog(null, "Invalid id, please sele"
                        + "ct an id from 1-999.", "Update Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                String[] gradeChar = {"A", "B", "C", "D", "F"};
                String[] creditHours = {"3", "6"};
                                        
                JComboBox letterGrade = new JComboBox(gradeChar);
                JComboBox creditsEnter = new JComboBox(creditHours);
                
                displayMessage.showMessageDialog(null, letterGrade, "Letter Grade", JOptionPane.QUESTION_MESSAGE);
                displayMessage.showMessageDialog(null, creditsEnter, "Credits Earned", JOptionPane.QUESTION_MESSAGE);
                
                String tempLetterGrade = (String) letterGrade.getSelectedItem();
                String tempCreditsEnter = (String) creditsEnter.getSelectedItem();
                char finalLetterGrade = tempLetterGrade.charAt(0);
                int finalCreditsEnter = Integer.parseInt(tempCreditsEnter);
                
                studentInfo[idContents].courseCompleted(finalLetterGrade, finalCreditsEnter);
                displayMessage.showMessageDialog(null, "You have successfully updated the records.");
            }
        }
        
        // Resets the text fields to not cause any mistakes
        idTxtField.setText("");
        nameTxtField.setText("");
        majorTxtField.setText("");
    }
    
    // GuiPanel default constructor to create the visuals for the gui
    public GuiPanel() {
        // Panel for the gui
        JPanel menuPanel = new JPanel();
        
        // Set up the panel
        menuPanel.setLayout(new GridLayout(5,2,5,5));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        menuPanel.add(idLbl);
        menuPanel.add(idTxtField);
        menuPanel.add(nameLbl);
        menuPanel.add(nameTxtField);
        menuPanel.add(majorLbl);
        menuPanel.add(majorTxtField);
        menuPanel.add(selectionLbl);
        menuPanel.add(requestComboBox);
        menuPanel.add(processRequestBtn);
        add(menuPanel);
        
        // Adds the actionlistner to the button
        processRequestBtn.addActionListener(this);
    }
    
}