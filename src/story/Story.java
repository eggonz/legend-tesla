package story;

import java.util.ArrayList;
import java.util.List;

public class Story {

    private static List<Integer> completedDungeons;
    private static int currentStoryState;

    public static void addCompletedDungeon(int id){
        if (completedDungeons == null)
            completedDungeons = new ArrayList<>();
        completedDungeons.add(id);
    }

    public static boolean isDungeonCompleted(int id){
        return completedDungeons.contains(id);
    }

    // Getters and Setters

    public static List<Integer> getCompletedDungeons(){
        return completedDungeons;
    }

    public static void setCurrentStoryState(int stateNumber) {
        currentStoryState = stateNumber;
    }

    public static int getCurrentStoryState() {
        return currentStoryState;
    }
}
