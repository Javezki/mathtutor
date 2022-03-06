package rocks.javezki;

import javax.security.auth.login.LoginException;


import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import rocks.javezki.Commands.Start;
import rocks.javezki.MathTopics.QuadraticTransformations;
import rocks.javezki.MathTopics.Topics;

/**
 * Hello world!
 *
 */
public class MathMain 
{
    private static EventWaiter waiter;
    public static void main( String[] args )
    {   
        Topics.addMathTopic(

            new QuadraticTransformations()

            );
        waiter = new EventWaiter();

        CommandClientBuilder builder = new CommandClientBuilder();

        builder.setPrefix(">");

        builder.setAlternativePrefix("+");

        builder.setOwnerId("295280700368617473");

        builder.addCommand(new Start(waiter));

        builder.setStatus(OnlineStatus.ONLINE);

        builder.setActivity(Activity.listening("students cries for help"));

        try {
            JDABuilder.createLight(DiscordKeys.getTestToken())
            .addEventListeners(waiter, builder.build(), new OnHelp())
            .build();
        } catch (LoginException e) {
            System.out.println("\u001B[35m" + "Error logging into bot! (Did you use the correct keys?)" + "\u001B[0m");
        }
    }

    public static EventWaiter getWaiter()
    {
        return waiter;
    }
}
