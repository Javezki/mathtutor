package rocks.javezki.MathTopics;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import rocks.javezki.MathMain;
import rocks.javezki.Commands.Start;

public class QuadraticTransformations extends MathTopics {

    private static int type = 0;

    private static int vertStretch = 0;

    private static int horStretch = 0;

    private static int vertDisplacement = 0;

    private static int horDisplacement = 0;

    private static EventWaiter waiter;

    private static CommandEvent event;

    public QuadraticTransformations() {
        super("Quadratic Transformations", "Practice for quadratic transformations");
        waiter = MathMain.getWaiter();
        event = Start.getCommandEvent();
    }

    @Override
    public void run() {
        event.reply("Starting: Quadratic Transformations");

        type = new Random().nextInt(0, 4);

        setType(type);

        if (type == 0) straightLine();

        if (type == 1) parabola();

        else if (type == 2) radical();

        else if (type == 4) absolute();;
    }

    /**
     * @apiNote Generates random numbers between -20, 20 for all numbers
     */

    private void generateNumbers() {
        Random rand = new Random();

        while (vertStretch == 0)
            vertStretch = rand.nextInt(-20, 20);

        while (horStretch == 0)
            horStretch = rand.nextInt(-20, 20);

        vertDisplacement = rand.nextInt(-20, 20);

        horDisplacement = rand.nextInt(-20, 20);
    }

    public static void setType(int type) {
        QuadraticTransformations.type = type;
    }

    /**
     * 
     * @return 0 - Straight Line | 1 - Parabola | 2 - Radical | 3 - Reciprocal | 4 -
     *         Absolute
     * 
     */
    public static int getType() {
        return type;
    }

    /**
     * 
     * @return Get Vertical Stretch of generated numbers
     */
    public static int getVertStretch() {
        return vertStretch;
    }

    /**
     * 
     * @return Get Horizontal Stretch of generated numbers
     */
    public static int getHorStretch() {
        return horStretch;
    }

    /**
     * 
     * @return Get Vertical Displacement of generated numbers
     */
    public static int getVertDisplacement() {
        return vertDisplacement;
    }

    /**
     * 
     * @return Get Horizontal Displacement of generated numbers
     */
    public static int getHorDisplacement() {
        return horDisplacement;
    }

    public void absolute() {
        generateNumbers();

        event.reply("The equation: \n \n " + vertStretch + "(" + horStretch + "(|x| + " + vertDisplacement + ")) + "
                + horDisplacement);

        startTransformations();
    }

    public void reciprocal() {
        generateNumbers();

        event.reply("The equation: \n \n" + vertStretch + "(1/x + " + vertDisplacement + ") + " + horDisplacement);

        startTransformations();
    }

    public void radical() {
        generateNumbers();

        event.reply("The equation: \n \n " + vertStretch + "(" + horStretch + "(√x + " + vertDisplacement + ")) + "
                + horDisplacement);

        startTransformations();
    }

    public void parabola() {
        generateNumbers();

        event.reply("The equation: \n \n" + vertStretch + "(" + horStretch + "(x + " + vertDisplacement + "))² + "
                + horDisplacement);

        startTransformations();
    }

    public void straightLine() {
        generateNumbers();

        event.reply("The equation: \n \n" + vertStretch + "(x + " + vertDisplacement + ") + " + horDisplacement);

        startTransformations();
    }

    private void horStretch() {
        if (type == 0) {
            vertDisplacement();
            return;
        }

        if (type == 3) {
            vertDisplacement();
            return;
        }

        event.reply("What is the horizontal stretch of the equation?");

        waiter.waitForEvent(MessageReceivedEvent.class, e -> e.getAuthor().equals(event.getAuthor())
                && e.getChannel().equals(event.getChannel())
                && !e.getMessage().equals(event.getMessage()), e -> {
                    if (e.getMessage().getContentRaw().equals(Integer.toString(horStretch)))
                        event.reply("Correct!");
                    else
                        event.reply("Incorrect! The answer is: " + horStretch);
                    vertDisplacement();
                }, 1, TimeUnit.MINUTES, () -> event.reply("You took too long!"));
    }

    private void vertDisplacement() {

        vertDisplacement = vertDisplacement * -1;

        event.reply("What is the vertical displacement of the equation?");
        waiter.waitForEvent(MessageReceivedEvent.class, e -> e.getAuthor().equals(event.getAuthor())
                && e.getChannel().equals(event.getChannel())
                && !e.getMessage().equals(event.getMessage()), e -> {
                    if (e.getMessage().getContentRaw().equals(Integer.toString(vertDisplacement)))
                        event.reply("Correct!");
                    else
                        event.reply("Incorrect! The answer is: " + vertDisplacement);

                    horDisplacement();
                });
    }

    private void horDisplacement() {

        event.reply("What is the horizontal displacement of the equation?");
        waiter.waitForEvent(MessageReceivedEvent.class, e -> e.getAuthor().equals(event.getAuthor())
                && e.getChannel().equals(event.getChannel())
                && !e.getMessage().equals(event.getMessage()), e -> {
                    if (e.getMessage().getContentRaw().equals(Integer.toString(horDisplacement)))
                        event.reply("Correct!");
                    else
                        event.reply("Incorrect! The answer is: " + horDisplacement);
                    event.reply("Good job you did idk lololol");
                }, 1, TimeUnit.MINUTES, () -> event.reply("Sorry you took too long!"));
    }

    private void vertStretch() {

        event.reply("What is the vertical stretch of the equation?");
        waiter.waitForEvent(MessageReceivedEvent.class, e -> e.getAuthor().equals(event.getAuthor())
                && e.getChannel().equals(event.getChannel())
                && !e.getMessage().equals(event.getMessage()), e -> {
                    if (e.getMessage().getContentRaw().equals(Integer.toString(vertStretch)))
                        event.reply("Correct!");
                    else
                        event.reply("Incorrect! The answer is: " + vertStretch);
                    horStretch();
                }, 1, TimeUnit.MINUTES, () -> event.reply("Sorry you took too long!"));
    }

    private void startTransformations() {

        event.reply("What is the type of this equation?");

        waiter.waitForEvent(MessageReceivedEvent.class, e -> e.getAuthor().equals(event.getAuthor())
                && e.getChannel().equals(event.getChannel())
                && !e.getMessage().equals(event.getMessage()), e -> {
                    if (e.getMessage().getContentRaw().equalsIgnoreCase(getStringType()))
                        event.reply("Correct!");
                    else
                        event.reply("Wrong! The answer is: " + getStringType());
                    vertStretch();
                }, 1, TimeUnit.MINUTES, () -> event.reply("Sorry you took too long!"));
    }

    private String getStringType() {
        if (type == 0)
            return "STRAIGHT";
        else if (type == 1)
            return "PARABOLA";
        else if (type == 2)
            return "RADICAL";
        else if (type == 3)
            return "RECIPROCAL";
        else if (type == 4)
            return "ABSOLUTE";

        return null;
    }

}
