package SingletonPattern;

public class SingletonPatternDemo {

    public static void main(String[] args){
        //  不合法
        //  SingleObject object = new SingleObject();

        //  获取唯一可用的对象
        SingleObject object = SingleObject.getInstance();

        object.showMessage();
    }
}
