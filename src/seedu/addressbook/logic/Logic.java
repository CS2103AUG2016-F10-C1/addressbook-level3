package seedu.addressbook.logic;

import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.ReadOnlyTag;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.storage.StorageFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents the main Logic of the AddressBook.
 */
public class Logic {


    private StorageFile storage;
    private AddressBook addressBook;

    /** The list of person shown to the user most recently. */
    private List<? extends ReadOnlyPerson> lastShownPersonList = Collections.emptyList();
    
    /** The list of tag shown to the user most recently. */
    private List<? extends ReadOnlyTag> lastShownTagList = Collections.emptyList();

    public Logic() throws Exception{
        setStorage(initializeStorage());
        setAddressBook(storage.load());
    }

    Logic(StorageFile storageFile, AddressBook addressBook){
        setStorage(storageFile);
        setAddressBook(addressBook);
    }

    void setStorage(StorageFile storage){
        this.storage = storage;
    }

    void setAddressBook(AddressBook addressBook){
        this.addressBook = addressBook;
    }

    /**
     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
     * @throws StorageFile.InvalidStorageFilePathException if the target file path is incorrect.
     */
    private StorageFile initializeStorage() throws StorageFile.InvalidStorageFilePathException {
        return new StorageFile();
    }

    public String getStorageFilePath() {
        return storage.getPath();
    }

    /**
     * Unmodifiable view of the current last shown person list.
     */
    public List<ReadOnlyPerson> getLastShownPersonList() {
        return Collections.unmodifiableList(lastShownPersonList);
    }
    
    /**
     * Unmodifiable view of the current last shown tag list.
     */
    public List<ReadOnlyTag> getLastShownTagList() {
        return Collections.unmodifiableList(lastShownTagList);
    }

    protected void setLastShownPersonList(List<? extends ReadOnlyPerson> newList) {
        lastShownPersonList = newList;
    }
    
    protected void setLastShownTagList(List<? extends ReadOnlyTag> newList) {
        lastShownTagList = newList;
    }

    /**
     * Parses the user command, executes it, and returns the result.
     * @throws Exception if there was any problem during command execution.
     */
    public CommandResult execute(String userCommandText) throws Exception {
        Command command = new Parser().parseCommand(userCommandText);
        CommandResult result = execute(command);
        recordResult(result);
        return result;
    }

    /**
     * Executes the command, updates storage, and returns the result.
     *
     * @param command user command
     * @return result of the command
     * @throws Exception if there was any problem during command execution.
     */
    private CommandResult execute(Command command) throws Exception {
        command.setData(addressBook, lastShownPersonList, lastShownTagList);
        CommandResult result = command.execute();
        storage.save(addressBook);
        return result;
    }

    /** Updates the {@link #lastShownList} if the result contains a list of Persons or a list of Tags. */
    private void recordResult(CommandResult result) {
        final Optional<List<? extends ReadOnlyPerson>> personList = result.getRelevantPersons();
        final Optional<List<? extends ReadOnlyTag>> tagList = result.getRelevantTags();
        if (personList.isPresent()) {
            lastShownPersonList = personList.get();
        }
        if (tagList.isPresent()) {
            lastShownTagList = tagList.get();
        }
    }
}
