import codegenerator.CG;
import codegenerator.UnrchbleCodeDgnoser;
import parser.Parser;
import scanner.CmpScnr;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static String inputAddress="src/main/java/t1.cool" ;
    public static String outputAddress = "src/main/java/t1.s";
    public static void main(String[] args) throws FileNotFoundException {
        String tableAddress = "src/main/java/parser/table.npt";
        CmpScnr scanner = new CmpScnr(new FileReader(inputAddress));
        CG codeGenerator = new CG(scanner,outputAddress);
        Parser parser = new Parser(scanner, codeGenerator, tableAddress);
        // For debugging parser, use bellow
        // parser.Parser parser = new parser.Parser(scanner, codeGenerator, tableAddress, true);
        parser.parse();inputAddress.matches("// [^\r\n]* [\r|\n|\r\n]?");
        UnrchbleCodeDgnoser ucd= new UnrchbleCodeDgnoser(outputAddress);
        ucd.main();
    }
}
