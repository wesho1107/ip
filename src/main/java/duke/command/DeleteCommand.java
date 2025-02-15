package duke.command;

import duke.exception.InvalidTaskNumberException;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import duke.task.Task;

import java.io.IOException;

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand object with the index of a specified task.
     *
     * @param index The index of the specified task to be deleted.
     */
    public DeleteCommand(int index) {
        super(null);
        this.index = index;
    }

    /**
     * Executes the command to delete a task from the task list, update storage, and display a success message.
     *
     * @param tasks The task list from which the task should be deleted.
     * @param ui The user interface for displaying messages.
     * @param storage The storage object for saving tasks to a file.
     * @throws IOException If there's an error while updating the storage.
     * @throws InvalidTaskNumberException If an invalid task number is encountered during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (!isValidIndex(index, tasks)) {
            throw new InvalidTaskNumberException();
        }
        Task task = tasks.getTaskAtIndex(this.index);
        tasks.remove(task);
        storage.update(tasks.getTasks());
        ui.showDeleteSuccess(task.toString());
    }
}
