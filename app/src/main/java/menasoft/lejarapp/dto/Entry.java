package menasoft.lejarapp.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by Rmena on 11/1/2015.
 */
public class Entry {
    public List<Entries> results;
    public class Entries{
        public String _id;
        public Date entry_date;
        public double amount;
        public boolean paid;
        public String user;
        public String type;
        public String approved_by;
        public String generated_on_mode;
    }
}
