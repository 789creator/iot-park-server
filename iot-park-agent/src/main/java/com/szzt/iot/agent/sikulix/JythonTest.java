//package com.szzt.iot.agent.sikulix;
//
//import org.python.core.Py;
//import org.python.core.PySystemState;
//import org.python.util.PythonInterpreter;
//
//import java.util.Properties;
//
//public class JythonTest {
//    public static void main(String[] args) {
////        PythonInterpreter interpreter = new PythonInterpreter();
////        Properties props = new Properties();
////        props.put("python.home", "D:\\Program Files\\Python\\Python37");
////        props.put("python.console.encoding", "UTF-8");
////        props.put("python.security.respectJavaAccessibility", "false");
////        props.put("python.import.site", "false");
//
//
////        Properties preprops = System.getProperties();
////        PythonInterpreter.initialize(preprops, props, new String[0]);
//        PySystemState sys = Py.getSystemState();
//        PythonInterpreter interpreter = new PythonInterpreter();
//        sys.path.add("D:\\Program Files\\Python\\Python37\\Lib");
//        sys.path.add("D:\\Program Files\\Python\\Python37\\Lib\\site-packages");
//        sys.path.add("D:\\Program Files\\Python\\Python37\\Lib\\site-packages\\numpy");
//        interpreter.exec("print('hello')");
//        interpreter.execfile("E:\\test\\test.sikuli\\test.py");
//    }
//}
//
