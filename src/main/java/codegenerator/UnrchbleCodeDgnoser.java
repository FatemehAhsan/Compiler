package codegenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class UnrchbleCodeDgnoser {
    public static String readerFileName;
    public UnrchbleCodeDgnoser(String inputAddress) {
        readerFileName=inputAddress;
    }
    public static void main(){
        try {
            String [] lines = new String(Files.readAllBytes(Paths.get(readerFileName))).split("\n");
            HashMap<String,String> rgstrs=new HashMap<>();
            ArrayList<String> flags = new ArrayList<String>();
            int nmbr=0;
            int v0 = 0;
            Boolean flag=null;
            String label = null;
            boolean rtrn=false;
            boolean brnch= false;
            boolean unrchs= true;
            for (String line:lines){
                nmbr++;
                if(brnch && !line.startsWith(label)){
                    if(unrchs)
                        System.out.print("this lines are unreachable: ");
                    unrchs=false;
                    System.out.print(nmbr+" ");
                    if(line.startsWith("_")){
                        flags.add(line.replace(":",""));
                    }
                    continue;
                }
                else if(rtrn && !line.matches("[\\w]+_[\\w]+:")){
                    if(unrchs)
                        System.out.print("this lines are unreachable: ");
                    unrchs=false;
                    System.out.print(nmbr+" ");
                    if(line.startsWith("_")){
                        flags.add(line.replace(":",""));
                    }
                    continue;
                }
                else if(brnch){
                    brnch=false;
                    System.out.println();
                }
                else if(rtrn){
                    rtrn=false;
                    System.out.println();
                }
                String [] args=line.split(" |, |\n");
                if(line.startsWith("\t\tjr ")){
                    rtrn=true;
                }
                else if(line.startsWith("_")){
                    flags.add(line.replace(":",""));
                }
                else if(line.startsWith("\t\tli.s ")){
                    rgstrs.put(args[1],args[2]);
                }
                else if(line.startsWith("\t\tmov.s ")){
                    if(rgstrs.containsKey(args[2]))
                        rgstrs.put(args[1],rgstrs.get(args[2]));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tadd.s ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Float.valueOf(rgstrs.get(args[2]))+Float.valueOf(rgstrs.get(args[3]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tsub.s ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Float.valueOf(rgstrs.get(args[2]))-Float.valueOf(rgstrs.get(args[3]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tmul.s ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Float.valueOf(rgstrs.get(args[2]))*Float.valueOf(rgstrs.get(args[3]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tdiv.s ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Float.valueOf(rgstrs.get(args[2]))/Float.valueOf(rgstrs.get(args[3]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\ttrunc.w.s ")){
                    if(rgstrs.containsKey(args[2]))
                        rgstrs.put(args[1],rgstrs.get(args[2]));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tcvt.s.w ")){
                    if(rgstrs.containsKey(args[2]))
                        rgstrs.put(args[1],rgstrs.get(args[2]));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tcvt.w.s ")){
                    if(rgstrs.containsKey(args[2]))
                        rgstrs.put(args[1],rgstrs.get(args[2]));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tor.s ")){
                    if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                    /*if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3])) {
                       // rgstrs.put(args[1], String.valueOf(Float.valueOf(rgstrs.get(args[2])) | Float.valueOf(rgstrs.get(args[3]))));
                    }
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);*/
                }
                else if(line.startsWith("\t\tand.s ")){
                    if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                    /*if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3])) {
                        //rgstrs.put(args[1],String.valueOf(Float.valueOf(rgstrs.get(args[2]))&Float.valueOf(rgstrs.get(args[3]))));
                    }
                    else if(rgstrs.containsKey(args[1]) && ( !rgstrs.containsKey(args[2]) || !rgstrs.containsKey(args[3])))
                        rgstrs.remove(args[1]);*/
                }
                else if(line.startsWith("\t\txor.s ")){
                    if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                    /*if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3])) {
                        //rgstrs.put(args[1],String.valueOf(Float.valueOf(rgstrs.get(args[2]))^Float.valueOf(rgstrs.get(args[3]))));
                    }
                    else if(rgstrs.containsKey(args[1]) && ( !rgstrs.containsKey(args[2]) || !rgstrs.containsKey(args[3])))
                        rgstrs.remove(args[1]);*/
                }
                else if(line.startsWith("\t\tneg.s ")){
                    if(rgstrs.containsKey(args[2]))
                        rgstrs.put(args[1],String.valueOf(-Float.valueOf(rgstrs.get(args[2]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tli ") || line.startsWith("\t\tla ")){
                    if(args[1].equals("$v0"))
                        v0=Integer.valueOf(args[2]);
                    rgstrs.put(args[1],args[2]);
                }
                else if(line.startsWith("\t\tsw ") || line.startsWith("\t\tsb ") || line.startsWith("\t\ts.s ")){
                    String tmp=args[2].replaceAll("[\\d]+[(]","");
                    tmp=tmp.replaceAll("[)]","");
                    if(rgstrs.containsKey(tmp))
                        rgstrs.remove(tmp);
                }
                else if(line.startsWith("\t\tlw ") || line.startsWith("\t\tlb ") || line.startsWith("\t\tlwc1 ") || line.startsWith("\t\tl.s ") ){
                    if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tmove ") || line.startsWith("\t\tmtc1 ") || line.startsWith("\t\tmfc1 ")){
                    if(rgstrs.containsKey(args[2]))
                        rgstrs.put(args[1],rgstrs.get(args[2]));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tadd ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Integer.valueOf(rgstrs.get(args[2]))+Integer.valueOf(rgstrs.get(args[3]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\taddi ")){
                    try {
                        Integer.parseInt(args[2]);
                        if(rgstrs.containsKey(args[2]))
                            rgstrs.put(args[1],String.valueOf(Integer.valueOf(rgstrs.get(args[2]))+Integer.valueOf(rgstrs.get(args[3]))));
                        else if(rgstrs.containsKey(args[1]))
                            rgstrs.remove(args[1]);
                    } catch (NumberFormatException nfe) {
                        rgstrs.remove(args[1]);
                    }
                }
                else if(line.startsWith("\t\tsub ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Integer.valueOf(rgstrs.get(args[2]))-Integer.valueOf(rgstrs.get(args[3]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tmul ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Integer.valueOf(rgstrs.get(args[2]))*Integer.valueOf(rgstrs.get(args[3]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tdiv ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Integer.valueOf(rgstrs.get(args[2]))/Integer.valueOf(rgstrs.get(args[3]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\trem ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Integer.valueOf(rgstrs.get(args[2]))%Integer.valueOf(rgstrs.get(args[3]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tor ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Integer.valueOf(rgstrs.get(args[2]))|Integer.valueOf(rgstrs.get(args[3]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tand ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Integer.valueOf(rgstrs.get(args[2]))&Integer.valueOf(rgstrs.get(args[3]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\txor ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Integer.valueOf(rgstrs.get(args[2]))^Integer.valueOf(rgstrs.get(args[3]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tneg ")){
                    if(rgstrs.containsKey(args[2]))
                        rgstrs.put(args[1],String.valueOf(-Integer.valueOf(rgstrs.get(args[2]))));
                    else if(rgstrs.containsKey(args[1]))
                        rgstrs.remove(args[1]);
                }
                else if(line.startsWith("\t\tslt ")){
                   if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Integer.valueOf(rgstrs.get(args[2]))<Integer.valueOf(rgstrs.get(args[3]))));
                }
                else if(line.startsWith("\t\tsgt ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(Integer.valueOf(rgstrs.get(args[2]))>Integer.valueOf(rgstrs.get(args[3]))));
                    }
                else if(line.startsWith("\t\tseq ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(rgstrs.get(args[2]).equals(rgstrs.get(args[3]))));
                    }
                else if(line.startsWith("\t\tsne ")){
                    if(rgstrs.containsKey(args[2]) && rgstrs.containsKey(args[3]))
                        rgstrs.put(args[1],String.valueOf(!rgstrs.get(args[2]).equals(rgstrs.get(args[3]))));
                }
                else if(line.startsWith("\t\tbeqz ")){
                    if(rgstrs.containsKey(args[1]))
                        if(rgstrs.get(args[1]).equals("false") && !flags.contains(args[2])){
                            brnch=true;
                            label=args[2];
                        }
                }
                else if(line.startsWith("\t\tbeq ")){
                    if(rgstrs.containsKey(args[1]) && rgstrs.containsKey(args[2]))
                        if(rgstrs.get(args[1]).equals(rgstrs.get(args[2])) && !flags.contains(args[3]) ){
                            brnch=true;
                            label=args[3];
                        }
                }
                else if(line.startsWith("\t\tbc1f ")){
                    if(flag!=null)
                        if(flag.equals("true") && !flags.contains(args[2])){
                            brnch=true;
                            label=args[2];
                        }
                }
                else if(line.startsWith("\t\tb ")){
                    if(!flags.contains(args[1])){
                        brnch=true;
                        label=args[1];
                    }
                }
                else if(line.startsWith("\t\tc.le.s ")){
                    if(rgstrs.containsKey(args[1]) && rgstrs.containsKey(args[2]))
                        flag = Float.valueOf(rgstrs.get(args[1]))<=Float.valueOf(rgstrs.get(args[2]));
                }
                else if(line.startsWith("\t\tc.lt.s ")){
                    if(rgstrs.containsKey(args[1]) && rgstrs.containsKey(args[2]))
                        flag = Float.valueOf(rgstrs.get(args[1]))<Float.valueOf(rgstrs.get(args[2]));
                }
                else if(line.startsWith("\t\tc.eq.s ")){
                    if(rgstrs.containsKey(args[1]) && rgstrs.containsKey(args[2]))
                        flag = rgstrs.get(args[1]).equals(rgstrs.get(args[2]));
                }
                else if(line.startsWith("\t\tsyscall ")){
                    if(v0==5 || v0==9)
                        rgstrs.remove("$v0");
                    else if(v0==8)
                        rgstrs.remove("$a0");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}