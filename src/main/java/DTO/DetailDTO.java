package DTO;

import java.util.ArrayList;

public class DetailDTO extends ProductDTO {
    private ArrayList<Object> historyDTOS;
    private ArrayList<Object> commentDTOS;

    public DetailDTO(String id, String name, String image, String id_item, int price, ArrayList<Object> historyDTOS, ArrayList<Object> commentDTOS) {
        super(id, name, image, id_item, price);
        this.historyDTOS = historyDTOS;
        this.commentDTOS = commentDTOS;
    }

    public ArrayList<Object> getHistoryDTOS() {
        return historyDTOS;
    }

    public void setHistoryDTOS(ArrayList<Object> historyDTOS) {
        this.historyDTOS = historyDTOS;
    }

    public ArrayList<Object> getCommentDTOS() {
        return commentDTOS;
    }

    public void setCommentDTOS(ArrayList<Object> commentDTOS) {
        this.commentDTOS = commentDTOS;
    }
}
