package robowiki.runner;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import robocode.control.RobotResults;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleErrorEvent;

public final class BattleListener extends BattleAdaptor {
  private final Multimap<String, RobotResults> _botResults;

  public BattleListener() {
    _botResults = ArrayListMultimap.create();
  }

  public final void onBattleCompleted(BattleCompletedEvent completedEvent) {
    RobotResults[] robotResultsArray =
      RobotResults.convertResults(completedEvent.getIndexedResults());
    for (RobotResults robotResults : robotResultsArray) {
      _botResults.put(robotResults.getTeamLeaderName(), robotResults);
    }
  }

  public final void onBattleError(BattleErrorEvent battleErrorEvent) {
    System.out.println("Robocode error: " + battleErrorEvent.getError());
  }

  public final Multimap<String, RobotResults> getRobotResultsMap() {
    return ImmutableMultimap.copyOf(_botResults);
  }

  public final void clear() {
    _botResults.clear();
  }
}
