package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controllers.HomeController;
import core.Model;
import core.View;

/**
 * View associated with {@link HomeController}. It will be responsible for program's
 * main screen view.
 *
 * Created by Kevin Durand
 */
@SuppressWarnings("serial")
public class HomeView extends JPanel implements View
{
    //-----------------------------------------------------------------------
    //		Attributes
    //-----------------------------------------------------------------------
    @SuppressWarnings("unused")
    private final HomeController homeController;
    private final JFrame mainFrame;
    private final static int MAIN_FRAME_WIDTH = 800;
    private final static int MAIN_FRAME_HEIGHT = 350;
    private final static int MAIN_FRAME_X = 100;
    private final static int MAIN_FRAME_Y = 100;


    //-----------------------------------------------------------------------
    //		Constructor
    //-----------------------------------------------------------------------
    /**
     * Initializes the view responsible for program's main screen.
     *
     * @param homeController Controller responsible for this view
     * @param mainFrame Main frame / window of the application
     */
    public HomeView(HomeController homeController, JFrame mainFrame)
    {
        this.homeController = homeController;
        this.mainFrame = mainFrame;

        make_mainFrame();
        make_tabs();
    }


    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
    @Override
    public void update(Model model, Object data)
    {
        //TODO
        //Por ejemplo si queremos cargar una lista de datos al iniciar el aplicativo y existe un error

    }

    /**
     * Configures the main frame of the application.
     */
    private void make_mainFrame()
    {
        mainFrame.setOpacity(1.0f);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBounds(MAIN_FRAME_X, MAIN_FRAME_Y, MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        mainFrame.setMinimumSize(new Dimension(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT));


        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
    }

    /**
     * Creates tab navigation.
     */
    private void make_tabs()
    {

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("New event", homeController.getNewEventView());
        tabbedPane.addTab("Events", homeController.getEventListView());
        add(tabbedPane, BorderLayout.CENTER);

        //add the Listener
        tabbedPane.addChangeListener(e -> {
            if(tabbedPane.getSelectedIndex()==1) //Index starts at 0, so Index 2 = Tab3
            {
                JOptionPane.showMessageDialog(null, "Click en tab: " + tabbedPane.getSelectedIndex());
                homeController.listEvents();
            }
        });
    }
}
