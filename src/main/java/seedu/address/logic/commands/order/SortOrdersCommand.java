package seedu.address.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDERING;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.sort.SortDescriptor;
import seedu.address.model.sort.SortField;
import seedu.address.model.sort.SortOrdering;

/**
 * Sorts orders in the address book based in either ascending or descending order based on the specified field.
 */
public class SortOrdersCommand extends Command {

    public static final String COMMAND_WORD = "sortorders";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all orders by a chosen field "
            + "in either ascending or descending order and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_SORT_FIELD + "FIELD "
            + PREFIX_SORT_ORDERING + "[ORDERING] "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORT_FIELD + "amount "
            + PREFIX_SORT_ORDERING + "desc";

    public static final String MESSAGE_SUCCESS = "Sorted all orders";

    private final SortField sortField;
    private final SortOrdering sortOrdering;

    /**
     * Creates an SortOrdersCommand to sort the orders by a specified {@code SortField} and {@code SortOrdering}
     */
    public SortOrdersCommand(SortField sortField, SortOrdering sortOrdering) {
        this.sortField = requireNonNull(sortField);
        this.sortOrdering = requireNonNull(sortOrdering);
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        SortDescriptor sortDescriptor = new SortDescriptor(sortField, sortOrdering);
        model.sortOrderList(sortDescriptor);
        String successMessage = sortDescriptor.generateSuccessMessage();
        return new CommandResult(successMessage, CommandResult.DisplayState.ORDER);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortOrdersCommand)) {
            return false;
        }

        // state check
        SortOrdersCommand e = (SortOrdersCommand) other;

        return sortField.equals(e.sortField) && sortOrdering.equals(e.sortOrdering);
    }
}

