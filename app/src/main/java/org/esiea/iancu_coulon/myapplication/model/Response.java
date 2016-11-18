package org.esiea.iancu_coulon.myapplication.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raphael on 2016-11-17.
 */

public class Response {
    private List<AndroidVersion> android = new ArrayList<AndroidVersion>();

    public List<AndroidVersion> getAndroid() {
        return android;
    }
}
