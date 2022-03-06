package studio.javezki.MathTopics;

import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import studio.javezki.MathMain;
import studio.javezki.Commands.Start;

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
    }

    @Override
    public void run() {

        waiter = MathMain.getWaiter();
        event = Start.getCommandEvent();

        java.util.Random rand = new java.util.Random();
        type = rand.nextInt(4);

        setType(type);

        generateNumbers();

        if (type == 0)
            straightLine();

        if (type == 1)
            parabola();

        else if (type == 2)
            radical();

        else if (type == 3)
            reciprocal();

        else if (type == 4)
            absolute();

        startTransformations();
    }

    @Override
    public void help() {
        
        EmbedBuilder builder = new EmbedBuilder();

        builder.setTitle("All you need to know about transformations");
        builder.setDescription("__**a[k(x - d)] + c**__");

        builder.addBlankField(false);

        String vertStretchDescription = "The vertical stretch is how much f(x) is being multiplied vertically" +
        " and is usually represented as the a in the main transformation formula.";

        builder.addField("The Vertical Stretch", vertStretchDescription, false);

        builder.addBlankField(false);
        
        String horStretchDescription = "The horizontal stretch is how much f(x) is being multipled horizontally" +
        " and is usually represented as the k in the main transformation formula." + 
        " I'm going to be honest, I'm not really sure how this one works";

        builder.addField("The Horizontal Stretch", horStretchDescription, false);
        
        builder.addBlankField(false);

        String vertDisplacementDescription = "The vertical displacement is how much f(x) is shifted on the x-axis." +
        " The direction is determined by the sign (+ = to the left, - = to the right). " +
        "Be sure to remember how the direction is the opposite to the sign on the number line.";

        builder.addField("The Vertical Displacement", vertDisplacementDescription, false);
        
        builder.addBlankField(false);

        String horDisplacementDescription = "The horizontal displacement is how much f(x) is shifted on the y-axes." +
        " The direction is determined by the sign (+ = shifted upwards, - = shifted downwards)." +
        " The signs are not swapped in this instance.";

        builder.addField("The Horizontal Displacement", horDisplacementDescription, false);

        builder.setThumbnail("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdqy3bxISAYtfHg9lWKo3gyKLTeyAfRS-T4A&usqp=CAU");

        event.getChannel().sendMessageEmbeds(builder.build()).queue();

    }

    /**
     * @apiNote Generates random numbers between -20, 20 for all numbers
     */

    private void generateNumbers() {
        java.util.Random rand = new java.util.Random();

        while (vertStretch == 0)
            vertStretch = rand.nextInt(40) - 20;

        while (horStretch == 0)
            horStretch = rand.nextInt(40) - 20;

        vertDisplacement = rand.nextInt(40) - 20;

        horDisplacement = rand.nextInt(40) - 20;
    }

    private static void setType(int type) {
        QuadraticTransformations.type = type;
    }

    private void absolute() {

        event.reply("`The equation: \n \n " + vertStretch + "(" + horStretch + "(|x| + " + vertDisplacement + ")) + "
                + horDisplacement + "`");
    }

    private void reciprocal() {

        event.reply(
                "`The equation: \n \n" + vertStretch + "(1/x + " + vertDisplacement + ") + " + horDisplacement + "`");
    }

    private void radical() {

        event.reply("`The equation: \n \n " + vertStretch + "(" + horStretch + "(√x + " + vertDisplacement + ")) + "
                + horDisplacement + "`");
    }

    private void parabola() {

        event.reply("`The equation: \n \n" + vertStretch + "(" + horStretch + "(x + " + vertDisplacement + "))² + "
                + horDisplacement + "`");
    }

    private void straightLine() {

        event.reply("`The equation: \n \n" + vertStretch + "(x + " + vertDisplacement + ") + " + horDisplacement + "`");
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
                    else if (e.getMessage().getContentRaw().equalsIgnoreCase("stop")) {
                        event.reply("Stopping!");
                        return;
                    } else
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
                    else if (e.getMessage().getContentRaw().equalsIgnoreCase("stop")) {
                        event.reply("Stopping!");
                        return;
                    } else
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
                    else if (e.getMessage().getContentRaw().equalsIgnoreCase("stop")) {
                        event.reply("Stopping!");
                        return;
                    } else
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
                    else if (e.getMessage().getContentRaw().equalsIgnoreCase("stop")) {
                        event.reply("Stopping!");
                        return;
                    } else
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
                    else if (e.getMessage().getContentRaw().equalsIgnoreCase("stop")) {
                        event.reply("Stopping!");
                        return;
                    } else
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
