package studio.javezki.Commands;

import java.util.ArrayList;
import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import studio.javezki.MathTopics.MathTopics;
import studio.javezki.MathTopics.Topics;

public class Start extends Command {

    private EventWaiter waiter;

    private static CommandEvent commandEvent;

    private static MathTopics currentTopic;

    private static List<Long> helpIDs = new ArrayList<>();

    public Start(EventWaiter waiter) {
        this.waiter = waiter;
        this.name = "start";
        this.help = "Starts the math stuffs";
    }

    @Override
    protected void execute(CommandEvent event) {
        commandEvent = event;

        event.reply("Please select your topic:");
        event.reply(Topics.displayMathtopics().build());

        waiter.waitForEvent(MessageReceivedEvent.class, e -> e.getAuthor().equals(event.getAuthor())
                && e.getChannel().equals(event.getChannel())
                && !e.getMessage().equals(event.getMessage()),
                e -> {
                    String rawMessage = e.getMessage().getContentRaw().toLowerCase();

                    for (MathTopics topic : Topics.getMathtopics()) {
                        if (rawMessage.equals(topic.getTitle())) {
                            event.reply("Starting: " + topic.getTitle());
                            addReaction();
                            topic.run();
                            currentTopic = topic;
                            return;
                        }
                    }

                    try {
                        MathTopics topic = Topics.getMathtopics().get(Integer.parseInt(rawMessage) - 1);
                        addReaction();
                        currentTopic = topic;
                        topic.run();
                    } catch (NumberFormatException ex) {
                        event.reply("Please list the number or the name of the topic!");
                    } catch (IndexOutOfBoundsException ex) {
                        event.reply("Not a valid option!");
                    }
                });
    }

    private void addReaction() {

        long messageId = commandEvent.getChannel().getLatestMessageIdLong();

        helpIDs.add(messageId);

        commandEvent.getChannel().addReactionById(messageId, "U+2753").queue();
    }

    public static CommandEvent getCommandEvent() {
        return commandEvent;
    }

    public static MathTopics getCurrentTopic() {
        return currentTopic;
    }

    public static List<Long> getHelpIDs() {
        return helpIDs;
    }

    public static void removeHelpId(int instance)
    {
        helpIDs.remove(instance);
    }

}