package org.hashcode.solution;

import org.junit.Assert;
import org.junit.Test;

public class Solution {

    @Test
    public void testSolution(){
        Assert.assertEquals(1, new SolutionImpl().doIt());
    }

}

class SolutionImpl{

    public int doIt() {
        return 0;
    }
}
