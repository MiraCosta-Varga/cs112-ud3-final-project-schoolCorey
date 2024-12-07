package cs112.ud3.models;

import java.io.Serializable;

/**
 * Class to represent the information shown on the Duel Stats screen for a given reward event.
 * Includes player and opponent stats for creatures/shields lost, player win/loss agains this opponent,
 * player total win/loss, and duel time.
 */
public class DMBattleStats implements Serializable {
    private static final int DEFAULT_STAT_VALUE = 0; //shared by all stats as the same default

    private int creaturesLostPlayer;
    private int creaturesLostOpp;
    private int shieldsLostPlayer;
    private int shieldsLostOpp;
    private int playerWinsLocal;
    private int playerLossesLocal;
    private int playerWinsTotal;
    private int playerLossesTotal;
    private int duelTime; //in seconds

    /**
     * Default constructor for DMBattleStats object. Currently defaults all values to 0
     */
    public DMBattleStats() {
        this(DEFAULT_STAT_VALUE, DEFAULT_STAT_VALUE, DEFAULT_STAT_VALUE, DEFAULT_STAT_VALUE, DEFAULT_STAT_VALUE, DEFAULT_STAT_VALUE, DEFAULT_STAT_VALUE, DEFAULT_STAT_VALUE, DEFAULT_STAT_VALUE, DEFAULT_STAT_VALUE);
    }

    /**
     * Full Constructor for DMBattleStats object. Of note, minutes and seconds of duel time are seperate parameters, and
     * the setter handles convering it into a single value for duelTime.
     * @param creaturesLostPlayer The number shown on the duel stats screen for the creatures the player lost. Must be >=0
     * @param creaturesLostOpp The number shown on the duel stats screen for the creatures the opponent lost. Must be >=0
     * @param shieldsLostPlayer The number shown on the duel stats screen for the shields the player lost. Must be >=0
     * @param shieldsLostOpp The number shown on the duel stats screen for the shields the opponent lost. Must be >=0
     * @param playerWinsLocal The number shown on the duel stats screen in the "Wins" category on the player side
     * @param playerLossesLocal The number shown on the duel stats screen in the "Wins" category on the opponent's side
     * @param playerWinsTotal The number shown on the duel stats screen in the "W/L Total" category before the "/"
     * @param playerLossesTotal The number shown on the duel stats screen in the "W/L Total" category after the "/"
     * @param duelTimeMinutes The section of Duel Time as seen on the duel stats screen before the ":"
     * @param duelTimeSeconds The section of Duel Time as seen on the duel stats screen after the ":"
     * @throws IllegalArgumentException If one of the arguments was invalid, such as because it was a negative number.
     */
    public DMBattleStats(int creaturesLostPlayer, int creaturesLostOpp, int shieldsLostPlayer, int shieldsLostOpp,
                         int playerWinsLocal, int playerLossesLocal,int playerWinsTotal,int playerLossesTotal,
                         int duelTimeMinutes,int duelTimeSeconds)throws IllegalArgumentException{
        if(!this.setAll(creaturesLostPlayer,creaturesLostOpp,shieldsLostPlayer,shieldsLostOpp,playerWinsLocal,playerLossesLocal,playerWinsTotal,playerLossesTotal,duelTimeMinutes,duelTimeSeconds)){
            throw new IllegalArgumentException("One or more parameters invalid; Try checking for negatives");
        }
    }

    /**
     * Copy constructor for DMBattleStats Object
     * @param other the DMStats object to be copied
     * @throws IllegalArgumentException if the object to be copied is null
     */
    public DMBattleStats(DMBattleStats other) throws IllegalArgumentException{
        if(other == null){
            throw new IllegalArgumentException("Tried to copy a null object");
        }else{
            this.setAll(other.creaturesLostPlayer,other.creaturesLostOpp,other.shieldsLostPlayer,other.shieldsLostOpp,other.playerWinsLocal,other.playerLossesLocal,other.playerWinsTotal,other.playerLossesTotal,other.duelTime);
        }
    }

    /**
     * Sets the stats for the creatures that the player lost during the duel.
     * @param creaturesLostPlayer The number shown on the duel stats screen for the creatures the player lost. Must be >=0
     * @return true if parameter valid and creaturesLostPlayer was set; false if invalid and thus not set
     */
    public boolean setCreaturesLostPlayer(int creaturesLostPlayer) {
        if (creaturesLostPlayer >=0){
            this.creaturesLostPlayer = creaturesLostPlayer;
            return true;
        }
        else return false;
    }

    /**
     * Sets the stats for the creatures that the opponent lost during the duel.
     * @param creaturesLostOpp The number shown on the duel stats screen for the creatures the opponent lost. Must be >=0
     * @return true if parameter valid and creaturesLostOpp was set; false if invalid and thus not set
     */
    public boolean setCreaturesLostOpp(int creaturesLostOpp) {
        if (creaturesLostOpp >=0){
            this.creaturesLostOpp = creaturesLostOpp;
            return true;
        }
        else return false;
    }

    /**
     * Sets the stats for the number of shields that the player lost during the duel.
     * @param shieldsLostPlayer The number shown on the duel stats screen for the shields the player lost. Must be >=0
     * @return true if parameter valid and shieldsLostPlayer was set; false if invalid and thus not set
     */
    public boolean setShieldsLostPlayer(int shieldsLostPlayer) {
        if (shieldsLostPlayer >=0){
            this.shieldsLostPlayer = shieldsLostPlayer;
            return true;
        }
        else return false;
    }

    /**
     * Sets the stats for the number of shields that the opponent lost during the duel.
     * @param shieldsLostOpp The number shown on the duel stats screen for the shields the opponent lost. Must be >=0
     * @return true if parameter valid and shieldsLostOpp was set; false if invalid and thus not set
     */
    public boolean setShieldsLostOpp(int shieldsLostOpp) {
        if (shieldsLostOpp >=0){
            this.shieldsLostOpp = shieldsLostOpp;
            return true;
        }
        else return false;
    }

    /**
     * Sets the stats for the number of games the player's character has won against this specific opponent
     * @param playerWinsLocal The number shown on the duel stats screen in the "Wins" category on the player side
     * @return true if parameter valid and playerWinsLocal was set; false if invalid and thus not set
     */
    public boolean setPlayerWinsLocal(int playerWinsLocal) {
        if (playerWinsLocal >=0){
            this.playerWinsLocal = playerWinsLocal;
            return true;
        }
        else return false;
    }

    /**
     * Sets the stats for the number of games the player's character has lost against this specific opponent
     * @param playerLossesLocal The number shown on the duel stats screen in the "Wins" category on the opponent's side
     * @return true if parameter valid and playerLossesLocal was set; false if invalid and thus not set
     */
    public boolean setPlayerLossesLocal(int playerLossesLocal) {
        if (playerLossesLocal >=0){
            this.playerLossesLocal = playerLossesLocal;
            return true;
        }
        else return false;
    }

    /**
     * Sets the stats for the number of games the player's character has won in total
     * @param playerWinsTotal The number shown on the duel stats screen in the "W/L Total" category before the "/"
     * @return true if parameter valid and playerWinsTotal was set; false if invalid and thus not set
     */
    public boolean setPlayerWinsTotal(int playerWinsTotal) {
        if (playerWinsTotal >=0){
            this.playerWinsTotal = playerWinsTotal;
            return true;
        }
        else return false;
    }

    /**
     * Sets the stats for the number of games the player's character has lost in total
     * @param playerLossesTotal The number shown on the duel stats screen in the "W/L Total" category after the "/"
     * @return true if parameter valid and playerLossesTotal was set; false if invalid and thus not set
     */
    public boolean setPlayerLossesTotal(int playerLossesTotal) {
        if (playerLossesTotal >=0){
            this.playerLossesTotal = playerLossesTotal;
            return true;
        }
        else return false;
    }

    /**
     * Sets the time the duel took, in seconds
     * @param duelTime The total seconds the duel took according to the game
     * @return true if parameter valid and duelTime was set; false if invalid and thus not set
     */
    public boolean setDuelTime(int duelTime) {
        if(duelTime >=0){
            this.duelTime = duelTime;
            return true;
        }else {
            return false;
        }

    }

    /**
     * Takes input of minutes and seconds (as seen in game) and converts them to total seconds in order
     * to set the value for duelTime
     * @param minutes The section of Duel Time as seen on the duel stats screen before the ":"
     * @param seconds The section of Duel Time as seen on the duel stats screen after the ":"
     * @return true if parameter valid and duelTime was set; false if invalid and thus not set
     */
    public boolean setDuelTime(int minutes,int seconds) {
        if(minutes<0||seconds<0||seconds>60){
            return false;
        }else {
            int totalSeconds = (minutes*60) + seconds;
            return setDuelTime(totalSeconds);
        }
    }

    /**
     * Sets all instance variables of a DMBattleStats object. Minutes and seconds seperated for DuelTime
     * @param creaturesLostPlayer The number shown on the duel stats screen for the creatures the player lost. Must be >=0
     * @param creaturesLostOpp The number shown on the duel stats screen for the creatures the opponent lost. Must be >=0
     * @param shieldsLostPlayer The number shown on the duel stats screen for the shields the player lost. Must be >=0
     * @param shieldsLostOpp The number shown on the duel stats screen for the shields the opponent lost. Must be >=0
     * @param playerWinsLocal The number shown on the duel stats screen in the "Wins" category on the player side
     * @param playerLossesLocal The number shown on the duel stats screen in the "Wins" category on the opponent's side
     * @param playerWinsTotal The number shown on the duel stats screen in the "W/L Total" category before the "/"
     * @param playerLossesTotal The number shown on the duel stats screen in the "W/L Total" category after the "/"
     * @param duelTimeMinutes The section of Duel Time as seen on the duel stats screen before the ":"
     * @param duelTimeSeconds The section of Duel Time as seen on the duel stats screen after the ":"
     * @return true if all parameters are valid and thus all instance variables were set, false if one or more were invalid and not set.
     */
    public boolean setAll(int creaturesLostPlayer, int creaturesLostOpp, int shieldsLostPlayer, int shieldsLostOpp, int playerWinsLocal, int playerLossesLocal,int playerWinsTotal,int playerLossesTotal,int duelTimeMinutes,int duelTimeSeconds){
        return setCreaturesLostPlayer(creaturesLostPlayer) && setCreaturesLostOpp(creaturesLostOpp) &&
                setShieldsLostPlayer(shieldsLostPlayer) && setShieldsLostOpp(shieldsLostOpp) &&
                setPlayerWinsLocal(playerWinsLocal) && setPlayerLossesLocal(playerLossesLocal) &&
                setPlayerWinsTotal(playerWinsTotal) && setPlayerLossesTotal(playerLossesTotal) &&
                setDuelTime(duelTimeMinutes,duelTimeSeconds);
    }

    /**
     * Sets all instance variables of a DMBattleStats object. Minutes and seconds seperated for DuelTime
     * @param creaturesLostPlayer The number shown on the duel stats screen for the creatures the player lost. Must be >=0
     * @param creaturesLostOpp The number shown on the duel stats screen for the creatures the opponent lost. Must be >=0
     * @param shieldsLostPlayer The number shown on the duel stats screen for the shields the player lost. Must be >=0
     * @param shieldsLostOpp The number shown on the duel stats screen for the shields the opponent lost. Must be >=0
     * @param playerWinsLocal The number shown on the duel stats screen in the "Wins" category on the player side
     * @param playerLossesLocal The number shown on the duel stats screen in the "Wins" category on the opponent's side
     * @param playerWinsTotal The number shown on the duel stats screen in the "W/L Total" category before the "/"
     * @param playerLossesTotal The number shown on the duel stats screen in the "W/L Total" category after the "/"
     * @param duelTime The time the duel took, in seconds
     * @return true if all parameters are valid and thus all instance variables were set, false if one or more were invalid and not set.
     */
    public boolean setAll(int creaturesLostPlayer, int creaturesLostOpp, int shieldsLostPlayer, int shieldsLostOpp, int playerWinsLocal, int playerLossesLocal,int playerWinsTotal,int playerLossesTotal,int duelTime){
        return setCreaturesLostPlayer(creaturesLostPlayer) && setCreaturesLostOpp(creaturesLostOpp) &&
                setShieldsLostPlayer(shieldsLostPlayer) && setShieldsLostOpp(shieldsLostOpp) &&
                setPlayerWinsLocal(playerWinsLocal) && setPlayerLossesLocal(playerLossesLocal) &&
                setPlayerWinsTotal(playerWinsTotal) && setPlayerLossesTotal(playerLossesTotal) &&
                setDuelTime(duelTime);
    }

    /**
     * Getter for the creaturesLostPlayer instance variable
     * @return int for the number of creatures the player lost during the duel that caused the reward event
     */
    public int getCreaturesLostPlayer() {
        return creaturesLostPlayer;
    }

    /**
     * Getter for the creaturesLostOpp instance variable
     * @return the number of creatures the opponent lost during the duel that caused the reward event
     */
    public int getCreaturesLostOpp() {
        return creaturesLostOpp;
    }

    /**
     * Getter for the shieldsLostPlayer instance variable
     * @return the number of shields the player lost during the duel that cause the reward event
     */
    public int getShieldsLostPlayer() {
        return shieldsLostPlayer;
    }

    /**
     * Getter for the shieldsLostOpp instance variable
     * @return the number of shields the opponent lost during the duel that cause the reward event
     */
    public int getShieldsLostOpp() {
        return shieldsLostOpp;
    }

    /**
     * Getter for the playerWinsLocal instance variable
     * @return int for the number of times the player has won against this specific opponent
     *         at the point of this reward event
     */
    public int getPlayerWinsLocal() {
        return playerWinsLocal;
    }

    /**
     * Getter for the playerLossesLocal instance variable
     * @return int for the number of times the player has lost against this specific opponent
     *         at the point of this reward event
     */
    public int getPlayerLossesLocal() {
        return playerLossesLocal;
    }

    /**
     * Getter for the playerWinsTotal instance variable
     * @return int for the number of times the player has won overall
     *         at the point of this reward event
     */
    public int getPlayerWinsTotal() {
        return playerWinsTotal;
    }

    /**
     * Getter for the playerLossesTotal instance variable
     * @return int for the number of times the player has lost overall
     *         at the point of this reward event
     */
    public int getPlayerLossesTotal() {
        return playerLossesTotal;
    }

    /**
     * Getter for duelTime instance variable
     * @return int for the time the duel took in total seconds
     */
    public int getDuelTime() {
        return duelTime;
    }

    /**
     * Getter for number of minutes the duel took
     * @return int for number of minutes the duel took (minutes portion of mm:ss format)
     */
    public int getDuelTimeMinutes(){
        return duelTime/60;
    }

    /**
     * Getter for seconds portion of duel time in Minutes:Seconds format
     * @return int for number of seconds for mm:ss format the duel took
     */
    public int getDuelTimeSeconds(){
        return duelTime%60;
    }

    @Override
    public String toString(){
        String result = "Duel Stats:\n";
        result+= "Creatures Lost:\n";
        result+= String.format("Player: %d\tOpponent: %d%n",creaturesLostPlayer,creaturesLostOpp);
        result+= "Shields Lost:\n";
        result += String.format("Player: %d\tOpponent: %d%n",shieldsLostPlayer,shieldsLostOpp);
        result+= "Matchup Wins:\n";
        result += String.format("Player: %d\tOpponent: %d%n",playerWinsLocal,playerLossesLocal);
        result += String.format("Total Player wins: %d%nTotal Player Losses: %d%n",playerWinsTotal,playerLossesTotal);
        int minutes = duelTime/60;
        int seconds = duelTime%60;
        result += String.format("Duel Time: %02d:%02d",minutes,seconds);
        return result;
    }

    @Override
    public boolean equals(Object other){
        if(other == null || ! (other instanceof DMBattleStats)){
            return false;
        }else {
            DMBattleStats otherStats  = (DMBattleStats) other;
            return this.creaturesLostPlayer == otherStats.creaturesLostPlayer &&
                    this.creaturesLostOpp == otherStats.creaturesLostOpp &&
                    this.shieldsLostPlayer == otherStats.shieldsLostOpp &&
                    this.playerWinsLocal == otherStats.playerWinsLocal &&
                    this.playerLossesLocal == otherStats.playerLossesLocal &&
                    this.playerWinsTotal == otherStats.playerWinsTotal &&
                    this.playerLossesTotal == otherStats.playerLossesTotal &&
                    this.duelTime == otherStats.duelTime;

        }
    }
}
