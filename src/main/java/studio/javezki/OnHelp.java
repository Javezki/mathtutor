package studio.javezki;

import java.util.HashMap;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import studio.javezki.Commands.Start;
import studio.javezki.MathTopics.MathTopics;

public class OnHelp extends ListenerAdapter{
    
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent ev)
    {
        if (ev.getUser().isBot()) return;
        
        HashMap<Long, MathTopics> helpIds = Start.getHelpIDs();

        if (!helpIds.containsKey(ev.getMessageIdLong())) return;

        helpIds.get(ev.getMessageIdLong()).help();
    }
}
