package soccerteam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * An implementation of the ITeamController interface that provides the logic and interaction
 * between the model and the view components of the soccer team management application. This class
 * responds to UI actions and updates the model and view accordingly.
 */
public class teamControllerImpl implements ITeamController, ActionListener {
  private ITeam model;
  private ITeamView view;

  /**
   * Constructs a new team controller with the given model and view.
   *
   * @param model The model representing the soccer team.
   * @param view  The view for displaying the user interface.
   */
  public teamControllerImpl(ITeam model, ITeamView view) {
    this.model = model;
    this.view = view;
    view.setAddPlayerListener(this);
    view.setRemovePlayerListener(this);
    view.setDisplayPlayersListener(this);
    view.setGenerateLineupListener(this);
  }

  /**
   * Handles actions triggered from the user interface.
   *
   * @param e The action event containing information about the triggered action.
   */
  @Override public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    try {
      switch (command) {
        case "addPlayer":
          addPlayer(view.getFirstName(), view.getLastName(), view.getDateOfBirth(),
              view.getSkillLevel(), view.getPreferredPosition());
          break;

        case "removePlayer":
          removePlayer(view.getJerseyNumber());
          break;

        case "generateLineup":
          generateStartingLineup();
          break;

        case "display":
          displayWholeTeam();
          break;

        default:
          break;
      }
    } catch (Exception error) {
      view.notifyMessage(error.getMessage());
    }
  }

  /**
   * Adds a new player into the team roster. If the player is successfully added, the user will be
   * notified, the team display will be updated, and input fields cleared. If any issues arise
   * during the addition, an error message will be displayed.
   *
   * @param firstName         Player's first name.
   * @param lastName          Player's last name.
   * @param dob               Player's date of birth, typically in a "YYYY-MM-DD" format.
   * @param skillLevel        Player's skill level, often a numeric value.
   * @param preferredPosition Player's favored playing position on the field.
   * @throws IllegalArgumentException If there's an issue with one of the passed parameters.
   */
  private void addPlayer(String firstName, String lastName, String dob, int skillLevel,
      String preferredPosition) {
    try {
      model.addPlayer(firstName, lastName, dob, skillLevel, preferredPosition);
      view.notifyMessage("Player added successfully!");
      displayWholeTeam();
      view.clearInputFields();
    } catch (IllegalArgumentException e) {
      view.notifyMessage(e.getMessage());
    }
  }

  /**
   * Removes a player from the team using their jersey number. If the player is successfully
   * removed, the user will be notified, the team display will be updated, and input fields cleared.
   * If any issues arise during the addition, an error message will be displayed.
   *
   * @param jerseyNumber The jersey number of the player to be removed.
   * @throws IllegalArgumentException If there's an issue with one of the passed parameters.
   */
  private void removePlayer(int jerseyNumber) {
    try {
      model.removePlayer(jerseyNumber);
      view.notifyMessage("Player removed successfully!");
      displayWholeTeam();
      view.clearInputFields();
    } catch (IllegalArgumentException e) {
      view.notifyMessage(e.getMessage());
      view.clearInputFields();
    }
  }

  /**
   * Displays all players currently in the team.
   */
  private void displayWholeTeam() {
    List<IPlayer> players = model.getPlayers();
    // Update the info
    StringBuilder info = new StringBuilder();
    if (players.size() < 10) {
      info.append("The current team is below the minimum of 10 players: ").append("[Team Size ")
          .append(model.getNumOfPlayers()).append("]");
      view.notifyInfo(info);
    } else {
      info.append("Displaying the complete team: ").append("[Team Size ")
          .append(model.getNumOfPlayers()).append("]");
      view.notifyInfo(info);
    }
    // Display the table
    view.updatePlayerTable(players);
  }

  /**
   * Generates and displays the starting lineup for the soccer team.
   */
  private void generateStartingLineup() {
    try {
      view.updatePlayerTable(model.getStartingLineup());
      view.notifyMessage("Starting lineup generated!");
      // Update the info
      StringBuilder info = new StringBuilder();
      info.append("[Team Size ").append(model.getNumOfPlayers()).append("]");
      view.notifyInfo(info);
    } catch (Exception e) {
      view.notifyMessage(e.getMessage());
    }
  }

  @Override public void go() {
    view.display();
  }
}
