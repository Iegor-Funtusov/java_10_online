package ua.com.alevel.controller;

import ua.com.alevel.config.LoaderPage;
import ua.com.alevel.reactiv.NativePubSub;

public class RootLayoutController {

    public void showStudents() {
        System.out.println("RootLayoutController.showStudents");
        NativePubSub.getInstance().publish(LoaderPage.STUDENTS);
    }

    public void showGroups() {
        System.out.println("RootLayoutController.showGroups");
        NativePubSub.getInstance().publish(LoaderPage.GROUPS);
    }
}

// publish -> subscriber (Observable)
