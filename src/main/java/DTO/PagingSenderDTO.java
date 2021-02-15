package DTO;

public class PagingSenderDTO extends SenderDTO{
    private int total = 0;
    private int per_page = 0;
    private int current_page = 0;
    private int last_page = 0;

    public PagingSenderDTO(Object data, boolean status, int total, int per_page, int current_page, int last_page) {
        super(data, status);
        this.total = total;
        this.per_page = per_page;
        this.current_page = current_page;
        this.last_page = last_page;

        this.put("total",total);
        this.put("per_page",per_page);
        this.put("current_page",current_page);
        this.put("last_page",last_page);
    }
}
