package deepshallow;

public class CopyObject implements Cloneable {

    private String name;
    private int age;

    private CopyObject() {
    }

    /* Cloneable 구현 복사 */
    @Override
    protected CopyObject clone() throws CloneNotSupportedException {
        return (CopyObject) super.clone();
    }

    /* 복사 생성자 */
    public CopyObject(CopyObject original) {
        this.name = original.name;
        this.age = original.age;
    }

    /* 복사 팩터리 */
    public static CopyObject copy(CopyObject original) {
        CopyObject copy = new CopyObject();
        copy.name = original.name;
        copy.age = original.age;
        return copy;
    }

    public CopyObject(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
