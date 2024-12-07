package cs112.ud3.controllers;
/**
 * Abstract Class for scenes which require information about the current RewardEvent and
 * whether or not the user is currently adding an event in its initial setup. Also lets us use a generic
 * changeInputScene() method in UtilityBelt rather than needing a unique method for each future scene
 * whenever we want to change scenes.
 * Child classes:
 * CardRewardInput, ConfirmPage, OpponentInput, StatsInput
 */

import cs112.ud3.models.RewardEvent;

public abstract class InputScreen {
    /**
     * Sets up scene before it is shown, using the status of the current RewardEvent and
     * whether or not the user is currently adding an event.
     * @param rewardEvent The current event that is being tracked in this window.
     * @param amAddingEvent true if the user is currently adding an event, false if they are viewing an event.
     */
    public abstract void initializeData(RewardEvent rewardEvent, boolean amAddingEvent);
}
