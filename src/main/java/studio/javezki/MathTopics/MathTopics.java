package studio.javezki.MathTopics;

import studio.javezki.Topic;

/**
 * @apiNote MAKE SURE TO ADD YOUR TOPIC BY RUNNING Topics.add(*MathTopic*) OR ELSE YOUR TOPIC WON'T RUN!
 */

public abstract class MathTopics extends Topic{
    
    private String formula;  

    /**
     * 
     * @param formula The formula for the designated formula
     * @param title The title of the actual math topic (Optional)
     * @param description The description of the topic (Optional)
     */
    public MathTopics(String title, String description, String formula) 
    {
        super(title, description);

        this.formula = formula;
    }

    /**
     * @apiNote What to run when math topic is called
     */

    public abstract void run();

    /**
     * @apiNote What to display when users click on the help emote
     */
    public abstract void help();

    public String getFormula()
    {
        return formula;
    }
}
