package soccerteam;

public class Driver {
  public static void main(String[] args) {
    ITeam model = new Team();
    ITeamView view = new teamViewImpl();
    ITeamController controller = new teamControllerImpl(model, view);
    controller.go();
  }
}


