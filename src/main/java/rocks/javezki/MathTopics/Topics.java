package rocks.javezki.MathTopics;

import java.util.ArrayList;
import java.util.List;

public class Topics {

    private static List<MathTopics> mathTopics = new ArrayList<>();

    private Topics(){}

    public static void addMathTopic(MathTopics topic)
    {
        mathTopics.add(topic);
    }

    /**
     * 
     * @return Returns the math topic list which contains all math topics available
     */
    public static List<MathTopics> getMathtopics()
    {
        return mathTopics;
    }

    /**
     * 
     * @return A string that displays all math topics
     */
    public static String displayMathtopics()
    {
        String display = "";
        int count = 1;
        for (MathTopics topic : mathTopics)
        {
            display += count + ". " + topic.getTitle() + "\n" + topic.getDescription() + "\n";
            count++;
        }

        return display;
    }
}
