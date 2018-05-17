package me.daylight.talk.utils;

import android.graphics.drawable.Drawable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.util.SparseIntArray;

import com.qmuiteam.qmui.qqface.IQMUIQQFaceManager;
import com.qmuiteam.qmui.qqface.QQFace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.daylight.talk.R;


public class QQFaceManager implements IQMUIQQFaceManager {
    private static final HashMap<String, Integer> sQQFaceMap = new HashMap<>();
    private static final List<QQFace> mQQFaceList = new ArrayList<>();
    private static final SparseIntArray sEmojisMap = new SparseIntArray(846);
    private static final SparseIntArray sSoftbanksMap = new SparseIntArray(471);
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") private static final ArrayMap<String, String> mQQFaceFileNameList = new ArrayMap<>();//存储QQ表情对应的文件名,方便混淆后可以获取到原文件名

    private static QQFaceManager sQQFaceManager = new QQFaceManager();

    static {
        long start = System.currentTimeMillis();

        mQQFaceList.add(new QQFace("[微笑]", R.drawable.smiley_0));
        mQQFaceList.add(new QQFace("[撇嘴]", R.drawable.smiley_1));
        mQQFaceList.add(new QQFace("[色]", R.drawable.smiley_2));
        mQQFaceList.add(new QQFace("[发呆]", R.drawable.smiley_3));
        mQQFaceList.add(new QQFace("[得意]", R.drawable.smiley_4));
        mQQFaceList.add(new QQFace("[流泪]", R.drawable.smiley_5));
        mQQFaceList.add(new QQFace("[害羞]", R.drawable.smiley_6));
        mQQFaceList.add(new QQFace("[闭嘴]", R.drawable.smiley_7));
        mQQFaceList.add(new QQFace("[睡]", R.drawable.smiley_8));
        mQQFaceList.add(new QQFace("[大哭]", R.drawable.smiley_9));
        mQQFaceList.add(new QQFace("[尴尬]", R.drawable.smiley_10));
        mQQFaceList.add(new QQFace("[发怒]", R.drawable.smiley_11));
        mQQFaceList.add(new QQFace("[调皮]", R.drawable.smiley_12));
        mQQFaceList.add(new QQFace("[呲牙]", R.drawable.smiley_13));
        mQQFaceList.add(new QQFace("[惊讶]", R.drawable.smiley_14));
        mQQFaceList.add(new QQFace("[难过]", R.drawable.smiley_15));
        mQQFaceList.add(new QQFace("[酷]", R.drawable.smiley_16));
        mQQFaceList.add(new QQFace("[冷汗]", R.drawable.smiley_17));
        mQQFaceList.add(new QQFace("[抓狂]", R.drawable.smiley_18));
        mQQFaceList.add(new QQFace("[吐]", R.drawable.smiley_19));
        mQQFaceList.add(new QQFace("[偷笑]", R.drawable.smiley_20));
        mQQFaceList.add(new QQFace("[可爱]", R.drawable.smiley_21));
        mQQFaceList.add(new QQFace("[白眼]", R.drawable.smiley_22));
        mQQFaceList.add(new QQFace("[傲慢]", R.drawable.smiley_23));
        mQQFaceList.add(new QQFace("[饥饿]", R.drawable.smiley_24));
        mQQFaceList.add(new QQFace("[困]", R.drawable.smiley_25));
        mQQFaceList.add(new QQFace("[惊恐]", R.drawable.smiley_26));
        mQQFaceList.add(new QQFace("[流汗]", R.drawable.smiley_27));
        mQQFaceList.add(new QQFace("[憨笑]", R.drawable.smiley_28));
        mQQFaceList.add(new QQFace("[大兵]", R.drawable.smiley_29));
        mQQFaceList.add(new QQFace("[奋斗]", R.drawable.smiley_30));
        mQQFaceList.add(new QQFace("[咒骂]", R.drawable.smiley_31));
        mQQFaceList.add(new QQFace("[疑问]", R.drawable.smiley_32));
        mQQFaceList.add(new QQFace("[嘘]", R.drawable.smiley_33));
        mQQFaceList.add(new QQFace("[晕]", R.drawable.smiley_34));
        mQQFaceList.add(new QQFace("[折磨]", R.drawable.smiley_35));
        mQQFaceList.add(new QQFace("[衰]", R.drawable.smiley_36));
        mQQFaceList.add(new QQFace("[骷髅]", R.drawable.smiley_37));
        mQQFaceList.add(new QQFace("[敲打]", R.drawable.smiley_38));
        mQQFaceList.add(new QQFace("[再见]", R.drawable.smiley_39));
        mQQFaceList.add(new QQFace("[擦汗]", R.drawable.smiley_40));
        mQQFaceList.add(new QQFace("[抠鼻]", R.drawable.smiley_41));
        mQQFaceList.add(new QQFace("[鼓掌]", R.drawable.smiley_42));
        mQQFaceList.add(new QQFace("[糗大了]", R.drawable.smiley_43));
        mQQFaceList.add(new QQFace("[坏笑]", R.drawable.smiley_44));
        mQQFaceList.add(new QQFace("[左哼哼]", R.drawable.smiley_45));
        mQQFaceList.add(new QQFace("[右哼哼]", R.drawable.smiley_46));
        mQQFaceList.add(new QQFace("[哈欠]", R.drawable.smiley_47));
        mQQFaceList.add(new QQFace("[鄙视]", R.drawable.smiley_48));
        mQQFaceList.add(new QQFace("[委屈]", R.drawable.smiley_49));
        mQQFaceList.add(new QQFace("[快哭了]", R.drawable.smiley_50));
        mQQFaceList.add(new QQFace("[阴险]", R.drawable.smiley_51));
        mQQFaceList.add(new QQFace("[亲亲]", R.drawable.smiley_52));
        mQQFaceList.add(new QQFace("[吓]", R.drawable.smiley_53));
        mQQFaceList.add(new QQFace("[可怜]", R.drawable.smiley_54));
        mQQFaceList.add(new QQFace("[菜刀]", R.drawable.smiley_55));
        mQQFaceList.add(new QQFace("[西瓜]", R.drawable.smiley_56));
        mQQFaceList.add(new QQFace("[啤酒]", R.drawable.smiley_57));
        mQQFaceList.add(new QQFace("[篮球]", R.drawable.smiley_58));
        mQQFaceList.add(new QQFace("[乒乓]", R.drawable.smiley_59));
        mQQFaceList.add(new QQFace("[咖啡]", R.drawable.smiley_60));
        mQQFaceList.add(new QQFace("[饭]", R.drawable.smiley_61));
        mQQFaceList.add(new QQFace("[猪头]", R.drawable.smiley_62));
        mQQFaceList.add(new QQFace("[玫瑰]", R.drawable.smiley_63));
        mQQFaceList.add(new QQFace("[凋谢]", R.drawable.smiley_64));
        mQQFaceList.add(new QQFace("[示爱]", R.drawable.smiley_65));
        mQQFaceList.add(new QQFace("[爱心]", R.drawable.smiley_66));
        mQQFaceList.add(new QQFace("[心碎]", R.drawable.smiley_67));
        mQQFaceList.add(new QQFace("[蛋糕]", R.drawable.smiley_68));
        mQQFaceList.add(new QQFace("[闪电]", R.drawable.smiley_69));
        mQQFaceList.add(new QQFace("[炸弹]", R.drawable.smiley_70));
        mQQFaceList.add(new QQFace("[刀]", R.drawable.smiley_71));
        mQQFaceList.add(new QQFace("[足球]", R.drawable.smiley_72));
        mQQFaceList.add(new QQFace("[瓢虫]", R.drawable.smiley_73));
        mQQFaceList.add(new QQFace("[便便]", R.drawable.smiley_74));
        mQQFaceList.add(new QQFace("[月亮]", R.drawable.smiley_75));
        mQQFaceList.add(new QQFace("[太阳]", R.drawable.smiley_76));
        mQQFaceList.add(new QQFace("[礼物]", R.drawable.smiley_77));
        mQQFaceList.add(new QQFace("[拥抱]", R.drawable.smiley_78));
        mQQFaceList.add(new QQFace("[强]", R.drawable.smiley_79));
        mQQFaceList.add(new QQFace("[弱]", R.drawable.smiley_80));
        mQQFaceList.add(new QQFace("[握手]", R.drawable.smiley_81));
        mQQFaceList.add(new QQFace("[胜利]", R.drawable.smiley_82));
        mQQFaceList.add(new QQFace("[抱拳]", R.drawable.smiley_83));
        mQQFaceList.add(new QQFace("[勾引]", R.drawable.smiley_84));
        mQQFaceList.add(new QQFace("[拳头]", R.drawable.smiley_85));
        mQQFaceList.add(new QQFace("[差劲]", R.drawable.smiley_86));
        mQQFaceList.add(new QQFace("[爱你]", R.drawable.smiley_87));
        mQQFaceList.add(new QQFace("[NO]", R.drawable.smiley_88));
        mQQFaceList.add(new QQFace("[OK]", R.drawable.smiley_89));
        mQQFaceList.add(new QQFace("[爱情]", R.drawable.smiley_90));
        mQQFaceList.add(new QQFace("[飞吻]", R.drawable.smiley_91));
        mQQFaceList.add(new QQFace("[跳跳]", R.drawable.smiley_92));
        mQQFaceList.add(new QQFace("[发抖]", R.drawable.smiley_93));
        mQQFaceList.add(new QQFace("[怄火]", R.drawable.smiley_94));
        mQQFaceList.add(new QQFace("[转圈]", R.drawable.smiley_95));
        mQQFaceList.add(new QQFace("[磕头]", R.drawable.smiley_96));
        mQQFaceList.add(new QQFace("[回头]", R.drawable.smiley_97));
        mQQFaceList.add(new QQFace("[跳绳]", R.drawable.smiley_98));
        mQQFaceList.add(new QQFace("[挥手]", R.drawable.smiley_99));
        mQQFaceList.add(new QQFace("[激动]", R.drawable.smiley_100));
        mQQFaceList.add(new QQFace("[街舞]", R.drawable.smiley_101));
        mQQFaceList.add(new QQFace("[献吻]", R.drawable.smiley_102));
        mQQFaceList.add(new QQFace("[左太极]", R.drawable.smiley_103));
        mQQFaceList.add(new QQFace("[右太极]", R.drawable.smiley_104));

        for (QQFace face : mQQFaceList) {
            sQQFaceMap.put(face.getName(), face.getRes());
        }

        mQQFaceFileNameList.put("[微笑]", "smiley_0");
        mQQFaceFileNameList.put("[撇嘴]", "smiley_1");
        mQQFaceFileNameList.put("[色]", "smiley_2");
        mQQFaceFileNameList.put("[发呆]", "smiley_3");
        mQQFaceFileNameList.put("[得意]", "smiley_4");
        mQQFaceFileNameList.put("[流泪]", "smiley_5");
        mQQFaceFileNameList.put("[害羞]", "smiley_6");
        mQQFaceFileNameList.put("[闭嘴]", "smiley_7");
        mQQFaceFileNameList.put("[睡]", "smiley_8");
        mQQFaceFileNameList.put("[大哭]", "smiley_9");
        mQQFaceFileNameList.put("[尴尬]", "smiley_10");
        mQQFaceFileNameList.put("[发怒]", "smiley_11");
        mQQFaceFileNameList.put("[调皮]", "smiley_12");
        mQQFaceFileNameList.put("[呲牙]", "smiley_13");
        mQQFaceFileNameList.put("[惊讶]", "smiley_14");
        mQQFaceFileNameList.put("[难过]", "smiley_15");
        mQQFaceFileNameList.put("[酷]", "smiley_16");
        mQQFaceFileNameList.put("[冷汗]", "smiley_17");
        mQQFaceFileNameList.put("[抓狂]", "smiley_18");
        mQQFaceFileNameList.put("[吐]", "smiley_19");
        mQQFaceFileNameList.put("[偷笑]", "smiley_20");
        mQQFaceFileNameList.put("[可爱]", "smiley_21");
        mQQFaceFileNameList.put("[白眼]", "smiley_22");
        mQQFaceFileNameList.put("[傲慢]", "smiley_23");
        mQQFaceFileNameList.put("[饥饿]", "smiley_24");
        mQQFaceFileNameList.put("[困]", "smiley_25");
        mQQFaceFileNameList.put("[惊恐]", "smiley_26");
        mQQFaceFileNameList.put("[流汗]", "smiley_27");
        mQQFaceFileNameList.put("[憨笑]", "smiley_28");
        mQQFaceFileNameList.put("[大兵]", "smiley_29");
        mQQFaceFileNameList.put("[奋斗]", "smiley_30");
        mQQFaceFileNameList.put("[咒骂]", "smiley_31");
        mQQFaceFileNameList.put("[疑问]", "smiley_32");
        mQQFaceFileNameList.put("[嘘]", "smiley_33");
        mQQFaceFileNameList.put("[晕]", "smiley_34");
        mQQFaceFileNameList.put("[折磨]", "smiley_35");
        mQQFaceFileNameList.put("[衰]", "smiley_36");
        mQQFaceFileNameList.put("[骷髅]", "smiley_37");
        mQQFaceFileNameList.put("[敲打]", "smiley_38");
        mQQFaceFileNameList.put("[再见]", "smiley_39");
        mQQFaceFileNameList.put("[擦汗]", "smiley_40");
        mQQFaceFileNameList.put("[抠鼻]", "smiley_41");
        mQQFaceFileNameList.put("[鼓掌]", "smiley_42");
        mQQFaceFileNameList.put("[糗大了]", "smiley_43");
        mQQFaceFileNameList.put("[坏笑]", "smiley_44");
        mQQFaceFileNameList.put("[左哼哼]", "smiley_45");
        mQQFaceFileNameList.put("[右哼哼]", "smiley_46");
        mQQFaceFileNameList.put("[哈欠]", "smiley_47");
        mQQFaceFileNameList.put("[鄙视]", "smiley_48");
        mQQFaceFileNameList.put("[委屈]", "smiley_49");
        mQQFaceFileNameList.put("[快哭了]", "smiley_50");
        mQQFaceFileNameList.put("[阴险]", "smiley_51");
        mQQFaceFileNameList.put("[亲亲]", "smiley_52");
        mQQFaceFileNameList.put("[吓]", "smiley_53");
        mQQFaceFileNameList.put("[可怜]", "smiley_54");
        mQQFaceFileNameList.put("[菜刀]", "smiley_55");
        mQQFaceFileNameList.put("[西瓜]", "smiley_56");
        mQQFaceFileNameList.put("[啤酒]", "smiley_57");
        mQQFaceFileNameList.put("[篮球]", "smiley_58");
        mQQFaceFileNameList.put("[乒乓]", "smiley_59");
        mQQFaceFileNameList.put("[咖啡]", "smiley_60");
        mQQFaceFileNameList.put("[饭]", "smiley_61");
        mQQFaceFileNameList.put("[猪头]", "smiley_62");
        mQQFaceFileNameList.put("[玫瑰]", "smiley_63");
        mQQFaceFileNameList.put("[凋谢]", "smiley_64");
        mQQFaceFileNameList.put("[示爱]", "smiley_65");
        mQQFaceFileNameList.put("[爱心]", "smiley_66");
        mQQFaceFileNameList.put("[心碎]", "smiley_67");
        mQQFaceFileNameList.put("[蛋糕]", "smiley_68");
        mQQFaceFileNameList.put("[闪电]", "smiley_69");
        mQQFaceFileNameList.put("[炸弹]", "smiley_70");
        mQQFaceFileNameList.put("[刀]", "smiley_71");
        mQQFaceFileNameList.put("[足球]", "smiley_72");
        mQQFaceFileNameList.put("[瓢虫]", "smiley_73");
        mQQFaceFileNameList.put("[便便]", "smiley_74");
        mQQFaceFileNameList.put("[月亮]", "smiley_75");
        mQQFaceFileNameList.put("[太阳]", "smiley_76");
        mQQFaceFileNameList.put("[礼物]", "smiley_77");
        mQQFaceFileNameList.put("[拥抱]", "smiley_78");
        mQQFaceFileNameList.put("[强]", "smiley_79");
        mQQFaceFileNameList.put("[弱]", "smiley_80");
        mQQFaceFileNameList.put("[握手]", "smiley_81");
        mQQFaceFileNameList.put("[胜利]", "smiley_82");
        mQQFaceFileNameList.put("[抱拳]", "smiley_83");
        mQQFaceFileNameList.put("[勾引]", "smiley_84");
        mQQFaceFileNameList.put("[拳头]", "smiley_85");
        mQQFaceFileNameList.put("[差劲]", "smiley_86");
        mQQFaceFileNameList.put("[爱你]", "smiley_87");
        mQQFaceFileNameList.put("[NO]", "smiley_88");
        mQQFaceFileNameList.put("[OK]", "smiley_89");
        mQQFaceFileNameList.put("[爱情]", "smiley_90");
        mQQFaceFileNameList.put("[飞吻]", "smiley_91");
        mQQFaceFileNameList.put("[跳跳]", "smiley_92");
        mQQFaceFileNameList.put("[发抖]", "smiley_93");
        mQQFaceFileNameList.put("[怄火]", "smiley_94");
        mQQFaceFileNameList.put("[转圈]", "smiley_95");
        mQQFaceFileNameList.put("[磕头]", "smiley_96");
        mQQFaceFileNameList.put("[回头]", "smiley_97");
        mQQFaceFileNameList.put("[跳绳]", "smiley_98");
        mQQFaceFileNameList.put("[挥手]", "smiley_99");
        mQQFaceFileNameList.put("[激动]", "smiley_100");
        mQQFaceFileNameList.put("[街舞]", "smiley_101");
        mQQFaceFileNameList.put("[献吻]", "smiley_102");
        mQQFaceFileNameList.put("[左太极]", "smiley_103");
        mQQFaceFileNameList.put("[右太极]", "smiley_104");

        Log.d("emoji", String.format("init emoji cost: %dms", (System.currentTimeMillis() - start)));
    }

    public static QQFaceManager getInstance() {
        return sQQFaceManager;
    }

    @Override
    public Drawable getSpecialBoundsDrawable(CharSequence text) {
        return null;
    }

    @Override
    public int getSpecialDrawableMaxHeight() {
        return 0;
    }

    @Override
    public boolean maybeSoftBankEmoji(char c) {
        return ((c >> 12) == 0xe);
    }

    @Override
    public boolean maybeEmoji(int codePoint) {
        return codePoint > 0xff;
    }

    @Override
    public int getEmojiResource(int codePoint) {
        return sEmojisMap.get(codePoint);
    }

    @Override
    public int getSoftbankEmojiResource(char c) {
        return sSoftbanksMap.get(c);
    }

    @Override
    public int getDoubleUnicodeEmoji(int currentCodePoint, int nextCodePoint) {
        return 0;
    }

    @Override
    public int getQQfaceResource(CharSequence text) {
        Integer integer = sQQFaceMap.get(text.toString());
        if (integer == null) {
            return 0;
        }
        return integer;
    }
}
