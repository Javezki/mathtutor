package studio.javezki;

public class Topic {

    private static String title;

    private static String description;

    public Topic(String title, String description)
    {
        Topic.title = title;
        Topic.description = description; 
    }

    
    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }
    
}
