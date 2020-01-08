package com.ui.menu;

import client.Node;

/**
 * Credits to deku
 */
public class MenuEntry extends Node {

    public String source;
    public String command;
    public long command_trigger;
    public int command_index;
    public int command_action;
    public int uid;

    public MenuEntry(String command, String source, int uid, long trigger, int index, int action) {
        this.command = command;
        this.source = source;
        this.uid = uid;
        this.command_trigger = trigger;
        this.command_index = index;
        this.command_action = action;
    }

    public boolean validate(int uid) {
        if (this.uid == uid) {
            return true;
        }
        return false;
    }

}