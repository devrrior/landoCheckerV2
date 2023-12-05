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

        Prod N = new Prod();
        N.setLabel("N");
        N.setRegex(Pattern.compile("^[0-9]+$"));
        N.setIsRegex(true);
        N.setIsTerminal(true);

        Prod B = new Prod();
        B.setLabel("B");
        B.setRegex(Pattern.compile("^(true|false)$"));
        B.setIsRegex(true);
        B.setIsTerminal(true);

        Prod SI = new Prod();
        SI.setLabel("SI");
        SI.setSymbols(":=");
        SI.setIsTerminal(true);

        Prod CD = new Prod();
        CD.setLabel("CD");
        CD.setSymbols("\"");
        CD.setIsTerminal(true);

        Prod P = new Prod();
        P.setLabel("P");
        P.setSymbols(".");
        P.setIsTerminal(true);

        Prod PCC = new Prod();
        PCC.setLabel("PCC");
        PCC.setSymbols(";");
        PCC.setIsTerminal(true);

        Prod C = new Prod();
        C.setLabel("C");
        C.setSymbols(",");
        C.setIsTerminal(true);

        Prod FU = new Prod();
        FU.setLabel("FU");
        FU.setSymbols("func");
        FU.setIsTerminal(true);

        Prod PA = new Prod();
        PA.setLabel("PA");
        PA.setSymbols("(");
        PA.setIsTerminal(true);

        Prod PC = new Prod();
        PC.setLabel("PC");
        PC.setSymbols(")");
        PC.setIsTerminal(true);

        Prod BA = new Prod();
        BA.setLabel("BA");
        BA.setSymbols("{");
        BA.setIsTerminal(true);

        Prod BC = new Prod();
        BC.setLabel("BC");
        BC.setSymbols("}");
        BC.setIsTerminal(true);

        Prod RT = new Prod();
        RT.setLabel("RT");
        RT.setSymbols("return");
        RT.setIsTerminal(true);

        Prod IF = new Prod();
        IF.setLabel("IF");
        IF.setSymbols("if");
        IF.setIsTerminal(true);

        Prod DO = new Prod();
        DO.setLabel("DO");
        DO.setSymbols("do");
        DO.setIsTerminal(true);

        Prod OC = new Prod();
        OC.setLabel("OC");
        OC.setSymbols(">", "<", "==", "!=", "<=", ">=");
        OC.setIsTerminal(true);

        Prod WH = new Prod();
        WH.setLabel("WH");
        WH.setSymbols("while");
        WH.setIsTerminal(true);

        Prod PR = new Prod();
        PR.setLabel("PR");
        PR.setSymbols("print");
        PR.setIsTerminal(true);

        Prod INDN = new Prod();
        INDN.setLabel("INDN");
        INDN.setSymbols("++", "--");
        INDN.setIsTerminal(true);

        Prod MA = new Prod();
        MA.setLabel("MA");
        MA.setSymbols("main");
        MA.setIsTerminal(true);

        Prod AR = new Prod();
        AR.setLabel("AR");
        AR.setSymbols("args");
        AR.setIsTerminal(true);

        Prod OPC = new Prod();
        OPC.setLabel("OPC");
        OPC.setSymbols("||", "&&");
        OPC.setIsTerminal(true);

        Prod CO = new Prod();
        CO.setLabel("CO");
        CO.setSymbols("continue");
        CO.setIsTerminal(true);

        // Varias letras y numeros
        Prod R = new Prod();
        R.setLabel("R");
        R.setChildren(Stream.<Prod[]>of(
                new Prod[]{L, R},
                new Prod[]{N, R},
                new Prod[]{L},
                new Prod[]{N}
        ).toList());

        // Uno o mas letras
        Prod ML = new Prod();
        ML.setLabel("ML");
        ML.setChildren(Stream.<Prod[]>of(
                new Prod[]{L, ML},
                new Prod[]{L}
        ).toList());

        // Uno o mas numeros
        Prod MN = new Prod();
        MN.setLabel("MN");
        MN.setChildren(Stream.<Prod[]>of(
                new Prod[]{N, MN},
                new Prod[]{N}
        ).toList());


        // Valor variable
        Prod VA2 = new Prod();
        VA2.setLabel("VA2");
        VA2.setChildren(Stream.<Prod[]>of(
                new Prod[]{P, MN}
        ).toList());

        Prod VA1 = new Prod();
        VA1.setLabel("VA1");
        VA1.setChildren(Stream.<Prod[]>of(
                new Prod[]{R, CD}
        ).toList());

        Prod VA = new Prod();
        VA.setLabel("VA");
        VA.setChildren(Stream.<Prod[]>of(
                new Prod[]{CD, VA1},
                new Prod[]{CD, CD},
                new Prod[]{MN},
                new Prod[]{MN, VA2},
                new Prod[]{B}
        ).toList());

        // Nombramiento de variables
        Prod NV = new Prod();
        NV.setLabel("NV");
        NV.setChildren(Stream.<Prod[]>of(
                new Prod[]{L, R},
                new Prod[]{L}
        ).toList());

        // Parametros variables
        Prod PV = new Prod();

        Prod PV1 = new Prod();
        PV1.setLabel("PV1");
        PV1.setChildren(Stream.<Prod[]>of(
                new Prod[]{C, PV}
        ).toList());

        PV.setLabel("PV");
        PV.setChildren(Stream.<Prod[]>of(
                new Prod[]{NV, PV1},
                new Prod[]{NV}
        ).toList());

        // Parametro valores y/o variables
        Prod PVV = new Prod();

        Prod PVV1 = new Prod();
        PVV1.setLabel("PVV1");
        PVV1.setChildren(Stream.<Prod[]>of(
                new Prod[]{C, PVV}
        ).toList());

        PVV.setLabel("PVV");
        PVV.setChildren(Stream.<Prod[]>of(
                new Prod[]{NV, PVV1},
                new Prod[]{VA, PVV1},
                new Prod[]{NV},
                new Prod[]{VA}
        ).toList());

        // Parametros condicional
        Prod PVC = new Prod();

        Prod PVC3 = new Prod();
        PVC3.setLabel("PVC3");
        PVC3.setChildren(Stream.<Prod[]>of(
                new Prod[]{OPC, PVC}
        ).toList());

        Prod PVC2 = new Prod();
        PVC2.setLabel("PVC2");
        PVC2.setChildren(Stream.<Prod[]>of(
                new Prod[]{VA, PVC3},
                new Prod[]{NV, PVC3},
                new Prod[]{VA},
                new Prod[]{NV}
        ).toList());

        Prod PVC1 = new Prod();
        PVC1.setLabel("PVC1");
        PVC1.setChildren(Stream.<Prod[]>of(
                new Prod[]{OC, PVC2}
        ).toList());

        PVC.setLabel("PVC");
        PVC.setChildren(Stream.<Prod[]>of(
                new Prod[]{VA, PVC1},
                new Prod[]{NV, PVC1}
        ).toList());

        // Return variable
        Prod RV1 = new Prod();
        RV1.setLabel("RV1");
        RV1.setChildren(Stream.<Prod[]>of(
                new Prod[]{VA, PCC}
        ).toList());

        Prod RV = new Prod();
        RV.setLabel("RV");
        RV.setChildren(Stream.<Prod[]>of(
                new Prod[]{RT, RV1}
        ).toList());

        // Declaracion de variables
        Prod V3 = new Prod();
        V3.setLabel("V3");
        V3.setChildren(Stream.<Prod[]>of(
                new Prod[]{VA, PCC}
        ).toList());

        Prod V2 = new Prod();
        V2.setLabel("V2");
        V2.setChildren(Stream.<Prod[]>of(
                new Prod[]{SI, V3}
        ).toList());

        Prod V1 = new Prod();
        V1.setLabel("V1");
        V1.setChildren(Stream.<Prod[]>of(
                new Prod[]{R, V2}
        ).toList());

        Prod V = new Prod();
        V.setLabel("V");
        V.setChildren(Stream.<Prod[]>of(
                new Prod[]{L, V1},
                new Prod[]{L, V2}
        ).toList());

        // Declaracion de funciones
        Prod F8 = new Prod();
        F8.setLabel("F8");
        F8.setChildren(Stream.<Prod[]>of(
                new Prod[]{BC, PCC}
        ).toList());

        Prod F7 = new Prod();
        F7.setLabel("F7");
        F7.setChildren(Stream.<Prod[]>of(
                new Prod[]{RV, F8}
        ).toList());

        Prod F6 = new Prod();
        F6.setLabel("F6");
        F6.setChildren(Stream.<Prod[]>of(
                new Prod[]{CO, F7},
                new Prod[]{RV, F8},
                new Prod[]{F8}
        ).toList());

        Prod F5 = new Prod();
        F5.setLabel("F5");
        F5.setChildren(Stream.<Prod[]>of(
                new Prod[]{BA, F6}
        ).toList());

        Prod F4 = new Prod();
        F4.setLabel("F4");
        F4.setChildren(Stream.<Prod[]>of(
                new Prod[]{PC, F5}
        ).toList());

        Prod F3 = new Prod();
        F3.setLabel("F3");
        F3.setChildren(Stream.<Prod[]>of(
                new Prod[]{PV, F4}
        ).toList());

        Prod F2 = new Prod();
        F2.setLabel("F2");
        F2.setChildren(Stream.<Prod[]>of(
                new Prod[]{PA, F3},
                new Prod[]{PA, F4}
        ).toList());

        Prod F1 = new Prod();
        F1.setLabel("F1");
        F1.setChildren(Stream.<Prod[]>of(
                new Prod[]{NV, F2}
        ).toList());

        Prod F = new Prod();
        F.setLabel("F");
        F.setChildren(Stream.<Prod[]>of(
                new Prod[]{FU, F1}
        ).toList());

        // Estrucutra condicional
        Prod CON7 = new Prod();
        CON7.setLabel("CO7");
        CON7.setChildren(Stream.<Prod[]>of(
                new Prod[]{BC, PCC}
        ).toList());

        Prod CON6 = new Prod();
        CON6.setLabel("CO6");
        CON6.setChildren(Stream.<Prod[]>of(
                new Prod[]{CO, CON7}
        ).toList());

        Prod CON5 = new Prod();
        CON5.setLabel("CO5");
        CON5.setChildren(Stream.<Prod[]>of(
                new Prod[]{BA, CON6}
        ).toList());

        Prod CON4 = new Prod();
        CON4.setLabel("CO4");
        CON4.setChildren(Stream.<Prod[]>of(
                new Prod[]{DO, CON5}
        ).toList());

        Prod CON3 = new Prod();
        CON3.setLabel("CO3");
        CON3.setChildren(Stream.<Prod[]>of(
                new Prod[]{PC, CON4}
        ).toList());

        Prod CON2 = new Prod();
        CON2.setLabel("CO2");
        CON2.setChildren(Stream.<Prod[]>of(
                new Prod[]{PVC, CON3}
        ).toList());

        Prod CON1 = new Prod();
        CON1.setLabel("CO1");
        CON1.setChildren(Stream.<Prod[]>of(
                new Prod[]{PA, CON2}
        ).toList());

        Prod CON = new Prod();
        CON.setLabel("CON");
        CON.setChildren(Stream.<Prod[]>of(
                new Prod[]{IF, CON1}
        ).toList());

        // Estructura de ciclo
        Prod BU7 = new Prod();
        BU7.setLabel("BU7");
        BU7.setChildren(Stream.<Prod[]>of(
                new Prod[]{BC, PCC}
        ).toList());

        Prod BU6 = new Prod();
        BU6.setLabel("BU6");
        BU6.setChildren(Stream.<Prod[]>of(
                new Prod[]{CO, BU7}
        ).toList());

        Prod BU5 = new Prod();
        BU5.setLabel("BU5");
        BU5.setChildren(Stream.<Prod[]>of(
                new Prod[]{BA, BU6}
        ).toList());

        Prod BU4 = new Prod();
        BU4.setLabel("BU4");
        BU4.setChildren(Stream.<Prod[]>of(
                new Prod[]{DO, BU5}
        ).toList());

        Prod BU3 = new Prod();
        BU3.setLabel("BU3");
        BU3.setChildren(Stream.<Prod[]>of(
                new Prod[]{PC, BU4}
        ).toList());

        Prod BU2 = new Prod();
        BU2.setLabel("BU2");
        BU2.setChildren(Stream.<Prod[]>of(
                new Prod[]{PVC, BU3}
        ).toList());

        Prod BU1 = new Prod();
        BU1.setLabel("BU1");
        BU1.setChildren(Stream.<Prod[]>of(
                new Prod[]{PA, BU2}
        ).toList());

        Prod BU = new Prod();
        BU.setLabel("BU");
        BU.setChildren(Stream.<Prod[]>of(
                new Prod[]{WH, BU1}
        ).toList());

        // Imprimir
        Prod PM3 = new Prod();
        PM3.setLabel("PM3");
        PM3.setChildren(Stream.<Prod[]>of(
                new Prod[]{PC, PCC}
        ).toList());

        Prod PM2 = new Prod();
        PM2.setLabel("PM2");
        PM2.setChildren(Stream.<Prod[]>of(
                new Prod[]{NV, PM3},
                new Prod[]{VA, PM3}
        ).toList());

        Prod PM1 = new Prod();
        PM1.setLabel("PM1");
        PM1.setChildren(Stream.<Prod[]>of(
                new Prod[]{PA, PM2}
        ).toList());

        Prod PM = new Prod();
        PM.setLabel("PM");
        PM.setChildren(Stream.<Prod[]>of(
                new Prod[]{PR, PM1}
        ).toList());

        // Incremento y decremento
        Prod IDV1 = new Prod();
        IDV1.setLabel("IDV1");
        IDV1.setChildren(Stream.<Prod[]>of(
                new Prod[]{INDN, PCC}
        ).toList());

        Prod IDV = new Prod();
        IDV.setLabel("IDV");
        IDV.setChildren(Stream.<Prod[]>of(
                new Prod[]{NV, IDV1}
        ).toList());

        // Llamar funcion
        Prod LF3 = new Prod();
        LF3.setLabel("LF3");
        LF3.setChildren(Stream.<Prod[]>of(
                new Prod[]{PC, PCC}
        ).toList());

        Prod LF2 = new Prod();
        LF2.setLabel("LF2");
        LF2.setChildren(Stream.<Prod[]>of(
                new Prod[]{PVV, LF3}
        ).toList());

        Prod LF1 = new Prod();
        LF1.setLabel("LF1");
        LF1.setChildren(Stream.<Prod[]>of(
                new Prod[]{PA, LF2},
                new Prod[]{LF3}
        ).toList());

        Prod LF = new Prod();
        LF.setLabel("LF");
        LF.setChildren(Stream.<Prod[]>of(
                new Prod[]{NV, LF1}
        ).toList());

        // Funcion main
        Prod FM8 = new Prod();
        FM8.setLabel("FM8");
        FM8.setChildren(Stream.<Prod[]>of(
                new Prod[]{BC, PCC}
        ).toList());

        Prod FM7 = new Prod();
        FM7.setLabel("FM7");
        FM7.setChildren(Stream.<Prod[]>of(
                new Prod[]{RV, FM8}
        ).toList());

        Prod FM6 = new Prod();
        FM6.setLabel("FM6");
        FM6.setChildren(Stream.<Prod[]>of(
                new Prod[]{CO, FM7},
                new Prod[]{RV, FM8},
                new Prod[]{FM8}
        ).toList());

        Prod FM5 = new Prod();
        FM5.setLabel("FM5");
        FM5.setChildren(Stream.<Prod[]>of(
                new Prod[]{BA, FM6}
        ).toList());

        Prod FM4 = new Prod();
        FM4.setLabel("FM4");
        FM4.setChildren(Stream.<Prod[]>of(
                new Prod[]{PC, FM5}
        ).toList());

        Prod FM3 = new Prod();
        FM3.setLabel("FM3");
        FM3.setChildren(Stream.<Prod[]>of(
                new Prod[]{AR, FM4}
        ).toList());

        Prod FM2 = new Prod();
        FM2.setLabel("FM2");
        FM2.setChildren(Stream.<Prod[]>of(
                new Prod[]{PA, FM3}
        ).toList());

        Prod FM1 = new Prod();
        FM1.setLabel("FM1");
        FM1.setChildren(Stream.<Prod[]>of(
                new Prod[]{MA, FM2}
        ).toList());

        Prod FM = new Prod();
        FM.setLabel("FM");
        FM.setChildren(Stream.<Prod[]>of(
                new Prod[]{FU, FM1}
        ).toList());


        Prod $ = new Prod();
        $.setLabel("$");
        $.setSymbols("$");
        $.setIsTerminal(true);

        Prod GENERAL = new Prod();
        GENERAL.setLabel("GENERAL");
        GENERAL.setChildren(Stream.<Prod[]>of(
                new Prod[]{V},
                new Prod[]{F},
                new Prod[]{CON},
                new Prod[]{BU},
                new Prod[]{PM},
                new Prod[]{IDV},
                new Prod[]{LF},
                new Prod[]{FM}
        ).toList());

        Stack<Prod> stack = new Stack<>();
        stack.push($);
        stack.push(CON);

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

    public static List<String> separateElements(String text) {
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
                } else if ("&|".indexOf(ch) != -1 && i < text.length() - 1 && text.charAt(i + 1) == ch) {
                    // Agrupar operadores lógicos &&, ||
                    elements.add(String.valueOf(ch) + ch);
                    i++; // Saltar al siguiente carácter después del segundo '&'
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
