package soccerteam;

import java.awt.event.ActionListener;
import java.util.List;

/**
 * Represents the view component of the soccer team management application. This interface defines
 * methods to display the UI, retrieve input data from the user, and update UI components based on
 * interactions or state changes. It also provides mechanisms to add listeners for various user
 * interactions.
 */
public interface ITeamView {

  /**
   * Displays the main user interface.
   */
  void display();

  /**
   * Retrieves the first name from the user input.
   *
   * @return the entered first name.
   * @throws IllegalArgumentException if the input is empty.
   */
  String getFirstName();

  /**
   * Retrieves the last name from the user input.
   *
   * @return the entered last name.
   * @throws IllegalArgumentException if the input is empty.
   */
  String getLastName();

  /**
   * Retrieves the date of birth from the user input.
   *
   * @return the entered date of birth.
   * @throws IllegalArgumentException if the input is empty.
   */
  String getDateOfBirth();

  /**
   * Retrieves the skill level from the user input.
   *
   * @return the selected skill level.
   * @throws IllegalArgumentException if the input is empty.
   */
  int getSkillLevel();

  /**
   * Retrieves the preferred position from the user input.
   *
   * @return the selected preferred position.
   * @throws IllegalArgumentException if the input is empty.
   */
  String getPreferredPosition();

  /**
   * Retrieves the jersey number from the user input.
   *
   * @return the selected jersey number.
   * @throws IllegalArgumentException if the input is empty.
   */
  int getJerseyNumber();

  /**
   * Updates the table displaying the list of players.
   *
   * @param players A list of players to be displayed.
   */
  void updatePlayerTable(List<IPlayer> players);

  /**
   * Sends a notification message to the user, usually in response to some action or an error.
   *
   * @param message The message to display to the user.
   */
  void notifyMessage(String message);

  /**
   * Sends an informational message to the user, providing information about the team size.
   *
   * @param message The informational message to display.
   */
  void notifyInfo(StringBuilder message);

  /**
   * Clears all input fields in the user interface after a successful operation.
   */
  void clearInputFields();

  /**
   * Sets a listener for the "Add Player" user action.
   *
   * @param listener The listener to handle the "Add Player" action.
   */
  void setAddPlayerListener(ActionListener listener);

  /**
   * Sets a listener for the "Remove Player" user action.
   *
   * @param listener The listener to handle the "Remove Player" action.
   */
  void setRemovePlayerListener(ActionListener listener);

  /**
   * Sets a listener for the "Display Players" user action.
   *
   * @param listener The listener to handle the "Display Players" action.
   */
  void setDisplayPlayersListener(ActionListener listener);

  /**
   * Sets a listener for the "Generate Starting Lineup" user action.
   *
   * @param listener The listener to handle the "Generate Starting Lineup" action.
   */
  void setGenerateLineupListener(ActionListener listener);

}
