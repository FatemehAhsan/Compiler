package scanner;
import java.util.ArrayList;
import java.io.IOException;
import parser.Lexical;
import static java.lang.System.exit;
import codegenerator.CG;
import java.io.FileWriter;
%%

%class CmpScnr
%public
%unicode
//%standalone
%line
%column
%function nxtSmbl
%type Symbol

%{
    public boolean inDcl=false;
    public ArrayList <Symbol> symTab= new ArrayList<>();
    public Symbol crntSymbl;
    public int lineNmbr=1;
    @Override
    public String nextToken() {
        try {
          crntSymbl = nxtSmbl();
          if(crntSymbl==null){
              boolean notFined=true;
              int index=0;
              for (Symbol symbol:symTab)
                if(symbol.value.equals("Main")){
                    notFined=false;
                    index=symTab.indexOf(symbol);
                }
                if(notFined) {
                    System.err.println("your program doesn't have class Main\nnot compiled");
                    exit(0);
                }
                else {
                    notFined=true;
                    for (Symbol symbol:symTab.get(index).subSymbols)
                        if(symbol.value.equals("main"))
                            notFined=false;
                }
                if (notFined){
                    System.err.println("your program doesn't have method main\nnot compiled");
                    exit(0);
                }
                CG.writer = new FileWriter(CG.outputAddress);
                String result=CG.data+CG.text;
                CG.writer.write(result);
                CG.writer.close();
              return "$";
          }
          if (crntSymbl.tokenName.equals("error")) {
              return "error";
          }
          if (crntSymbl.tokenName.equals("reserved")) {
            return crntSymbl.value;
          }
          if (crntSymbl.tokenName.equals("oprndpnctution")) {
              if(crntSymbl.value.equals(";"))
                  return "semiCln";
              if(crntSymbl.value.equals(","))
                  return "comma";
              return crntSymbl.value;
          }
          if (crntSymbl.tokenName.equals("id")) {
              return "id";
          }
          if (crntSymbl.tokenName.equals("mthdId")) {
               return "mthdId";
          }
          if (crntSymbl.tokenName.equals("complexId")) {
                return "complexId";
          }
          if (crntSymbl.tokenName.equals("complexMthdId")) {
                 return "complexMthdId";
          }
          return crntSymbl.tokenName;
        } catch (IOException e) {
          e.printStackTrace();
          return null;
        }
      }
%}

LineTrmntr = \r|\n|\r\n
WhiteSpace = [ \t\f]
Chrctr = \' ([^\'] | {SpcialChrctr}) \' | \'\'
Space = {LineTrmntr} | {WhiteSpace}

Int= [0-9]+
Sign=[+-]?
Hex= {Sign} "0" [Xx] [0-9a-fA-F]*
Real= (0|[1-9][0-9]*) "." [0-9]*
SntfcNtation={Real}[E]{Sign} {Int}
OprndPnctution= "+" | "-" | "*" | "/" | "+=" | "-=" | "*=" | "/=" | "++" | "--" | "<" | "<=" | ">" | ">=" | "!=" | "==" | "<-" | "%" | "&&" | "||" | "&" | "|" | "“" | "^" | "!" | "." | "," | ";" | "[" | "]" | "(" | ")" | "{" | "}" | ":"

Rsrvd = "void" | "int" | "real" | "bool" | "string" | "static" | "class" | "for" | "while" | "if" | "else" | "return" | "break" | "rof" | "let" | "fi" | "Array" | "void" | "in_string" | "out_string" | "new" | "break" | "continue" | "loop" | "pool" | "in_int" | "out_int" | "then" | "len"
Id = [a-zA-Z][A-Za-z0-9_]{0,30}
ComplexId = {Id} {Space}* "." {Space}* {Id}
MthdId = {Id} {Space}* "("
ComplexMthdId = {Id} {Space}* "." {Space}* {Id} "("
String = \" ( [^\"] | {SpcialChrctr} )~ \" | \"\"
SpcialChrctr= \\t | \\n | \\f | \\r | \\b | \\\ | \\\' | \\\"

LineCmnt= "//" [^\r\n]* {LineTrmntr}?
TrndtnlCmnt= "/*" [^*]~ "*/" | "/*" "*"+ "/"
Cmnt={LineCmnt} | {TrndtnlCmnt}



%%
<YYINITIAL> {
    {Int} {
        return new Symbol("constant", yytext(), "int");
    }
    {Hex} {
        return new Symbol("constant", yytext(), "int");
    }
    {Real} {
         return new Symbol("constant", yytext(), "real");
    }
    {SntfcNtation} {
        return new Symbol("constant", yytext(), "real");
    }
    {Chrctr} {
        return new Symbol("constant", yytext(), "char");
    }
    {Rsrvd} {
        switch (yytext()) {
            case "void":
                return new Symbol("id", yytext(), "void");
            case "int":
                return new Symbol("int", yytext(), "int");
            case "in_int":
            case "out_int":
                return new Symbol("reserved", yytext(), "int");
            case "real":
                return new Symbol("real", yytext(), "real");
            case "bool":
                return new Symbol("bool", yytext(), "bool");
            case "string":
                return new Symbol("id", yytext(), "string");
            case "in_string":
            case "out_string":
                return new Symbol("reserved", yytext(), "string");
        }
        return new Symbol("reserved", yytext());
    }
    {ComplexId} {
             return new Symbol("complexId", yytext());
    }
    {MthdId} {
         return new Symbol("mthdId", yytext());
    }
    {ComplexMthdId} {
         return new Symbol("complexMthdId", yytext());
    }
    {Id} {
        switch (yytext()) {
            case "true":
            case "false":
            return new Symbol("constant", yytext(),"bool");
        }
        return new Symbol("id", yytext());
    }
    {LineCmnt} {
        lineNmbr++;
        yybegin(YYINITIAL);
    }
    {TrndtnlCmnt} {
        lineNmbr+=yytext().chars().filter(ch -> ch == '\n').count();
        yybegin(YYINITIAL);
    }
    {WhiteSpace} {
        yybegin(YYINITIAL);
    }
    {LineTrmntr} {
        lineNmbr++;
        yybegin(YYINITIAL);
    }
    {OprndPnctution} {
        return new Symbol("oprndpnctution", yytext());
    }
    {String} {
        return new Symbol("constant", yytext(),"string");
    }
    [^] {
        System.err.println("token not acceptable");
    }
}