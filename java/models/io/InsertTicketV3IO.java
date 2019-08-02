package models.io;

import java.util.List;

/**
 * Created by dao on 31/07/2018.
 */
public class InsertTicketV3IO {
    public Long id;
    public List<Long> events;
    public boolean isFinished;
    public String begin;
    public String end;
    public Long eventCount;
    public boolean notifyExternal;
}
