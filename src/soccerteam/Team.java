package soccerteam;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Team class implements the ITeam interface and is responsible for creating, managing and
 * representing a soccer team. A team can have a maximum size of 20 players and a minimum size of 10
 * players. The team is created with a list of players and a list of lineup. The players are
 * represented by instances of the IPlayer interface. The lineup is sublist of the players list,
 * representing the players currently playing. Each player is assigned a unique jersey number
 * between 1 and 20.
 */
public class Team implements ITeam {
  private static final int MAX_TEAM_SIZE = 20;
  private static final int MIN_TEAM_SIZE = 10;
  private List<IPlayer> players; // The list of all current players
  private List<IPlayer> startingLineup; // The list of all current players in the lineup
  private List<Integer> availableJerseyNumbers; // An array to store jersey numbers for players
  private Map<Position, Integer> formation; // A hashmap to store the 2-3-1 position

  /**
   * Constructs a new Team with an empty list of players and lineup. Also sets up a list of assigned
   * jersey numbers from 1 to 20. Defines a formation with 1 goalie, 2 defenders, 3 midfielders, and
   * 1 forward.
   */
  public Team() {
    this.players = new ArrayList<>();
    this.startingLineup = new ArrayList<>();
    // Creates a list of 20 jersey numbers
    this.availableJerseyNumbers = new ArrayList<>();
    for (int i = 1; i <= 20; i++) {
      availableJerseyNumbers.add(i);
    }
    // Creates a formation
    this.formation = new HashMap<>();
    formation.put(Position.GOALIE, 1);
    formation.put(Position.DEFENDER, 2);
    formation.put(Position.MIDFIELDER, 3);
    formation.put(Position.FORWARD, 1);
  }

  @Override public void addPlayer(String firstName, String lastName, String dateOfBirth,
      int skillLevel, String preferredPosition)
      throws IllegalStateException, DateTimeParseException, IllegalArgumentException {
    // Can add player if current number of players are below 20.
    if (players.size() < MAX_TEAM_SIZE) {
      // Check if the input String dateOfBirth can be converted to a LocalDate date type.
      LocalDate dob;
      try {
        dob = LocalDate.parse(dateOfBirth); // convert the string to a LocalDate
      } catch (DateTimeParseException e) {
        throw new DateTimeParseException(
            "The provided date " + dateOfBirth + " is not in the correct format (yyyy-MM-dd).",
            e.getParsedString(), e.getErrorIndex());
      }

      // Check if the input String preferredPosition can be converted to an enum constant.
      Position position;
      try {
        position = Position.valueOf(
            preferredPosition.toUpperCase()); // Convert String to Position enum
      } catch (IllegalArgumentException er) {
        throw new IllegalArgumentException(preferredPosition + " is not a valid position");
      }

      IPlayer player = new Player(firstName, lastName, dob, skillLevel,
          position); // create player object
      players.add(player);
      assignJerseyNumber(player);
    } else {
      throw new IllegalStateException("Cannot add player due to team reaching maximum capacity 20");
    }
  }

  @Override public void removePlayer(int jerseyNumber) throws IllegalArgumentException {
    if (jerseyNumber > 20 || jerseyNumber < 1) {
      throw new IllegalArgumentException("The provided jersey number is invalid.");
    }

    if (availableJerseyNumbers.contains(jerseyNumber)) {
      throw new IllegalArgumentException("There is no player with the provided jersey number.");
    }

    for (IPlayer player : players) {
      if (player.getJerseyNumber() == jerseyNumber) {
        players.remove(player);
        break;
      }
    }
    availableJerseyNumbers.add(jerseyNumber);
    Collections.sort(availableJerseyNumbers);
  }

  /**
   * Assigns a unique jersey number to the specified player in order.
   *
   * @param player The player to be assigned a jersey number.
   */
  private void assignJerseyNumber(IPlayer player) {
    player.setJerseyNumber(availableJerseyNumbers.get(0));
    availableJerseyNumbers.remove(0);
  }

  @Override public void createTeam() throws IllegalStateException {
    // If the number of players are less than 10, the team can not be created
    if (players.size() < MIN_TEAM_SIZE) {
      throw new IllegalStateException(
          "Not enough players to create a team. At least 10 players are needed.");
    }
  }

  @Override public void createStartingLineup() throws IllegalStateException {
    if (players.size() < 10) {
      throw new IllegalStateException(
          "Can not create starting lineup since there are not enough players to create a team.");
    }
    players.sort(Comparator.comparingInt(IPlayer::getSkillLevel).reversed()
        .thenComparing(IPlayer::getLastName));

    // Empty the previous lineup
    for (IPlayer player : startingLineup) {
      player.setAssignedPosition(Position.BENCH);
    }
    startingLineup.clear();

    // Add players to the lineup
    for (int i = 0; i < 7; i++) {
      startingLineup.add(players.get(i));
    }
    assignLineupPositions();
  }

  /**
   * This method assigns positions to the lineup players in order. The players will be assigned
   * their preferred positions. If their preferred positions are taken, assign them available
   * positions in the order of Goalie, Defender, Midfielder and Forward.
   */
  private void assignLineupPositions() {
    // probably combine this with the createStartingLineup function
    Map<Position, Integer> availablePositions = new HashMap<>(formation);
    Position[] positionOrder = { Position.GOALIE, Position.DEFENDER, Position.MIDFIELDER,
        Position.FORWARD };
    for (IPlayer player : startingLineup) {
      Position preferred = player.getPreferredPosition();
      if (availablePositions.containsKey(preferred) && availablePositions.get(preferred) > 0) {
        player.setAssignedPosition(preferred);
        availablePositions.put(preferred, availablePositions.get(preferred) - 1);
      } else {
        for (Position possiblePosition : positionOrder) {
          if (availablePositions.containsKey(possiblePosition)
              && availablePositions.get(possiblePosition) > 0) {
            player.setAssignedPosition(possiblePosition);
            availablePositions.put(possiblePosition, availablePositions.get(possiblePosition) - 1);
            break;
          }
        }
      }
    }
  }

  @Override public String getPlayersAsString() {
    createTeam();
    StringBuilder output = new StringBuilder();
    // Sort players by their last name
    players.sort(Comparator.comparing(IPlayer::getLastName));

    for (IPlayer player : players) {
      output.append(player.toString()).append("\n");
    }

    return output.toString();
  }

  @Override public String getStartingLineupAsString() {
    // generate the lineup and sort it
    createStartingLineup();
    startingLineup.sort(Comparator.comparing((IPlayer p) -> p.getAssignedPosition().ordinal())
        .thenComparing(IPlayer::getLastName));

    StringBuilder output = new StringBuilder();
    for (IPlayer player : startingLineup) {
      output.append(player.toString()).append(", Position: ").append(player.getAssignedPosition())
          .append("\n");
    }
    return output.toString();
  }

  @Override public int getNumOfPlayers() {
    return players.size();
  }

  @Override public List<IPlayer> getPlayers() {
    players.sort(Comparator.comparing(IPlayer::getLastName));
    return players;
  }

  @Override public List<IPlayer> getStartingLineup() {
    createStartingLineup();
    startingLineup.sort(Comparator.comparing((IPlayer p) -> p.getAssignedPosition().ordinal())
        .thenComparing(IPlayer::getLastName));
    return startingLineup;
  }
}

