package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.client.AddCommand;
import seedu.address.logic.commands.order.AddOrderCommand;
import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.client.DeleteCommand;
import seedu.address.logic.commands.order.DeleteOrderCommand;
import seedu.address.logic.commands.task.DeleteTaskCommand;
import seedu.address.logic.commands.client.EditCommand;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.client.FindCommand;
import seedu.address.logic.commands.order.FindOrderCommand;
import seedu.address.logic.commands.task.FindTaskCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.client.ListCommand;
import seedu.address.logic.commands.order.ListOrderCommand;
import seedu.address.logic.commands.task.ListTaskCommand;
import seedu.address.logic.commands.order.MarkOrderCommand;
import seedu.address.logic.commands.task.MarkTaskCommand;
import seedu.address.logic.commands.order.ShowCompletedOrders;
import seedu.address.logic.commands.task.ShowCompletedTasks;
import seedu.address.logic.commands.order.ShowIncompleteOrders;
import seedu.address.logic.commands.task.ShowIncompleteTasks;
import seedu.address.logic.commands.order.SortOrdersCommand;
import seedu.address.logic.commands.order.TotalOrdersCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class SalesNoteParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        //======================================== PERSON COMMANDS ========================================
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        //======================================== TASK COMMANDS ========================================

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case MarkTaskCommand.COMMAND_WORD:
            return new MarkTaskCommandParser().parse(arguments);

        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();

        case ShowCompletedTasks.COMMAND_WORD:
            return new ShowCompletedTasks();

        case ShowIncompleteTasks.COMMAND_WORD:
            return new ShowIncompleteTasks();

        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommandParser().parse(arguments);

        //======================================== ORDER COMMANDS ========================================

        case MarkOrderCommand.COMMAND_WORD:
            return new MarkOrderCommandParser().parse(arguments);

        case AddOrderCommand.COMMAND_WORD:
            return new AddOrderCommandParser().parse(arguments);

        case ShowCompletedOrders.COMMAND_WORD:
            return new ShowCompletedOrders();

        case ShowIncompleteOrders.COMMAND_WORD:
            return new ShowIncompleteOrders();

        case DeleteOrderCommand.COMMAND_WORD:
            return new DeleteOrderCommandParser().parse(arguments);

        case TotalOrdersCommand.COMMAND_WORD:
            return new TotalOrdersCommand();

        case SortOrdersCommand.COMMAND_WORD:
            return new SortOrdersCommandParser().parse(arguments);

        case FindOrderCommand.COMMAND_WORD:
            return new FindOrderCommandParser().parse(arguments);

        case ListOrderCommand.COMMAND_WORD:
            return new ListOrderCommand();


        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
