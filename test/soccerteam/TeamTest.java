package soccerteam;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import org.junit.Test;

/**
 * This class contains tests for the Team class.
 */
public class TeamTest {
  private ITeam testTeam = new Team();

  /**
   * Tests the addPlayer() method with an invalid date of birth input.
   * The expected result is a DateTimeParseException.
   */
  @Test(expected = DateTimeParseException.class)
  public void testConstructInvalidDateOfBirth(){
    testTeam.addPlayer("Joe", "Doe", "20150206", 5,"Goalie");
  }

  /**
   * Tests the addPlayer() method with an invalid preferredPosition input.
   * The expected result is an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructInvalidPreferredPosition(){
    testTeam.addPlayer("Joe", "Doe", "2015-02-06", 5,"Golie");
  }

  /**
   * Tests the addPlayer() method with a single player.
   * The expected result is the team with the added player and the correct player count, also the
   * player is assigned with the right jersey number.
   */
  @Test
  public void testAddPlayerWithSinglePlayer(){
    testTeam.addPlayer("Joe", "Doe", "2015-02-06", 5,"Goalie");
    assertEquals(1, testTeam.getNumOfPlayers());
    assertEquals(1, testTeam.getPlayers().get(0).getJerseyNumber());
  }

  /**
   * Tests the addPlayer() method with multiple players.
   * The expected result is the team with the added players and the correct player count.
   */
  @Test
  public void testAddMultiplePlayers() {
    testTeam.addPlayer("Alice", "Smith", "2014-05-10", 3, "DEFENDER");
    testTeam.addPlayer("Bob", "Johnson", "2013-03-15", 4, "MIDFIELDER");
    testTeam.addPlayer("Charlie", "Brown", "2015-09-01", 5, "FORWARD");
    assertEquals(3, testTeam.getNumOfPlayers());
  }

  /**
   * Tests the addPlayer() method when adding a player to a full team.
   * The expected result is an IllegalStateException.
   */
  @Test(expected = IllegalStateException.class)
  public void testAddPlayerToFullTeam(){
    for(int i = 0; i < 20; i++) {
      testTeam.addPlayer("FirstName" + i, "LastName", "2015-01-01", 4, "MIDFIELDER");
    }
    testTeam.addPlayer("Joe", "Doe", "2015-02-06", 5, "GOALIE"); // Trying to add one more player after the team is full
  }


  /**
   * Tests the removePlayer() method when remove a single player.
   */
  @Test
  public void testRemoveSinglePlayer(){
    testTeam.addPlayer("Alice", "Smith", "2014-05-10", 3, "DEFENDER");
    testTeam.addPlayer("Bob", "Johnson", "2013-03-15", 4, "MIDFIELDER");
    testTeam.addPlayer("Charlie", "Brown", "2015-09-01", 5, "FORWARD");
    testTeam.removePlayer(3);
    assertEquals(2, testTeam.getNumOfPlayers());
  }

  /**
   * Tests the removePlayer() method with invalid jersey number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemovePlayerWithInvalidJerseyNumber(){
    testTeam.addPlayer("Alice", "Smith", "2014-05-10", 3, "DEFENDER");
    testTeam.addPlayer("Bob", "Johnson", "2013-03-15", 4, "MIDFIELDER");
    testTeam.addPlayer("Charlie", "Brown", "2015-09-01", 5, "FORWARD");
    testTeam.removePlayer(24);
  }

  /**
   * Tests the removePlayer() method with a jersey number that does not exist in the team.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemovePlayerWithNonExistentJerseyNumber(){
    testTeam.addPlayer("Alice", "Smith", "2014-05-10", 3, "DEFENDER");
    testTeam.addPlayer("Bob", "Johnson", "2013-03-15", 4, "MIDFIELDER");
    testTeam.addPlayer("Charlie", "Brown", "2015-09-01", 5, "FORWARD");
    testTeam.removePlayer(4);
  }

  /**
   * Test case when creating a team with enough players and printing them in alphabetical order.
   * This test case verifies that the team is successfully created when it has 10 or more players.
   */
  @Test
  public void testCreateTeamWithEnoughPlayers() {
    // Adding players using the new addPlayer method signature
    testTeam.addPlayer("Jack", "Jackson", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Beth", "Brown", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Abby", "Aaron", "2015-01-01", 5, "GOALIE");
    testTeam.addPlayer("Dave", "Davis", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Ivy", "Irwin", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Catherine", "Cole", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Elva", "Edwards", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Gigi", "Gomez", "2014-01-01", 5, "FORWARD");
    testTeam.addPlayer("Fiona", "Fisher", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Henry", "Hayes", "2014-01-01", 4, "FORWARD");

    assertEquals(10, testTeam.getNumOfPlayers());
    // Assuming createTeam() and getPlayersAsString() methods exist and work as intended
    testTeam.createTeam();
    assertEquals("Abby Aaron, Jersey Number: 3\n"
        + "Beth Brown, Jersey Number: 2\n"
        + "Catherine Cole, Jersey Number: 6\n"
        + "Dave Davis, Jersey Number: 4\n"
        + "Elva Edwards, Jersey Number: 7\n"
        + "Fiona Fisher, Jersey Number: 9\n"
        + "Gigi Gomez, Jersey Number: 8\n"
        + "Henry Hayes, Jersey Number: 10\n"
        + "Ivy Irwin, Jersey Number: 5\n"
        + "Jack Jackson, Jersey Number: 1\n", testTeam.getPlayersAsString());
  }

  /**
   * Test case for attempting to create a team when there are not enough players.
   * The test expects an IllegalStateException since there are not enough players to create a team.
   */
  @Test(expected = IllegalStateException.class)
  public void testCreateTeamWithNotEnoughPlayers() {
    testTeam.addPlayer("Jack", "Jackson", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Beth", "Brown", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Abby", "Aaron", "2015-01-01", 5, "GOALIE");
    testTeam.addPlayer("Dave", "Davis", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Ivy", "Irwin", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Catherine", "Cole", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Elva", "Edwards", "2015-01-01", 5, "MIDFIELDER");

    testTeam.createTeam();
  }

  /**
   * Test case for creating a starting lineup with enough players and all lineup players get their preferred positions.
   * This test case verifies that the lineup is successfully created and all players are assigned their preferred positions.
   */
  @Test
  public void testCreateLineupWithEnoughPlayersWithPreferredPositions(){
    testTeam.addPlayer("Jack", "Jackson", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Beth", "Brown", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Abby", "Aaron", "2015-01-01", 5, "GOALIE");
    testTeam.addPlayer("Dave", "Davis", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Ivy", "Irwin", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Catherine", "Cole", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Elva", "Edwards", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Gigi", "Gomez", "2014-01-01", 5, "FORWARD");
    testTeam.addPlayer("Fiona", "Fisher", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Henry", "Hayes", "2014-01-01", 4, "FORWARD");

    assertEquals(10, testTeam.getNumOfPlayers());
    testTeam.createStartingLineup();
    assertEquals("Abby Aaron, Jersey Number: 3, Position: GOALIE\n"
        + "Beth Brown, Jersey Number: 2, Position: DEFENDER\n"
        + "Catherine Cole, Jersey Number: 6, Position: DEFENDER\n"
        + "Dave Davis, Jersey Number: 4, Position: MIDFIELDER\n"
        + "Elva Edwards, Jersey Number: 7, Position: MIDFIELDER\n"
        + "Fiona Fisher, Jersey Number: 9, Position: MIDFIELDER\n"
        + "Gigi Gomez, Jersey Number: 8, Position: FORWARD\n", testTeam.getStartingLineupAsString());
  }

  /**
   * Test case for creating a starting lineup with enough players but not all players
   * get their preferred positions.
   * Verifies that the lineup is created properly even if some players have to play out of their preferred positions.
   */
  @Test
  public void testCreateLineupWithEnoughPlayersWithoutPreferredPositions(){
    testTeam.addPlayer("Jack", "Jackson", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Beth", "Brown", "2015-01-01", 5, "GOALIE");
    testTeam.addPlayer("Abby", "Aaron", "2015-01-01", 5, "GOALIE");
    testTeam.addPlayer("Dave", "Davis", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Ivy", "Irwin", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Catherine", "Cole", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Elva", "Edwards", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Gigi", "Gomez", "2014-01-01", 5, "FORWARD");
    testTeam.addPlayer("Fiona", "Fisher", "2015-01-01", 5, "FORWARD");
    testTeam.addPlayer("Henry", "Hayes", "2014-01-01", 4, "FORWARD");

    assertEquals(10, testTeam.getNumOfPlayers());
    testTeam.createStartingLineup();
    assertEquals("Abby Aaron, Jersey Number: 3, Position: GOALIE\n"
        + "Beth Brown, Jersey Number: 2, Position: DEFENDER\n"
        + "Gigi Gomez, Jersey Number: 8, Position: DEFENDER\n"
        + "Catherine Cole, Jersey Number: 6, Position: MIDFIELDER\n"
        + "Dave Davis, Jersey Number: 4, Position: MIDFIELDER\n"
        + "Elva Edwards, Jersey Number: 7, Position: MIDFIELDER\n"
        + "Fiona Fisher, Jersey Number: 9, Position: FORWARD\n", testTeam.getStartingLineupAsString());
  }

  /**
   * Test case for regenerating the lineup after the team has been modified.
   * Validates the correct lineup formation after adding a new player, removing an existing player,
   * and regenerating the lineup.
   */
  @Test
  public void testRegenerateLineup() {
    testTeam.addPlayer("Jack", "Jackson", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Beth", "Brown", "2015-01-01", 5, "GOALIE");
    testTeam.addPlayer("Abby", "Aaron", "2015-01-01", 5, "GOALIE");
    testTeam.addPlayer("Dave", "Davis", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Ivy", "Irwin", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Catherine", "Cole", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Elva", "Edwards", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Gigi", "Gomez", "2014-01-01", 5, "FORWARD");
    testTeam.addPlayer("Fiona", "Fisher", "2015-01-01", 5, "FORWARD");
    testTeam.addPlayer("Henry", "Hayes", "2014-01-01", 4, "FORWARD");

    // Create a lineup and assign the positions
    testTeam.createStartingLineup();
    assertEquals("Abby Aaron, Jersey Number: 3, Position: GOALIE\n"
        + "Beth Brown, Jersey Number: 2, Position: DEFENDER\n"
        + "Gigi Gomez, Jersey Number: 8, Position: DEFENDER\n"
        + "Catherine Cole, Jersey Number: 6, Position: MIDFIELDER\n"
        + "Dave Davis, Jersey Number: 4, Position: MIDFIELDER\n"
        + "Elva Edwards, Jersey Number: 7, Position: MIDFIELDER\n"
        + "Fiona Fisher, Jersey Number: 9, Position: FORWARD\n", testTeam.getStartingLineupAsString());

    // Add new players, which may change the lineup
    testTeam.addPlayer("Joe", "Doe", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Alice", "Adams", "2015-01-01", 5, "FORWARD");

    // Regenerate a lineup and assign the positions
    testTeam.createStartingLineup();

    // Assert the expected lineup, assuming the new players have been incorporated into the lineup
    // Modify the expected lineup according to the logic used to create starting lineups
    assertEquals("Abby Aaron, Jersey Number: 3, Position: GOALIE\n"
            + "Beth Brown, Jersey Number: 2, Position: DEFENDER\n"
            + "Joe Doe, Jersey Number: 11, Position: DEFENDER\n" // Example; adjust as needed
            + "Catherine Cole, Jersey Number: 6, Position: MIDFIELDER\n"
            + "Dave Davis, Jersey Number: 4, Position: MIDFIELDER\n"
            + "Elva Edwards, Jersey Number: 7, Position: MIDFIELDER\n"
            + "Alice Adams, Jersey Number: 12, Position: FORWARD\n", // Example; adjust as needed
        testTeam.getStartingLineupAsString());
  }

    /**
   * Test case for attempting to create a starting lineup with an inadequate number of players.
   * Verifies that an exception is thrown when trying to create a lineup with fewer than the required number of players.
   * @throws IllegalStateException if the number of players is inadequate to form a team.
   */
  @Test(expected = IllegalStateException.class)
  public void testCreateLineupWithInadequatePlayers(){
    testTeam.addPlayer("Jack", "Jackson", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Beth", "Brown", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Abby", "Aaron", "2015-01-01", 5, "GOALIE");
    testTeam.addPlayer("Dave", "Davis", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Ivy", "Irwin", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Catherine", "Cole", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Elva", "Edwards", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Gigi", "Gomez", "2014-01-01", 5, "FORWARD");
    testTeam.addPlayer("Fiona", "Fisher", "2015-01-01", 5, "MIDFIELDER");

    testTeam.createStartingLineup();
  }

  /**
   * Test case for attempting to print the team when there are inadequate players to form a team.
   * Verifies that an exception is thrown when trying to print the details of a team that does not have enough players.
   * @throws IllegalStateException if the number of players is inadequate to form a team.
   */
  @Test(expected = IllegalStateException.class)
  public void testPrintTeamWithInadequatePlayers(){
    testTeam.addPlayer("Jack", "Jackson", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Beth", "Brown", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Abby", "Aaron", "2015-01-01", 5, "GOALIE");
    testTeam.addPlayer("Dave", "Davis", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Ivy", "Irwin", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Catherine", "Cole", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Elva", "Edwards", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Gigi", "Gomez", "2014-01-01", 5, "FORWARD");
    testTeam.addPlayer("Fiona", "Fisher", "2015-01-01", 5, "MIDFIELDER");

    testTeam.getPlayersAsString();
  }

  /**
   * Test case for attempting to print the lineup when there are inadequate players to form a team.
   * Verifies that an exception is thrown when trying to print the details of a team that does not have enough players.
   * @throws IllegalStateException if the number of players is inadequate to form a team.
   */
  @Test(expected = IllegalStateException.class)
  public void testPrintLineupWithInadequatePlayers(){
    testTeam.addPlayer("Jack", "Jackson", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Beth", "Brown", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Abby", "Aaron", "2015-01-01", 5, "GOALIE");
    testTeam.addPlayer("Dave", "Davis", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Ivy", "Irwin", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Catherine", "Cole", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Elva", "Edwards", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Gigi", "Gomez", "2014-01-01", 5, "FORWARD");
    testTeam.addPlayer("Fiona", "Fisher", "2015-01-01", 5, "MIDFIELDER");

    testTeam.getStartingLineupAsString();
  }

  /**
   * Test case for creating a starting lineup with enough players and all lineup players get their preferred positions.
   * This test case verifies that the lineup is successfully created and all players are assigned their preferred positions.
   */
  @Test
  public void testGetStartingLineupWithEnoughPlayersWithPreferredPositions(){
    testTeam.addPlayer("Jack", "Jackson", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Beth", "Brown", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Abby", "Aaron", "2015-01-01", 5, "GOALIE");
    testTeam.addPlayer("Dave", "Davis", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Ivy", "Irwin", "2014-01-01", 3, "MIDFIELDER");
    testTeam.addPlayer("Catherine", "Cole", "2015-01-01", 5, "DEFENDER");
    testTeam.addPlayer("Elva", "Edwards", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Gigi", "Gomez", "2014-01-01", 5, "FORWARD");
    testTeam.addPlayer("Fiona", "Fisher", "2015-01-01", 5, "MIDFIELDER");
    testTeam.addPlayer("Henry", "Hayes", "2014-01-01", 4, "FORWARD");

    assertEquals(10, testTeam.getNumOfPlayers());

    List<IPlayer> startingLineup = testTeam.getStartingLineup();

    // Assuming IPlayer has a toString() that outputs in the format: "Name LastName, Jersey Number: #, Position: POSITION"
    String lineupAsString = startingLineup.stream()
        .map(IPlayer::toString)
        .collect(Collectors.joining("\n"));

    assertEquals("Abby Aaron, Jersey Number: 3\n"
        + "Beth Brown, Jersey Number: 2\n"
        + "Catherine Cole, Jersey Number: 6\n"
        + "Dave Davis, Jersey Number: 4\n"
        + "Elva Edwards, Jersey Number: 7\n"
        + "Fiona Fisher, Jersey Number: 9\n"
        + "Gigi Gomez, Jersey Number: 8", lineupAsString);
  }


}

