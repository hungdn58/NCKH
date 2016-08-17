/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uet.spellingcheck.spellcheck;

import java.util.*;
import DiacriticsRestoration.*;

/**
 *
 * @author TUNG
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static void funcTest(StringBuilder input) {
//        input.replace(0,input.length(),"good");
        StringBuilder t = new StringBuilder("good");
        input = t;
    }

    static void listTest(List<String> list) {
        list.add("ha");
        list.add("haha");
        list.add("hahaha");
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Runtime runtime = Runtime.getRuntime();

        long startTime = System.currentTimeMillis();

//        List<String> test = new ArrayList<>();

////        StringBuilder t = new StringBuilder("$%^&*");
//        System.out.println(t.delete(0, 2));
//        StringBuilder tmp1 = new StringBuilder();
//        StringBuilder tmp2 = new StringBuilder();
//        String tes = "   ";
//        System.out.println((int)' ');
//        for(int i = 0;i < tes.length();i++)
//            System.out.println((int)tes.charAt(i));
//        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬĐÈẺẼÉẸÊỀỂỄẾỆÌỈĨÍỊÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢÙỦŨÚỤƯỪỬỮỨỰỲỶỸÝỴ";

//        sp.remove_symbol(t, tmp1, tmp2);
//        System.out.println(tmp1);
//        System.out.println(t);
//        System.out.println(tmp2);

        try {
//            SpellChecker sp = new SpellChecker();
//            String command = " \"E:/python/diacritics_restoration.py\" -i restoresign_test_1.txt -o resutl_test1.txt";
//        String command = "E:\\Entertainment\\Program\\Calibre\\calibre.exe";
//            Process p = Runtime.getRuntime().exec("cmd E:/python/py.cmd");
//            p.waitFor();
//            System.out.println(p.exitValue());
            SpellcheckUI ui = new SpellcheckUI();
            ui.setTitle("Vietnamese spell checking");
            ui.setVisible(true);
//            System.out.println(sp.processSentence("Ánh xáng nung ninh chên cao tại tỉnh Quảng Linh."));
//        sp.processFile_v2(args[0], args[1]);
//        sp.printCode();
//        sp.makeDict();
//        sp.processFile("E:/test/filetest.txt", "E:/test/filetestout.txt");
//        System.out.println(sp.processSentence("Ánh xáng nung ninh chên cao tại tỉnh Quảng Linh."));
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
//        System.out.println("memory usage: "+allocatedMemory/1024);
            //System.out.println(totalTime + " ms");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        //Restoration res = new Restoration();
        //ArrayList<String> candidate = res.getCandidate("bai");
        //for(int i=0; i<candidate.size(); i++){
        //    System.out.println(candidate.get(i));
        //}
        //System.out.println(res.transition("bài", "<s>", "<s>"));
        //System.out.println(candidate.size());
        //String str = res.restoration("toi ko giai được bài toán này");
        //System.out.println(str);
    }
}
