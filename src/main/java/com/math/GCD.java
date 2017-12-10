
package com.math;

public class GCD {
    /* Step 1 = Divide the larger number 24 by the smaller number 18. And this division will give remainder 6.
    Step 2 = Now, divide 18 (divisor of step 1) with 6 (remainder of step 1)
    Step 3 = Division in Step 2 give us remainder 0 (Zero). And The Last Divisor is the GCD of 24 & 18.
    Hence, GCD = 6
            18 | 24 | 1
                 18
                ______
                    6 | 18 | 3
                        18
                       ______

                        0 */

    int findGcd(int n1, int n2){
        int small = n1>n2 ? n2:n1;
        int large = n1<n2 ? n2:n1;

        int reminder = small;
        while(reminder!=0){
            reminder = large%small;
            large=small;
            small = reminder;
        }
        return large;
    }
}
