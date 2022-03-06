package rocks.javezki;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import rocks.javezki.Commands.Start;

public class OnHelp extends ListenerAdapter{
    
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent ev)
    {
        if (ev.getUser().isBot()) return;
        int instance = 0;
        for (long helpID : Start.getHelpIDs())
        {
            if (ev.getMessageIdLong() == helpID)
            {
                Start.getCurrentTopic().help();
                Start.removeHelpId(instance);
            }
            instance++;
        }
    }
}
