package seedu.addressbook.commands;

import java.util.List;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.ReadOnlyTag;

/**
 * Lists all distinct tags in the address book.
 */
public class ListTagCommand extends Command {

    public static final String COMMAND_WORD = "listtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Displays all distinct tags in the address book as a list with index numbers.\n\t"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyTag> allTags = addressBook.getAllTags().immutableListView();
        return new CommandResult(getMessageForTagListShownSummary(allTags), null, allTags);
    }

}
