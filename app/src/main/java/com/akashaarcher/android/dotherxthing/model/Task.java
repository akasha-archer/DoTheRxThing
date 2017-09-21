package com.akashaarcher.android.dotherxthing.model;

/**
 * Created by Akasha on 9/14/17.
 */

public class Task {

    private Long _id;
    private String taskEntry;

    public Task(String taskEntry) {
        this.taskEntry = taskEntry;
    }

    public Task() {

    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getTaskEntry() {
        return taskEntry;
    }

    public void setTaskEntry(String taskEntry) {
        this.taskEntry = taskEntry;
    }
}
