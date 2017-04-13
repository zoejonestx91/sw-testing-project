package edu.utdallas.coveragetool;

import java.util.Stack;
import java.util.concurrent.*;

/**
 * Created by z on 3/22/17.
 */
public class Main {
    static Stack<String> classesToInstrument= new Stack<String>();

    public static void main(String args[]){
        //TODO get list of classes to instrument.
        initAndStartIntrumentors();
        //TODO start tests
    }

    private static void initAndStartIntrumentors(){

        int numProcessors = getNumProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(numProcessors);
        for(String className : classesToInstrument){
            pool.execute(new InstrumentRunner(className));
        }
        waitForPoolStop(pool);
    }

    private static void waitForPoolStop(ExecutorService pool){
        final int POLL_INTERVAL = 100;
        pool.shutdown();//shutdown actually tells the pool to finish executing tasks before termination.
        try {
            while(!pool.isShutdown()){
                Thread.sleep(POLL_INTERVAL);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static int getNumProcessors(){
        Runtime runtime = Runtime.getRuntime();
        return runtime.availableProcessors();
    }

    public static Stack<String> getClassesToInstrument() {
        return classesToInstrument;
    }
}
