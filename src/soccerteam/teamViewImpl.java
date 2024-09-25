package soccerteam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * This class provides an implementation of the ITeamView interface. It creates and manages the
 * graphical user interface (GUI) components required for displaying and interacting with soccer
 * team data.
 */
public class teamViewImpl implements ITeamView {
  // Declare the containers
  private JFrame frame;
  private JPanel actionsPanel;
  private JPanel centerPanel;
  private JPanel playerDetailsPanel;
  private JPanel messagePanel;
  private JScrollPane rosterPanel;

  // Declare the components
  private JButton addButton;
  private JButton removeButton;
  private JButton displayButton;
  private JButton generateLineupButton;
  private JTextField firstNameText;
  private JTextField lastNameText;
  private JTextField dateOfBirthText;
  private JComboBox<Integer> skillLevelText;
  private JComboBox<String> preferredPositionText;
  private JComboBox<Integer> jerseyNumberText;
  private JLabel messageLabel;
  private JLabel infoLabel;
  private JTable displayTable;
  private DefaultTableModel tableModel;

  /**
   * Constructs a new instance, initializing the GUI components and setting up the layout.
   */
  public teamViewImpl() {
    // Set up the frame
    frame = new JFrame("British Columbia U10 Soccer Team Management");
    frame.setSize(800, 550);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    // Add button components to the actionsPanel
    actionsPanel = new JPanel();
    addButton = new JButton("Add");
    addButton.setActionCommand("addPlayer");
    removeButton = new JButton("Remove");
    removeButton.setActionCommand("removePlayer");
    generateLineupButton = new JButton("Generate Starting Lineup");
    generateLineupButton.setActionCommand("generateLineup");
    displayButton = new JButton("Display Team");
    displayButton.setActionCommand("display");
    actionsPanel.add(addButton);
    actionsPanel.add(removeButton);
    actionsPanel.add(generateLineupButton);
    actionsPanel.add(displayButton);
    frame.add(actionsPanel, BorderLayout.NORTH);

    // Initiate components for adding a player
    playerDetailsPanel = new JPanel(new GridLayout(8, 2, 1, 1)); // added gaps
    playerDetailsPanel.setBorder(BorderFactory.createEmptyBorder(1, 150, 20, 150));
    firstNameText = new JTextField(10);
    lastNameText = new JTextField(10);
    dateOfBirthText = new JTextField(10);
    skillLevelText = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5 });
    preferredPositionText = new JComboBox<>(
        new String[] { "Goalie", "Defender", "Midfielder", "Forward" });

    // Construct part for adding a player
    playerDetailsPanel.add(
        new JLabel("<html><i><u><b>To add a player, " + "please fill details below :"));
    playerDetailsPanel.add(new JLabel(""));
    playerDetailsPanel.add(new JLabel("First Name:"));
    playerDetailsPanel.add(firstNameText);
    playerDetailsPanel.add(new JLabel("Last Name:"));
    playerDetailsPanel.add(lastNameText);
    playerDetailsPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
    playerDetailsPanel.add(dateOfBirthText);
    playerDetailsPanel.add(new JLabel("Skill Level:"));
    playerDetailsPanel.add(skillLevelText);
    playerDetailsPanel.add(new JLabel("Preferred Position:"));
    playerDetailsPanel.add(preferredPositionText);

    // Construct part for deleting a player
    jerseyNumberText = new JComboBox<>();
    for (int i = 1; i <= 20; i++) {
      jerseyNumberText.addItem(i);
    }
    playerDetailsPanel.add(new JLabel("<html><i><u><b>To remove a player, enter jersey number :"));
    playerDetailsPanel.add(new JLabel(""));
    playerDetailsPanel.add(new JLabel("Jersey Number"));
    playerDetailsPanel.add(jerseyNumberText);

    // Construct the message part
    messageLabel = new JLabel(" ");
    messageLabel.setHorizontalAlignment(JLabel.CENTER);
    messageLabel.setForeground(Color.RED);
    messagePanel = new JPanel(new BorderLayout());
    messagePanel.add(messageLabel, BorderLayout.NORTH);
    messagePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));

    // Add an info part
    infoLabel = new JLabel("");
    infoLabel.setHorizontalAlignment(JLabel.LEFT);

    // Organize the center panel
    centerPanel = new JPanel(new BorderLayout());
    centerPanel.add(playerDetailsPanel, BorderLayout.NORTH);
    centerPanel.add(messagePanel, BorderLayout.CENTER);
    centerPanel.add(infoLabel, BorderLayout.SOUTH);
    frame.add(centerPanel, BorderLayout.CENTER);

    // Define the table's column names
    String[] columns = { "First Name", "Last Name", "Age", "Skill Level", "Jersey Number",
        "Preferred Position", "Assigned Position" };
    tableModel = new DefaultTableModel(columns, 0);
    displayTable = new JTable(tableModel);
    displayTable.setDefaultEditor(Object.class, null);
    rosterPanel = new JScrollPane(displayTable);
    // Set table header style
    JTableHeader header = displayTable.getTableHeader();
    Font boldFont = header.getFont().deriveFont(Font.BOLD);
    header.setFont(boldFont);
    // Set the size
    int rowHeight = displayTable.getRowHeight();
    int desiredRows = 15;
    int headerHeight = displayTable.getTableHeader().getPreferredSize().height;
    Dimension preferredSize = rosterPanel.getPreferredSize();
    preferredSize.height = (rowHeight * desiredRows) + headerHeight;
    rosterPanel.setPreferredSize(preferredSize);
    frame.add(rosterPanel, BorderLayout.SOUTH);

    frame.pack();
    frame.setVisible(true);
  }

  @Override public void display() {

    frame.setVisible(true);
    clearInputFields();
  }

  @Override public String getFirstName() {
    String firstName = firstNameText.getText().trim();
    if (firstName.isEmpty()) {
      throw new IllegalArgumentException("First name is required.");
    }
    return firstName;
  }

  @Override public String getLastName() {
    String lastName = lastNameText.getText().trim();
    if (lastName.isEmpty()) {
      throw new IllegalArgumentException("Last name is required.");
    }
    return lastName;
  }

  @Override public String getDateOfBirth() {
    String dateOfBirth = dateOfBirthText.getText().trim();
    if (dateOfBirth.isEmpty()) {
      throw new IllegalArgumentException("Date of birth is required.");
    }
    return dateOfBirth;
  }

  @Override public int getSkillLevel() {
    if (skillLevelText.getSelectedItem() == null) {
      throw new IllegalArgumentException("Skill level is required.");
    }
    return (Integer) skillLevelText.getSelectedItem();
  }

  @Override public String getPreferredPosition() {
    if (preferredPositionText.getSelectedItem() == null) {
      throw new IllegalArgumentException("Preferred Position is required.");
    }
    String selectedPosition = (String) preferredPositionText.getSelectedItem();
    return selectedPosition;
  }

  @Override public int getJerseyNumber() {
    if (jerseyNumberText.getSelectedItem() == null) {
      throw new IllegalArgumentException("Jersey number not selected.");
    }
    return (Integer) jerseyNumberText.getSelectedItem();
  }

  @Override public void updatePlayerTable(List<IPlayer> players) {
    tableModel.setRowCount(0);
    for (IPlayer player : players) {
      Object[] row = new Object[] { player.getFirstName(), player.getLastName(), player.getAge(),
          player.getSkillLevel(), player.getJerseyNumber(), player.getPreferredPosition(),
          player.getAssignedPosition(), };
      tableModel.addRow(row);
    }
  }

  @Override public void notifyMessage(String message) {
    messageLabel.setText(message);
  }

  @Override public void notifyInfo(StringBuilder message) {
    infoLabel.setText(String.valueOf(message));
  }

  @Override public void clearInputFields() {
    firstNameText.setText("");
    lastNameText.setText("");
    dateOfBirthText.setText("");
    skillLevelText.setSelectedIndex(-1);  // Set JComboBox to no selection
    preferredPositionText.setSelectedIndex(-1);
    jerseyNumberText.setSelectedIndex(-1);
  }

  @Override public void setAddPlayerListener(ActionListener listener) {
    addButton.addActionListener(listener);
  }

  @Override public void setRemovePlayerListener(ActionListener listener) {
    removeButton.addActionListener(listener);
  }

  @Override public void setDisplayPlayersListener(ActionListener listener) {
    displayButton.addActionListener(listener);
  }

  @Override public void setGenerateLineupListener(ActionListener listener) {
    generateLineupButton.addActionListener(listener);
  }

}
