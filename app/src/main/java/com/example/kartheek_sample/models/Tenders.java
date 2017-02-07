package com.example.kartheek_sample.models;

import java.io.Serializable;

public class Tenders implements Serializable {
    String id;
    boolean editable;
    boolean enabled;
    boolean visible;
    boolean opensCashDrawer;
    String label;
    String labelKey;

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    public boolean isOpensCashDrawer() {
        return opensCashDrawer;
    }

    public void setOpensCashDrawer(boolean opensCashDrawer) {
        this.opensCashDrawer = opensCashDrawer;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
