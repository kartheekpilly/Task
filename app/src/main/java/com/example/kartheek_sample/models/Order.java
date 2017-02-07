package com.example.kartheek_sample.models;

import java.io.Serializable;

/**
 * Created by saisandeep on 11/7/2016.
 */
public class Order implements Serializable {

    String id;
    String currency;
    String employeeId;
    int total;
    boolean  taxRemoved;
    boolean isVat;
    String state;
    boolean manualTransaction;
    boolean groupLineItems;
    boolean testMode ;
    String createdTime;
    String clientCreatedTime;
    String modifiedTime;
    String deviceId;

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isGroupLineItems() {
        return groupLineItems;
    }

    public void setGroupLineItems(boolean groupLineItems) {
        this.groupLineItems = groupLineItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isVat() {
        return isVat;
    }

    public void setVat(boolean vat) {
        isVat = vat;
    }

    public boolean isManualTransaction() {
        return manualTransaction;
    }

    public void setManualTransaction(boolean manualTransaction) {
        this.manualTransaction = manualTransaction;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isTaxRemoved() {
        return taxRemoved;
    }

    public void setTaxRemoved(boolean taxRemoved) {
        this.taxRemoved = taxRemoved;
    }

    public boolean isTestMode() {
        return testMode;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
