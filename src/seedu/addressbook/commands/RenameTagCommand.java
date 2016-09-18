package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.tag.ReadOnlyTag;
import seedu.addressbook.data.tag.UniqueTagList.TagNotFoundException;

/**
 * Renames a tag identified using it's last displayed index from the address book.
 */
public class RenameTagCommand extends Command {

    public static final String COMMAND_WORD = "renametag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Rename the tag identified by the index number used in the last tag listing.\n\t"
            + "Parameters: INDEX NEW_TAG_NAME\n\t" + "Example: " + COMMAND_WORD + " 1 family";

    public static final String MESSAGE_RENAME_TAG_SUCCESS = "Renamed Tag: %1$s";

    private String newTagName;

    public RenameTagCommand(int targetVisibleIndex, String newTagName) {
        super(targetVisibleIndex);
        this.newTagName = newTagName;
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyTag target = getTargetTag();
            addressBook.renameTag(target, newTagName);
            return new CommandResult(String.format(MESSAGE_RENAME_TAG_SUCCESS, target));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
        } catch (TagNotFoundException tnfe) {
            return new CommandResult(Messages.MESSAGE_TAG_NOT_IN_ADDRESSBOOK);
        } catch (IllegalValueException e) {
            return new CommandResult(Messages.MESSAGE_INVALID_TAG_NAME);
        }
    }

}
