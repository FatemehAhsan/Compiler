package codegenerator;

import scanner.CmpScnr;
import scanner.Symbol;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Stack;

import static java.lang.System.exit;

public class CG implements parser.CodeGenerator {
    public static CmpScnr lxcal;
    public static int crntClsIndx;
    public static int crntMthdIndx;
    public static int mthdSsIndx;
    public static String text;
    public static String data;
    int nmbr=0;
    public boolean notInMthd=true;
    public boolean builtInMthd;
    public boolean lenMthd=false;
    boolean rtrn=false;
    public static String outputAddress;
    int countArg = 0;
    Stack<Symbol> sS = new Stack<>();
    ArrayList<String> strings=new ArrayList<>();
    Stack<Symbol> plusPlusStack = new Stack<>();
    Stack<Symbol> minusMinusStack = new Stack<>();
    Stack<Integer> labelStck = new Stack<>();
    public static LinkedHashMap<String,Integer> typeSize=new LinkedHashMap<>();
    String [] types = {"int","bool","real","void","string","array"};
    ArrayList<String> typs= new ArrayList<>();
    public LinkedHashMap<String,Integer> datas=new LinkedHashMap<>();
    public static FileWriter writer;

    Symbol symbol;
    int tmp;
    Symbol symbol0;
    Symbol symbol1;
    Symbol symbol2;
    int strngNmbr=0;
    int floatNmbr=3;
    int labelNmbr =0;

    public CG(CmpScnr lxcal, String outputAddress) {
        this.lxcal = lxcal;
        this.outputAddress=outputAddress;
        text="\t\t.text\n"+
                "\t\t.globl main\n";
        data="\t\t.data\n";
        typeSize.put("int",4);
        typeSize.put("real",8);
        typeSize.put("bool",4);
        typeSize.put("string",4);
        typeSize.put("array",4);
        typs.addAll(Arrays.asList(types));
    }
    void loadRgtrAdrs(Symbol symbol){
        if (symbol.isNotStatic && symbol.level!=1 && !symbol.inArgs)
            text += "\t\tla $t0, " + lxcal.symTab.get(crntClsIndx).value + "\n";
        else
            text += "\t\tla $t0, " + symbol.classSymbol.value + "\n";
    }
    void loadRgtrAdrs2(Symbol symbol){
        //System.out.println(symbol.value+symbol.classSymbol.value + symbol.level);
        if (symbol.isNotStatic && symbol.level!=1 && !symbol.inArgs)
            text += "\t\tla $t1, " + lxcal.symTab.get(crntClsIndx).value + "\n";
        else
            text += "\t\tla $t1, " + symbol.classSymbol.value + "\n";
    }
    void saveRgstr(Symbol symbol1){
        if(!symbol1.type.equals("real"))
        if(!symbol1.tokenName.equals("registerArray")) {
            loadRgtrAdrs(symbol1);
            text += "\t\tsw $t1, " + symbol1.address() + "($t0)\n";
        }
        else{
            nmbr--;
            text+="\t\tsw $t1, 0("+symbol1.value+")\n";
        }
        else {
            if(!symbol1.tokenName.equals("registerArray")) {
                loadRgtrAdrs(symbol1);
                text += "\t\ts.s $f1, " + symbol1.address() + "($t0)\n";
            }
            else{
                floatNmbr--;
                text+="\t\ts.s $f1, 0("+symbol1.value+")\n";
            }
        }
    }
    void saveRgstrWithOutLoadAddrs(Symbol symbol1){
        if(!symbol1.type.equals("real"))
            if(!symbol1.tokenName.equals("registerArray")) {
                text += "\t\tsw $t1, " + symbol1.address() + "($t0)\n";
            }
            else{
                nmbr--;
                text+="\t\tsw $t1, 0("+symbol1.value+")\n";
            }
        else {
            if(!symbol1.tokenName.equals("registerArray")) {
                text += "\t\ts.s $f1, " + symbol1.address() + "($t0)\n";
            }
            else{
                floatNmbr--;
                text+="\t\ts.s $f1, 0("+symbol1.value+")\n";
            }
        }
    }
    void loadRgstr(Symbol symbol1){
        if(!symbol1.type.equals("real"))
            if(!symbol1.tokenName.equals("registerArray")) {
                loadRgtrAdrs(symbol1);
                text += "\t\tlw $t0, " + symbol1.address() + "($t0)\n";
            }
            else{
                nmbr--;
                text+="\t\tlw $t0, 0("+symbol1.value+")\n";
            }
        else {
            if(!symbol1.tokenName.equals("registerArray")) {
                loadRgtrAdrs(symbol1);
                text += "\t\tl.s $f0, " + symbol1.address() + "($t0)\n";
            }
            else{
                floatNmbr--;
                text+="\t\tl.s $f0, 0("+symbol1.value+")\n";
            }
        }
    }
    void loadRgstr2(Symbol symbol1){
        if(!symbol1.type.equals("real"))
            if(!symbol1.tokenName.equals("registerArray")) {
                loadRgtrAdrs2(symbol1);
                text += "\t\tlw $t1, " + symbol1.address() + "($t1)\n";
            }
            else{
                nmbr--;
                text+="\t\tlw $t1, 0("+symbol1.value+")\n";
            }
        else {
            if(!symbol1.tokenName.equals("registerArray")) {
                loadRgtrAdrs(symbol1);
                text += "\t\tl.s $f1, " + symbol1.address() + "($t0)\n";
            }
            else{
                floatNmbr--;
                text+="\t\tl.s $f1, 0("+symbol1.value+")\n";
            }
        }
    }
    void loadFrstRgstr(Symbol symbol1){
        switch (symbol1.tokenName) {
            case "constant":
                text += "\t\tli $t0, " + symbol1.value + "\n";
                break;
            case "id":
                loadRgstr(symbol1);
                break;
            case "register":
                nmbr--;
                text += "\t\tmove $t0, " + symbol1.value + "\n";
                break;
            case "registerArray":
                nmbr--;
                text += "\t\tmove $t0, " + symbol1.value + "\n";
                text += "\t\tlw $t0, 0($t0)\n";
                break;
        }
    }
    void loadFrstBRgstr(Symbol symbol1){
        switch (symbol1.tokenName) {
            case "constant":
                if(symbol1.value.equals("true"))
                    text += "\t\tli $t0, 1\n";
                else
                    text += "\t\tli $t0, 0\n";
                break;
            case "id":
                loadRgstr(symbol1);
                break;
            case "register":
                nmbr--;
                text += "\t\tmove $t0, " + symbol1.value + "\n";
                break;
            case "registerArray":
                nmbr--;
                text += "\t\tmove $t0, " + symbol1.value + "\n";
                text += "\t\tlw $t0, 0($t0)\n";
                break;
        }
    }
    void loadFrstSRgstr(Symbol symbol1){
        switch (symbol1.tokenName) {
            case "id":
                loadRgtrAdrs(symbol1);
                text += "\t\taddi $t0, $t0, "+ symbol1.address() + "\n";
                text += "\t\tlw $t0, 0($t0)\n";
                break;
            case "constant":
                int index=strngNmbr;
                if(datas.containsKey(symbol1.value)){
                    index=datas.get(symbol1.value);
                }
                else {
                    data += "\t.space 20\n";
                    data += "_s"+index+":\t.asciiz "+symbol1.value+"\n";
                    datas.put(symbol1.value,strngNmbr);
                    strngNmbr++;
                }
                text += "\t\tla $t0, _s"+index+"\n";
                break;
            case "registerArray":
            case "register":
                nmbr--;
                text += "\t\tmove $t0, " + symbol1.value + "\n";
                break;
        }
    }
    void loadFrstArRgstr(Symbol symbol1){
        switch (symbol1.tokenName) {
            case "id":
                if (symbol1.isNotStatic && symbol1.level!=1 && !symbol1.inArgs)
                    text+="\t\tla $t0, "+ lxcal.symTab.get(crntClsIndx).value+"\n";
                else
                    text+="\t\tla $t0, "+ symbol1.classSymbol.value +"\n";
                text += "\t\taddi $t0, $t0, "+ symbol1.address() + "\n";
                text += "\t\tlw $t0, 0($t0)\n";
                break;
            case "register":
                nmbr--;
                text += "\t\tmove $t0, " + symbol1.value + "\n";
                break;
            case "constant":
                text+="\t\tli $v0, 9\n";
                text+="\t\tli $t0, "+symbol1.sizeOfArrayType()+"\n";
                loadScndRgstr(symbol1.elmentNmbr);
                text+="\t\tmul $t1, $t0, $t1\n";
                text+="\t\tmove $a0, $t1\n";
                text+="\t\tsyscall\n";
                text+="\t\tmove $t0, $v0\n";
                break;
        }
    }
    void loadFrstFRgstr(Symbol symbol1){
        switch (symbol1.tokenName) {
            case "constant":
                text += "\t\tli.s $f0, " + symbol1.value + "\n";
                break;
            case "id":
                loadRgtrAdrs(symbol1);
                text += "\t\tlwc1 $f0, " + symbol1.address() + "($t0)\n";
                break;
            case "register":
                floatNmbr--;
                text+="\t\tmov.s $f0, "+symbol1.value+"\n";
                break;
            case "registerArray":
                nmbr--;
                text += "\t\tmove $t0, " + symbol1.value + "\n";
                text += "\t\tlwc1 $f0, 0($t0)\n";
                break;
        }
    }
    void loadScndRgstr(Symbol symbol2){
        switch (symbol2.tokenName) {
            case "constant":
                text += "\t\tli $t1, " + symbol2.value + "\n";
                break;
            case "id":
                loadRgstr2(symbol2);
                break;
            case "register":
                nmbr--;
                text += "\t\tmove $t1, " + symbol2.value + "\n";
                break;
            case "registerArray":
                nmbr--;
                text += "\t\tmove $t1, " + symbol2.value + "\n";
                text += "\t\tlw $t1, 0($t1)\n";
                break;    
        }
    }
    void loadScndBRgstr(Symbol symbol2){
        switch (symbol2.tokenName) {
            case "constant":
                if(symbol2.value.equals("true"))
                    text += "\t\tli $t1, 1\n";
                else
                    text += "\t\tli $t1, 0\n";
                break;
            case "id":
                loadRgstr2(symbol2);
                break;
            case "register":
                nmbr--;
                text += "\t\tmove $t1, " + symbol2.value + "\n";
                break;
            case "registerArray":
                nmbr--;
                text += "\t\tmove $t1, " + symbol2.value + "\n";
                text += "\t\tlw $t1, 0($t1)\n";
                break;
        }
    }
    void loadScndSRgstr(Symbol symbol2){
        switch (symbol2.tokenName) {
            case "id":
                loadRgtrAdrs2(symbol2);
                text += "\t\taddi $t1, $t1, "+ symbol2.address() + "\n";
                text += "\t\tlw $t1, 0($t1)\n";
                break;
            case "constant":
                int index=strngNmbr;
                if(datas.containsKey(symbol2.value)){
                    index=datas.get(symbol2.value);
                }
                else{
                    data +="\t.space 20\n";
                    data += "_s"+index+":\t.asciiz "+symbol2.value+"\n";
                    datas.put(symbol2.value,strngNmbr);
                    strngNmbr++;
                }
                text += "\t\tla $t1, _s"+index+"\n";
                break;
            case "registerArray":
            case "register":
                nmbr--;
                text += "\t\tmove $t1, " + symbol2.value + "\n";
                break;
        }
    }
    void loadScndArRgstr(Symbol symbol2){
        switch (symbol2.tokenName) {
            case "id":
                loadRgtrAdrs2(symbol2);
                text += "\t\taddi $t1, $t1, "+ symbol2.address() + "\n";
                text += "\t\tlw $t1, 0($t1)\n";
                break;
            case "register":
                nmbr--;
                text += "\t\tmove $t1, " + symbol1.value + "\n";
                break;
            case "constant":
                text+="\t\tli $v0, 9\n";
                text+="\t\tli $t0, "+symbol2.sizeOfArrayType()+"\n";
                loadScndRgstr(symbol2.elmentNmbr);
                text+="\t\tmul $t1, $t0, $t1\n";
                text+="\t\tmove $a0, $t1\n";
                text+="\t\tsyscall\n";
                text+="\t\tmove $t1, $v0\n";
                break;
        }
    }
    void loadScndFRgstr(Symbol symbol2){
        switch (symbol2.tokenName) {
            case "constant":
                text += "\t\tli.s $f1, " + symbol2.value + "\n";
                break;
            case "id":
                loadRgtrAdrs(symbol2);
                text += "\t\tlwc1 $f1, " + symbol2.address() + "($t0)\n";
                break;
            case "register":
                floatNmbr--;
                text+="\t\tmov.s $f1, "+symbol2.value+"\n";
                break;
            case "registerArray":
                nmbr--;
                text += "\t\tmove $t0, " + symbol2.value + "\n";
                text += "\t\tlwc1 $f1, 0($t0)\n";
                break;
        }
    }
    void assign(){
        symbol2 = sS.pop();
        symbol0 = sS.pop();
        //System.out.println(symbol0.value+symbol2.value+"+"+symbol2.classSymbol.value+":"+symbol2.type+" "+symbol2.tokenName);
        symbol1 = sS.pop();
        if(!symbol1.type.equals(symbol2.type)){
            System.err.println("error line number "+lxcal.lineNmbr+"\n"+"types aren't identical"+"\nnot compiled");
            exit(0);
        }
        if(symbol0.value.equals("<-")){
            switch (symbol1.type){
                case "int":
                    loadScndRgstr(symbol2);
                    saveRgstr(symbol1);
                    break;
                case "string":
                    loadScndSRgstr(symbol2);
                    saveRgstr(symbol1);
                    break;
                case "real":
                    loadScndFRgstr(symbol2);
                    saveRgstr(symbol1);
                    break;
                case "bool":
                    loadScndBRgstr(symbol2);
                    saveRgstr(symbol1);
                    break;
                case "array":
                    loadScndArRgstr(symbol2);
                    saveRgstr(symbol1);
                    break;
            }
        }
        else if(symbol0.value.equals("+=")){
            if(symbol1.type.equals("bool")){
                System.err.println("error line number "+lxcal.lineNmbr+"\n"+"bool type isn't supported for addition or subtraction"+"\nnot compiled");
                exit(0);
            }
            if(symbol1.type.equals("array")){
                System.err.println("error line number "+lxcal.lineNmbr+"\n"+"array type isn't supported for addition or subtraction"+"\nnot compiled");
                exit(0);
            }
            if(symbol1.type.equals("int")){
                loadScndRgstr(symbol2);
                loadRgtrAdrs(symbol1);
                if(!symbol1.tokenName.equals("registerArray"))
                    text += "\t\tlw $t2, " + symbol1.address() + "($t0)\n";
                else{
                    nmbr--;
                    text += "\t\tlw $t2, 0(" + symbol1.value + ")\n";
                }
                text+="\t\tadd $t1, $t1, $t2\n";
                saveRgstrWithOutLoadAddrs(symbol1);
            }
            if(symbol1.type.equals("real")){
                loadScndFRgstr(symbol2);
                loadRgtrAdrs(symbol1);
                if(!symbol1.tokenName.equals("registerArray"))
                    text += "\t\tlwc1 $f0, " + symbol1.address() + "($t0)\n";
                else{
                    floatNmbr--;
                    text += "\t\tlwc1 $f0, 0(" + symbol1.value + ")\n";
                }
                text+="\t\tadd.s $f1, $f0, $f1\n";
                saveRgstrWithOutLoadAddrs(symbol1);
            }
            if(symbol2.type.equals("string")){
                //Intentionally
                System.err.println("error line number "+lxcal.lineNmbr+"\n"+"string type isn't supported for addition or subtraction"+"\nnot compiled");
                exit(0);
                if (symbol2.tokenName.equals("constant")){
                }
                else if (symbol2.tokenName.equals("id")){
                }
                else{
                }
            }
        }
        else if(symbol0.value.equals("-=")){
            if(symbol1.type.equals("bool") || symbol1.type.equals("string") || symbol1.type.equals("array")){
                System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol1.type+" type isn't supported for addition or subtraction"+"\nnot compiled");
                exit(0);
            }
            if(symbol1.type.equals("int")) {
                loadScndRgstr(symbol2);
                loadRgtrAdrs(symbol1);
                if(!symbol1.tokenName.equals("registerArray"))
                    text += "\t\tlw $t2, " + symbol1.address() + "($t0)\n";
                else{
                    nmbr--;
                    text += "\t\tlw $t2, 0(" + symbol1.value + ")\n";
                }
                text += "\t\tsub $t1, $t2, $t1\n";
                saveRgstrWithOutLoadAddrs(symbol1);
            }
            else{
                loadScndFRgstr(symbol2);
                loadRgtrAdrs(symbol1);
                if(!symbol1.tokenName.equals("registerArray"))
                    text += "\t\tlwc1 $f0, " + symbol1.address() + "($t0)\n";
                else{
                    floatNmbr--;
                    text += "\t\tlwc1 $f0, 0(" + symbol1.value + ")\n";
                }
                text+="\t\tsub.s $f1, $f0, $f1\n";
                saveRgstrWithOutLoadAddrs(symbol1);
            }
        }
        else if(symbol0.value.equals("*=")){
            if(symbol1.type.equals("bool") || symbol1.type.equals("string") || symbol1.type.equals("array")){
                System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol1.type+" type isn't supported for multiplication"+"\nnot compiled");
                exit(0);
            }
            if(symbol1.type.equals("int")) {
                loadScndRgstr(symbol2);
                loadRgtrAdrs(symbol1);
                if(!symbol1.tokenName.equals("registerArray"))
                    text += "\t\tlw $t2, " + symbol1.address() + "($t0)\n";
                else{
                    nmbr--;
                    text += "\t\tlw $t2, 0(" + symbol1.value + ")\n";
                }
                text += "\t\tmul $t1, $t2, $t1\n";
                saveRgstrWithOutLoadAddrs(symbol1);
            }
            else{
                loadScndFRgstr(symbol2);
                loadRgtrAdrs(symbol1);
                if(!symbol1.tokenName.equals("registerArray"))
                    text += "\t\tlwc1 $f0, " + symbol1.address() + "($t0)\n";
                else{
                    floatNmbr--;
                    text += "\t\tlwc1 $f0, 0(" + symbol1.value + ")\n";
                }
                text+="\t\tmul.s $f1, $f0, $f1\n";
                saveRgstrWithOutLoadAddrs(symbol1);
            }
        }
        else if(symbol0.value.equals("/=")){
            if(symbol1.type.equals("bool") || symbol1.type.equals("string") || symbol1.type.equals("array")){
                System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol1.type+" type isn't supported for division"+"\nnot compiled");
                exit(0);
            }
            if(symbol1.type.equals("int")) {
                loadScndRgstr(symbol2);
                loadRgtrAdrs(symbol1);
                if(!symbol1.tokenName.equals("registerArray"))
                    text += "\t\tlw $t2, " + symbol1.address() + "($t0)\n";
                else{
                    nmbr--;
                    text += "\t\tlw $t2, 0(" + symbol1.value + ")\n";
                }
                text+="\t\tdiv $t1, $t2, $t1\n";
                saveRgstrWithOutLoadAddrs(symbol1);
            }
            else{
                loadScndFRgstr(symbol2);
                loadRgtrAdrs(symbol1);
                if(!symbol1.tokenName.equals("registerArray"))
                    text += "\t\tlwc1 $f0, " + symbol1.address() + "($t0)\n";
                else{
                    floatNmbr--;
                    text += "\t\tlwc1 $f0, 0(" + symbol1.value + ")\n";
                }
                text+="\t\tdiv.s $f1, $f0, $f1\n";
                saveRgstrWithOutLoadAddrs(symbol1);
            }
        }
        while (!plusPlusStack.empty()){
            symbol=plusPlusStack.pop();
            if(symbol.type.equals("int")){
                loadRgstr2(symbol);
                text += "\t\taddi $t1, $t1, 1\n";
                saveRgstr(symbol);
            }
            else{
                loadRgstr2(symbol);
                text +="\t\tli.s $f1, 1.0\n";
                text += "\t\tadd.s $f0, $f0, $f1\n";
                saveRgstr(symbol);
            }
        }
        while (!minusMinusStack.empty()){
            symbol=minusMinusStack.pop();
            if(symbol.type.equals("int")){
                loadRgstr(symbol);
                text += "\t\taddi $t1, $t1, -1\n";
                saveRgstr(symbol);
            }
            else{
                loadRgstr(symbol);
                text +="\t\tli.s $f1, 1.0\n";
                text += "\t\tsub.s $f0, $f0, $f1\n";
                saveRgstr(symbol);
            }
        }
        if(!sS.empty()){
            sS.push(symbol2);
        }
    }

    @Override
    public void doSemantic(String sem) {
       switch (sem) {
            case "switch":
                lxcal.inDcl=!lxcal.inDcl;
                break;
           case "static":
               sS.peek().isNotStatic=false;
               break;
            case "makeClassSymbolTable":
                lxcal.crntSymbl.level=0;
                if(lxcal.symTab.contains(lxcal.crntSymbl)) {
                    System.err.println("error line number "+lxcal.lineNmbr+"\n"+"class "+ lxcal.crntSymbl.value+" has been declared before"+"\nnot compiled");
                    exit(0);
                }
                lxcal.crntSymbl.type= lxcal.crntSymbl.value;
                lxcal.crntSymbl.index= lxcal.symTab.size();
                crntClsIndx= lxcal.crntSymbl.index;
                lxcal.symTab.add(lxcal.crntSymbl);
                //text+= lxcal.crntSymbl.value+"Class:\n";
                break;
            case "completeClassSize":
                data+="\t.align 2\n";
                data+=lxcal.symTab.get(crntClsIndx).value+":\t.space "+lxcal.symTab.get(crntClsIndx).size()+"\n";
                break;
            case "makeMethodSymbolTable":
                countArg = 0;
                String name=lxcal.crntSymbl.value.substring(0, lxcal.crntSymbl.value.indexOf('('));
                name = name.replaceAll("[\n\r\t\f |\r\n]","");
                //System.out.println(name);
                lxcal.crntSymbl.value=name;
                lxcal.crntSymbl.level=1;
                lxcal.crntSymbl.classSymbol=lxcal.symTab.get(crntClsIndx);
                if(lxcal.symTab.get(crntClsIndx).subSymbols.contains(lxcal.crntSymbl)){
                    System.err.println("error line number "+lxcal.lineNmbr+"\n"+"method "+ lxcal.crntSymbl.value+" has been declared before"+"\nnot compiled");
                    exit(0);
                }
                lxcal.crntSymbl.index= lxcal.symTab.get(crntClsIndx).subSymbols.size();
                crntMthdIndx=lxcal.crntSymbl.index;
                lxcal.symTab.get(crntClsIndx).subSymbols.add(lxcal.crntSymbl);
                sS.push(lxcal.crntSymbl);
                notInMthd=false;
                lxcal.inDcl=!lxcal.inDcl;
                if (lxcal.symTab.get(crntClsIndx).value.equals("Main") && lxcal.crntSymbl.value.equals("main"))
                    text+= lxcal.crntSymbl.value+":\n";
                else
                    text+= lxcal.symTab.get(crntClsIndx).value+"_"+lxcal.crntSymbl.value+":\n";
                break;
           case "cntMthdArgs":
               lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).cntArg = countArg;
               lxcal.inDcl=!lxcal.inDcl;
               break;
           case "exitFromMethod":
                if(!rtrn && !lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).type.equals("void")){
                    System.err.println("error line number "+lxcal.lineNmbr+"\n"+"return is needed for type "+lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).type+"\nnot compiled");
                    exit(0);
                }
                else if(!rtrn)
                    text+="\t\tjr $ra\n";
                notInMthd=true;
                break;
           case "array":
               //System.out.println("dd"+sS.peek());
               symbol2=sS.pop();
               symbol1=sS.pop();
               loadRgstr(symbol1);
               loadScndRgstr(symbol2);
               text+="\t\tli $t2, "+symbol1.sizeOfArrayType()+"\n";
               text+="\t\tmul $t1, $t1, $t2\n";
               text+="\t\tadd $s"+nmbr+", $t0, $t1\n";
               sS.push(new Symbol("registerArray", "$s"+nmbr,symbol1.arrayType));
               nmbr++;
               break;
           case "push":
                if(lxcal.crntSymbl.tokenName.equals("oprndpnctution")){
                    sS.push(lxcal.crntSymbl);
                    break;
                }
                if(lxcal.crntSymbl.tokenName.equals("reserved")){
                    sS.push(lxcal.crntSymbl);
                    break;
                }
                if(lxcal.crntSymbl.tokenName.equals("constant")){
                    lxcal.crntSymbl.level=2;
                    lxcal.crntSymbl.classSymbol=lxcal.symTab.get(crntClsIndx);
                    sS.push(lxcal.crntSymbl);
                    break;
                }
                lxcal.crntSymbl.level=2;
                lxcal.crntSymbl.classSymbol=lxcal.symTab.get(crntClsIndx);
                symbol= lxcal.crntSymbl;
                    if(notInMthd){
                        if(lxcal.symTab.get(crntClsIndx).subSymbols.contains(symbol)){
                            System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol.value+" has been declared before"+"\nnot compiled");
                            exit(0);
                        }
                        else {
                            symbol.index= lxcal.symTab.get(crntClsIndx).subSymbols.size();
                            lxcal.symTab.get(crntClsIndx).subSymbols.add(symbol);
                        }
                        sS.push(lxcal.symTab.get(crntClsIndx).subSymbols.get(symbol.index));
                    }
                    else {
                        for (Symbol symbol2: lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols)
                            if(symbol.equals(symbol2) && !symbol2.notInMethod){
                                symbol.index=symbol2.index;
                                symbol.notInMethod=false;
                            }
                        if(lxcal.inDcl && lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.contains(symbol) && !symbol.notInMethod){
                            System.err.println("error line number "+lxcal.lineNmbr+"\n"+"variable "+symbol.value+" has been declared before"+"\nnot compiled");
                            exit(0);
                        }
                        if(!lxcal.inDcl && !lxcal.symTab.get(crntClsIndx).subSymbols.contains(symbol) && !lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.contains(symbol)){
                            System.err.println("error line number "+lxcal.lineNmbr+"\n"+"variable "+symbol.value+" hasn't been declared before"+"\nnot compiled");
                            exit(0);
                        }
                        if (lxcal.symTab.get(crntClsIndx).subSymbols.contains(symbol) && symbol.notInMethod){
                            symbol = lxcal.symTab.get(crntClsIndx).subSymbols.get(lxcal.symTab.get(crntClsIndx).subSymbols.indexOf(symbol));
                            symbol.index=lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.size();
                            symbol.notInMethod=false;
                            lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.add(symbol);
                            countArg++;
                        }
                        else if(lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.contains(symbol))
                            symbol.index= lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.indexOf(symbol);
                        else{
                            symbol.notInMethod=false;
                            symbol.index= lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.size();
                            lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.add(symbol);
                            countArg++;
                        }
                        sS.push(lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.get(symbol.index));
                    }
                break;
           case "MSDCP":
               symbol0= sS.pop();
               if (symbol0.value.equals("]")){
                   symbol= sS.pop();
                   symbol.arrayType=symbol.type;
                   symbol.type="array";
                   //System.out.println(symbol.arrayType);
               }
               break;
           case "mthdPush":
               name=lxcal.crntSymbl.value.substring(0, lxcal.crntSymbl.value.indexOf('('));
               name = name.replaceAll("[\n\r\t\f |\r\n]","");
               if(name.equals("Array") || name.equals("for")){
                   break;
               }
               if(name.equals("in_int")) {
                   text += "\t\tli $v0, 5\n" +
                           "\t\tsyscall\n" +
                           "\t\tmove $s" + nmbr + ", $v0\n";
                   builtInMthd = true;
                   sS.push(new Symbol("register", "$s" + nmbr, "int"));
                   nmbr++;
                   break;
               }
               if(name.equals("in_string")){
                    builtInMthd=true;
                    data += "_s"+strngNmbr+": .space 20\n";
                    text+="\t\tli $v0, 8\n"+
                       "\t\tla $a0, _s"+strngNmbr+"\n"+
                       "\t\tli $a1, 20\n"+
                       "\t\tsyscall\n"+
                       "\t\tmove $s"+nmbr+", $a0\n";
                    sS.push(new Symbol("register", "$s"+nmbr,"string"));
                    strngNmbr++;
                    nmbr++;
                    break;
               }
               if(name.equals("len")){
                   lenMthd=true;
                   break;
               }
               if(name.equals("out_int")){
                   sS.push(new Symbol("reserved", "out_int","int"));
                   break;
               }
               if(name.equals("out_string")){
                   sS.push(new Symbol("reserved", "out_string","string"));
                   break;
               }
               builtInMthd=false;
               boolean notFound=true;
               for (Symbol smb: lxcal.symTab.get(crntClsIndx).subSymbols){
                   if(smb.level==1 && smb.value.equals(name)){
                       notFound=false;
                       smb.tokenName="id";
                       mthdSsIndx=sS.size();
                       sS.push(smb);
                       //System.out.println(smb.classSymbol.value);
                       break;
                   }
               }
               if(notFound){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+"method "+name+" hasn't been declared before"+"\nnot compiled");
                   exit(0);
               }
               break;
           case "pushType":
               symbol= sS.peek();
               lxcal.crntSymbl.classSymbol=null;
               lxcal.crntSymbl.level=0;
               if(typs.contains(lxcal.crntSymbl.value)){
                   lxcal.crntSymbl.classSymbol=lxcal.symTab.get(crntClsIndx);
                   lxcal.crntSymbl.level=2;
                   if(symbol.level==1) {
                       lxcal.symTab.get(crntClsIndx).subSymbols.get(symbol.index).type = lxcal.crntSymbl.value;
                       break;
                   }
                   if(notInMthd) {
                       lxcal.symTab.get(crntClsIndx).subSymbols.get(symbol.index).type = lxcal.crntSymbl.value;
                   }
                   else
                       lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.get(symbol.index).type= lxcal.crntSymbl.value;
               }
               else if(symbol.level==1 && lxcal.crntSymbl.value.equals("void")){
                   lxcal.symTab.get(crntClsIndx).subSymbols.get(symbol.index).type = lxcal.crntSymbl.value;
               }
               else if(lxcal.symTab.contains(lxcal.crntSymbl)) {
                   if (symbol.level == 1) {
                       lxcal.symTab.get(crntClsIndx).subSymbols.get(symbol.index).type = lxcal.crntSymbl.value;
                       break;
                   }
                   if (notInMthd)
                       lxcal.symTab.get(crntClsIndx).subSymbols.get(symbol.index).type = lxcal.crntSymbl.value;
                   else
                       lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.get(symbol.index).type = lxcal.crntSymbl.value;
               }
               else {
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+lxcal.crntSymbl.value+" isn't a valid type"+"\nnot compiled");
                   exit(0);
               }
               break;
           case "checkArrayType":
               symbol = new Symbol("constant");
               symbol.level = 2;
               lxcal.crntSymbl.classSymbol=null;
               lxcal.crntSymbl.level=0;
               if(typs.contains(lxcal.crntSymbl.value)) {
                   lxcal.crntSymbl.classSymbol = lxcal.symTab.get(crntClsIndx);
                   lxcal.crntSymbl.level = 2;
                   symbol.arrayType=lxcal.crntSymbl.value;
               }
               else if(lxcal.symTab.contains(lxcal.crntSymbl)) {
                   symbol.arrayType=lxcal.crntSymbl.value;
               }
               else {
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+lxcal.crntSymbl.value+" isn't a valid type"+"\nnot compiled");
                   exit(0);
               }
               symbol.classSymbol=lxcal.symTab.get(crntClsIndx);
               symbol.type="array";
               sS.push(symbol);
               break;
           case "setSize":
               //System.out.println(sS.peek().value);
               symbol = sS.pop();
               lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.get(sS.peek().index).elmentNmbr=symbol;
               sS.peek().elmentNmbr=symbol;
               break;
           case "complexPush":
               Symbol newSymbol = new Symbol("id");
               newSymbol.level=2;
               lxcal.crntSymbl.value=lxcal.crntSymbl.value.replaceAll("[\n\r\t\f |\r\n]","");
               String [] values= lxcal.crntSymbl.value.split("\\.");
               Symbol symbolClass=new Symbol("id",values[0],0);
               newSymbol.value=values[1];
               boolean notFined=true;
               int indx = -1;
               for (Symbol symbol:lxcal.symTab)
                   if(symbol.value.equals(symbolClass.value)){
                       for (Symbol symbol2:lxcal.symTab.get(symbol.index).subSymbols)
                           if(symbol2.level==2 && symbol2.value.equals(newSymbol.value)){
                               if(symbol2.isNotStatic){
                                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+newSymbol.value+"isn't static");
                                   exit(0);
                               }
                               newSymbol=lxcal.symTab.get(symbol.index).subSymbols.get(symbol2.index);
                               notFined=false;
                               sS.push(newSymbol);
                           }
                   }
               for (Symbol symbol:lxcal.symTab.get(crntClsIndx).subSymbols)
                   if(symbol.value.equals(symbolClass.value)){
                       for (Symbol symbol3:lxcal.symTab)
                           if(symbol3.value.equals(symbol.type)){
                               indx=symbol3.index;
                               notFined=false;
                           }
                       if(notFined){
                           System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbolClass.value+" doesn't have such variable");
                           exit(0);
                       }
                       notFined=true;
                       for (Symbol symbol2:lxcal.symTab.get(indx).subSymbols)
                           if(symbol2.level==2 && symbol2.value.equals(newSymbol.value)){
                               newSymbol=lxcal.symTab.get(indx).subSymbols.get(symbol2.index);
                               notFined=false;
                               if(!lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.contains(symbol))
                                    lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx).subSymbols.add(symbol);
                               sS.push(newSymbol);
                           }
                   }
               if(notFined){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbolClass.value+" doesn't have such variable");
                   exit(0);
               }
               break;
           case "complexMthdPush":
               newSymbol = new Symbol("id");
               newSymbol.level=2;
               lxcal.crntSymbl.value=lxcal.crntSymbl.value.substring(0, lxcal.crntSymbl.value.indexOf('('));;
               lxcal.crntSymbl.value=lxcal.crntSymbl.value.replaceAll("[\n\r\t\f |\r\n]","");
               values= lxcal.crntSymbl.value.split("\\.");
               symbolClass=new Symbol("id",values[0],0);
               newSymbol.value=values[1];
               notFined=true;
               indx=-1;
               //System.out.println(values[0]);
               for (Symbol symbol:lxcal.symTab.get(crntClsIndx).subSymbols)
                   if(symbol.value.equals(symbolClass.value)){
                       for (Symbol symbol3:lxcal.symTab)
                           if(symbol3.value.equals(symbol.type)){
                               indx=symbol3.index;
                               notFined=false;
                           }
                           if(notFined){
                               System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbolClass.value+" doesn't have such method");
                               exit(0);
                           }
                           notFined=true;
                           //System.out.println(newSymbol.value);
                           for (Symbol symbol2:lxcal.symTab.get(indx).subSymbols){
                               if(symbol2.level==1 && symbol2.value.equals(newSymbol.value)){
                                    newSymbol=lxcal.symTab.get(indx).subSymbols.get(symbol2.index);
                                    newSymbol.tokenName="id";
                                    notFined=false;
                                    mthdSsIndx=sS.size();
                                    sS.push(newSymbol);
                           }
                       }
                   }
               if(notFined){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbolClass.value+" doesn't have such method");
                   exit(0);
               }
               break;
           case "completeArgs":
               if(lenMthd){
                   symbol=sS.pop();
                   if(symbol.type.equals("int") || symbol.type.equals("bool") ||symbol.type.equals("real")) {
                       System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol.type+" type isn't supported for len operation"+"\nnot compiled");
                       exit(0);
                   }
                   if(symbol.type.equals("string")){
                       loadFrstSRgstr(symbol);
                       text += "\t\tmove $t4, $t0\n";
                       text += "_read"+ labelNmbr +":\n";
                       text += "\t\tlb $t2, 0($t0)\n";
                       text += "\t\tbeq  $t2, 0, _exit"+ labelNmbr +"\n";
                       text += "\t\taddi $t0, $t0, 1\n";
                       text += "\t\tb _read"+ labelNmbr +"\n";
                       text += "_exit"+ labelNmbr +":\n";
                       text += "\t\tsub $t0, $t0, $t4\n";
                       text += "\t\tmove $s"+nmbr+", $t0\n";
                       sS.push(new Symbol("register", "$s"+nmbr,"int"));
                       nmbr++;
                       labelNmbr++;
                   }
                   else {
                       if(symbol.tokenName.equals("constant"))
                            loadFrstArRgstr(symbol);
                       loadFrstRgstr(symbol.elmentNmbr);
                       text += "\t\tmove $s"+nmbr+", $t0\n";
                       sS.push(new Symbol("register", "$s" + nmbr, "int"));
                       nmbr++;
                       labelNmbr++;
                   }
                   break;
               }
               if(builtInMthd) break;
               Symbol [] symbols= new Symbol[sS.size()-mthdSsIndx];
               int i;
               for (i = 0 ; sS.size() > mthdSsIndx+1; i++){
                   symbols[i]= sS.pop();
               }
               //System.out.println(sS.size());
               symbols[i]= sS.peek();
               if(symbols.length-1!=symbols[symbols.length-1].cntArg){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbols[symbols.length-1].value+" doesn't have such arguments");
                   exit(0);
               }
               for (i = 0; i<symbols.length-1; i++){
                   symbol0 = symbols[symbols.length-1];
                   symbol2 = symbols[i];
                   symbol0.tokenName="mthdId";
                   symbol1=lxcal.symTab.get(symbol0.classSymbol.index).subSymbols.get(symbol0.index).subSymbols.get(i);
                   symbol1.inArgs=true;
                   symbol1.mthdIndx=symbol0.index;
                   symbol0.tokenName="id";
                   //System.out.println(symbol1.classSymbol.value+" "+symbol1.equals(lxcal.symTab.get(symbol0.classSymbol.index).subSymbols.get(symbol0.index).subSymbols.get(0)));
                   if(!symbol1.type.equals(symbol2.type)){
                       System.err.println("error line number "+lxcal.lineNmbr+"\n"+"argument types aren't identical"+"\nnot compiled");
                       exit(0);
                   }
                   switch (symbol2.type){
                       case "int":
                           //System.out.println(symbol1.value+" "+symbol1.classSymbol.value);
                           loadScndRgstr(symbol2);
                           saveRgstr(symbol1);
                           break;
                       case "string":
                           loadScndSRgstr(symbol2);
                           saveRgstr(symbol1);
                           break;
                       case "real":
                           loadScndFRgstr(symbol2);
                           saveRgstr(symbol1);
                           break;
                       case "bool":
                           loadScndBRgstr(symbol2);
                           saveRgstr(symbol1);
                           break;
                       case "array":
                           loadScndArRgstr(symbol2);
                           saveRgstr(symbol1);
                   }
               }
               text+="\t\taddi $sp, $sp, -4\n";
               text+="\t\tsw $ra, 0($sp)\n";
               text+="\t\tjal "+symbols[symbols.length-1].classSymbol.value+"_"+symbols[symbols.length-1].value+"\n";
               text+="\t\tlw $ra, 0($sp)\n";
               text+="\t\taddi $sp, $sp, 4\n";
               break;
           case "return":
               if(sS.empty())
                   break;
               symbol2 = sS.pop();
               symbol1 = lxcal.symTab.get(crntClsIndx).subSymbols.get(crntMthdIndx);
               if(!symbol1.type.equals(symbol2.type)){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+"types aren't identical for return"+"\nnot compiled");
                   exit(0);
               }
               switch (symbol2.type){
                   case "int":
                       loadScndRgstr(symbol2);
                       saveRgstr(symbol1);
                       break;
                   case "string":
                       loadScndSRgstr(symbol2);
                       saveRgstr(symbol1);
                       break;
                   case "real":
                       loadScndFRgstr(symbol2);
                       saveRgstr(symbol1);
                       break;
                   case "bool":
                       loadScndBRgstr(symbol2);
                       saveRgstr(symbol1);
                       break;
                   case "array":
                       loadScndArRgstr(symbol2);
                       saveRgstr(symbol1);
                       break;
               }
               rtrn=true;
               text+="\t\tjr $ra\n";
               break;
           case "pushCast":
               if(lxcal.crntSymbl.value.equals("int") || lxcal.crntSymbl.value.equals("real") || lxcal.crntSymbl.value.equals("bool")){
                    sS.push(new Symbol("type",lxcal.crntSymbl.value,lxcal.crntSymbl.value));
               }
               break;
           case "assign":
                assign();
                break;
           case "addSub":
                symbol2 = sS.pop();
                symbol0 = sS.pop();
                symbol1 = sS.pop();
               // System.out.println(symbol1.value);
               // System.out.println(symbol2.value);
                if(!symbol1.type.equals(symbol2.type)){
                    System.err.println("error line number "+lxcal.lineNmbr+"\n"+"types aren't identical"+"\nnot compiled");
                    exit(0);

                }
                if(symbol1.type.equals("bool") || symbol1.type.equals("array")){
                    System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol1.type+" type isn't supported for addition or subtraction"+"\nnot compiled");
                    exit(0);
                }
                else if(symbol1.type.equals("int")) {
                    loadFrstRgstr(symbol1);
                    loadScndRgstr(symbol2);
                    if (symbol0.value.equals("+"))
                        text += "\t\tadd $s"+nmbr+", $t0, $t1\n";
                    else
                        text += "\t\tsub $s"+nmbr+", $t0, $t1\n";
                    sS.push(new Symbol("register", "$s"+nmbr,"int"));
                    nmbr++;
                }
                else if(symbol1.type.equals("string")){
                    loadFrstSRgstr(symbol1);
                    loadScndSRgstr(symbol2);
                    if (!symbol0.value.equals("+")){
                        System.err.println("error line number "+lxcal.lineNmbr+"\n"+"error line number "+lxcal.lineNmbr+"\n"+"error line number "+lxcal.lineNmbr+"\n"+"subtraction isn't supported for string type"+"\nnot compiled");
                        exit(0);
                    }
                    text += "\t\tmove $s"+nmbr+", $t0\n";
                    text += "_read"+ labelNmbr +":\n";
                    text += "\t\tlb $t2, 0($t0)\n";
                    text += "\t\tbeq  $t2, 0, _exit"+ labelNmbr +"\n";
                    text += "\t\taddi $t0, $t0, 1\n";
                    text += "\t\tb _read"+ labelNmbr +"\n";
                    text += "_exit"+ labelNmbr +":\n";
                    labelNmbr++;
                    text += "_read"+ labelNmbr +":\n";
                    text += "\t\tlb $t2, 0($t1)\n";
                    text += "\t\tbeq  $t2, 0, _exit"+ labelNmbr +"\n";
                    text += "\t\tsb $t2, 0($t0)\n";
                    text += "\t\taddi $t0, $t0, 1\n";
                    text += "\t\taddi $t1, $t1, 1\n";
                    text += "\t\tb _read"+ labelNmbr +"\n";
                    text += "_exit"+ labelNmbr +":\n";
                    labelNmbr++;
                    sS.push(new Symbol("register","$s"+nmbr ,"string"));
                    nmbr++;
                }
                else{
                    loadFrstFRgstr(symbol1);
                    loadScndFRgstr(symbol2);
                    if (symbol0.value.equals("+"))
                        text += "\t\tadd.s $f"+floatNmbr+", $f0, $f1\n";
                    else
                        text += "\t\tsub.s $f"+floatNmbr+", $f0, $f1\n";
                    sS.push(new Symbol("register", "$f"+floatNmbr,"real"));
                    floatNmbr++;
                }
                break;
           case "mulDiv":
               symbol2 = sS.pop();
               symbol0 = sS.pop();
               symbol1 = sS.pop();
               if(!symbol1.type.equals(symbol2.type)){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+"error line number "+lxcal.lineNmbr+"\n"+"error line number "+lxcal.lineNmbr+"\n"+"types aren't identical"+"\nnot compiled");
                   exit(0);
               }
               if(symbol1.type.equals("bool") || symbol1.type.equals("string") || symbol1.type.equals("array")){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol1.type+" type isn't supported for multiplication or division"+"\nnot compiled");
                   exit(0);
               }
               else if(symbol1.type.equals("int")){
                   loadFrstRgstr(symbol1);
                   loadScndRgstr(symbol2);
                   if(symbol0.value.equals("*"))
                       text+="\t\tmul $s"+nmbr+", $t0, $t1\n";
                   else if(symbol0.value.equals("/"))
                       text+="\t\tdiv $s"+nmbr+", $t0, $t1\n";
                   else{
                       text += "\t\trem $s"+nmbr+", $t0, $t1\n";
                   }
                   sS.push(new Symbol("register","$s"+nmbr,"int"));
                   nmbr++;
               }
               else {
                   loadFrstFRgstr(symbol1);
                   loadScndFRgstr(symbol2);
                   if (symbol0.value.equals("*"))
                       text += "\t\tmul.s $f"+floatNmbr+", $f0, $f1\n";
                   else if(symbol0.value.equals("/"))
                       text += "\t\tdiv.s $f"+floatNmbr+", $f0, $f1\n";
                   else{
                       text += "\t\tdiv.s $f2, $f0, $f1\n" +
                               "\t\ttrunc.w.s $f2, $f2  \n" +
                               "\t\tcvt.s.w $f2, $f2 \n" +
                               "\t\tmul.s $f1, $f2, $f1\n" +
                               "\t\tsub.s $f"+floatNmbr+", $f0, $f1\n";
                   }
                   sS.push(new Symbol("register", "$f"+floatNmbr,"real"));
                   floatNmbr++;
               }
               break;
           case "equalty":
               symbol2 = sS.pop();
               symbol0 = sS.pop();
               symbol1 = sS.pop();
               if(!symbol1.type.equals(symbol2.type)){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+"types aren't identical"+"\nnot compiled");
                   exit(0);
               }
               if(symbol1.type.equals("int")) {
                   loadFrstRgstr(symbol1);
                   loadScndRgstr(symbol2);
                   if (symbol0.value.equals("==")){
                       text += "\t\tseq $s"+nmbr+", $t0, $t1\n";
                   }
                   else{
                       text += "\t\tsne $s"+nmbr+", $t0, $t1\n";
                   }
                   sS.push(new Symbol("register", "$s"+nmbr,"bool"));
                   nmbr++;
               }
               if(symbol1.type.equals("bool")) {
                   loadFrstBRgstr(symbol1);
                   loadScndBRgstr(symbol2);
                   if (symbol0.value.equals("==")){
                       text += "\t\tseq $s"+nmbr+", $t0, $t1\n";
                   }
                   else{
                       text += "\t\tsne $s"+nmbr+", $t0, $t1\n";
                   }
                   sS.push(new Symbol("register", "$s"+nmbr,"bool"));
                   nmbr++;
               }
               if(symbol1.type.equals("string")) {
                   loadFrstSRgstr(symbol1);
                   loadScndSRgstr(symbol2);
                   if (symbol0.value.equals("==")){
                       text += "\t\tseq $s"+nmbr+", $t0, $t1\n";
                   }
                   else{
                       text += "\t\tsne $s"+nmbr+", $t0, $t1\n";
                   }
                   sS.push(new Symbol("register", "$s"+nmbr,"bool"));
                   nmbr++;
               }
               if(symbol1.type.equals("array")) {
                   loadFrstArRgstr(symbol1);
                   loadScndArRgstr(symbol2);
                   if (symbol0.value.equals("==")){
                       text += "\t\tseq $s"+nmbr+", $t0, $t1\n";
                   }
                   else{
                       text += "\t\tsne $s"+nmbr+", $t0, $t1\n";
                   }
                   sS.push(new Symbol("register", "$s"+nmbr,"bool"));
                   nmbr++;
               }
               if(symbol1.type.equals("real")) {
                   loadFrstFRgstr(symbol1);
                   loadScndFRgstr(symbol2);
                   tmp= labelNmbr +1;
                   if (symbol0.value.equals("==")){
                       text += "\t\tc.eq.s $f0, $f1\n";
                       text += "\t\tbc1f _L"+ labelNmbr +"\n";
                   }
                   else {
                       text += "\t\tc.eq.s $f0, $f1\n";
                       text += "\t\tbc1t _L"+ labelNmbr +"\n";
                   }
                   text += "\t\tli $s"+nmbr+" 1\n";
                   text += "\t\tb _L"+tmp+"\n";
                   text += "_L"+ labelNmbr +":\n";
                   text += "\t\tli $s"+nmbr+" 0\n";
                   text += "_L"+tmp+":\n";
                   labelNmbr +=2;
                   sS.push(new Symbol("register", "$s"+nmbr,"bool"));
                   nmbr++;
               }
               break;
           case "nEqualty":
               symbol2 = sS.pop();
               symbol0 = sS.pop();
               symbol1 = sS.pop();
               if(!symbol1.type.equals(symbol2.type)){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+"types aren't identical"+"\nnot compiled");
                   exit(0);

               }
               if(symbol1.type.equals("bool") || symbol1.type.equals("string") || symbol1.type.equals("array")){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol1.type+" type isn't supported for notEquality"+"\nnot compiled");
                   exit(0);
               }
               else if(symbol1.type.equals("int")) {
                   loadFrstRgstr(symbol1);
                   loadScndRgstr(symbol2);
                   if (symbol0.value.equals("<=")){
                       text += "\t\tsle $s"+nmbr+", $t0, $t1\n";
                   }
                   else if (symbol0.value.equals(">=")){
                       text += "\t\tsge $s"+nmbr+", $t0, $t1\n";
                   }
                   else if (symbol0.value.equals("<")){
                       text += "\t\tslt $s"+nmbr+", $t0, $t1\n";
                   }
                   else{
                       text += "\t\tsgt $s"+nmbr+", $t0, $t1\n";
                   }
                   sS.push(new Symbol("register", "$s"+nmbr,"bool"));
                   nmbr++;
               }
               else{
                   loadFrstFRgstr(symbol1);
                   loadScndFRgstr(symbol2);
                   tmp= labelNmbr +1;
                   if (symbol0.value.equals("<=")){
                       text += "\t\tc.le.s $f0, $f1\n";
                       text += "\t\tbc1f _L"+ labelNmbr +"\n";
                       text += "\t\tli $s"+nmbr+" 1\n";
                       text += "\t\tb _L"+tmp+"\n";
                       text += "_L"+ labelNmbr +":\n";
                       text += "\t\tli $s"+nmbr+" 0\n";
                       text += "_L"+tmp+":\n";
                   }
                   else if (symbol0.value.equals(">=")){
                       text += "\t\tc.lt.s $f0, $f1\n";
                       text += "\t\tbc1t _L"+ labelNmbr +"\n";
                       text += "\t\tli $s"+nmbr+" 1\n";
                       text += "\t\tb _L"+tmp+"\n";
                       text += "_L"+ labelNmbr +":\n";
                       text += "\t\tli $s"+nmbr+" 0\n";
                       text += "_L"+tmp+":\n";
                   }
                   else if (symbol0.value.equals("<")){
                       text += "\t\tc.lt.s $f0, $f1\n";
                       text += "\t\tbc1f _L"+ labelNmbr +"\n";
                       text += "\t\tli $s"+nmbr+" 1\n";
                       text += "\t\tb _L"+tmp+"\n";
                       text += "_L"+ labelNmbr +":\n";
                       text += "\t\tli $s"+nmbr+" 0\n";
                       text += "_L"+tmp+":\n";
                   }
                   else {
                       text += "\t\tc.le.s $f0, $f1\n";
                       text += "\t\tbc1t _L" + labelNmbr + "\n";
                       text += "\t\tli $s" + nmbr + " 1\n";
                       text += "\t\tb _L" + tmp + "\n";
                       text += "_L" + labelNmbr + ":\n";
                       text += "\t\tli $s" + nmbr + " 0\n";
                       text += "_L" + tmp + ":\n";
                   }
                   labelNmbr +=2;
                   sS.push(new Symbol("register", "$s"+nmbr,"bool"));
                   nmbr++;
               }
               break;
           case "btwsAnd":
               symbol2 = sS.pop();
               symbol1 = sS.pop();
               if(!symbol1.type.equals(symbol2.type)){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+"types aren't identical"+"\nnot compiled");
                   exit(0);

               }
               if(symbol1.type.equals("bool") || symbol1.type.equals("string") || symbol1.type.equals("array")){
                    System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol1.type+" type isn't supported for bitwise and"+"\nnot compiled");
                    exit(0);
               }
               else if(symbol1.type.equals("int")) {
                   loadFrstRgstr(symbol1);
                   loadScndRgstr(symbol2);
                   text += "\t\tand $s"+nmbr+", $t0, $t1\n";
                   sS.push(new Symbol("register", "$s"+nmbr,"int"));
                   nmbr++;
               }
               else{
                   loadFrstFRgstr(symbol1);
                   loadScndFRgstr(symbol2);
                   text += "\t\tand $f"+floatNmbr+", $f0, $f1\n";
                   sS.push(new Symbol("register", "$f"+floatNmbr,"int"));
                   floatNmbr++;
               }
               break;
           case "btwsOr":
               symbol2 = sS.pop();
               symbol1 = sS.pop();
               if(!symbol1.type.equals(symbol2.type)){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+"types aren't identical"+"\nnot compiled");
                   exit(0);

               }
               if(symbol1.type.equals("bool") || symbol1.type.equals("string") || symbol1.type.equals("array")){
               System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol1.type+" type isn't supported for bitwise or"+"\nnot compiled");
               exit(0);
               }
               if(symbol1.type.equals("int")) {
                   loadFrstRgstr(symbol1);
                   loadScndRgstr(symbol2);
                   text += "\t\tor $s"+nmbr+", $t0, $t1\n";
                   sS.push(new Symbol("register", "$s"+nmbr,"int"));
                   nmbr++;
               }
               else{
                   loadFrstRgstr(symbol1);
                   loadScndRgstr(symbol2);
                   text += "\t\tor.s $f"+floatNmbr+", $f0, $f1\n";
                   sS.push(new Symbol("register", "$f"+floatNmbr,"real"));
                   floatNmbr++;
               }
               break;
           case "btwsXor":
               symbol2 = sS.pop();
               symbol1 = sS.pop();
               if(!symbol1.type.equals(symbol2.type)){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+"types aren't identical"+"\nnot compiled");
                   exit(0);
               }
               if(symbol1.type.equals("bool") || symbol1.type.equals("string") || symbol1.type.equals("array")){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol1.type+" type isn't supported for bitwise xor"+"\nnot compiled");
                   exit(0);
               }
               if(symbol1.type.equals("int")) {
                   loadFrstRgstr(symbol1);
                   loadScndRgstr(symbol2);
                   text += "\t\txor $s"+nmbr+", $t0, $t1\n";
                   sS.push(new Symbol("register", "$s"+nmbr,"int"));
                   nmbr++;
               }
               else{
                   loadFrstFRgstr(symbol1);
                   loadScndFRgstr(symbol2);
                   text += "\t\txor.s $f"+floatNmbr+", $f0, $f1\n";
                   sS.push(new Symbol("register", "$f"+floatNmbr,"real"));
                   floatNmbr++;
               }
               break;
           case "and":
               symbol2 = sS.pop();
               symbol1 = sS.pop();
               if(!symbol1.type.equals(symbol2.type)){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+"types aren't identical"+"\nnot compiled");
                   exit(0);

               }
               if(symbol1.type.equals("int") || symbol1.type.equals("real") || symbol1.type.equals("string") || symbol1.type.equals("array")){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol1.type+" type isn't supported for and"+"\nnot compiled");
                   exit(0);
               }
               loadFrstBRgstr(symbol1);
               loadScndBRgstr(symbol2);
               text += "\t\tand $s"+nmbr+", $t0, $t1\n";
               sS.push(new Symbol("register", "$s"+nmbr,"bool"));
               nmbr++;
               break;
           case "or":
               symbol2 = sS.pop();
               symbol1 = sS.pop();
               if(!symbol1.type.equals(symbol2.type)){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+"types aren't identical"+"\nnot compiled");
                   exit(0);

               }
               if(symbol1.type.equals("int") || symbol1.type.equals("real") || symbol1.type.equals("string") || symbol1.type.equals("array")){
               System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol1.type+" type isn't supported for or"+"\nnot compiled");
               exit(0);
               }
               loadFrstBRgstr(symbol1);
               loadScndBRgstr(symbol2);
               text += "\t\tor $s"+nmbr+", $t0, $t1\n";
               sS.push(new Symbol("register", "$s"+nmbr,"bool"));
               nmbr++;
               break;
           case "pre":
               symbol1 = sS.pop();
               symbol0 = sS.pop();
               //System.out.println(symbol0.value);
               if(symbol1.type.equals("string") || symbol1.type.equals("array")){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol1.type+" type isn't supported for "+symbol0.value+" operation\nnot compiled");
                   exit(0);
               }
               if(symbol1.type.equals("int")){
                   switch (symbol0.value){
                       case "-":
                           loadFrstRgstr(symbol1);
                           text+="\t\tneg $s"+nmbr+", $t0\n";
                           sS.push(new Symbol("register","$s"+nmbr,"int"));
                           nmbr++;
                            break;
                       case "++":
                           if(!symbol1.tokenName.equals("id")  && !symbol1.tokenName.equals("registerArray")){
                               System.err.println("error line number "+lxcal.lineNmbr+"\n"+"variable expected for ++"+symbol0.value+" operation\nnot compiled");
                               exit(0);
                           }
                           loadScndRgstr(symbol1);
                           text+="\t\taddi $t1, $t1, 1\n";
                           saveRgstr(symbol1);
                           sS.push(symbol1);
                           break;
                       case "--":
                           if(!symbol1.tokenName.equals("id")  && !symbol1.tokenName.equals("registerArray")){
                               System.err.println("error line number "+lxcal.lineNmbr+"\n"+"variable expected for --"+symbol0.value+" operation\nnot compiled");
                               exit(0);
                           }
                           loadScndRgstr(symbol1);
                           text+="\t\taddi $t1, $t1, -1\n";
                           saveRgstr(symbol1);
                           sS.push(symbol1);
                           break;
                       case "real":
                          loadFrstRgstr(symbol1);
                          text+="\t\tmtc1 $t0, $f"+floatNmbr+"\n";
                          text+="\t\tcvt.s.w $f"+floatNmbr+", $f"+floatNmbr+"\n";
                          sS.push(new Symbol("register", "$f"+floatNmbr,"real"));
                          floatNmbr++;
                          break;
                       case "bool":
                           loadFrstRgstr(symbol1);
                           text+="\t\tsgt $s"+nmbr+", $t0, $0\n";
                           sS.push(new Symbol("register", "$s"+nmbr,"bool"));
                           nmbr++;
                           break;
                       default:
                           if(symbol0.value.equals("!"))
                               System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol0.value+" isn't supported for int"+"\nnot compiled");
                           else
                               System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol0.value+" cast isn't supported for int"+"\nnot compiled");
                           exit(0);
                           break;
                   }
               }
               else if(symbol1.type.equals("real")){
                   loadFrstFRgstr(symbol1);
                   switch (symbol0.value){
                       case "-":
                           text+="\t\tneg.s $f"+floatNmbr+", $f0\n";
                           sS.push(new Symbol("register","$f"+floatNmbr,"real"));
                           floatNmbr++;
                           break;
                       case "++":
                           if(!symbol1.tokenName.equals("id") && !symbol1.tokenName.equals("registerArray")){
                               System.err.println("error line number "+lxcal.lineNmbr+"\n"+"variable expected for ++"+symbol0.value+" operation\nnot compiled");
                               exit(0);
                           }
                           text+="\t\tli.s $f1, 1.0\n";
                           text+="\t\tadd.s $f0, $f0, $f1\n";
                           saveRgstr(symbol1);
                           sS.push(symbol1);
                           break;
                       case "--":
                           if(!symbol1.tokenName.equals("id") && !symbol1.tokenName.equals("registerArray")){
                               System.err.println("error line number "+lxcal.lineNmbr+"\n"+"variable expected for --"+symbol0.value+" operation\nnot compiled");
                               exit(0);
                           }
                           text+="\t\tli.s $f1, 1.0\n";
                           text+="\t\tsub.s $f0, $f0, $f1\n";
                           saveRgstr(symbol1);
                           sS.push(symbol1);
                           break;
                       case "int":
                           text+="\t\tcvt.w.s $f0, $f0\n";
                           text+="\t\tmfc1 $s"+nmbr+", $f0\n";
                           sS.push(new Symbol("register", "$s"+nmbr,"int"));
                           nmbr++;
                           break;
                       default:
                           if(symbol0.value.equals("!"))
                                System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol0.value+" isn't supported for real"+"\nnot compiled");
                           else
                               System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol0.value+" cast isn't supported for real"+"\nnot compiled");
                           exit(0);
                           break;
                   }
               }
               else if(symbol1.type.equals("bool")){
                   loadFrstBRgstr(symbol1);
                   switch (symbol0.value){
                       case "!":
                           text+="\t\tseq $s"+nmbr+", $t0, $0\n";
                           sS.push(new Symbol("register", "$s"+nmbr,"bool"));
                           nmbr++;
                           break;
                       case "int":
                           text+="\t\tmove $s"+nmbr+", $t0\n";
                           sS.push(new Symbol("register", "$s"+nmbr,"int"));
                           nmbr++;
                           break;
                       default:
                           if(symbol0.value.equals("real") || symbol0.value.equals("bool"))
                               System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol0.value+" cast isn't supported for bool"+"\nnot compiled");
                           else
                               System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol0.value+" isn't supported for bool"+"\nnot compiled");
                           exit(0);
                           break;
                   }
               }
               break;
           case "out":
               symbol=sS.pop();
               symbol0=sS.pop();
               if(symbol.type.equals("bool") || symbol.type.equals("real") || symbol.type.equals("array")) {
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol.type+" type isn't supported for write in console"+"\nnot compiled");
                   exit(0);
               }
               if(symbol0.value.equals("out_int")){
                   if(symbol.type.equals("string")) {
                       System.err.println("error line number "+lxcal.lineNmbr+"\n"+"string type isn't supported for writing int in console"+"\nnot compiled");
                       exit(0);
                   }
                   switch (symbol.tokenName){
                       case "constant":
                           text+="\t\tli $v0, 1\n"+
                                   "\t\tli $a0, "+ symbol.value+"\n"+
                                   "\t\tsyscall\n";
                           break;
                       case "id":
                           text+="\t\tli $v0, 1\n"+
                                   "\t\tla $a0, "+ symbol.classSymbol.value+"\n"+
                                   "\t\tlw $a0, "+ symbol.address()+"($a0)\n"+
                                   "\t\tsyscall\n";
                           break;
                       case "register":
                           nmbr--;
                           text+="\t\tli $v0, 1\n"+
                                   "\t\tmove $a0, $s"+nmbr+"\n"+
                                   "\t\tsyscall\n";
                           break;
                       case "registerArray":
                           nmbr--;
                           text+="\t\tmove $a0, $s"+nmbr+"\n";
                           text+="\t\tlw $a0, 0($a0)\n"+
                                   "\t\tsyscall\n";
                           break;
                   }
               }
               else{
                   if(symbol.type.equals("int")) {
                       System.err.println("error line number "+lxcal.lineNmbr+"\n"+"int type isn't supported for writing string in console"+"\nnot compiled");
                       exit(0);
                   }
                   text+="\t\tli $v0, 4\n";
                   switch (symbol.tokenName){
                       case "constant":
                           data += "_s"+strngNmbr+":\t.asciiz "+symbol.value+"\n";
                           text += "\t\tla $a0, _s"+strngNmbr+"\n"+
                                   "\t\tsyscall\n";
                           strngNmbr++;
                           break;
                       case "id":
                           text += "\t\tla $a0, "+symbol.classSymbol.value+"\n"+
                                   "\t\taddi $a0, $a0 ,"+symbol.address()+"\n"+
                                   "\t\tlw $a0, 0($a0)\n"+
                                   "\t\tsyscall\n";
                           break;
                       case "registerArray":
                       case "register":
                           nmbr--;
                           text+="\t\tmove $a0, $s"+nmbr+"\n"+
                                   "\t\tsyscall\n";
                           break;
                   }
               }
               break;
           case "post":
               symbol0=lxcal.crntSymbl;
               symbol=sS.peek();
               if(symbol.type.equals("bool") || symbol.type.equals("string") || symbol.type.equals("array")) {
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+symbol.type+" type isn't supported for making postfix operation"+"\nnot compiled");
                   exit(0);
               }
               switch (symbol0.value){
                   case "++":
                       plusPlusStack.add(symbol);
                       break;
                   case "--":
                       minusMinusStack.add(symbol);
                       break;
               }
               break;
           case "chck":
               symbol=sS.pop();
               if(!symbol.type.equals("bool")){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+"expression for if isn't boolean");
                   exit(0);
               }
               loadFrstBRgstr(symbol);
               text+="\t\tbeqz $t0, _L"+labelNmbr+"\n";
               labelStck.push(labelNmbr);
               labelNmbr++;
               break;
           case "else":
               tmp=labelStck.pop();
               text+="\t\tb _L"+labelNmbr+"\n";
               labelStck.push(labelNmbr);
               labelNmbr++;
               text+="_L"+tmp+":\n";
               break;
           case "fi":
               text+="_L"+labelStck.pop()+":\n";
               break;
           case "while":
               text+="_L"+labelNmbr+":\n";
               labelStck.push(labelNmbr);
               labelNmbr++;
               break;
           case "for":
               if(sS.size()==1){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+"first part of for must be assignment");
                   exit(0);
               }
               else{
                   assign();
               }
               text+="_L"+labelNmbr+":\n";
               labelStck.push(labelNmbr);
               labelNmbr++;
               break;
           case "pool":
               tmp=labelStck.pop();
               text+="\t\tb _L"+labelStck.pop()+"\n";
               text+="_L"+tmp+":\n";
               break;
           case "chckFor":
               symbol=sS.pop();
               if(!symbol.type.equals("bool")){
                   System.err.println("error line number "+lxcal.lineNmbr+"\n"+"expression for if isn't boolean");
                   exit(0);
               }
               loadFrstBRgstr(symbol);
               text+="\t\tbeqz $t0, _L"+labelNmbr+"\n";
               labelStck.push(labelNmbr);
               labelNmbr++;
               text+="\t\tb _L"+labelNmbr+"\n";
               labelStck.push(labelNmbr);
               labelNmbr++;
               text+="_L"+labelNmbr+":\n";
               labelStck.push(labelNmbr);
               labelNmbr++;
               break;
           case "branch":
               if(sS.size()==1){
                   sS.pop();
               }
               else{
                   assign();
               }
               tmp=labelStck.pop();
               int tmp2=labelStck.pop();
               int tmp3=labelStck.pop();
               text+="\t\tb _L"+labelStck.pop()+"\n";
               labelStck.push(tmp3);
               labelStck.push(tmp);
               text+="_L"+tmp2+":\n";
               break;
           case "rof":
               text+="\t\tb _L"+labelStck.pop()+"\n";
               text+="_L"+labelStck.pop()+":\n";
               break;
           case "break":
               text+="\t\tb _L"+labelStck.peek()+"\n";
               break;
           case "continue":
               tmp=labelStck.pop();
               text+="\t\tb _L"+labelStck.peek()+"\n";
               labelStck.push(tmp);
               break;
           default:
                System.err.println("error line number "+lxcal.lineNmbr+"\n"+"semantic "+sem+" Is Not Supported");
        }
    }
}