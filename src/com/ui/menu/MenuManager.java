package com.ui.menu;

import client.LinkedList;

/**
 * Credits to deku
 */
public class MenuManager {

    private final LinkedList linkedList = new LinkedList();

    public static LinkedList list = new LinkedList();

    public static int results;
    public static boolean open;
    public static MenuEntry cancel;

    public static void init() {
        cancel = new MenuEntry("Cancel", null, 1107, 0L, 0, 0);
    }

    public static void add(String command, int uid) {
        add(command, null, uid, 0L, 0, 0);
    }

    public static void add(String command, int uid, int action) {
        add(command, null, uid, 0L, 0, action);
    }

    public static void add(String command, String source, int uid) {
        add(command, source, uid, 0L, 0, 0);
    }

    public static void add(String command, int uid, int index, int action) {
        add(command, null, uid, 0L, index, action);
    }

    public static void add(String command, int uid, long trigger, int index, int action) {
        add(command, null, uid, trigger, index, action);
    }

    public static void add(String command, String source, int uid, long trigger, int index, int action) {
        if (!open && results < 500) {
            list.insertBack(new MenuEntry(command, source, uid, trigger, index, action));
            results++;
        }
    }

    public static String get(MenuEntry entry) {
        if (entry.source == null || entry.source.length() <= 0) {
            return entry.command;
        }
        return (entry.command + " " + entry.source);
    }

    public static void clear() {
        list.clear();
        list.insertBack(cancel);
        results = 1;
    }

}