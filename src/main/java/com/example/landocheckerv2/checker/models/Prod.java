package com.example.landocheckerv2.checker.models;

import java.util.List;
import java.util.regex.Pattern;

public class Prod {
    private String label;
    private String[] symbols;
    private Pattern regex;
    private Boolean isTerminal;
    private Boolean isRegex;
    private List<Prod[]> children;

    public Prod() {
        this.isTerminal = false;
        this.isRegex = false;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String[] getSymbols() {
        return symbols;
    }

    public void setSymbols(String... symbols) {
        this.symbols = symbols;
    }

    public Pattern getRegex() {
        return regex;
    }

    public void setRegex(Pattern regex) {
        this.regex = regex;
    }

    public Boolean getIsTerminal() {
        return isTerminal;
    }

    public void setIsTerminal(Boolean terminal) {
        isTerminal = terminal;
    }

    public Boolean getIsRegex() {
        return isRegex;
    }

    public void setIsRegex(Boolean regex) {
        isRegex = regex;
    }

    public List<Prod[]> getChildren() {
        return children;
    }

    public void setChildren(List<Prod[]> children) {
        this.children = children;
    }
}
