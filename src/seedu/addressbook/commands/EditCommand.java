package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.UniqueTagList.TagNotFoundException;


/**
 * Edit a person identified using it's last displayed index from the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n"
            + "Edits any details of a person identified by the index number used in the last person listing.\n\t"
            + "Parameters: INDEX n/NAME p/PHONE e/EMAIL a/ADDRESS\n\t"
            + "Example: " + COMMAND_WORD 
            + " 1 n/John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";

    private String[] argsToEdit;


    public EditCommand(int targetVisibleIndex, String[] argsToEdit) {
        super(targetVisibleIndex);
        this.argsToEdit = argsToEdit;
    }


    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            final ReadOnlyPerson editedTarget = addressBook.editPerson(target, argsToEdit);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedTarget));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK + Messages.MESSAGE_EXECUTE_LIST);
        } catch (IllegalValueException ive) {
            return new CommandResult(ive.getMessage());
        } catch (TagNotFoundException tnfe) {
            return new CommandResult(tnfe.getMessage());
        }
    }

}
