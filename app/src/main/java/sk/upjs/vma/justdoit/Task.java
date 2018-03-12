package sk.upjs.vma.justdoit;

import java.io.Serializable;

public class Task implements Serializable {

    private Long id;

    private String name;

    private boolean isDone;

    private static final boolean IS_NOT_DONE = false;

    public Task() {
        // empty constructor
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public Task(String name) {
        this(name, IS_NOT_DONE);
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (isDone) {
            sb.append(" [hotovo]");
        }
        return sb.toString();
    }
}