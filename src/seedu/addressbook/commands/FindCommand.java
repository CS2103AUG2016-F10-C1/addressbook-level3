package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;

import java.util.*;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithNameContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieve all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> dataInPerson = fillPersonDataSet(person);
            if (!Collections.disjoint(changeToUpperCase(dataInPerson), changeToUpperCase(keywords))) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }
    
    /**
     * Consolidate all the non-private data of a person into a set
     * 
     * @param person
     * @return set of a person's data
     */
    private Set<String> fillPersonDataSet(ReadOnlyPerson person) {
        
        
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append(" ");
        if (!person.getPhone().isPrivate()) {
            builder.append(person.getPhone())
                .append(" ");
        }

        if (!person.getEmail().isPrivate()) {
            builder.append(person.getEmail())
                .append(" ");
        }
        
        if (!person.getAddress().isPrivate()) {
            builder.append(person.getAddress())
                .append(" ");
        }

        for (Tag tag : person.getTags()) {
            builder.append(tag)
                .append(" ");
        }
      
        
        Set<String> dataInPerson = new HashSet<>(Arrays.asList(builder.toString().split("\\s+")));


        return dataInPerson;
    }
    
    /**
     * Changes all words in wordsInName and keywords to Upper Case
     * 
     * @param setToChange
     * @return new set of words that are in upper case
     */
    private Set<String> changeToUpperCase(Set<String> setToChange) {
        Set<String> caseInsensitiveSet = new HashSet<String>();
        for (String word : setToChange) {
            caseInsensitiveSet.add(word.toUpperCase());
        }
        return caseInsensitiveSet;
    }

}
