/**
* @author Orel Lichtenstein
* @version 1.8
* @since 2019-06-21
* @param <T> - the type of the task.
* */
public interface Task<T> {
    /**
     * Run the task.
     * @return playing the task.
     */
    T run();
}
