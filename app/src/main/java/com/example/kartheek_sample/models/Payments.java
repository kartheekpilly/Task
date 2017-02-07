package com.example.kartheek_sample.models;

import java.io.Serializable;

public class Payments implements Serializable,Item{

    String id;
    String amount;
    String createdTime;
    String clientCreatedTime;
    String modifiedTime;
    String result;
    String orderId;
    String employeeId;
    CardTransaction cardtranscation;
    Order orders;
    Tenders tenders;


    public CardTransaction getCardtranscation() {
        return cardtranscation;
    }

    public void setCardtranscation(CardTransaction cardtranscation) {
        this.cardtranscation = cardtranscation;
    }



    public Tenders getTenders() {
        return tenders;
    }

    public void setTenders(Tenders tenders) {
        this.tenders = tenders;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getClientCreatedTime() {
        return clientCreatedTime;
    }

    public void setClientCreatedTime(String clientCreatedTime) {
        this.clientCreatedTime = clientCreatedTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean isHeader() {
        return false;
    }
}
