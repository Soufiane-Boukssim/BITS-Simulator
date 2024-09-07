package com.simulator.externalTools;

import java.util.Random;

public class MiscTools  {
    private Random myRandom = new Random();
    char[] myChars = new char[]{ '0','1','2','3','4','5','6','7','8','9'
            ,'A','B','C','D','E','F','G','H','I','J'
            ,'K','L','M','N','O','P','Q','R','S','T'
            ,'U','V','W','X','Y','Z'};

    public MiscTools(){

    }

    public synchronized String genAuthNumber () throws InterruptedException {

        char[] vTmpCharArr=new char[6];

        for (int i=0;i<vTmpCharArr.length;++i)
            vTmpCharArr[i]=myChars[myRandom.nextInt(myChars.length)];

        String vAuthNum= new String(vTmpCharArr);

        return vAuthNum;
    }
}
