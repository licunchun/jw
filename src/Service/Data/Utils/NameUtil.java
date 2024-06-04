package Service.Data.Utils;

import java.util.Random;

public class NameUtil {
    final public static int MAX_NAME_LENGTH = 10;
    public static boolean check(String name){
        if(name==null||name.isEmpty())
            return false;
        if(!checkLength(name))
            return false;
        return checkChar(name);
    }



    private static final String COMMON_CHINESE_CHARACTERS = "明华红丽娜刚军秀志辉宇飞雄晓瑞莉婷健涛静雷妮翔欣婉峰宁洋晨婕伟丹青燕建蕾宝霞龙欢春芳杰浩艳鸿婵慧海琳蕙欣良蕊立兰浪光莹敏俊艺芬艾瑶莎艾斌佳星惠佩子雯铭琦奕宏磊云家妤佳婷卓俊博梅韵家怡仁钰俐宜蔚涵靖菲婉";
    private static final String theBookOfFamilyNames = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时付皮卞齐康伍余元卜顾孟平黄和穆肖尹";
    public static String getRandomName() {
        Random random = new Random();
        StringBuilder name = new StringBuilder();
        int index = random.nextInt(COMMON_CHINESE_CHARACTERS.length());
        name.append(theBookOfFamilyNames.charAt(index));
        int length = random.nextInt(2);
        for (int i = 0; i < length+1; i++) {
            // 从常用汉字表中随机选择字符
            index = random.nextInt(COMMON_CHINESE_CHARACTERS.length());
            char randomChar = COMMON_CHINESE_CHARACTERS.charAt(index);
            name.append(randomChar);
        }
        return name.toString();
    }
public static boolean checkLength(String name) {
        return name.length() <= MAX_NAME_LENGTH;
    }
    public static boolean checkChar(String name) {
        return name.matches("^[一-龥·]+$");
    }
}
