# BC U10 Soccer Club Management Application

## About
The BC U10 Soccer Team Management Application is designed to assist coaches in organizing and managing their soccer teams. The application provides a simple interface to add, remove, display, and create lineups for players.

## List of Features
1. **Add Player**: Intuitively add player details, including name, date of birth, skill level, and preferred position.
2. **Remove Player**: Quickly remove a player using their jersey number.
3. **Display Team**: View the entire roster.
4. **Generate Starting Lineup**: Automatically generate a starting lineup based on the players' skill levels and preferred positions.

## How To Run
### How to run the jar file
- Open a terminal or command prompt.
  - Navigate to the directory containing the `SoccerTeam.jar` file.
  - Run the command: `java -jar SoccerTeam.jar`.
- Simply click on the `SoccerTeam.jar` file.

### Arguments
None required. Just execute the jar file.

## How to Use the Program
Upon launching, you'll be presented with an interface:

- **Adding Players**: Fill out the player details in the provided fields and click "Add".
- **Removing Players**: Enter the jersey number of the player you wish to remove and click "Remove".
- **Generate Lineup**: Use the "Generate Starting Lineup" button to automatically create a lineup.
- **Display Team**: Click the "Display Team" button to view the entire roster.

## Design/Model Changes
- **Version 1.0**: Initial design with command-line interaction.
- **Version 2.0**: Transitioned to a GUI interface for improved user experience.

## Assumptions
- A team can have a minimum of 10 and a maximum of 20 players.
- Each player has a unique jersey number between 1 and 20.

## Limitations
- At present, the application is designed to manage a single team and does not offer multi-team management capabilities.
- The data for players is basic, with functionalities limited to actions like adding, removing, and displaying players.

## Citations
All solutions and implementations were original.
