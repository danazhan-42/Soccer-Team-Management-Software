package soccerteam;

import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The ITeam class is an interface and is responsible for managing and representing a soccer team.
 */
public interface ITeam {
  /**
   * Adds a player to the team based on provided information.
   *
   * @param firstName         First name of the player.
   * @param lastName          Last name of the player.
   * @param dateOfBirth       Date of birth of the player in the format "yyyy-MM-dd".
   * @param skillLevel        Skill level of the player.
   * @param preferredPosition Preferred position of the player as a string.
   * @throws IllegalStateException    If the team has already reached the maximum size.
   * @throws DateTimeParseException   If the provided dateOfBirth string cannot be parsed to a
   *                                  LocalDate.
   * @throws IllegalArgumentException If the provided preferredPosition is not a valid position.
   */
  void addPlayer(String firstName, String lastName, String dateOfBirth, int skillLevel,
      String preferredPosition) // Change type to String
      throws IllegalStateException;

  /**
   * Removes a player from the team based on the provided jersey number.
   *
   * @param jerseyNumber Jersey number of the player to be removed.
   * @throws IllegalArgumentException If the provided jersey number is not within valid range or if
   *                                  there's no player associated with the given jersey number.
   */
  void removePlayer(int jerseyNumber);

  /**
   * This method creates a team from the list of players. Throws an exception if the number of
   * players is less than the minimum team size.
   *
   * @throws IllegalStateException If there are not enough players to create a team.
   */
  void createTeam();

  /**
   * This method creates a lineup from the list of players, selecting seven players based on their
   * skill level and last name. Then assigns positions to the lineup players in order. The players
   * will be assigned their preferred positions. If their preferred positions are taken, assign them
   * available positions in the order of Goalie, Defender, Midfielder and Forward.
   *
   * @throws IllegalStateException If trying to create lineup when there are not enough players to
   *                               create a team.
   */
  void createStartingLineup();

  /**
   * This method returns a string representation of the players in the team. Throws an exception if
   * there are not enough players and the team can not be created.
   *
   * @return A string representation of the players in the team.
   */
  String getPlayersAsString();

  /**
   * This method returns a string representation of the players in the lineup. Throws an exception
   * if there are not enough players and the team can not be created.
   *
   * @return A string representation of the players in the lineup.
   */
  String getStartingLineupAsString();

  /**
   * This method returns the number of players in the team.
   *
   * @return The number of players in the team.
   */
  int getNumOfPlayers();

  /**
   * This method returns a list of players.
   *
   * @return A list of players.
   */
  List<IPlayer> getPlayers();

  /**
   * This method returns a list of players in starting lineup.
   *
   * @return A list of players in starting lineup.
   */
  List<IPlayer> getStartingLineup();
}
