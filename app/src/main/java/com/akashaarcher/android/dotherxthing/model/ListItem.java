package com.akashaarcher.android.dotherxthing.model;

/**
 * Created by Akasha on 9/14/17.
 */

public class ListItem {

 private String taskEntry;

    public ListItem(String taskEntry) {
        this.taskEntry = taskEntry;
    }

    public String getTaskEntry() {
        return taskEntry;
    }

    public void setTaskEntry(String taskEntry) {
        this.taskEntry = taskEntry;
    }
}
