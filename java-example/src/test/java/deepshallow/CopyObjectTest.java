package deepshallow;

import org.junit.jupiter.api.Test;

class CopyObjectTest {

    @Test
    void shallowCopy() {
        CopyObject original = new CopyObject("JuHyun", 20);
        CopyObject copyConstructor = new CopyObject(original);
        CopyObject copyFactory = CopyObject.copy(original);

        copyConstructor.setName("JuBal");
        copyFactory.setName("BalJu");

        System.out.println(original);
        System.out.println(copyConstructor);
        System.out.println(copyFactory);
        System.out.println(original.getName());
        System.out.println(copyConstructor.getName());
        System.out.println(copyFactory.getName());
    }
}