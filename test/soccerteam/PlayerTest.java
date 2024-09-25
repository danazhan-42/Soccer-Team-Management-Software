package soccerteam;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Test;

/**
 * This class contains tests for the Player class.
 */
public class PlayerTest {
  /**
   * Tests the Player constructor with valid parameters.
   * The expected result is a Player instance with the specified values.
   */
  @Test
  public void testConstructValidPlayer(){
    IPlayer testPlayer = new Player("Joe", "Doe", LocalDate.of(2015,2,6),
        5, Position.GOALIE);
    assertEquals("Joe", testPlayer.getFirstName());
    assertEquals("Doe", testPlayer.getLastName());
    assertEquals(LocalDate.of(2015, 2, 6), testPlayer.getDateOfBirth());
    assertEquals(5, testPlayer.getSkillLevel());
    assertEquals(Position.GOALIE, testPlayer.getPreferredPosition());
  }

  /**
   * Tests the Player constructor with an invalid age.
   * The expected result is an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructInvalidPlayerAge(){
    IPlayer player = new Player("Joe", "Doe", LocalDate.of(2000,1,15),
        5, Position.FORWARD);
  }

  /**
   * Tests the Player constructor with an invalid skill level.
   * The expected result is an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructInvalidPlayerSkillLevel(){
    IPlayer player = new Player("Joe", "Doe", LocalDate.of(2015,1,15),
        9, Position.FORWARD);
  }

  /**
   * Tests the setJerseyNumber and getJerseyNumber methods.
   * The expected result is a Player instance with the specified jersey number.
   */
  @Test
  public void testSetGetJerseyNumber(){
    IPlayer testPlayer = new Player("Joe", "Doe", LocalDate.of(2015,2,6),
        5, Position.GOALIE);
    testPlayer.setJerseyNumber(10);
    assertEquals(10, testPlayer.getJerseyNumber());
  }

  /**
   * Tests the setJerseyNumber with invalid argument.
   * The expected result is an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidJerseyNumber(){
    IPlayer testPlayer = new Player("Joe", "Doe", LocalDate.of(2015,2,6),
        5, Position.GOALIE);
    testPlayer.setJerseyNumber(25);
  }

  /**
   * Tests the setAssignedPosition and getAssignedPosition methods.
   * The expected result is a Player instance with the specified assigned position.
   */
  @Test
  public void testSetGetAssignedPosition(){
    IPlayer testPlayer = new Player("Joe", "Doe", LocalDate.of(2015,2,6),
        5, Position.GOALIE);
    testPlayer.setAssignedPosition(Position.GOALIE);
    assertEquals(Position.GOALIE, testPlayer.getAssignedPosition());
  }

  /**
   * Tests the toString method.
   */
  @Test
  public void testToString(){
    IPlayer testPlayer = new Player("Joe", "Doe", LocalDate.of(2015,2,6),
        5, Position.GOALIE);
    testPlayer.setJerseyNumber(1);
    assertEquals("Joe Doe, Jersey Number: 1", testPlayer.toString());
  }



}