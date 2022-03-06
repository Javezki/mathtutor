package rocks.javezki.MathTopics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;

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
     * @return An embed that displays all math topics in an embed form
     */
    public static EmbedBuilder displayMathtopics()
    {
        
        EmbedBuilder builder = new EmbedBuilder();
        int count = 1;

        builder.setTitle("Math Topics: ");

        builder.setColor(Color.RED);

        builder.setDescription("The current list of Math Topics:");

        builder.setThumbnail("https://i.imgflip.com/3njb30.jpg");

        for (MathTopics topic : mathTopics)
        {
            builder.addField(count + ". " + topic.getTitle(), "\n \n \n" + topic.getDescription(), false);
            count++;
        }

        return builder;
    }
}
