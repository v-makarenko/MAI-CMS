package ru.vmakarenko.entities.events.financial;

/**
 * Created by VMakarenko on 14.12.2015.
 */
public enum FinancialDocumentStatus {
    REQUESTED("asked", "Требуется документ"), ACCEPTED("accepted", "Документ принят"), ERROR("errot", "Ошибка в документах");

    private String name, description;

    FinancialDocumentStatus(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

}
