package org.test4j.junit;

import java.util.Iterator;

import org.junit.Test;
import org.test4j.junit.annotations.DataFrom;
import org.test4j.junit.annotations.Group;
import org.test4j.module.ICore.DataIterator;

@SuppressWarnings("rawtypes")
@Group({ "davey.wu" })
public class Test4JTest_Parameter extends Test4J {
    /**
     * 验证jmockit带mock参数的方法可以正常运行
     * 
     * @param o
     */
    @Test
    public void testWithMockPara(MockDto o) {
        want.object(o).notNull();
    }

    /**
     * 验证junit普通的测试方法可以正常运行
     */
    @Test
    @Group({ "exclude.test" })
    public void testNoParameter() {

    }

    /**
     * 验证test4j数据驱动的方法可以正常运行<br>
     * 数据来源是测试类中的静态方法
     * 
     * @param name
     * @param index
     */
    @DataFrom("dataWithParameter")
    @Test
    public void testWithParameter(String name, Integer index) {
        // System.out.println("Name=" + name + ", Index=" + index);
        want.string(name).in("darui.wu", "jobs.he");
    }

    public static Iterator dataWithParameter() {
        return new DataIterator() {
            {
                data("darui.wu", 2);
                data("jobs.he", 1);
            }
        };
    }

    /**
     * 验证test4j数据驱动的方法可以正常运行<br>
     * 数据来源是另外一个类中的静态方法
     * 
     * @param name
     * @param index
     */
    @Test
    @DataFrom(value = "dataWithParameter", clazz = DataCase.class)
    public void testWithParameter_DataFromOtherClazz(String name) {
        // System.out.println("name=" + name);
    }

    static class MockDto {
        private final String name = "init";

        public void setName(String name) {

        }

        @Override
        public String toString() {
            return "MockDto [name=" + name + "]";
        }
    }
}
