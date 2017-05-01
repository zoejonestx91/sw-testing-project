package edu.utdallas.metricstool.metrics;

import edu.utdallas.metricstool.MetricCollector;
import edu.utdallas.metricstool.utils.AccessBitflagConverter;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;

/**
 * This metric outputs a method's modifiers.
 */
public class ModifierMetric extends MetricCollector {
    int access;
    public ModifierMetric(MethodVisitor mv, String cName, int access, String mName, String desc, String signature, String[] exceptions) {
        super(mv, cName, access, mName, desc, signature, exceptions);
        this.access = access;
    }

    public void visitEnd(){
        ArrayList<String> modifiers = new ArrayList<>();
        AccessBitflagConverter bitflag = AccessBitflagConverter.convert(access);
        StringBuilder output = new StringBuilder();
        for(int flag: AccessBitflagConverter.FLAGS){
            if(bitflag.getFlagState(flag)){
                modifiers.add(bitflag.getFlagName(flag));
            }
        }

        for(int i = 0; i < modifiers.size(); i++){
            output.append(modifiers.get(i));
            if(i < modifiers.size() - 1){
                output.append(", ");
            }
        }
        System.out.println("Modifiers: " + output);
    }
}
