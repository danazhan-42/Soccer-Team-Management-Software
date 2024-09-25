package soccerteam;

import java.time.LocalDate;

/**
 * The IPlayer interface represents a player in a soccer team. It provides methods to get and set
 * various attributes of a player such as: their name, date of birth, jersey number, skill level,
 * preferred position, assigned position, and age.
 */
public interface IPlayer {

  /**
   * Gets the first name of the player.
   *
   * @return The player's first name.
   */
  String getFirstName();

  /**
   * Gets the last name of the player.
   *
   * @return The player's last name.
   */
  String getLastName();

  /**
   * Gets the date of birth of the player.
   *
   * @return The player's date of birth.
   */
  LocalDate getDateOfBirth();

  /**
   * Gets the jersey number of the player.
   *
   * @return The player's jersey number.
   */
  int getJerseyNumber();

  /**
   * Gets the skill level of the player.
   *
   * @return The player's skill level.
   */
  int getSkillLevel();

  /**
   * Gets the preferred position of the player.
   *
   * @return The player's preferred position.
   */
  Position getPreferredPosition();

  /**
   * Gets the assigned position of the player.
   *
   * @return The player's assigned position.
   */
  Position getAssignedPosition();

  /**
   * Gets the age of the player.
   *
   * @return The player's age.
   */
  int getAge();

  /**
   * Sets the jersey number of the player.
   *
   * @param number The jersey number to set for the player.
   * @throws IllegalArgumentException If the jersey number is not an integer between 1 and 20.
   */
  void setJerseyNumber(int number);

  /**
   * Sets the assigned position of the player.
   *
   * @param position The position to set for the player.
   */
  void setAssignedPosition(Position position);

}
