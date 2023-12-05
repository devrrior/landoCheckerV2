package com.example.landocheckerv2.checker;

import com.example.landocheckerv2.checker.models.Prod;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Checker {
    private String logs = "";

    public Stack<Prod> getInitialStack() {
        Prod L = new Prod();
        L.setLabel("L");
        L.setRegex(Pattern.compile("^[a-zA-Z]+$"));
        L.setIsRegex(true);
        L.setIsTerminal(true);

        Prod D = new Prod();
        D.setLabel("D");
        D.setRegex(Pattern.compile("^[0-9]+$"));
        D.setIsRegex(true);
        D.setIsTerminal(true);

        Prod A = new Prod();
        A.setLabel("A");
        A.setSymbols(":=");
        A.setIsTerminal(true);

        Prod P = new Prod();
        P.setLabel("P");
        P.setSymbols(".");
        P.setIsTerminal(true);

        Prod PW = new Prod();
        PW.setLabel("PW");
        PW.setSymbols(";");
        PW.setIsTerminal(true);

        Prod CM = new Prod();
        CM.setLabel("CM");
        CM.setRegex(Pattern.compile("^[\"]$"));
        CM.setIsRegex(true);
        CM.setIsTerminal(true);

        Prod B = new Prod();
        B.setLabel("B");
        B.setRegex(Pattern.compile("^(true|false)$"));
        B.setIsRegex(true);
        B.setIsTerminal(true);

        Prod R = new Prod();
        R.setLabel("R");
        R.setChildren(Stream.<Prod[]>of(
                new Prod[]{L, R},
                new Prod[]{D, R},
                new Prod[]{L},
                new Prod[]{D}
        ).toList());

        Prod F = new Prod();
        F.setLabel("F");
        F.setChildren(Stream.<Prod[]>of(
                new Prod[]{D, F},
                new Prod[]{D}
        ).toList());

        Prod T7 = new Prod();
        T7.setLabel("T7");
        T7.setChildren(Stream.<Prod[]>of(
                new Prod[]{P, D}
        ).toList());

        Prod T6 = new Prod();
        T6.setLabel("T6");
        T6.setChildren(Stream.<Prod[]>of(
                new Prod[]{F, T7}
        ).toList());

        Prod T5 = new Prod();
        T5.setLabel("T5");
        T5.setChildren(Stream.<Prod[]>of(
                new Prod[]{D, F}
        ).toList());

        Prod T4 = new Prod();
        T4.setLabel("T4");
        T4.setChildren(Stream.<Prod[]>of(
                new Prod[]{P, T5}
        ).toList());

        Prod T3 = new Prod();
        T3.setLabel("T3");
        T3.setChildren(Stream.<Prod[]>of(
                new Prod[]{F, T4}
        ).toList());

        Prod T2 = new Prod();
        T2.setLabel("T2");
        T2.setChildren(Stream.<Prod[]>of(
                new Prod[]{R, CM}
        ).toList());

        Prod T = new Prod();
        T.setLabel("T");
        T.setChildren(Stream.<Prod[]>of(
                new Prod[]{CM, T2},
                new Prod[]{CM, CM},
                new Prod[]{D, F},
                new Prod[]{D},
                new Prod[]{D, T3},
                new Prod[]{D, T4},
                new Prod[]{D, T6},
                new Prod[]{D, T7},
                new Prod[]{B}
        ).toList());

        Prod DV4 = new Prod();
        DV4.setLabel("DV4");
        DV4.setChildren(Stream.<Prod[]>of(
                new Prod[]{T, PW}
        ).toList());

        Prod DV3 = new Prod();
        DV3.setLabel("DV3");
        DV3.setChildren(Stream.<Prod[]>of(
                new Prod[]{A, DV4}
        ).toList());

        Prod DV2 = new Prod();
        DV2.setLabel("DV2");
        DV2.setChildren(Stream.<Prod[]>of(
                new Prod[]{R, DV3}
        ).toList());

        Prod DV = new Prod();
        DV.setLabel("DV");
        DV.setChildren(Stream.<Prod[]>of(
                new Prod[]{L, DV2},
                new Prod[]{L, DV3}
        ).toList());

        Prod VA = new Prod();
        VA.setLabel("VA");
        VA.setChildren(Stream.<Prod[]>of(
                new Prod[]{L, R},
                new Prod[]{L}
        ).toList());

        Prod RE2 = new Prod();
        RE2.setLabel("RE2");
        RE2.setChildren(Stream.<Prod[]>of(
                new Prod[]{VA, PW}
        ).toList());

        Prod RT = new Prod();
        RT.setLabel("RT");
        RT.setSymbols("return");
        RT.setIsTerminal(true);

        Prod RE = new Prod();
        RE.setLabel("RE");
        RE.setChildren(Stream.<Prod[]>of(
                new Prod[]{RT, RE2}
        ).toList());

        Prod CO = new Prod();
        CO.setLabel("CO");
        CO.setSymbols("Contenido");
        CO.setIsTerminal(true);

        Prod BO = new Prod();
        BO.setLabel("BO");
        BO.setSymbols("{");
        BO.setIsTerminal(true);

        Prod BC = new Prod();
        BC.setLabel("BC");
        BC.setSymbols("}");
        BC.setIsTerminal(true);

        Prod C = new Prod();
        C.setLabel("C");
        C.setSymbols(",");
        C.setIsTerminal(true);

        Prod BR3 = new Prod();
        BR3.setLabel("BR3");
        BR3.setChildren(Stream.<Prod[]>of(
                new Prod[]{RE, BC},
                new Prod[]{BC}
        ).toList());

        Prod BR2 = new Prod();
        BR2.setLabel("BR2");
        BR2.setChildren(Stream.<Prod[]>of(
                new Prod[]{CO, BR3},
                new Prod[]{BR3}
        ).toList());

        Prod BR = new Prod();
        BR.setLabel("BR");
        BR.setChildren(Stream.<Prod[]>of(
                new Prod[]{BO, BR2}
        ).toList());

        Prod PA = new Prod();
        PA.setLabel("PA");

        Prod PA2 = new Prod();
        PA.setChildren(Stream.<Prod[]>of(
                new Prod[]{L},
                new Prod[]{L, R},
                new Prod[]{VA, PA2}
        ).toList());

        PA2.setLabel("PA2");
        PA2.setChildren(Stream.<Prod[]>of(
                new Prod[]{C, PA}
        ).toList());

        Prod PC = new Prod();
        PC.setLabel("PC");
        PC.setSymbols(")");
        PC.setIsTerminal(true);

        Prod PO = new Prod();
        PO.setLabel("PO");
        PO.setSymbols("(");
        PO.setIsTerminal(true);

        Prod FU = new Prod();
        FU.setLabel("FU");
        FU.setSymbols("func");
        FU.setIsTerminal(true);

        Prod DF7 = new Prod();
        DF7.setLabel("DF7");
        DF7.setChildren(Stream.<Prod[]>of(
                new Prod[]{BR, PW}
        ).toList());

        Prod DF6 = new Prod();
        DF6.setLabel("DF6");
        DF6.setChildren(Stream.<Prod[]>of(
                new Prod[]{PC, DF7}
        ).toList());

        Prod DF5 = new Prod();
        DF5.setLabel("DF5");
        DF5.setChildren(Stream.<Prod[]>of(
                new Prod[]{PA, DF6},
                new Prod[]{DF6}
        ).toList());

        Prod DF4 = new Prod();
        DF4.setLabel("DF4");
        DF4.setChildren(Stream.<Prod[]>of(
                new Prod[]{PO, DF5}
        ).toList());

        Prod DF3 = new Prod();
        DF3.setLabel("DF3");
        DF3.setChildren(Stream.<Prod[]>of(
                new Prod[]{R, DF4}
        ).toList());

        Prod DF2 = new Prod();
        DF2.setLabel("DF2");
        DF2.setChildren(Stream.<Prod[]>of(
                new Prod[]{L, DF3}
        ).toList());

        Prod DF8 = new Prod();
        DF8.setLabel("DF8");
        DF8.setChildren(Stream.<Prod[]>of(
                new Prod[]{L, DF4}
        ).toList());

        Prod DF = new Prod();
        DF.setLabel("DF");
        DF.setChildren(Stream.<Prod[]>of(
                new Prod[]{FU, DF2},
                new Prod[]{FU, DF8}
        ).toList());

//        DI -> I DI2
//        DI2 -> PO DI3
//        DI3 -> C DI4
//        DI4 -> PC DI5
//        DI5 -> DO DI6
//        DI6 -> BR PW
//        I -> if
//        PO -> (
//                PC -> )
//        CM -> "
//        C -> VA C2
//        C2 -> OP VA
//        VA -> L R | a...z | A...Z | D F | 0...9 | D VA1 | D VA2 | D VA4 | D VA5 | true | false | CM VA6 | CM CM
//        VA1 -> F VA2
//        VA2 -> P VA3
//        VA3 -> D F
//        VA4 -> F VA5
//        VA5 -> P D
//        VA6 -> R CM
//        F -> D F | 0...9
//        P -> .
//                L -> a...z | A...Z
//        R -> LR | DR | a...z | A...Z | 0...9
//        D -> 0...9
//        DO -> do
//            OP -> > | < | == | != | <= | >=
//        BR -> BO BR1
//        BR1 -> CO BR2
//        BR2 -> RE BC
//        BO -> {
//            BC -> }
//        CO -> Contenido
//        RE -> RE2 RE1
//        RE1 -> VA PW
//        RE2 -> return
//        PW -> ;





        Prod $ = new Prod();
        $.setLabel("$");
        $.setSymbols("$");
        $.setIsTerminal(true);

        Prod GENERAL = new Prod();
        GENERAL.setLabel("GENERAL");
        GENERAL.setChildren(Stream.<Prod[]>of(
                new Prod[]{DV},
                new Prod[]{DF}
        ).toList());

        Stack<Prod> stack = new Stack<>();
        stack.push($);
        stack.push(GENERAL);

        return stack;
    }

    public Boolean check(String input, Stack<Prod> stack, int index) {
        while (!stack.isEmpty()) {
            System.out.println(stack);
            this.logs += stack + "\n";
            Prod currentProd = stack.peek();

            if (currentProd.getIsTerminal()) {
                String currentElement = getCurrentElement(index, input);
                if (currentProd.getIsRegex()) {
                    if (currentProd.getRegex().matcher(currentElement).matches()) {
                        stack.pop();
                        index++;
                    } else {
                        return false;
                    }
                } else {
                    boolean result = false;
                    for (int i = currentProd.getSymbols().length - 1; i >= 0; i--) {
                        if (currentProd.getSymbols()[i].equals(currentElement)) {
                            stack.pop();
                            index++;
                            result = true;
                            break;
                        }
                    }
                    if (!result) {
                        return false;
                    }
                }
            } else {
                Prod prodNoTerminal = stack.pop();

                for (Prod[] productions : prodNoTerminal.getChildren()) {
                    Stack<Prod> newStack = new Stack<>();
                    newStack.addAll(stack);
                    for (int i = productions.length - 1; i >= 0; i--) {
                        newStack.push(productions[i]);
                    }
                    if (check(input, newStack, index)) {
                        return true;
                    }
                }

                return false;
            }

        }

        return true;
    }

    private String getCurrentElement(int index, String input) {
        List<String> elements = separateElements(input);

        if (index < 0 || index >= elements.size()) {
            return "$";
        }

        return elements.get(index);
    }

    private List<String> separateElements(String text) {
        List<String> elements = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetter(ch) || ch == '_') {
                // Si es una letra o guión bajo, es parte de una palabra
                currentWord.append(ch);
            } else {
                if (currentWord.length() > 0) {
                    // Si hay una palabra almacenada, añádela a la lista de elementos
                    elements.add(currentWord.toString());
                    currentWord.setLength(0);
                }

                // Comprobar otros caracteres especiales
                if ("(){};=,".indexOf(ch) != -1) {
                    elements.add(String.valueOf(ch));
                } else if (ch == '"' || ch == '\'') {
                    // Si se encuentran comillas, añadir la comilla de apertura como un elemento separado
                    elements.add(String.valueOf(ch));

                    // Recoger el contenido de la cadena
                    StringBuilder stringContent = new StringBuilder();
                    i++; // Saltar al siguiente carácter después de la comilla inicial
                    while (i < text.length() && text.charAt(i) != ch) {
                        stringContent.append(text.charAt(i));
                        i++;
                    }

                    // Añadir el contenido de la cadena como un elemento separado
                    elements.add(stringContent.toString());

                    // Añadir la comilla de cierre como un elemento separado
                    elements.add(String.valueOf(ch));
                } else if (ch == '/' && i < text.length() - 1 && text.charAt(i + 1) == '/') {
                    // Si se encuentra una doble barra, es un comentario de línea, ignorarlo
                    while (i < text.length() && text.charAt(i) != '\n') {
                        i++;
                    }
                } else if (ch == ':' && i < text.length() - 1 && text.charAt(i + 1) == '=') {
                    // Si se encuentra ':=', agrúpalo como un solo elemento
                    elements.add(":=");
                    i++; // Saltar al siguiente carácter después de '='
                } else if ("=<>!".indexOf(ch) != -1) {
                    // Agrupar operadores adicionales como ==, <, >, <=, >=, !=
                    StringBuilder operator = new StringBuilder().append(ch);
                    while (i < text.length() - 1 && "=<>".indexOf(text.charAt(i + 1)) != -1) {
                        operator.append(text.charAt(i + 1));
                        i++;
                    }
                    elements.add(operator.toString());
                } else if ("+-".indexOf(ch) != -1 && i < text.length() - 1 && text.charAt(i + 1) == ch) {
                    // Agrupar operadores de incremento/decremento como ++, --
                    elements.add(String.valueOf(ch) + ch);
                    i++; // Saltar al siguiente carácter después del segundo '+'
                } else if (Character.isDigit(ch)) {
                    // Agrupar números
                    StringBuilder number = new StringBuilder().append(ch);
                    while (i < text.length() - 1 && Character.isDigit(text.charAt(i + 1))) {
                        number.append(text.charAt(i + 1));
                        i++;
                    }
                    elements.add(number.toString());
                }
            }
        }

        // Eliminar espacios en blanco
        elements.removeIf(String::isBlank);

        return elements;
    }

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }
}
