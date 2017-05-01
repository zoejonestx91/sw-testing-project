package edu.utdallas.metricstool.utils;

import org.objectweb.asm.Opcodes;

/**
 * This converts the ASM access bitflag into a more useful representation.
 */
public class AccessBitflagConverter  {

    public static final int[] FLAGS = {
            Opcodes.ACC_PUBLIC,//1
            Opcodes.ACC_PRIVATE,//2
            Opcodes.ACC_PROTECTED,//4
            Opcodes.ACC_STATIC,//8
            Opcodes.ACC_FINAL,//16
            Opcodes.ACC_SYNCHRONIZED,//32
            Opcodes.ACC_VOLATILE,//64
            Opcodes.ACC_TRANSIENT,//128
            Opcodes.ACC_NATIVE,//256
            Opcodes.ACC_INTERFACE,//512
            Opcodes.ACC_ABSTRACT,//1024
            Opcodes.ACC_STRICT,//2048
            Opcodes.ACC_SYNTHETIC,//4096
            Opcodes.ACC_ANNOTATION,//8192
            Opcodes.ACC_ENUM,//16384
            Opcodes.ACC_MANDATED,//32786
            65536,//Not defined by ASM.
            Opcodes.ACC_DEPRECATED
    };

    public static final String[] FLAG_NAMES = {"public", "private", "protected", "static", "final", "synchronized",
        "volatile", "transient", "native", "interface", "abstract", "strict", "synthetic", "annotation", "enum",
        "mandated", "invalid", "deprecated"};

    private boolean[] flagSetting = new boolean[FLAGS.length];

    public static AccessBitflagConverter convert(int bitfield){
        return new AccessBitflagConverter(bitfield);
    }

    private AccessBitflagConverter(int num){
        setFlagSetting(num);
    }

    private void setFlagSetting(int bitfield){
        setFlagSetting(bitfield, FLAGS.length-1);
    }

    private void setFlagSetting(int bitfield, int index){
        if(index < 0) {return;}
        if(bitfield == 0){return;}
        if(bitfield >= FLAGS[index]){
            bitfield -= FLAGS[index];
            flagSetting[index] = true;
        } else {
            flagSetting[index] = false;
        }
        setFlagSetting(bitfield, index-1);
    }

    public boolean getFlagState(int flag){
        if(flag < 1) {return false;}//invalid input guard
        if(flag > FLAGS[FLAGS.length-1]){return false;}//invalid input guard
        for(int i = 0; FLAGS[i] <= flag; i++){
            if(FLAGS[i] == flag){
                return flagSetting[i];
            }
        }
        return false;//invalid input guard
    }

    public String getFlagName(int flag){
        if(flag < 1) {return null;}//invalid input guard
        if(flag > FLAGS[FLAGS.length-1]){return null;}//invalid input guard
        for(int i = 0; FLAGS[i] <= flag; i++){
            if(FLAGS[i] == flag){
                return FLAG_NAMES[i];
            }
        }
        return null;//invalid input guard
    }
}
