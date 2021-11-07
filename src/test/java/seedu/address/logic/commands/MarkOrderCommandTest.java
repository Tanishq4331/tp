package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.ModelStub;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.order.MarkOrderCommand;
import seedu.address.model.order.Order;
import seedu.address.testutil.OrderBuilder;

class MarkOrderCommandTest {

    private static final Order testOrder = new OrderBuilder().build();

    @Test
    public void execute_validIndexMarkOrder_success() throws Exception {
        Index targetIndex = Index.fromOneBased(1);
        ModelStubWithOneOrder modelStub = new ModelStubWithOneOrder();

        assertFalse(testOrder.getIsComplete());
        CommandResult commandResult = new MarkOrderCommand(targetIndex).execute(modelStub);

        assertTrue(testOrder.getIsComplete());
        assertEquals(String.format(MarkOrderCommand.MESSAGE_MARK_ORDER_SUCCESS, testOrder),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(2);
        ModelStubWithOneOrder modelStub = new ModelStubWithOneOrder();
        MarkOrderCommand markOrderCommand = new MarkOrderCommand(outOfBoundIndex);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX, (
        ) -> markOrderCommand.execute(modelStub));

    }

    @Test
    public void execute_markAlreadyCompleted_notification() throws Exception {
        Index targetIndex = Index.fromOneBased(1);
        ModelStubWithOneOrder modelStub = new ModelStubWithOneOrder();

        // mark the order twice, verify that notification is given.
        new MarkOrderCommand(targetIndex).execute(modelStub);
        CommandResult commandResult = new MarkOrderCommand(targetIndex).execute(modelStub);

        assertEquals(String.format(MarkOrderCommand.MESSAGE_ORDER_ALREADY_MARKED, testOrder),
                commandResult.getFeedbackToUser());
    }

    private class ModelStubWithOneOrder extends ModelStub {
        private final ObservableList<Order> listWithOneOrder = FXCollections.observableArrayList(testOrder);

        @Override
        public boolean markOrder(Order order) {
            return listWithOneOrder.get(0).markCompleted();
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            return listWithOneOrder;
        }
    }

}
