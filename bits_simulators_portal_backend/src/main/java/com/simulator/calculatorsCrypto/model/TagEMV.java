package com.simulator.calculatorsCrypto.model;

public class TagEMV {
    String tag;
    String name;
    String length;
    String value;

    public TagEMV() {
        super();
    }
    public TagEMV(String tag, String length, String value) {
        super();
        this.tag = tag;
        this.name = TagDescriptionList.getTagName(tag);
        this.length = length;
        this.value = value;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        this.length = length;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        // return "TagEMV [tag=" + tag + ", length=" + length + ", value=" + value + "]";
        return "tag=" + tag + ", length=" + length + ", value=" + value + ".";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
