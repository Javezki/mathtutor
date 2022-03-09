package studio.javezki.MathTopics;



/**
 * @apiNote MAKE SURE TO ADD YOUR TOPIC BY RUNNING Topics.add(*MathTopic*) OR ELSE YOUR TOPIC WON'T RUN!
 */

public abstract class MathTopics {

    private String title;
    private String description;
    private String formula;  

    /**
     * 
     * @param 
     * @param title The title of the actual math topic (Optional)
     * @param description The description of the topic (Optional)
     */
    public MathTopics(String formula, String title, String description) 
    {
        this.formula = formula;
        this.title = title;
        this.description = description; 
    }

    public abstract void run();

    /**
     * @apiNote What to display when users click on the help emote
     */
    public abstract void help();
    

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public String getFormula()
    {
        return formula;
    }
}
