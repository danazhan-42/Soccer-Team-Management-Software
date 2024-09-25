package soccerteam;

import java.time.LocalDate;
import java.time.Period;

/**
 * The Player class implements the IPlayer interface. It provides methods to get and set various attributes of a player such as:
 * their name, date of birth, jersey number, skill level, preferred position, assigned position, and age.
 */
public class Player implements IPlayer {
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private int jerseyNumber;
  private int skillLevel;
  private Position preferredPosition;
  private Position assignedPosition;

  /**
   * Constructs a new Player with the given details.
   *
   * @param firstName the player's first name.
   * @param lastName the player's last name.
   * @param dateOfBirth the player's date of birth.
   * @param skillLevel the player's skill level.
   * @param preferredPosition the player's preferred playing position.
   * @throws IllegalArgumentException if the player's age is greater than 10. The U10 soccer team is only for children under ten years old.
   */
  public Player(String firstName, String lastName, LocalDate dateOfBirth, int skillLevel,
      Position preferredPosition) throws IllegalArgumentException{
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.skillLevel = skillLevel;
    this.preferredPosition = preferredPosition;
    this.assignedPosition = Position.BENCH;
    if(getAge() > 10 || getAge() < 0){
      throw new IllegalArgumentException("U10 soccer team is only for children under ten years old");
    }
    if(skillLevel < 1 || skillLevel > 5){
      throw new IllegalArgumentException("Skill level should be an integer between 1 and 5.");
    }
  }

  @Override public String getFirstName() {
    return firstName;
  }

  @Override public String getLastName() {
    return lastName;
  }

  @Override public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  @Override public int getJerseyNumber() {
    return jerseyNumber;
  }

  @Override public int getSkillLevel() {
    return skillLevel;
  }

  @Override public Position getPreferredPosition() {
    return preferredPosition;
  }

  @Override public Position getAssignedPosition() {
    return assignedPosition;
  }

  @Override public int getAge() {
    return Period.between(dateOfBirth, LocalDate.now()).getYears();
  }

  @Override public void setJerseyNumber(int number) throws IllegalArgumentException{
    this.jerseyNumber = number;
    if(number > 20 || number < 1){
      throw new IllegalArgumentException("Jersey number should be an integer between 1 and 20.");
    }
  }

  @Override public void setAssignedPosition(Position position) {
    this.assignedPosition = position;
  }

  @Override
  public String toString(){
    return String.format("%s %s, Jersey Number: %d", firstName, lastName, jerseyNumber);
  }

}
