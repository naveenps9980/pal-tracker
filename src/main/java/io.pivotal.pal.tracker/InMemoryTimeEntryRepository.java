package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    public HashMap<Long, TimeEntry> timeEntryHashMap = new HashMap<>();
    private Long id = 0L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long identifier = ++id;
        timeEntry.setId(identifier);
        timeEntryHashMap.put(identifier, timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntryHashMap.get(id);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(timeEntryHashMap.get(id) != null) {
            timeEntry.setId(id);
            timeEntryHashMap.put(id, timeEntry);
        }
        return timeEntry;
    }

    @Override
    public void delete(long id) {
        if(timeEntryHashMap.get(id) != null) {
            timeEntryHashMap.remove(id);
        }
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryHashMap.values());
    }
}
