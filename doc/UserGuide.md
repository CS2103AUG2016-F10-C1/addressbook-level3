# User Guide

This product is not meant for end-users and therefore there is no user-friendly installer. 
Please refer to the [Setting up](DeveloperGuide.md#setting-up) section to learn how to set up the project.

## Starting the program

1. Find the project in the `Project Explorer` or `Package Explorer` (usually located at the left side)
2. Right click on the project
3. Click `Run As` > `Java Application` and choose the `Main` class.
4. The GUI should appear in a few seconds.

<img src="images/Ui.png">

## Viewing help : `help`
Format: `help`

> Help is also shown if you enter an incorrect command e.g. `abcd`
 
## Adding a person: `add`
Adds a person to the address book<br>
Format: `add NAME [p]p/PHONE_NUMBER [p]e/EMAIL [p]a/ADDRESS [t/TAG]...` 
 
> Words in `UPPER_CASE` are the parameters, items in `SQUARE_BRACKETS` are optional, 
> items with `...` after them can have multiple instances. Order of parameters are fixed. 
> 
> Put a `p` before the phone / email / address prefixes to mark it as `private`. `private` details can only
> be seen using the `viewall` command.
> 
> Persons can have any number of tags (including 0)

Examples: 
* `add John Doe p/98765432 e/johnd@gmail.com a/John street, block 123, #01-01`
* `add Betsy Crowe pp/1234567 e/betsycrowe@gmail.com pa/Newgate Prison t/criminal t/friend`

## Listing all persons : `list`
Shows a list of all persons in the address book.<br>
Format: `list`

## Listing all tags: `listtag`
Shows a list of all tags in the address book.<br>
Format: `listtag`

## Renaming a tag: `renametag`
Rename a tag in the address book.<br>
Format: `renametag INDEX NEW_TAG_NAME`

> Renames the tag at the specified `INDEX`. 
  The index refers to the index number shown in the most recent tag listing.

Examples: 
* `listtag`<br>
  `renametag 1 family`<br>
  Renames the 1st tag in the results of the `listtag` command.

## Finding all persons containing any keyword in their contact details: `find`
Finds persons whose contact details contain any of the given keywords.<br>
Format: `find KEYWORD [MORE_KEYWORDS]`

> The search is case insensitive, the order of the keywords does not matter, all non-private contact details is searched, 
and persons matching at least one keyword will be returned (i.e. `OR` search).
>
> Note: To search using tags, enclose the tag in square brackets "[ ]".

Examples: 
* `find John`<br>
  Returns `John Doe`
* `find Betsy Tim John`<br>
  Returns Any person having names `Betsy`, `Tim`, or `John`
* `find John Clementi 96354323`<br>
  Returns Any person having names `John`, lives in `Clementi`, and/or whose phone number is `96354323` <br>
* `find [Friends] <br>
  Returns Any person with the `Friends` tag
  
## Sorting all persons: `sort`
Sorts the list of all persons in the address book alphabetically and shows a list of all persons in the address book.<br>
Format: `sort`   

## Editing a person: `edit`
Edits the specified person from the address book.<br>
Format: `edit INDEX n/NAME p/PHONE e/EMAIL a/ADDRESS ta/TAGTOADD tr/TAGTOREMOVE`

> Edits the person at the specified `INDEX`. 
  The index refers to the index number shown in the most recent listing.

> `INDEX` and at least one detail to edit is required.

> Details to edit can be entered in any order.

Examples:
* `list`<br>
  `edit 2 n/Jonathan Doe p/9123456`<br>
  Edits the 2nd person in the address book by changing his name and phone number.
* `list`<br>
  `edit 1 a/123 ABC Street #01-23`<br>
  Edits the 1st person in the address book by changing the address.
* `list`<br>
  `edit 1 ta/Cool tr/owesMoney`<br>
  Edits the 1st person in the address book by adding a new tag and removing an existing tag.

## Deleting a person : `delete`
Deletes the specified person from the address book. Irreversible.<br>
Format: `delete INDEX`

> Deletes the person at the specified `INDEX`. 
  The index refers to the index number shown in the most recent listing.

Examples: 
* `list`<br>
  `delete 2`<br>
  Deletes the 2nd person in the address book.
* `find Betsy`<br> 
  `delete 1`<br>
  Deletes the 1st person in the results of the `find` command.

## View non-private details of a person : `view`
Displays the non-private details of the specified person.<br>
Format: `view INDEX`

> Views the person at the specified `INDEX`. 
  The index refers to the index number shown in the most recent listing.

Examples: 
* `list`<br>
  `view 2`<br>
  Views the 2nd person in the address book.
* `find Betsy` <br> 
  `view 1`<br>
  Views the 1st person in the results of the `find` command.

## View all details of a person : `viewall`
Displays all details (including private details) of the specified person.<br>
Format: `viewall INDEX`

> Views all details of the person at the specified `INDEX`. 
  The index refers to the index number shown in the most recent listing.

Examples: 
* `list`<br>
  `viewall 2`<br>
  Views all details of the 2nd person in the address book.
* `find Betsy`<br> 
  `viewall 1`<br>
  Views all details of the 1st person in the results of the `find` command.

## Clearing all entries : `clear`
Clears all entries from the address book.<br>
Format: `clear`  

## Exiting the program : `exit`
Exits the program.<br>
Format: `exit`  

## Saving the data 
Address book data are saved in the hard disk automatically after any command that changes the data.<br>
There is no need to save manually.

## Changing the save location
Address book data are saved in a file called `addressbook.txt` in the project root folder.
You can change the location by specifying the file path as a program argument.<br>

> The file name must end in `.txt` for it to be acceptable to the program.
>
> When running the program inside Eclipse, you can 
  [set command line parameters before running the program](http://stackoverflow.com/questions/7574543/how-to-pass-console-arguments-to-application-in-eclipse).
