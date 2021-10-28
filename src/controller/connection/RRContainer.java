package controller.connection;

import java.util.Map;

public class RRContainer {
    public String header;
    public Map<String, Object> model;

    public RRContainer(String header, Map<String, Object> model) {
        this.header = header;
        this.model = model;
    }
}
