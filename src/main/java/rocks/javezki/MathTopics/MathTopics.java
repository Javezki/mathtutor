package rocks.javezki.MathTopics;



/**
 * @apiNote MAKE SURE TO ADD YOUR TOPIC BY RUNNING Topics.add(*MathTopic*) OR ELSE YOUR TOPIC WON'T RUN!
 */

public abstract class MathTopics {

    private String title;
    private String description;  

    /**
     * 
     * @param waiter The event waiter initialized in main class so u can do fancy stuff
     * @param title The title of the actual math topic (Optional)
     * @param description The description of the topic (Optional)
     */
    public MathTopics(String title, String description) 
    {
        this.title = title;
        this.description = description; 
    }

    public abstract void run();
    

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }
}
