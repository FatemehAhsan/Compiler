package scanner;

import codegenerator.CG;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

import static java.lang.System.exit;

public class Symbol {
    public String tokenName;
    public String value;
    public String type;
    public String arrayType;
    public int containItself=0;
    public int level;
    public int index;
    public int adrs;
    public boolean notInMethod=true;
    public boolean isNotStatic=true;
    public Symbol classSymbol=null;

    public ArrayList<Symbol> subSymbols = new ArrayList<Symbol>();
    public int cntArg;
    public Symbol elmentNmbr;
    public boolean inArgs=false;
    public int mthdIndx;

    public Symbol(String tokenName, String value,int level) {
        this.tokenName = tokenName;
        this.value = value;
        this.level=level;
    }
    public Symbol(String tokenName, String value,String type) {
        this.tokenName = tokenName;
        this.value = value;
        this.type=type;
    }
    public Symbol(String tokenName, String value) {
        this.tokenName = tokenName;
        this.value = value;
    }
    public Symbol(String tokenName) {
        this.tokenName = tokenName;
    }
    public int size(){
        int size=0;
        switch (level){
            case 2:
                //System.out.println(type);
                switch (type){
                    case "int":
                    case "bool":
                    case "array":
                    case "string":
                    case "real":
                        size=4;
                        break;
                    default:
                        if(!type.equals(CG.lxcal.symTab.get(CG.crntClsIndx).value)){
                            //System.out.println("no");
                            for(Symbol smb:CG.lxcal.symTab)
                                if(smb.value.equals(type))
                                    size=CG.lxcal.symTab.get(CG.lxcal.symTab.indexOf(smb)).size();
                        }
                        else{
                            this.classSymbol.containItself++;
                           // System.out.println(this.classSymbol.containItself);
                        }
                }
                break;
            case 1:
                switch (type){
                    case "int":
                    case "bool":
                    case "string":
                    case "array":
                    case "real":
                        size=4;
                        break;
                    case "void":
                        size=0;
                        break;
                    default:
                        if(!type.equals(CG.lxcal.symTab.get(CG.crntClsIndx).value)){
                           // System.out.println("no");
                            for(Symbol smb:CG.lxcal.symTab)
                                if(smb.value.equals(type))
                                    size=CG.lxcal.symTab.get(CG.lxcal.symTab.indexOf(smb)).size();
                        }
                        else{
                            this.classSymbol.containItself++;
                        }
                }
            case 0:
                for (Symbol symbl: subSymbols){
                    //System.out.println(symbl.value);
                    size+=symbl.size();
                }
                size+=containItself*size;
                containItself=0;
                break;
        }
        return size;
    }
    public int address(){
        int address=0;
        int i=0;
        boolean notFinded=true;
        if(level==1){
            for (i = 0 ; i < CG.lxcal.symTab.get(classSymbol.index).size() && !CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(i).equals(CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(index)) ; i++)
                address += CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(i).size();
            return address;
        }
        if(!isNotStatic){
            for (i = 0 ; i < CG.lxcal.symTab.get(classSymbol.index).size() && !CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(i).equals(CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(index)) ; i++)
                address += CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(i).size();
            return address;
        }
        if(inArgs){
            address=0;
            for (i = 0 ; i < CG.lxcal.symTab.get(classSymbol.index).size() && !CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(i).equals(CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(mthdIndx)) ; i++){
                //System.out.println(address);
                address += CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(i).size();
            }
            address += CG.typeSize.get(classSymbol.subSymbols.get(i).type);
            for (i = 0 ; i < CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(mthdIndx).size() && !CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(mthdIndx).subSymbols.get(i).equals(this) ; i++){
                address += CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(mthdIndx).subSymbols.get(i).size();
            }
           // System.out.println(address);
            return address;
        }
        for (i = 0 ; i < CG.lxcal.symTab.get(CG.crntClsIndx).size() && !CG.lxcal.symTab.get(CG.crntClsIndx).subSymbols.get(i).equals(CG.lxcal.symTab.get(CG.crntClsIndx).subSymbols.get(CG.crntMthdIndx)) ; i++)
            address += CG.lxcal.symTab.get(CG.crntClsIndx).subSymbols.get(i).size();
        //System.out.println(i);
        if(i!=CG.lxcal.symTab.get(CG.crntClsIndx).size() && CG.lxcal.symTab.get(CG.crntClsIndx).subSymbols.get(i).equals(this))
            notFinded=false;
        //System.out.println(value);
        if(notFinded) {
            address += CG.typeSize.get(CG.lxcal.symTab.get(CG.crntClsIndx).subSymbols.get(i).type);
            int tmp=address;
            for (i = 0; i < CG.lxcal.symTab.get(CG.crntClsIndx).subSymbols.get(CG.crntMthdIndx).subSymbols.size() && !CG.lxcal.symTab.get(CG.crntClsIndx).subSymbols.get(CG.crntMthdIndx).subSymbols.get(i).equals(this);){
                address += CG.lxcal.symTab.get(CG.crntClsIndx).subSymbols.get(CG.crntMthdIndx).subSymbols.get(i).size();
                i++;
            }
            if(i>0 && i==CG.lxcal.symTab.get(CG.crntClsIndx).subSymbols.get(CG.crntMthdIndx).subSymbols.size() && !CG.lxcal.symTab.get(CG.crntClsIndx).subSymbols.get(CG.crntMthdIndx).subSymbols.get(i-1).equals(this)){
                address=tmp;
                for (i = 0 ; i < CG.lxcal.symTab.get(classSymbol.index).size() && !CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(i).equals(this) ; i++)
                    address += CG.lxcal.symTab.get(classSymbol.index).subSymbols.get(i).size();
            }
        }
        return address;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return Objects.equals(tokenName, symbol.tokenName) && Objects.equals(value, symbol.value) && Objects.equals(level, symbol.level) && Objects.equals(classSymbol,symbol.classSymbol);
    }

    public int sizeOfArrayType() {
        int size=0;
        switch (level){
            case 2:
                switch (arrayType){
                    case "int":
                    case "bool":
                    case "array":
                    case "string":
                    case "real":
                        size=4;
                        break;
                    default:
                        if(!arrayType.equals(CG.lxcal.symTab.get(CG.crntClsIndx).value)){
                            //System.out.println("no");
                            for(Symbol smb:CG.lxcal.symTab)
                                if(smb.value.equals(arrayType))
                                    size=CG.lxcal.symTab.get(CG.lxcal.symTab.indexOf(smb)).size();
                        }
                        else{
                            this.classSymbol.containItself++;
                            // System.out.println(this.classSymbol.containItself);
                        }
                }
                break;
            case 1:
                switch (arrayType){
                    case "int":
                    case "bool":
                    case "string":
                    case "array":
                    case "real":
                        size=4;
                        break;
                    case "void":
                        size=0;
                        break;
                    default:
                        if(!arrayType.equals(CG.lxcal.symTab.get(CG.crntClsIndx).value)){
                            // System.out.println("no");
                            for(Symbol smb:CG.lxcal.symTab)
                                if(smb.value.equals(arrayType))
                                    size=CG.lxcal.symTab.get(CG.lxcal.symTab.indexOf(smb)).size();
                        }
                        else{
                            this.classSymbol.containItself++;
                        }
                }
            case 0:
                for (Symbol symbl: subSymbols){
                    //System.out.println(symbl.value);
                    size+=symbl.size();
                }
                size+=containItself*size;
                containItself=0;
                break;
        }
        return size;
    }
}
