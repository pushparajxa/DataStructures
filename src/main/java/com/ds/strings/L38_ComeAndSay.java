/*
 ** COPYRIGHT **
 */
package com.ds.strings;


// Time complexity is O(4^(n/3))
// After three iterations length of the string becomes quadrapuled.
class L38_ComeAndSay {
    public String countAndSay(int n) {
        if(n==1)
            return "1";
        else {
            String temp = countAndSay(n-1);
            
            StringBuilder res = new StringBuilder();
            char c = temp.charAt(0);
            int cnt = 1;
            
            for(int i=1;i<temp.length(); i++){
                if(temp.charAt(i) == c){
                    cnt++;
                }
                else {
                    res.append(cnt).append(c);
                    c = temp.charAt(i);
                    cnt = 1;
                }
            }
            res.append(cnt).append(c);
            return res.toString();
        }
    }
}