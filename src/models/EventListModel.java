package models;

import core.Model;
import core.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class EventListModel implements Model {

    //-----------------------------------------------------------------------
    //		Attributes
    //-----------------------------------------------------------------------
    private static final String DIRECTORY = ".";
    private static final String FILE = "events.txt"; // conexion //service API
    private final List<View> views = new ArrayList<>();
    private String notice;

    @Override
    public void attach(View view)
    {
        views.add(view);
    }

    @Override
    public void detach(View view)
    {
        views.remove(view);
    }

    @Override
    public void notifyViews()
    {
        for (View v : views) {
            v.update(this, notice);
        }
    }

    /**
     * Reads a {@link SchedulerEvent} saved in disk with name {@link #FILE}.
     * @return List of lists (matrix) of the events
     */
    public Vector<Vector<Object>> getEvents() {
        Vector<Vector<Object>> response = new Vector<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY, FILE)));
            String line = reader.readLine();

            while (line != null) {
                Vector<Object> eventInfo = new Vector<Object>();
                String[] tokens = line.split(";");

                eventInfo.add(tokens[0]);
                eventInfo.add(tokens[1]);
                eventInfo.add(Frequency.valueOf(tokens[2]));
                eventInfo.add(tokens[3]);
                eventInfo.add(tokens[4].equals("1") ? "ON" : "OFF");

                response.add(eventInfo);
                line = reader.readLine();
            }

            reader.close();
        } catch (FileNotFoundException fnfe) {
            notice = "File not found";
            notifyViews();
        } catch (Exception ex) {
            notice = "There was a problem reading the event file";
            notifyViews();
        }
        return response;
    }
}
